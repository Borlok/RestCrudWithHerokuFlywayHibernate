package com.borlok.servlet.util;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

public class ServletsUtils {
    public static String getJsonFormatFromObject(Object object) {
        Gson gson = new Gson();
        return gson.toJson((object.getClass().cast(object)));
    }

    public static Object getObjectFromRequest(HttpServletRequest request, Object object) throws IOException {
        Gson gson = new Gson();
        String x = request.getReader().lines().reduce("", String::concat);
        return gson.fromJson(x, object.getClass());
    }

    public static String[] getSpitedRequestUri(HttpServletRequest request) {
        return  Arrays.stream(request.getRequestURI().split("/")).filter(x-> !(x.equals(""))).toArray(String[]::new);
    }


}
