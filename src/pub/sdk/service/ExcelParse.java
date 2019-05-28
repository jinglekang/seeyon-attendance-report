package pub.sdk.service;

import pub.sdk.model.ReadModel;
import pub.sdk.model.ReportModel;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author JING
 */
public class ExcelParse {

    private final static String COMPANY = "公司";
    private final static int NOON = 12;


    public static List<ReportModel> parse(List<Object> list) {
        // 用于存放不重复的考勤报表对象，键是姓名
        Map<String, ReportModel> map = new HashMap<>(16);
        // 遍历读取的签到数据
        for (Object o : list) {
            ReadModel read = (ReadModel) o;
            // todo 每次遍历都生成一个对应的考勤报表对象，会生成很多垃圾对象，暂未找到替代方案
            ReportModel model = new ReportModel();
            // 把对象使用不覆盖的方法加入到map，如果已存在会返回值， 不存在会返回空
            ReportModel report = map.putIfAbsent(read.getName(), model);
            // 当返回的是空值时，就把新加入map的对象赋值给他
            if (report == null) {
                report = model;
            }
            // 固定字段赋值
            report.setName(read.getName());
            report.setDepart(read.getDepart());
            // 天
            SimpleDateFormat day = new SimpleDateFormat("dd");
            // 小时
            SimpleDateFormat hour = new SimpleDateFormat("HH");
            // 分钟
            SimpleDateFormat minute = new SimpleDateFormat("mm");
            // 获得签到的日期信息
            String readDay = day.format(read.getTime());
            String readHour = hour.format(read.getTime());
            String readMinute = minute.format(read.getTime());
            String remark = read.getRemark();
            if (Integer.parseInt(readHour) < NOON) {
                setAm(report, readDay, readHour, readMinute, remark);
            } else {
                setPm(report, readDay, readHour, readMinute, remark);
            }
        }
        // 把map里面的对象放入list返回
        return new ArrayList<>(map.values());
    }

    private static long differ(String temp, String hour, String minute) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        long differ = 0;
        try {
            Date date = sdf.parse(hour + ":" + minute);
            Date refer = sdf.parse(temp);
            differ = refer.getTime() - date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return differ;
    }

    private static void setAm(ReportModel report, String day, String hour, String minute, String remark) {
        if (remark == null) {
            remark = "无备注";
        } else {
            if (remark.contains(COMPANY)) {
                long differ = differ("08:30", hour, minute);
                if (differ >= 0) {
                    // 9点30前上班
                    remark = "";
                } else {
                    // 其他时间上班
                    remark = "\t迟到:" + Math.abs(differ / (1000 * 60)) + "分";
                }
            } else {
                long differ = differ("9:30", hour, minute);
                if (differ >= 0) {
                    // 9点30前上班
                    remark = "";
                } else {
                    // 其他时间上班
                    remark = "\t迟到:" + Math.abs(differ / (1000 * 60)) + "分";
                }
            }
        }
        switch (Integer.valueOf(day)) {
            case 1:
                report.setAm1(hour + ":" + minute + " " + remark);
                break;
            case 2:
                report.setAm2(hour + ":" + minute + "" + remark);
                break;
            case 3:
                report.setAm3(hour + ":" + minute + "" + remark);
                break;
            case 4:
                report.setAm4(hour + ":" + minute + "" + remark);
                break;
            case 5:
                report.setAm5(hour + ":" + minute + "" + remark);
                break;
            case 6:
                report.setAm6(hour + ":" + minute + "" + remark);
                break;
            case 7:
                report.setAm7(hour + ":" + minute + "" + remark);
                break;
            case 8:
                report.setAm8(hour + ":" + minute + "" + remark);
                break;
            case 9:
                report.setAm9(hour + ":" + minute + "" + remark);
                break;
            case 10:
                report.setAm10(hour + ":" + minute + "" + remark);
                break;
            case 11:
                report.setAm11(hour + ":" + minute + "" + remark);
                break;
            case 12:
                report.setAm12(hour + ":" + minute + "" + remark);
                break;
            case 13:
                report.setAm13(hour + ":" + minute + "" + remark);
                break;
            case 14:
                report.setAm14(hour + ":" + minute + "" + remark);
                break;
            case 15:
                report.setAm15(hour + ":" + minute + "" + remark);
                break;
            case 16:
                report.setAm16(hour + ":" + minute + "" + remark);
                break;
            case 17:
                report.setAm17(hour + ":" + minute + "" + remark);
                break;
            case 18:
                report.setAm18(hour + ":" + minute + "" + remark);
                break;
            case 19:
                report.setAm19(hour + ":" + minute + "" + remark);
                break;
            case 20:
                report.setAm20(hour + ":" + minute + "" + remark);
                break;
            case 21:
                report.setAm21(hour + ":" + minute + "" + remark);
                break;
            case 22:
                report.setAm22(hour + ":" + minute + "" + remark);
                break;
            case 23:
                report.setAm23(hour + ":" + minute + "" + remark);
                break;
            case 24:
                report.setAm24(hour + ":" + minute + "" + remark);
                break;
            case 25:
                report.setAm25(hour + ":" + minute + "" + remark);
                break;
            case 26:
                report.setAm26(hour + ":" + minute + "" + remark);
                break;
            case 27:
                report.setAm27(hour + ":" + minute + "" + remark);
                break;
            case 28:
                report.setAm28(hour + ":" + minute + "" + remark);
                break;
            case 29:
                report.setAm29(hour + ":" + minute + "" + remark);
                break;
            case 30:
                report.setAm30(hour + ":" + minute + "" + remark);
                break;
            case 31:
                report.setAm31(hour + ":" + minute + "" + remark);
                break;
            default:
                break;
        }
    }

