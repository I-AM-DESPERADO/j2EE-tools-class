package com.trace.app.framework.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONString;


import java.security.Key;
import java.util.*;

/**
 * json 与对象互相转化工具类，利用Gson, 此处用单列模式
 * Created by liyuan on 2015/1/1.
 * Mod by MEX on 2015/3/27
 */
public class JsonUtils {
    private static Gson gson;
    private static JsonUtils jsonUtils;

    //创建私有构造函数，防止外部其他类使用
    private JsonUtils() {
        gson = new Gson();
    }

    //获取实例
    public static JsonUtils getInstance() {
        if (jsonUtils == null) {
            jsonUtils = new JsonUtils();

        }
        return jsonUtils;
    }

    // json 字符串转换成object
    public Object jsonStr2Object(String json, Class className) {
        Object object = null;
        try {
            object = gson.fromJson(json, className);
        } catch (Exception e) {
            object = null;
        }
        System.out.println(json);
        return object;
    }



    // object 字符串转换成json
    public String objec2JsonStr(Object object) {
        String jsonStr = null;
        try {
            jsonStr = gson.toJson(object);
        } catch (Exception e) {
            jsonStr = null;
        }
        return jsonStr;
    }

    //map字符串转换成json
    public String map2JsonStr(Map map) {
        String jsonStr = null;
        try {
            jsonStr = gson.toJson(map);
        } catch (Exception e) {
            jsonStr = null;
        }
        return jsonStr;
    }

    // json 字符串转换成HashMap
    public Map<String,String> jsonStr2HashMap(String json) {
        Map<String,String> map;
        try {
            map = gson.fromJson(json,new TypeToken<HashMap<String,String>>(){}.getType());
        } catch (Exception e) {
            map = null;
        }
        return map;
    }


    public List jsonArray2ObjectList(String json,Class className){
        JSONArray jsonarray = JSONArray.fromObject(json);
        List list = (List)JSONArray.toCollection(jsonarray, className);
        return list;
    }


    public  Map<String, Object> parseJSON2Map(String jsonStr){
        Map<String, Object> map = new HashMap<String, Object>();
        //最外层解析
        JSONObject json = JSONObject.fromObject(jsonStr);
        for(Object k : json.keySet()){
            Object v = json.get(k);
            //如果内层还是数组的话，继续解析
            if(v instanceof JSONArray){
                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
                Iterator<JSONObject> it = ((JSONArray)v).iterator();
                while(it.hasNext()){
                    JSONObject json2 = it.next();
                    list.add(parseJSON2Map(json2.toString()));
                }
                map.put(k.toString(), list);
            } else {
                map.put(k.toString(), v);
            }
        }
        return map;
    }




}
