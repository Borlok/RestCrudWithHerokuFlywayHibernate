package com.borlok.servlet;

import com.borlok.controller.SpecialtyController;
import com.borlok.model.Specialty;
import com.borlok.servlet.util.ServletsUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SpecialtyServlet extends HttpServlet {
    private SpecialtyController specialtyController = new SpecialtyController();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Gson gson = new Gson();
        String jsonObject = "";

        String [] splitId = ServletsUtils.getSpitedRequestUri(request);
        if (splitId.length > 1) {
            jsonObject = ServletsUtils.getJsonFormatFromObject(specialtyController.getById(Integer.parseInt(splitId[1])));
        } else {
            jsonObject = ServletsUtils.getJsonFormatFromObject(specialtyController.getAll());
        }
        response.getWriter().println(jsonObject);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Specialty specialty = (Specialty) ServletsUtils.getObjectFromRequest(request, new Specialty());
        specialtyController.create(specialty);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String [] splitId = ServletsUtils.getSpitedRequestUri(request);
        Specialty specialty = (Specialty) ServletsUtils.getObjectFromRequest(request, new Specialty());
        specialty.setId(Integer.parseInt(splitId[1]));
        specialtyController.update(specialty);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        specialtyController.delete(Integer.valueOf(ServletsUtils.getSpitedRequestUri(request)[1]));
    }
}
