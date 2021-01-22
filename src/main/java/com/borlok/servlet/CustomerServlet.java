package com.borlok.servlet;

import com.borlok.controller.CustomerController;
import com.borlok.model.Customer;
import com.borlok.servlet.util.ServletsUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerServlet extends HttpServlet {
    private CustomerController customerController = new CustomerController();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String jsonObject = "";

        String [] splitId = ServletsUtils.getSpitedRequestUri(request);
        if (splitId.length > 1) {
            jsonObject = ServletsUtils.getJsonFormatFromObject(customerController.getById(Integer.parseInt(splitId[1])));
        } else {
            jsonObject = ServletsUtils.getJsonFormatFromObject(customerController.getAll());
        }
        response.getWriter().println(jsonObject);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Customer customer = (Customer) ServletsUtils.getObjectFromRequest(request, new Customer());
        customerController.create(customer);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String [] splitId = ServletsUtils.getSpitedRequestUri(request);
        Customer customer = (Customer) ServletsUtils.getObjectFromRequest(request, new Customer());
        customer.setId(Integer.parseInt(splitId[1]));
        customerController.update(customer);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        customerController.delete(Integer.valueOf(ServletsUtils.getSpitedRequestUri(request)[1]));
    }
}
