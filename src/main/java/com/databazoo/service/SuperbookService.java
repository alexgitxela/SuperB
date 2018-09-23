package com.databazoo.service;

import com.databazoo.bo.Superbook;

import java.util.List;
import java.util.UUID;

public interface SuperbookService {
    List<Superbook> listAll();

    Superbook getById(UUID id);

    Superbook create(Superbook entity);

    Superbook update(Superbook entity);

    void delete(Superbook entity);

}