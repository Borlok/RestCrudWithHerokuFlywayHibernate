package com.borlok.servlet.util;

import com.borlok.model.Customer;
import com.borlok.model.Specialty;
import com.google.gson.*;

import java.lang.reflect.Type;

public class CustomerSerializer implements JsonSerializer<Customer> {
    @Override
    public JsonElement serialize(Customer src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject customer = new JsonObject();

        customer.addProperty("Id",src.getId());
        JsonArray specialties = new JsonArray();
        for (Specialty s : src.getSpecialties()) {
            specialties.add(s.getId());
        }
        customer.add("Specialties",specialties);
        JsonObject account = new JsonObject();
        account.addProperty("id", src.getAccount().getId());
        account.addProperty("name", src.getAccount().getName());
        account.addProperty("status", src.getAccount().getStatus().ordinal());
        customer.add("Account", account);

        return customer;
    }
}