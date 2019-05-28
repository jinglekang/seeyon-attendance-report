package pub.sdk.util;

import pub.sdk.model.ReportModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JING
 */
public class ReportUtils {

    /**
     * todo 方案待优化 List<ReportModel>对象转换成List<List<Object>>对象
     *
     * @param modelList 原始数据
     * @return easyExcel所需要的多重list对象
     */
    public static List<List<Object>> transform(List<ReportModel> modelList) {
        List<List<Object>> lists = new ArrayList<>();
        for (ReportModel model : modelList) {
            // 遍历每一个属性，加入到list中
            List<Object> list = new ArrayList<>();
            list.add(model.getName());
            list.add(model.getDepart());
            list.add(model.getPm1());
            list.add(model.getAm1());
            list.add(model.getPm2());
            list.add(model.getAm2());
            list.add(model.getPm3());
            list.add(model.getAm3());
            list.add(model.getPm4());
            list.add(model.getAm4());
            list.add(model.getPm5());
            list.add(model.getAm5());
            list.add(model.getPm6());
            list.add(model.getAm6());
            list.add(model.getPm7());
            list.add(model.getAm7());
            list.add(model.getPm8());
            list.add(model.getAm8());
            list.add(model.getPm9());
            list.add(model.getAm9());
            list.add(model.getPm10());
            list.add(model.getAm10());
            list.add(model.getPm11());
            list.add(model.getAm11());
            list.add(model.getPm12());
            list.add(model.getAm12());
            list.add(model.getPm13());
            list.add(model.getAm13());
            list.add(model.getPm14());
            list.add(model.getAm14());
            list.add(model.getPm15());
            list.add(model.getAm15());
            list.add(model.getPm16());
            list.add(model.getAm16());
            list.add(model.getPm17());
            list.add(model.getAm17());
            list.add(model.getPm18());
            list.add(model.getAm18());
            list.add(model.getPm19());
            list.add(model.getAm19());
            list.add(model.getPm20());
            list.add(model.getAm20());
            list.add(model.getPm21());
            list.add(model.getAm21());
            list.add(model.getPm22());
            list.add(model.getAm22());
            list.add(model.getPm23());
            list.add(model.getAm23());
            list.add(model.getPm24());
            list.add(model.getAm24());
            list.add(model.getPm25());
            list.add(model.getAm25());
            list.add(model.getPm26());
            list.add(model.getAm26());
            list.add(model.getPm27());
            list.add(model.getAm27());
            list.add(model.getPm28());
            list.add(model.getAm28());
            list.add(model.getPm29());
            list.add(model.getAm29());
            list.add(model.getPm30());
            list.add(model.getAm30());
            list.add(model.getPm31());
            list.add(model.getAm31());
            lists.add(list);
        }
        return lists;
    }
}
