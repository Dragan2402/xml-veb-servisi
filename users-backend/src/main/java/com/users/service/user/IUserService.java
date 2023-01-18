package com.users.service.user;

import com.users.controller.Requests.LoginRequest;
import com.users.controller.Responses.LoginResponse;
import com.users.model.user.User;
import org.xmldb.api.base.XMLDBException;

import java.io.IOException;

public interface IUserService {

    User create(User user) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException;

    User getUserByEmail(String email, boolean existing) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException;

    LoginResponse login(LoginRequest request) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException;
}
