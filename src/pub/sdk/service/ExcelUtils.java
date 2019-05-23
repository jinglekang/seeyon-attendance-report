package pub.sdk.service;

import pub.sdk.model.ReadModel;
import pub.sdk.model.ReportModel;

import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelUtils {

    public static List<ReportModel> analysis(List<Object> list) {
        // 三种日期格式化对象
        SimpleDateFormat day = new SimpleDateFormat("dd"); // 天
        SimpleDateFormat hour = new SimpleDateFormat("HH"); // 小时
        SimpleDateFormat minute = new SimpleDateFormat("mm"); // 分钟

        // 用于存放不重复的考勤报表对象，键是姓名
        Map<String, ReportModel> map = new HashMap<>();

        // 遍历读取的签到数据
        for (Object o : list) {
            ReadModel read = (ReadModel) o;
            // todo 每次遍历都生成一个对应的考勤报表对象，会生成很多垃圾对象，暂未找到替代方案
            ReportModel model = new ReportModel();
            // 把对象使用不覆盖的方法加入到map，如果已存在会返回值， 不存在会返回空
            ReportModel report = map.putIfAbsent(read.getName(), model);

            // 当返回的是空值时，就把新加入map的对象赋值给他
            if (report == null) report = model;

            // 固定字段赋值
            report.setName(read.getName());
            report.setDepart(read.getDepart());

            // 获得签到的日期信息
            String readDay = day.format(read.getTime());
            String readHour = hour.format(read.getTime());
            String readMinute = minute.format(read.getTime());

            // todo 判断备注是否外勤，暂未参与计算
            String readRemark = read.getRemark();
            if (readRemark == null) readRemark = "";
            boolean isOuter = false;
            if (!readRemark.contains("公司")) isOuter = true;

            // todo 根据签到的天自动调用不同的方法，实现对报表对象每一天的赋值，方案待优化，目前可用
            switch (readDay) {
                case "01":
                    if (Integer.parseInt(readHour) < 12) { // 判断是上午还是下午，m代表上午，a代表下午，数字代表几号
                        report.setM1(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA1(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
                case "02":
                    if (Integer.parseInt(readHour) < 12) {
                        report.setM2(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA2(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
                case "03":
                    if (Integer.parseInt(readHour) < 12) {
                        report.setM3(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA3(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
                case "04":
                    if (Integer.parseInt(readHour) < 12) {
                        report.setM4(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA4(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
                case "05":
                    if (Integer.parseInt(readHour) < 12) {
                        report.setM5(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA5(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
                case "06":
                    if (Integer.parseInt(readHour) < 12) {
                        report.setM6(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA6(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
                case "07":
                    if (Integer.parseInt(readHour) < 12) {
                        report.setM7(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA7(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
                case "08":
                    if (Integer.parseInt(readHour) < 12) {
                        report.setM8(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA8(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
                case "09":
                    if (Integer.parseInt(readHour) < 12) {
                        report.setM9(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA9(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
                case "10":
                    if (Integer.parseInt(readHour) < 12) {
                        report.setM10(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA10(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
                case "11":
                    if (Integer.parseInt(readHour) < 12) {
                        report.setM11(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA11(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
                case "12":
                    if (Integer.parseInt(readHour) < 12) {
                        report.setM12(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA12(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
                case "13":
                    if (Integer.parseInt(readHour) < 12) {
                        report.setM13(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA13(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
                case "14":
                    if (Integer.parseInt(readHour) < 12) {
                        report.setM14(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA14(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
                case "15":
                    if (Integer.parseInt(readHour) < 12) {
                        report.setM15(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA15(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
                case "16":
                    if (Integer.parseInt(readHour) < 12) {
                        report.setM16(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA16(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
                case "17":
                    if (Integer.parseInt(readHour) < 12) {
                        report.setM17(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA17(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
                case "18":
                    if (Integer.parseInt(readHour) < 12) {
                        report.setM18(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA18(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
                case "19":
                    if (Integer.parseInt(readHour) < 12) {
                        report.setM19(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA19(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
                case "20":
                    if (Integer.parseInt(readHour) < 12) {
                        report.setM20(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA20(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
                case "21":
                    if (Integer.parseInt(readHour) < 12) {
                        report.setM21(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA21(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
                case "22":
                    if (Integer.parseInt(readHour) < 12) {
                        report.setM22(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA22(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
                case "23":
                    if (Integer.parseInt(readHour) < 12) {
                        report.setM23(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA23(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
                case "24":
                    if (Integer.parseInt(readHour) < 12) {
                        report.setM24(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA24(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
                case "25":
                    if (Integer.parseInt(readHour) < 12) {
                        report.setM25(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA25(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
                case "26":
                    if (Integer.parseInt(readHour) < 12) {
                        report.setM26(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA26(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
                case "27":
                    if (Integer.parseInt(readHour) < 12) {
                        report.setM27(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA27(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
                case "28":
                    if (Integer.parseInt(readHour) < 12) {
                        report.setM28(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA28(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
                case "29":
                    if (Integer.parseInt(readHour) < 12) {
                        report.setM29(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA29(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
                case "30":
                    if (Integer.parseInt(readHour) < 12) {
                        report.setM30(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA30(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
                case "31":
                    if (Integer.parseInt(readHour) < 12) {
                        report.setM31(readHour + ":" + readMinute + " " + readRemark);
                    } else {
                        report.setA31(readHour + ":" + readMinute + "" + readRemark);
                    }
                    break;
            }
        }

        // 把map里面的对象放入list返回
        return new ArrayList<>(map.values());

    }

    /**
     * todo 方案待优化 List<ReportModel>对象转换成List<List<Object>>对象
     * @param modelList 原始数据
     * @return easyExcel所需要的多重list对象
     */
    static List<List<Object>> transform(List<ReportModel> modelList) {
        List<List<Object>> lists = new ArrayList<>();
        for (ReportModel model : modelList) {
            // 遍历每一个属性，加入到list中
            List<Object> list = new ArrayList<>();
            list.add(model.getName());
            list.add(model.getDepart());
            list.add(model.getM1());
            list.add(model.getA1());
            list.add(model.getM2());
            list.add(model.getA2());
            list.add(model.getM3());
            list.add(model.getA3());
            list.add(model.getM4());
            list.add(model.getA4());
            list.add(model.getM5());
            list.add(model.getA5());
            list.add(model.getM6());
            list.add(model.getA6());
            list.add(model.getM7());
            list.add(model.getA7());
            list.add(model.getM8());
            list.add(model.getA8());
            list.add(model.getM9());
            list.add(model.getA9());
            list.add(model.getM10());
            list.add(model.getA10());
            list.add(model.getM11());
            list.add(model.getA11());
            list.add(model.getM12());
            list.add(model.getA12());
            list.add(model.getM13());
            list.add(model.getA13());
            list.add(model.getM14());
            list.add(model.getA14());
            list.add(model.getM15());
            list.add(model.getA15());
            list.add(model.getM16());
            list.add(model.getA16());
            list.add(model.getM17());
            list.add(model.getA17());
            list.add(model.getM18());
            list.add(model.getA18());
            list.add(model.getM19());
            list.add(model.getA19());
            list.add(model.getM20());
            list.add(model.getA20());
            list.add(model.getM21());
            list.add(model.getA21());
            list.add(model.getM22());
            list.add(model.getA22());
            list.add(model.getM23());
            list.add(model.getA23());
            list.add(model.getM24());
            list.add(model.getA24());
            list.add(model.getM25());
            list.add(model.getA25());
            list.add(model.getM26());
            list.add(model.getA26());
            list.add(model.getM27());
            list.add(model.getA27());
            list.add(model.getM28());
            list.add(model.getA28());
            list.add(model.getM29());
            list.add(model.getA29());
            list.add(model.getM30());
            list.add(model.getA30());
            list.add(model.getM31());
            list.add(model.getA31());
            lists.add(list);
        }
        return lists;
    }
}