    private static void setPm(ReportModel report, String day, String hour, String minute, String remark) {
        if (remark == null) {
            remark = "无备注";
        } else {
            if (remark.contains(COMPANY)) {
                long differ = differ("17:30", hour, minute);
                if (differ <= 0) {
                    // 9点30前上班
                    remark = "";
                } else {
                    // 其他时间上班
                    remark = "\t早退:" + Math.abs(differ / (1000 * 60)) + "分";
                }
            } else {
                long differ = differ("16:30", hour, minute);
                if (differ <= 0) {
                    // 9点30前上班
                    remark = "";
                } else {
                    // 其他时间上班
                    remark = "\t早退:" + Math.abs(differ / (1000 * 60)) + "分";
                }
            }
        }
        switch (day) {
            case "01":
                report.setPm1(hour + ":" + minute + " " + remark);
                break;
            case "02":
                report.setPm2(hour + ":" + minute + "" + remark);
                break;
            case "03":
                report.setPm3(hour + ":" + minute + "" + remark);
                break;
            case "04":
                report.setPm4(hour + ":" + minute + "" + remark);
                break;
            case "05":
                report.setPm5(hour + ":" + minute + "" + remark);
                break;
            case "06":
                report.setPm6(hour + ":" + minute + "" + remark);
                break;
            case "07":
                report.setPm7(hour + ":" + minute + "" + remark);
                break;
            case "08":
                report.setPm8(hour + ":" + minute + "" + remark);
                break;
            case "09":
                report.setPm9(hour + ":" + minute + "" + remark);
                break;
            case "10":
                report.setPm10(hour + ":" + minute + "" + remark);
                break;
            case "11":
                report.setPm11(hour + ":" + minute + "" + remark);
                break;
            case "12":
                report.setPm12(hour + ":" + minute + "" + remark);
                break;
            case "13":
                report.setPm13(hour + ":" + minute + "" + remark);
                break;
            case "14":
                report.setPm14(hour + ":" + minute + "" + remark);
                break;
            case "15":
                report.setPm15(hour + ":" + minute + "" + remark);
                break;
            case "16":
                report.setPm16(hour + ":" + minute + "" + remark);
                break;
            case "17":
                report.setPm17(hour + ":" + minute + "" + remark);
                break;
            case "18":
                report.setPm18(hour + ":" + minute + "" + remark);
                break;
            case "19":
                report.setPm19(hour + ":" + minute + "" + remark);
                break;
            case "20":
                report.setPm20(hour + ":" + minute + "" + remark);
                break;
            case "21":
                report.setPm21(hour + ":" + minute + "" + remark);
                break;
            case "22":
                report.setPm22(hour + ":" + minute + "" + remark);
                break;
            case "23":
                report.setPm23(hour + ":" + minute + "" + remark);
                break;
            case "24":
                report.setPm24(hour + ":" + minute + "" + remark);
                break;
            case "25":
                report.setPm25(hour + ":" + minute + "" + remark);
                break;
            case "26":
                report.setPm26(hour + ":" + minute + "" + remark);
                break;
            case "27":
                report.setPm27(hour + ":" + minute + "" + remark);
                break;
            case "28":
                report.setPm28(hour + ":" + minute + "" + remark);
                break;
            case "29":
                report.setPm29(hour + ":" + minute + "" + remark);
                break;
            case "30":
                report.setPm30(hour + ":" + minute + "" + remark);
                break;
            case "31":
                // if(repeat(report.getPm31(),)){
                //     break;
                // }
                report.setPm31(hour + ":" + minute + "" + remark);
                break;
            default:
                break;
        }
    }

    // todo 待处理
    // private static boolean repeat(String report,){
    //
    // }
}
