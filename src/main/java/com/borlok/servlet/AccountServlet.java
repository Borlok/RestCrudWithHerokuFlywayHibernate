package com.borlok.servlet;

import com.borlok.controller.AccountController;
import com.borlok.model.Account;
import com.borlok.servlet.util.ServletsUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccountServlet extends HttpServlet {
    private AccountController accountController = new AccountController();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String jsonObject = "";

        String [] splitId = ServletsUtils.getSplicedUriFromRequest(request);

        if (splitId.length > 1) {
            jsonObject = ServletsUtils.getJsonFromObject(accountController.getById(Integer.parseInt(splitId[1])));
        } else {
            jsonObject = ServletsUtils.getJsonFromObject(accountController.getAll());
        }
        response.getWriter().println(jsonObject);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = (Account) ServletsUtils.getObjectFromRequestJson(request, new Account());
        accountController.create(account);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String [] splitId = ServletsUtils.getSplicedUriFromRequest(request);
        Account account = (Account) ServletsUtils.getObjectFromRequestJson(request, new Account());
        account.setId(Integer.parseInt(splitId[1]));
        accountController.update(account);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        accountController.delete(Integer.valueOf(ServletsUtils.getSplicedUriFromRequest(request)[1]));
    }
}
