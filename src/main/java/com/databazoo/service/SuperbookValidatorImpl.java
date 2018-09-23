package com.databazoo.service;

import com.databazoo.bo.Superbook;
import org.springframework.stereotype.Service;

@Service
public class SuperbookValidatorImpl implements SuperbookValidator {
    @Override
    public void checkCreate(Superbook entity) {
        if (entity.getTitle() == null || entity.getId() != null || entity.getPublisher() == null) {
            throw new IllegalArgumentException("Validation failed");
        }
    }

    @Override
    public void checkUpdate(Superbook entity) {

    }
}