package com.admin.study.admin.ifs;

import com.admin.study.admin.model.network.Header;

public interface CrudInterface {
    Header create();

    Header read(Long id);

    Header update();

    Header delete(Long id);
}
