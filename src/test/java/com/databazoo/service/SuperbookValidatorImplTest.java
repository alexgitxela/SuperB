package com.databazoo.service;

import com.databazoo.bo.Publisher;
import com.databazoo.bo.Superbook;
import org.junit.Test;

import java.util.UUID;

public class SuperbookValidatorImplTest {
    private SuperbookValidator validator = new SuperbookValidatorImpl();

    @Test
    public void checkCreateValid() {
        Superbook entity = new Superbook();
        entity.setTitle("Tester");
        entity.setPublisher(new Publisher());
        validator.checkCreate(entity);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkCreateInvalid() {
        Superbook entity = new Superbook();
        validator.checkCreate(entity);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkCreateInvalid2() {
        Superbook entity = new Superbook();
        entity.setId(UUID.randomUUID());
        validator.checkCreate(entity);
    }

    @Test
    public void checkUpdate() {
        Superbook entity = new Superbook();
        validator.checkUpdate(entity);
    }
}
