package com.LC.LaraCulturaCommunity.service;

import com.LC.LaraCulturaCommunity.constants.LCConstants;

import com.LC.LaraCulturaCommunity.model.Person;
import com.LC.LaraCulturaCommunity.model.Roles;

import com.LC.LaraCulturaCommunity.repository.PersonRepository;
import com.LC.LaraCulturaCommunity.repository.RolesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createNewPerson(Person person){
        boolean isSaved = false;
        Roles role = rolesRepository.getByRoleName(LCConstants.FINANCE_ROLE);
        person.setRoles(role);
        person.setPwd(passwordEncoder.encode(person.getPwd()));
        person = personRepository.save(person);
        if (null != person && person.getPersonId() > 0)
        {
            isSaved = true;
        }
        return isSaved;
    }
}
