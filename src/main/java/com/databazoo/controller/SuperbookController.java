package com.databazoo.controller;

import com.databazoo.bo.Superbook;
import com.databazoo.service.SuperbookService;
import com.databazoo.service.SuperbookValidator;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * A simple CRUD for {@link Superbook} entities.
 */
@RestController
public class SuperbookController {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(SuperbookController.class);

    @Autowired
    private SuperbookService service;

    @Autowired
    private SuperbookValidator validator;

    /**
     * List all
     *
     * @return superbook list
     */
    @GetMapping("/superbook")
    public List<Superbook> getList() {
        List<Superbook> list = service.listAll();
        LOG.info("Listing all " + list.size() + " elements");
        return list;
    }

    /**
     * Selecting by ID, maybe some other key will be used in future.
     *
     * @param id UUID
     * @return one superbook
     * @throws IllegalArgumentException in case ID is invalid or entity was not found.
     */
    @GetMapping("/superbook/{id}")
    public Superbook getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    /**
     * Create a new entity
     */
    @PostMapping("/superbook")
    public Superbook create(@RequestBody Superbook entity) {
        validator.checkCreate(entity);
        return service.create(entity);
    }

    /**
     * Re-save
     */
    @PutMapping("/superbook/{id}")
    public Superbook save(@PathVariable UUID id, @RequestBody Superbook entity) {
        entity.setId(id);
        validator.checkUpdate(entity);
        return service.update(entity);
    }

    /**
     * Delete an entity
     */
    @DeleteMapping("/superbook/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(service.getById(id));
    }
}