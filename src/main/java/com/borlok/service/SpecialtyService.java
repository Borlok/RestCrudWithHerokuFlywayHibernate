package com.borlok.service;

import com.borlok.model.Specialty;
import com.borlok.repository.SpecialtyRepository;
import com.borlok.repository.hibernate.JpaSpecialtyRepository;

import java.util.List;

public class SpecialtyService {
    private SpecialtyRepository repository = new JpaSpecialtyRepository();

    public Specialty create(Specialty specialty) {
        return repository.create(specialty);
    }

    public List<Specialty> getAll() {
        List<Specialty> specialties = repository.getAll();
        for (Specialty x : specialties)
            x.setCustomers(null);
        return specialties;
    }

    public Specialty getById(int id) {
        Specialty specialty = repository.getById(id);
        specialty.setCustomers(null);
        return specialty;
    }

    public Specialty update(Specialty specialty) {
        return repository.update(specialty);
    }

    public void delete(Integer id) {repository.delete(id);}
}
