package com.trace.app.framework.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.trace.app.framework.toolsmodel.BPResult;
import com.trace.app.framework.toolsmodel.Constant;

/**
 * Created by 魏伟 on 2016/3/4.
 * Updated by Jin on 2016/08/08
 * Description:主要是改原本的单个Object为可变参数,可传入多个.不影响之前传入单个,满足开放闭合
 */
public class CommonLogic {
    public static String jsonAssemblyMuti(String code, String message, Object... content) {//修改可传多个0~n

        BPResult bpResult = new BPResult();
        bpResult.setCode(code);
        bpResult.setMessage(message);
        bpResult.setContents(content);
        return JsonUtils.getInstance().objec2JsonStr(bpResult);
    }

    public static String jsonAssembly(String code, String message, Object content) {//原有单个参数

        BPResult bpResult = new BPResult();
        bpResult.setCode(code);
        bpResult.setContents(content);
        bpResult.setMessage(message);
        return JsonUtils.getInstance().objec2JsonStr(bpResult);
    }

    public static String jsonAssemblyWithoutHtmlEscape(String code, String message, Object content) {
        GsonBuilder gb = new GsonBuilder();
        gb.disableHtmlEscaping();
        Gson gson = gb.create();
        BPResult bpResult = new BPResult();
        bpResult.setCode(code);
        bpResult.setMessage(message);
        bpResult.setContents(content);
        return gson.toJson(bpResult);
    }

    public static String dataNull() {
        return jsonAssembly(Constant.ERROR_CODE, "数据库数据为空", null);
    }

    public static String getMethodName() {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[2];
        String methodName = e.getMethodName();
        return methodName;
    }



    public static String getGovDepartment(String governmentType){
        String department = "";
        if(governmentType.equals("0")){
            department = "农业局";
        }else if(governmentType.equals("1")){
            department = "质监局";
        }
        return department;
    }

    /**
     * 控制台输出 进行调试
     * update on 20170502 by mcg
     * @param s
     */
    public static void consolePrint(String s){
        if(Constant.CONSOLE_Flag){
            System.out.println(s);
        }
    }




}
