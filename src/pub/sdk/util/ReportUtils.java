package pub.sdk.util;

import net.sf.cglib.beans.BeanMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author JING
 */
@SuppressWarnings("unused")
public class ReportUtils {


    public static <T> T mapToBean(Map<String, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    /**
     * todo 方案待优化 List<WriteModel>对象转换成List<List<Object>>对象
     *
     * @param modelList 原始数据
     * @return easyExcel所需要的多重list对象
     */
    public static List<List<Object>> transform(List<Map<String, Object>> modelList) {
        List<List<Object>> lists = new ArrayList<>();
        for (Map<String, Object> model : modelList) {
            // 遍历每一个属性，加入到list中
            List<Object> list = new ArrayList<>();
            list.add(model.get("name"));
            list.add(model.get("depart"));
            list.add(model.get("pm01"));
            list.add(model.get("am01"));
            list.add(model.get("pm02"));
            list.add(model.get("am02"));
            list.add(model.get("pm03"));
            list.add(model.get("am03"));
            list.add(model.get("pm04"));
            list.add(model.get("am04"));
            list.add(model.get("pm05"));
            list.add(model.get("am05"));
            list.add(model.get("pm06"));
            list.add(model.get("am06"));
            list.add(model.get("pm07"));
            list.add(model.get("am07"));
            list.add(model.get("pm08"));
            list.add(model.get("am08"));
            list.add(model.get("pm09"));
            list.add(model.get("am09"));
            list.add(model.get("pm10"));
            list.add(model.get("am10"));
            list.add(model.get("pm11"));
            list.add(model.get("am11"));
            list.add(model.get("pm12"));
            list.add(model.get("am12"));
            list.add(model.get("pm13"));
            list.add(model.get("am13"));
            list.add(model.get("pm14"));
            list.add(model.get("am14"));
            list.add(model.get("pm15"));
            list.add(model.get("am15"));
            list.add(model.get("pm16"));
            list.add(model.get("am16"));
            list.add(model.get("pm17"));
            list.add(model.get("am17"));
            list.add(model.get("pm18"));
            list.add(model.get("am18"));
            list.add(model.get("pm19"));
            list.add(model.get("am19"));
            list.add(model.get("pm20"));
            list.add(model.get("am20"));
            list.add(model.get("pm21"));
            list.add(model.get("am21"));
            list.add(model.get("pm22"));
            list.add(model.get("am22"));
            list.add(model.get("pm23"));
            list.add(model.get("am23"));
            list.add(model.get("pm24"));
            list.add(model.get("am24"));
            list.add(model.get("pm25"));
            list.add(model.get("am25"));
            list.add(model.get("pm26"));
            list.add(model.get("am26"));
            list.add(model.get("pm27"));
            list.add(model.get("am27"));
            list.add(model.get("pm28"));
            list.add(model.get("am28"));
            list.add(model.get("pm29"));
            list.add(model.get("am29"));
            list.add(model.get("pm30"));
            list.add(model.get("am30"));
            list.add(model.get("pm31"));
            list.add(model.get("am31"));
            lists.add(list);
        }
        return lists;
    }
}
