package pub.sdk.service;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import pub.sdk.model.ReadModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author JING
 */
public class ReadExcel {

    /**
     * 07版本excel读数据量少于1千行数据，内部采用回调方法
     *
     * @param is InputStream
     * @return List<Object>
     * @throws IOException IOException
     */
    List<Object> read2007Excel(InputStream is) throws IOException {
        List<Object> data = EasyExcelFactory.read(is, new Sheet(1, 0));
        is.close();
        return data;
    }

    /**
     * 07版本excel读数据量少于1千行数据自动转成javaModel，内部采用回调方法
     *
     * @param is InputStream
     * @return List<Object>
     * @throws IOException IOException
     */
    List<Object> read2007ExcelWithModel(InputStream is) throws IOException {
        List<Object> list = EasyExcelFactory.read(is, new Sheet(1, 2, ReadModel.class));
        is.close();
        return list;
    }

    /**
     * 07版本excel读数据量大于1千行，内部采用回调方法.
     *
     * @param is InputStream
     * @throws IOException IOException
     */
    void read2007ExcelGiant(InputStream is) throws IOException {
        ExcelListener excelListener = new ExcelListener();
        EasyExcelFactory.readBySax(is, new Sheet(1, 1), excelListener);
        is.close();
    }

    /**
     * 07版本excel读数据量大于1千行，内部采用回调方法.
     *
     * @param is InputStream
     * @throws IOException IOException
     */
    void read2007ExcelGiantWithModel(InputStream is) throws IOException {
        ExcelListener excelListener = new ExcelListener();
        EasyExcelFactory.readBySax(is, new Sheet(1, 1, ReadModel.class), excelListener);
        is.close();
    }

    /**
     * 03版本excel读数据量少于1千行数据，内部采用回调方法.
     *
     * @param is InputStream
     * @return List<Object>
     * @throws IOException IOException
     */
    List<Object> read2003Excel(InputStream is) throws IOException {
        List<Object> list = EasyExcelFactory.read(is, new Sheet(1, 0));
        is.close();
        return list;
    }

    /**
     * 03版本excel读数据量少于1千行数据转成javaModel，内部采用回调方法.
     *
     * @param is InputStream
     * @return List<Object>
     * @throws IOException IOException
     */
    public List<Object> read2003ExcelWithModel(InputStream is) throws IOException {
        List<Object> list = EasyExcelFactory.read(is, new Sheet(1, 2, ReadModel.class));
        is.close();
        return list;
    }

    /**
     * 03版本excel读数据量大于1千行数据，内部采用回调方法.
     *
     * @param is InputStream
     * @throws IOException IOException
     */
    void read2003ExcelGiant(InputStream is) throws IOException {
        ExcelListener excelListener = new ExcelListener();
        EasyExcelFactory.readBySax(is, new Sheet(2, 1), excelListener);
        is.close();
    }

    /**
     * 03版本excel读数据量大于1千行数据转成javaModel，内部采用回调方法.
     *
     * @param is InputStream
     * @throws IOException IOException
     */
    void read2003ExcelGiantWithModel(InputStream is) throws IOException {
        ExcelListener excelListener = new ExcelListener();
        EasyExcelFactory.readBySax(is, new Sheet(2, 1, ReadModel.class), excelListener);
        is.close();
    }

    void print(List<Object> datas) {
        int i = 0;
        for (Object ob : datas) {
            System.out.println(i++);
            System.out.println(ob);
        }
    }

}
