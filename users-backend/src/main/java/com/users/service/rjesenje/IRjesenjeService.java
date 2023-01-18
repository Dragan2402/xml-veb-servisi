package com.users.service.rjesenje;

import com.users.model.rjesenje.Rjesenje;


public interface IRjesenjeService {
    Rjesenje create(Rjesenje rjesenje) throws Exception;

    Rjesenje findById(long id) throws Exception;
}
