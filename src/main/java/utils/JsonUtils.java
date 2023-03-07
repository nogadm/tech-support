package utils;

import com.google.gson.Gson;

// A utility class for working with JSON strings using Google's Gson library
public class JsonUtils {
    private static final Gson gson = new Gson();

    // Parses the specified JSON string into an object of the specified type
    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    // Serializes the specified object to a JSON string
    public static String toJson(Object object) {
        return gson.toJson(object);
    }
}
