package com.admin.study.admin.controller;

import com.admin.study.admin.ifs.CrudInterface;
import com.admin.study.admin.model.network.Header;
import org.springframework.web.bind.annotation.*;

public abstract class CrudController<req, res> implements CrudInterface<req, res> {

    protected CrudInterface<req,res> baseService;

    @Override
    @PostMapping
    public Header<res> create(@RequestBody Header<req> request) {
        return baseService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<res> read(@PathVariable Long id) {
        return baseService.read(id);
    }

    @Override
    @PutMapping
    public Header<res> update(@RequestBody Header<req> request) {
        return baseService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header<res> delete(@PathVariable Long id) {
        return baseService.delete(id);
    }
}
