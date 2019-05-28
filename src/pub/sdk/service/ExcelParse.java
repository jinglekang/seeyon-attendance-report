package pub.sdk.service;

import pub.sdk.model.ReadModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author JING
 */
public class ExcelParse {

    private final static String COMPANY = "公司";
    private final static int NOON = 12;
    private final static SimpleDateFormat SDF = new SimpleDateFormat("HH:mm");

    public static List<Map<String, Object>> parse(List<Object> list) {
        // 用于存放不重复的考勤报表对象，键是姓名
        Map<String, Map<String, Object>> map = new HashMap<>(16);
        // 遍历读取的签到数据
        for (Object o : list) {
            ReadModel read = (ReadModel) o;
            // todo 每次遍历都生成一个对应的考勤报表对象，会生成很多垃圾对象，暂未找到替代方案
            Map<String, Object> model = new HashMap<>(1024);
            // 把对象使用不覆盖的方法加入到map，如果已存在会返回值， 不存在会返回空
            Map<String, Object> report = map.putIfAbsent(read.getName(), model);
            // 当返回的是空值时，就把新加入map的对象赋值给他
            if (report == null) {
                report = model;
            }
            // 固定字段赋值
            report.put("name", read.getName());
            report.put("depart", read.getDepart());
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
            String readRemark = read.getRemark();
            if (Integer.parseInt(readHour) < NOON) {
                String remark = initAmRemark(readHour, readMinute, readRemark);
                report.put("am" + readDay, readHour + ":" + readMinute + " " + remark);
            } else {
                String remark = initPmRemark(readHour, readMinute, readRemark);
                if (repeat(report.get("pm" + readDay), readHour, readMinute)) {
                    report.put("pm" + readDay, readHour + ":" + readMinute + " " + remark);
                }
            }
        }
        // 把map里面的对象放入list返回
        return new ArrayList<>(map.values());
    }

    /**
     * 计算打卡时间与要求打卡时间的差值
     *
     * @param temp   要求打卡时间
     * @param hour   实际打卡几点
     * @param minute 实际打卡几分
     * @return long差
     */
    private static long differ(String temp, String hour, String minute) {
        long differ = 0;
        try {
            Date date = SDF.parse(hour + ":" + minute);
            Date refer = SDF.parse(temp);
            differ = refer.getTime() - date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return differ;
    }

    /**
     * 用于初始化上午的备注
     *
     * @param hour   几点
     * @param minute 几分
     * @param remark 备注
     * @return String
     */
    private static String initAmRemark(String hour, String minute, String remark) {
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
                    remark = "迟：" + Math.abs(differ / (1000 * 60)) + "分";
                }
            } else {
                long differ = differ("9:30", hour, minute);
                if (differ >= 0) {
                    // 9点30前上班
                    remark = "";
                } else {
                    // 其他时间上班
                    remark = "迟：" + Math.abs(differ / (1000 * 60)) + "分";
                }
            }
        }
        return remark;
    }

    /**
     * 用于初始化下午的备注
     *
     * @param hour   几点
     * @param minute 几分
     * @param remark 备注
     * @return String
     */
    private static String initPmRemark(String hour, String minute, String remark) {
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
                    remark = " 早:" + Math.abs(differ / (1000 * 60)) + "分";
                }
            } else {
                long differ = differ("16:30", hour, minute);
                if (differ <= 0) {
                    // 9点30前上班
                    remark = "";
                } else {
                    // 其他时间上班
                    remark = " 早:" + Math.abs(differ / (1000 * 60)) + "分";
                }
            }
        }
        return remark;
    }

    /**
     * todo 待处理
     *
     * @param time   可能已添加的时间
     * @param hour   打卡小时
     * @param minute 打卡分钟
     * @return boolean
     */
    private static boolean repeat(Object time, String hour, String minute) {
        if (time != null) {
            try {
                Date temp = SDF.parse(time.toString().substring(0, 5));
                Date onTime = SDF.parse(hour + ":" + minute);
                return temp.getTime() < onTime.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
