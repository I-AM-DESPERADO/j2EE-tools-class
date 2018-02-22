package com.trace.app.framework.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;

/**
 * Created by 成刚 on 2016/8/8.
 */
public class NTUtil {
    private static char[] array = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static String GenerateJiaoYan(String old) {
        char ch = '0';
        int sum = 0;
        if (old.length() != 24) {
            // MessageBox.Show("要转化的编码长度要为24位哦！");
            return old;
        } else {//校验码生成算法24位code求和，结果对36取模，取得数字，当做数组的下标
            for (int start = 0; start < old.length(); start++)
                sum = sum + Integer.parseInt(old.charAt(start) + "");
            int index = (sum % 36);

            ch = array[index];
            return old + ch;
        }
    }

    public static String GenerateJiaoYan2(String old) {
        char ch = '0';
        int sum = 0;
        int mul = 1;

        if (old.length() != 24) {
            // MessageBox.Show("要转化的编码长度要为24位哦！");
            return old;
        } else {//校验码生成算法24位code求和，结果对36取模，取得数字，当做数组的下标
            for (int start = 0; start < old.length(); start++)
                sum = sum + Integer.parseInt(old.charAt(start) + "");

            //从0开始计数，总串第6位，总串第12位，从0开始计数，公司编号第2位；商品编号第3位；
            mul = Integer.parseInt(old.charAt(6) + "") + Integer.parseInt(old.charAt(12) + "");

            sum = sum * mul;

            int index = (sum % 36);
            ch = array[index];
            return old + ch;
        }
    }

    public static String GenerateJiaoYan3(String old)
    {
        char ch = '0';
        int sum = 0;

        int startPosition = 0;
        int offPosition = 0;

        int mul1Position = 1;
        int mul2Position = 1;
        int mul = 1;

        if (old.length() != 24)
        {
            // MessageBox.Show("要转化的编码长度要为24位哦！");
            return old;
        }
        else
        {//校验码生成算法24位code求和，结果对36取模，取得数字，当做数组的下标
            for (int start = 0; start < old.length(); start++)
            //    sum += Convert.ToInt32(old[start]);
            sum = sum + Integer.parseInt(old.charAt(start) + "");

            //startPosition = Convert.ToInt32(old[12]);
            //offPosition = Convert.ToInt32(old[13]);
            startPosition = Integer.parseInt(old.charAt(12) + "");
            offPosition = Integer.parseInt((old.charAt(old.length()-1))+"");

            //从0开始计数，总串第6位，总串第12位，从0开始计数，公司编号第2位；商品编号第3位；

            mul1Position = ((startPosition + offPosition) % 24);
            mul2Position = ((startPosition + offPosition + offPosition) % 24);
            /*mul = Convert.ToInt32(old[mul1Position]) + Convert.ToInt32(old[mul2Position]);*/
            mul = Integer.parseInt(old.charAt(mul1Position)+"") + Integer.parseInt(old.charAt(mul2Position)+"");

            sum = sum * mul;

            int index = (sum % 36);
            ch = array[index];
            return old + ch;
        }
    }

    //校验检验
    public static boolean JiaoYanCheck(String s){
        String old = s.substring(0,24);
        old = GenerateJiaoYan3(old);
        if(s.equals(old)){
            return true;
        }
        return false;
    }

    public static void main(String[] args){
        String unif = "96927750768681";
        int start = 0;
        int end = 99;
        String traceNumber = "";
        String s = "";
        DecimalFormat decimalFormat = new DecimalFormat("0000000000");

        for(int i=start ; i<=end;i++){
            s = decimalFormat.format(i);
            traceNumber = unif+s;
            traceNumber = NTUtil.GenerateJiaoYan3(traceNumber);
            System.out.println(traceNumber);
        }
    }




}
