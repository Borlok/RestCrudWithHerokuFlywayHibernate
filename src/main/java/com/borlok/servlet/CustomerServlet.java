package com.borlok.servlet;

import com.borlok.controller.CustomerController;
import com.borlok.model.Customer;
import com.borlok.servlet.util.CollectionSerializer;
import com.borlok.servlet.util.CustomerDeserializer;
import com.borlok.servlet.util.CustomerSerializer;
import com.borlok.servlet.util.ServletsUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerServlet extends HttpServlet {
    private CustomerController customerController = new CustomerController();
    private Gson serializer = new GsonBuilder().setPrettyPrinting()
            .registerTypeAdapter(Customer.class, new CustomerSerializer())
            .create();
    private Gson deserializer = new GsonBuilder().setPrettyPrinting()
            .registerTypeAdapter(Customer.class, new CustomerDeserializer())
            .create();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String jsonObject = "";

        String [] splitId = ServletsUtils.getSplicedUriFromRequest(request);
        if (splitId.length > 1) {
            jsonObject = serializer.toJson(customerController.getById(Integer.parseInt(splitId[1])));
        } else {
            jsonObject = CollectionSerializer.saveCollectionToJson(customerController.getAll());
        }
        response.getWriter().println(jsonObject);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Customer customer = deserializer.fromJson(ServletsUtils.getStringFromRequestJson(request),Customer.class);
        customerController.create(customer);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String [] splitId = ServletsUtils.getSplicedUriFromRequest(request);
        Customer customer =  deserializer.fromJson(ServletsUtils.getStringFromRequestJson(request), Customer.class);
        customer.setId(Integer.parseInt(splitId[1]));
        customerController.update(customer);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        customerController.delete(Integer.valueOf(ServletsUtils.getSplicedUriFromRequest(request)[1]));
    }
}
