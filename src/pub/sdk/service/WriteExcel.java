package pub.sdk.service;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import pub.sdk.model.ReportModel;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class WriteExcel {
    public void write2007Excel(List<ReportModel> modelList, OutputStream os) throws IOException {
        ExcelWriter writer = EasyExcelFactory.getWriter(os);
        //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
        Sheet sheet = new Sheet(1, 1, ReportModel.class);
        sheet.setSheetName("sheet");
        //or 设置自适应宽度
        sheet.setAutoWidth(Boolean.TRUE);
        writer.write1(ExcelUtils.transform(modelList), sheet);
        writer.finish();
        os.close();
    }

    public void write2003Excel(List<ReportModel> modelList, OutputStream os) throws IOException {
        ExcelWriter writer = EasyExcelFactory.getWriter(os, ExcelTypeEnum.XLS, true);
        //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
        Sheet sheet = new Sheet(1, 1, ReportModel.class);
        sheet.setSheetName("sheet");
        //or 设置自适应宽度
        sheet.setAutoWidth(Boolean.TRUE);
        writer.write1(ExcelUtils.transform(modelList), sheet);
        writer.finish();
        os.close();
    }
}
