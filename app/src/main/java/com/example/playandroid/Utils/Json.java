package com.example.playandroid.Utils;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author 徐国林
 * @data 2020/4/4
 * @decription 解析数据
 */
public class Json {
    public static String getElement(String data, String name) {
        try {
            return new JSONObject(data).getString(name);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.w("tag", e.toString());
        }
        return null;
    }
}
