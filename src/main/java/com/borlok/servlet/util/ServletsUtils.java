package com.borlok.servlet.util;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

public class ServletsUtils {
    public static String getJsonFromObject(Object object) {
        Gson gson = new Gson();
        return gson.toJson((object.getClass().cast(object)));
    }

    public static String getStringFromRequestJson(HttpServletRequest request) throws IOException {
        return request.getReader().lines().reduce("",String::concat);
    }

    public static Object getObjectFromRequestJson(HttpServletRequest request, Object object) throws IOException {
        Gson gson = new Gson();
        return gson.fromJson(getStringFromRequestJson(request), object.getClass());
    }

    public static String[] getSplicedUriFromRequest(HttpServletRequest request) {
        return  Arrays.stream(request.getRequestURI().split("/")).filter(x-> !(x.equals(""))).toArray(String[]::new);
    }


}
