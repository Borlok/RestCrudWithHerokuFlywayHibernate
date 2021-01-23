package com.borlok.servlet.util;

import com.borlok.model.Customer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class CollectionSerializer {
    private static final Gson saver = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Customer.class, new CustomerSerializer())
            .create();

    public static <T> String saveCollectionToJson(List<T> collection) {
            return saver.toJson(collection);
    }
}
