package com.borlok.servlet.util;

import com.borlok.controller.AccountController;
import com.borlok.controller.SpecialtyController;
import com.borlok.model.Customer;
import com.borlok.model.Specialty;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class CustomerDeserializer implements JsonDeserializer<Customer> {

    @Override
    public Customer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject cus = json.getAsJsonObject();
        Customer customer = new Customer();

        customer.setId(cus.get("Id").getAsInt());

        JsonArray specialties = cus.getAsJsonArray("Specialties");
        Set<Specialty> specialtySet = new HashSet<>();
        for (JsonElement x : specialties)
            specialtySet.add(new SpecialtyController().getById(x.getAsInt()));
        customer.setSpecialties(specialtySet);

        customer.setAccount(new AccountController().getById(cus.get("AccountId").getAsInt()));
        return customer;
    }
}