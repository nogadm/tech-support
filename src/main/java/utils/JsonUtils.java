package utils;

import com.google.gson.Gson;

public class JsonUtils {
    private static final Gson gson = new Gson();

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public static String toJson(Object object) {
        return gson.toJson(object);
    }
}
