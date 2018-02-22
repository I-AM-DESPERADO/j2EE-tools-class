package com.trace.app.framework.utils;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by MEX on 2014/11/16.
 * Updated by Jin on 2016/07/28  v1
 * Updated by Jin on 2017/01/01  v2
 * Updated by wk on 2017/07/31
 */
public class TimeUtils {

    public static int getTime()
    {
        long nowTime = System.currentTimeMillis();
        int time = (int)(nowTime/1000);
        return  time;
    }

    public static int[][] getYearTimeArray(int time) throws ParseException {
        // 初始化二维数组
        int intArray[][] = new int[12][2];
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(time * 1000l));
        String year = date.substring(0, 4);
        System.out.println(date);
        for (int i=0; i<2; i++ )
        {
            for (int j=0; j<12; j++)
            {
                if (i==0)
                {
                    Date epochS = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("1" + "/" + Integer.toString(j+1) + "/" + year + " " + "00:00:00");
                    intArray[j][i] = (int) (epochS.getTime() / 1000);
                }
                else if (i==1)
                {
                    intArray[j][i] = getMonthlyEndTime(Integer.toString(j+1), year);
                }
            }
        }
        return intArray;
    }

    private static int getMonthlyEndTime(String month, String year) throws ParseException
    {
        int endTime = 0;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if (month.equals("2")) {
            int yearInt = Integer.parseInt(year);
            if (((yearInt % 4 == 0) && (yearInt % 100 != 0)) || yearInt % 400 == 0) {
                //闰年2月29天
                Date epochE = format.parse("29" + "/" + month + "/" + year + " " + "23:59:59");
                endTime = (int) (epochE.getTime() / 1000);
                System.out.println(year + " 是闰年");
            } else {
                //平年2月28天
                Date epochE = format.parse("28" + "/" + month + "/" + year + " " + "23:59:59");
                endTime = (int) (epochE.getTime() / 1000);
                System.out.println(year + " 不是闰年");
            }
        } else if (month.equals("1") || month.equals("3") || month.equals("5") || month.equals("7")
                || month.equals("8") || month.equals("10") || month.equals("12")) {
            //1,3,5,7,8,10,12月31天
            Date epochE = format.parse("31" + "/" + month + "/" + year + " " + "23:59:59");
            endTime = (int) (epochE.getTime() / 1000);

        } else {
            Date epochE = format.parse("30" + "/" + month + "/" + year + " " + "23:59:59");
            endTime = (int) (epochE.getTime() / 1000);
        }
        return endTime;
    }

    public static int getDailyStartTime(int checkOutTime) throws ParseException {
        int startTime = 0;
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(checkOutTime * 1000l));
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        String day = date.substring(8, 10);
        Date epochS = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(day + "/" + month + "/" + year + " " + "00:00:00");
        startTime = (int) (epochS.getTime() / 1000);
        return startTime;
    }

    //获取当日结束时间
    public static int getDailyEndTime(int checkOutTime) throws ParseException {
        int endTime = 0;
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(checkOutTime * 1000l));
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        String day = date.substring(8, 10);
        Date epochE = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(day + "/" + month + "/" + year + " " + "23:59:59");
        endTime = (int) (epochE.getTime() / 1000);
        return endTime;
    }

    public static int getMonthlyStartTime(int checkOutTime) throws ParseException {
        int startTime = 0;
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(checkOutTime * 1000l));
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        //String day = date.substring(8, 10);
        Date epochS = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01" + "/" + month + "/" + year + " " + "00:00:00");
        startTime = (int) (epochS.getTime() / 1000);
        return startTime;
    }

    //获取当月结束时间
    public static int getMonthlyEndTime(int checkOutTime) throws ParseException {
        int endTime = 0;
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(checkOutTime * 1000l));
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        //String day = date.substring(8, 10);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if (month.equals("02")) {
            int yearInt = Integer.parseInt(year);
            if (((yearInt % 4 == 0) && (yearInt % 100 != 0)) || yearInt % 400 == 0) {
                //闰年2月29天
                Date epochE = format.parse("29" + "/" + month + "/" + year + " " + "23:59:59");
                endTime = (int) (epochE.getTime() / 1000);
                System.out.println(year + " 是闰年");
            } else {
                //平年2月28天
                Date epochE = format.parse("28" + "/" + month + "/" + year + " " + "23:59:59");
                endTime = (int) (epochE.getTime() / 1000);
                System.out.println(year + " 不是闰年");
            }
        } else if (month.equals("01") || month.equals("03") || month.equals("05") || month.equals("07")
                || month.equals("08") || month.equals("10") || month.equals("12")) {
            //1,3,5,7,8,10,12月31天
            Date epochE = format.parse("31" + "/" + month + "/" + year + " " + "23:59:59");
            endTime = (int) (epochE.getTime() / 1000);

        } else {
            Date epochE = format.parse("30" + "/" + month + "/" + year + " " + "23:59:59");
            endTime = (int) (epochE.getTime() / 1000);
        }
        //endTime = (int) (epochE.getTime() / 1000);
        return endTime;
    }

    public static String TimeStamp2Date(String timestampString, String formats){
        Long timestamp = Long.parseLong(timestampString)*1000;
        String date = new SimpleDateFormat(formats).format(new Date(timestamp));
        return date;
    }
    /*-------------------------------------------修改分割线 By Jin-------------------------------------------
     *unixTime:定义为从格林威治时间1970年01月01日00时00分00秒起至现在的总秒数
     */
    public static int getLast24hTime(int unixTime){
        int lastTime = unixTime-24*3600;        //当前时间-24*3600-->24h之前的时间戳
        return lastTime;
    }

    public static int getAfter24hTime(int unixTime){
        int afterTime = unixTime+24*3600;        //当前时间-24*3600-->24h之前的时间戳
        return afterTime;
    }

    public static int getLastDayTime(int unixTime,int day){
        int lastDayTime = unixTime-(24*3600*day); //获取一周前的时间戳
        return lastDayTime;
    }



    public static void main(String[] args)throws Exception{
        /*String date = TimeUtils.TimeStamp2Date(1469537036+"", "yyyy/MM/dd HH:mm:ss");
        System.out.print(date);*/
        System.out.println(getDailyStartTime(1483281422));
        System.out.println("开始时间："+TimeUtils.getMonthlyStartTime(1469537036) +" 结束时间：" + TimeUtils.getMonthlyEndTime(1469537036));
    }

    /*-------------------------------------------修改分割线 By wk-------------------------------------------
     * checkYearType():判断一个时间戳该年的类型（闰年/平年）
     * getYearlyStartTime():给一个时间戳求该年的最开始的时间戳
     * getYearlyEndTime():给一个时间戳求该年的结束的时间戳
     * getCurrentQuarterStartTime(): 给一个时间戳求该年每个季度开始的时间戳
     * getCurrentQuarterEndTime(): 给一个时间戳求该年每个季度结束的时间戳
     * getBeforeQuarterStartTime():获取当前时间戳所在季度往前推4个季度的开始时间
     * getBeforeQuarterEndTime()：获取当前时间戳所在季度往前推一个季度的结束时间
     */
    public static boolean checkYearType(int checkOutTime) throws ParseException{
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(checkOutTime * 1000l));
        String year = date.substring(0,4);
        int yearInt = Integer.parseInt(year);
        if (((yearInt % 4 == 0) && (yearInt % 100 != 0)) || yearInt % 400 == 0){
            return true;
        }
        else
            return false;

    }
    public static int getYearlyStartTime(int checkOutTime) throws ParseException{
        int startTime = 0;
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(checkOutTime * 1000l));
        System.out.println("date:"+date);
        String year = date.substring(0,4);
        System.out.println("Year:"+year);
        Date epochS = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01" + "/" + "01" + "/" + year + " " + "00:00:00");
        startTime = (int) (epochS.getTime() / 1000);
        System.out.println("工具类中的时间是："+startTime);
        return startTime;
    }

    public static int getYearlyEndTime(int checkOutTime) throws ParseException{
        int endTime = 0;
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(checkOutTime * 1000l));
        System.out.println("date2"+date);
        String year = date.substring(0, 4);
        System.out.println("Year2:"+year);
        Date epochE = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("31" + "/" + "12"+ "/" + year + " " + "23:59:59");
        endTime = (int) (epochE.getTime() / 1000);
        return endTime;
    }

    public static int getCurrentQuarterStartTime(int checkOutTime , int quarter){
        int startTime = 0;
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(checkOutTime * 1000l));
        String year = date.substring(0, 4);
        Date epochE = null ;
        try{
            if( quarter == 1 ){
                epochE = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01" + "/" + "01"+ "/" + year + " " +  "00:00:00");
            }
            else if( quarter == 2){
                epochE = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01" + "/" + "04"+ "/" + year + " " +  "00:00:00");
            }
            else if( quarter == 3){
                epochE = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01" + "/" + "07"+ "/" + year + " " +  "00:00:00");
            }
            else if( quarter == 4){
                epochE = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01" + "/" + "10"+ "/" + year + " " +  "00:00:00");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        startTime = (int)(epochE.getTime()/1000);
        return startTime;
    }

    public static int getCurrentQuarterEndTime(int checkOutTime , int quarter){
        int endTime = 0;
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(checkOutTime * 1000l));
        String year = date.substring(0, 4);
        Date epochE = null ;
        try{
            if( quarter == 1 ){
                epochE = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("31" + "/" + "03"+ "/" + year + " " +  "23:59:59");
            }
            else if( quarter == 2){
                epochE = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("30" + "/" + "06"+ "/" + year + " " +  "23:59:59");
            }
            else if( quarter == 3){
                epochE = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("30" + "/" + "09"+ "/" + year + " " +  "23:59:59");
            }
            else if( quarter == 4){
                epochE = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("31" + "/" + "12"+ "/" + year + " " +  "23:59:59");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        endTime = (int)(epochE.getTime()/1000);
        return endTime;
    }

    public static int getBeforeQuarterStartTime() throws ParseException {
        int startTime = 0;
        Calendar c = Calendar.getInstance();

        int m = c.get(Calendar.MONTH) + 1;            //月份
        int s = (int)Math.ceil( (m - 1)  / 3) + 1;     //季度
        System.out.println(s);
        int em = (s - 1) * 3;                        //上一个季度最后一个月

        Calendar sc = Calendar.getInstance();
        sc.setTimeInMillis(c.getTimeInMillis());
        sc.set(Calendar.MONTH, em);
        sc.set(Calendar.DAY_OF_MONTH, 1);
        sc.set(Calendar.HOUR_OF_DAY, 0);
        sc.set(Calendar.MINUTE, 0);
        sc.set(Calendar.SECOND, 0);
        sc.set(Calendar.MILLISECOND, 0);
        sc.add(Calendar.MILLISECOND, -1);

        Calendar ec = Calendar.getInstance();
        ec.setTimeInMillis(c.getTimeInMillis());
        ec.set(Calendar.MONTH, em - 1);    //month 基于0,一月的值是0
        ec.set(Calendar.DAY_OF_MONTH, 1);
        ec.set(Calendar.HOUR_OF_DAY, 0);
        ec.set(Calendar.MINUTE, 0);
        ec.set(Calendar.SECOND, 0);
        ec.set(Calendar.MILLISECOND, 0);
        ec.add(Calendar.MONTH, -11);     //向前12个月，但是-11
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ec.getTime());
        String year = date.substring(0,4);
        String month = date.substring(5,7);
        String day = date.substring(8, 10);
        Date epochE = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(day + "/" + month+ "/" + year + " " +  "00:00:00");
        startTime = (int)(epochE.getTime()/1000);
        return startTime;
    }

    public static int getBeforeQuarterEndTime() throws ParseException{
        int endTime = 0;
        Calendar c = Calendar.getInstance();

        int m = c.get(Calendar.MONTH) + 1;            //月份
        int s = (int)Math.ceil( (m - 1)  / 3) + 1;     //季度
        System.out.println(s);
        int em = (s - 1) * 3;                        //上一个季度最后一个月

        Calendar sc = Calendar.getInstance();
        sc.setTimeInMillis(c.getTimeInMillis());
        sc.set(Calendar.MONTH, em);
        sc.set(Calendar.DAY_OF_MONTH, 1);
        sc.set(Calendar.HOUR_OF_DAY, 0);
        sc.set(Calendar.MINUTE, 0);
        sc.set(Calendar.SECOND, 0);
        sc.set(Calendar.MILLISECOND, 0);
        sc.add(Calendar.MILLISECOND, -1);
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sc.getTime());
        String year = date.substring(0,4);
        String month = date.substring(5,7);
        String day = date.substring(8, 10);
        Date epochE = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(day + "/" + month+ "/" + year + " " +  "23:59:59");
        endTime = (int)(epochE.getTime()/1000);
        return endTime;
    }

    public static int getBeforeQuarterStartMonth() throws ParseException {
        int startTime = getBeforeQuarterStartTime();
        int month = 0;
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(startTime * 1000l));
        month = Integer.parseInt(date.substring(5, 7));
        return month;
    }

    public static boolean checkBeforeQuarterStartTimeYearType() throws ParseException {
        int startTime = getBeforeQuarterStartTime();
        return checkYearType( startTime );
    }

    public static boolean checkBeforeQuarterEndTimeYearType() throws ParseException {
        int endTime = getBeforeQuarterEndTime();
        return checkYearType(endTime);

    }
    public static int getYear(int checkOutTime) throws ParseException{
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(checkOutTime * 1000l));
        int year =Integer.parseInt(date.substring(0,4));
        return year;
    }

    public static int getMonth(int checkOutTime) throws ParseException{
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(checkOutTime * 1000l));
        int month =Integer.parseInt(date.substring(5,7));
        return month;
    }

    public static int getDay(int checkOutTime) throws ParseException{
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(checkOutTime * 1000l));
        int day = Integer.parseInt(date.substring(8,10));
        return day;
    }

    public static int getDayTime(int checkOutTime) throws ParseException{
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(checkOutTime * 1000l));
        int time = Integer.parseInt(date.substring(11,13));
        return time;
    }
}
