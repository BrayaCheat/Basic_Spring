package com._5.basic.service;

import com._5.basic.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    Page<User> listUsers(Pageable pageable);
}
