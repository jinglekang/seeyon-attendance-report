package pub.sdk;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import pub.sdk.model.ReportModel;
import pub.sdk.service.ExcelUtils;
import pub.sdk.service.ReadExcel;
import pub.sdk.service.WriteExcel;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

public class Report extends HttpServlet {

    private List<ReportModel> modelList;

    /**
     * 下载考勤报表的接口
     *
     * @param req  request
     * @param resp response
     * @throws IOException IO
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json; charset=utf-8");
        WriteExcel we = new WriteExcel();
        we.write2003Excel(modelList, resp.getOutputStream());
    }

    /**
     * 上传签到数据的接口
     *
     * @param req  request
     * @param resp response
     * @throws IOException IO
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json; charset=utf-8");
        // 判断是否是表单文件类
        if (ServletFileUpload.isMultipartContent(req)) {
            // 创建FileItem工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 核心操作类
            ServletFileUpload sfu = new ServletFileUpload(factory);
            try {
                // 解析出FileItem对象
                List<FileItem> items = sfu.parseRequest(req);
                for (FileItem item : items) { // 遍历
                    if (!item.isFormField()) { // 判读是file类型还是text类型
                        // 创建输入流，不过此流必须是BufferedInputStream
                        InputStream inputStream = new BufferedInputStream(item.getInputStream());
                        ReadExcel re = new ReadExcel(); // 实例化读取工具类

                        // 文件为2003版本，使用Model解析
                        List<Object> list = re.read2003ExcelWithModel(inputStream);

                        // 交给service层解析
                        this.modelList = ExcelUtils.analysis(list);

                        for (ReportModel reportModel : this.modelList) {
                            System.out.println(reportModel);
                        }

                        // 打印给前端页面
                        PrintWriter pw = resp.getWriter();

                        JSONObject json = new JSONObject();
                        json.put("code", "1");
                        json.put("msg", "OK");
                        json.put("data", this.modelList);
                        pw.write(json.toJSONString());
                        pw.close();
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("not 'multipart/form-data'");
        }

    }
}
