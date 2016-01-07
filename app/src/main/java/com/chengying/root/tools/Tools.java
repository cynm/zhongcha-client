package com.chengying.root.tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 16-1-6.
 */
public class Tools {
    public static Map<String,String> JArrayToMap(String jsonArray){
        Map<String,String>map = new HashMap<>();
        try {
            JSONArray jsa=new JSONArray(jsonArray);
            JSONObject jo=jsa.getJSONObject(0);
            Iterator<String> keys = jo.keys();
            String key;
            while(keys.hasNext()){
                key=keys.next();

                map.put(key,jo.getString(key));


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map;
    }
    public static List<Map<String,String>> JArrayToMaps(String jsonArray)
    {
        Map<String,String>map =new HashMap<>();
        List<Map<String,String>> list=null;
        try {
            JSONArray jsa=new JSONArray(jsonArray);
            JSONObject jo=new JSONObject();
            for(int i=0;i<jsa.length();i++) {
                jo=jsa.getJSONObject(i);
                Iterator<String> keys = jo.keys();
                String key;
                while (keys.hasNext()) {
                    key = keys.next();

                    map.put(key, jo.getString(key));


                }
                list.add(map);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}
