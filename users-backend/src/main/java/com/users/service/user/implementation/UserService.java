package com.users.service.user.implementation;

import com.users.controller.Requests.LoginRequest;
import com.users.controller.Responses.LoginResponse;
import com.users.model.user.User;
import com.users.rdf.FusekiWriter;
import com.users.rdf.MetadataExtractor;
import com.users.repository.UserRepository;
import com.users.service.user.IUserService;
import com.users.util.Utility;
import com.users.util.exception.customExceptions.InvalidRequestException;
import com.users.util.exception.customExceptions.ObjectNotFoundException;
import com.users.util.exception.customExceptions.UserRoleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.namespace.QName;
import javax.xml.transform.TransformerException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Objects;

@SuppressWarnings("unchecked")
@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final MetadataExtractor metadataExtractor;

    @Override
    public User create(User user) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if(user.getRola() == null || user.getRola().getValue() == null){
            throw new UserRoleException("Invalid role");
        }
        if(user.getEmail() == null || user.getIme()==null || user.getPrezime() == null || user.getSifra() == null){
            throw new InvalidRequestException("Invalid request");
        }
        getUserByEmail(user.getEmail().getValue(), false);
        try {
            long id = Utility.getNextId();
            user.setId(id);
            user.setAbout("http://users.com/model/rdf/user/" + id);
            user.setTypeof("pred:Id");

            user.getEmail().setProperty("pred:Email");
            user.getEmail().setDatatype("xs:string");

            user.getIme().setProperty("pred:Ime");
            user.getIme().setDatatype("xs:string");

            user.getPrezime().setProperty("pred:Prezime");
            user.getPrezime().setDatatype("xs:string");

            user.getRola().setProperty("pred:Rola");
            user.getRola().setDatatype("xs:string");

            user.getOtherAttributes().put(QName.valueOf("xmlns:pred"), "http://users.com/model/rdf/user/predicate/");
            user.getOtherAttributes().put(QName.valueOf("xmlns:xs"), "http://www.w3.org/2001/XMLSchema#");

            userRepository.save("/db/users", "id_" + user.getId(), user);

            XMLResource resource = userRepository.loadXmlResource("/db/users", "id_" + user.getId());
            byte[] out = metadataExtractor.extractMetadataFromXmlContent(resource.getContent().toString());
            FusekiWriter.saveRDF(new ByteArrayInputStream(out), "user");

            return user;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("User creation failed with message "+e.getMessage());
        }
    }

    @Override
    public User getUserByEmail(String email, boolean existing) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        return userRepository.getUserByEmail(email, existing);
    }

    @Override
    public LoginResponse login(LoginRequest request) throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        User user = this.getUserByEmail(request.getEmail(), true);
        if(user == null){
            throw new ObjectNotFoundException("User not found.");
        }
        if(Objects.equals(user.getEmail().getValue(), request.getEmail()) && Objects.equals(user.getSifra(), request.getPassword())){
            return new LoginResponse(user.getId(), user.getRola().getValue().value(), user.getEmail().getValue(), user.getIme().getValue(), user.getPrezime().getValue());
        }else{
            throw new InvalidRequestException("Bad credentials");
        }
    }
}
