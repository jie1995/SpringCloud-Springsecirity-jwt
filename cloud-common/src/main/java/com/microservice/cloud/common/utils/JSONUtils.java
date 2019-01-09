package com.microservice.cloud.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * TODO: Json工具类
 *
 * @author junyunxiao
 * @date 2018-9-12 16:18
 */
public class JSONUtils {
    private static Gson gson = (new GsonBuilder()).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    private static JsonParser jsonParser = new JsonParser();

    public static String toJSON(Object data) {
        return data != null ? gson.toJson(data) : "";
    }

    public static String toJSON(JsonElement jsonElement) {
        return jsonElement != null ? gson.toJson(jsonElement) : "";
    }

    public static <T> T fromJSON(String data, Class<T> type) {
        return TextUtils.isNotEmpty(data) && type != null ? gson.fromJson(data.replaceAll("[\n\r\t]", ""), type) : null;
    }

    public static <T> T fromJSON(String data, Type type) {
        return (T) (TextUtils.isNotEmpty(data) && type != null ? gson.fromJson(data.replaceAll("[\n\r\t]", ""), type) : null);
    }

    public static <T> T fromJSON(JsonElement jsonElement, Class<T> type) {
        return jsonElement != null && type != null ? gson.fromJson(jsonElement, type) : null;
    }

    public static <T> T fromJSON(JsonElement jsonElement, Type type) {
        return (T) (jsonElement != null && type != null ? gson.fromJson(jsonElement, type) : null);
    }

    public static JsonElement toJSONElement(String data) {
        return data != null ? jsonParser.parse(data) : null;
    }

    public static JsonElement getJSONElement(JsonElement jsonElement, String... name) {
        if (jsonElement == null) {
            return null;
        } else {
            JsonElement currentJsonElement = jsonElement;
            JsonObject jsonObject = null;
            String[] arg3 = name;
            int arg4 = name.length;

            for (int arg5 = 0; arg5 < arg4; ++arg5) {
                String currentName = arg3[arg5];
                if (!currentJsonElement.isJsonObject()) {
                    break;
                }

                jsonObject = currentJsonElement.getAsJsonObject();
                if (!jsonObject.has(currentName)) {
                    break;
                }

                currentJsonElement = jsonObject.get(currentName);
            }

            return currentJsonElement;
        }
    }

    public static Map<String, Object> toMap(JsonElement jsonElement) {
        HashMap map = new HashMap(16);
        if (jsonElement != null && jsonElement.isJsonObject()) {
            Iterator arg1 = jsonElement.getAsJsonObject().entrySet().iterator();

            while (arg1.hasNext()) {
                Entry entry = (Entry) arg1.next();
                map.put(entry.getKey(), toObject((JsonElement) entry.getValue()));
            }
        }

        return map;
    }

    public static List<Object> toList(JsonElement jsonElement) {
        ArrayList list = new ArrayList();
        if (jsonElement != null && jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            int lr = 0;

            for (int count = jsonArray.size(); lr < count; ++lr) {
                list.add(toObject(jsonArray.get(lr)));
            }
        }

        return list;
    }

    private static Object toObject(JsonElement jsonElement) {
        if (jsonElement.isJsonPrimitive()) {
            JsonPrimitive jsonPrimitive = jsonElement.getAsJsonPrimitive();
            if (jsonPrimitive.isString()) {
                return jsonPrimitive.getAsString();
            }

            if (jsonPrimitive.isNumber()) {
                String result = jsonPrimitive.getAsString();
                if (result.indexOf(".") > 0) {
                    return Double.valueOf(jsonPrimitive.getAsDouble());
                }

                if (result.length() > 9) {
                    return Long.valueOf(jsonPrimitive.getAsLong());
                }

                return Integer.valueOf(jsonPrimitive.getAsInt());
            }

            if (jsonPrimitive.isBoolean()) {
                return Boolean.valueOf(jsonPrimitive.getAsBoolean());
            }
        } else {
            if (jsonElement.isJsonObject()) {
                return toMap(jsonElement);
            }

            if (jsonElement.isJsonArray()) {
                return toList(jsonElement);
            }
        }

        return null;
    }
}
