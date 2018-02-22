package com.trace.app.framework.utils;

import java.text.DecimalFormat;

/**
 * Created by 魏伟 on 2016/3/30.
 * Update by mcg on 2016/8/10
 */
public class RandomCode {
    public static String getRandomCode(){
        int num=(int)(Math.random()*100000);
        String returnNum;
        if (num<10000){
            returnNum ="0"+Integer.toString(num);
        }else{
            returnNum=Integer.toString(num);
        }

       /* DecimalFormat df = new DecimalFormat("00000");
        returnNum = df.format(num);*/

        //String returnNum =Integer.toString(num);
        return returnNum;
    }

    public static void main(String[] args){
        for(int i=0;i<100000;i++){
            System.out.println(getRandomCode());
        }
    }
}
