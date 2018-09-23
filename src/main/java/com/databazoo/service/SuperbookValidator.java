package com.databazoo.service;

import com.databazoo.bo.Superbook;

public interface SuperbookValidator {
    void checkCreate(Superbook entity);

    void checkUpdate(Superbook entity);
}
