package com.databazoo.service;

import com.databazoo.bo.Superbook;
import com.databazoo.dao.SuperbookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SuperbookServiceImpl implements SuperbookService {

    @Autowired
    private SuperbookRepository dao;

    @Override
    public List<Superbook> listAll() {
        List<Superbook> target = new ArrayList<>();
        dao.findAll().forEach(target::add);
        return target;
    }

    @Override
    public Superbook getById(UUID id) {
        return dao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Superbook with ID " + id + " not found"));
    }

    @Override
    public Superbook create(Superbook entity) {
        dao.save(entity);
        return getById(entity.getId());
    }

    @Override
    public Superbook update(Superbook entity) {
        Superbook superbook = getById(entity.getId());

        superbook.setTitle(entity.getTitle());
        superbook.setAuthor(entity.getAuthor());

        dao.save(superbook);
        return superbook;
    }

    @Override
    public void delete(Superbook entity) {
        dao.delete(entity);
    }
}