package pub.sdk.service;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 读取超过1000行数据时需要的类，easyExcel提供的
 *
 * @author JING
 */
public class ExcelListener extends AnalysisEventListener {


    private List<Object> data = new ArrayList<>();

    @Override
    public void invoke(Object object, AnalysisContext context) {
        int num = 100;
        System.out.println(context.getCurrentSheet());
        data.add(object);
        if (data.size() >= num) {
            doSomething();
            data = new ArrayList<>();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        doSomething();
    }

    private void doSomething() {
        for (Object o : data) {
            System.out.println(o);
        }
    }
}
