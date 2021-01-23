package com.borlok.servlet.util;

import com.borlok.controller.AccountController;
import com.borlok.controller.SpecialtyController;
import com.borlok.model.Account;
import com.borlok.model.AccountStatus;
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
        Set<Specialty> specialtySet = new HashSet<>();
        Account account = new Account();

        customer.setId(cus.get("Id").getAsInt());

        JsonArray specialties = cus.getAsJsonArray("Specialties");
        for (JsonElement x : specialties)
            specialtySet.add(new SpecialtyController().getById(x.getAsInt()));
        customer.setSpecialties(specialtySet);

        account.setId(cus.getAsJsonObject("Account").get("id").getAsInt());

        if (account.getId() != 0)
            account = new AccountController().getById(account.getId());
        else {
            account.setName(cus.getAsJsonObject("Account").get("name").getAsString());
            account.setStatus(AccountStatus.values()[(cus.getAsJsonObject("Account").get("status").getAsInt())]);
        }
        customer.setAccount(account);
        return customer;
    }
}