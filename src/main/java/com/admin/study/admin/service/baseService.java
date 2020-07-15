package com.admin.study.admin.service;

import com.admin.study.admin.ifs.CrudInterface;
import com.admin.study.admin.model.network.Header;

public abstract class baseService<req, res> implements CrudInterface<req, res> {
    @Override
    public Header<res> create(Header<req> request) {
        return null;
    }

    @Override
    public Header<res> read(Long id) {
        return null;
    }

    @Override
    public Header<res> update(Header<req> request) {
        return null;
    }

    @Override
    public Header<res> delete(Long id) {
        return null;
    }
}
