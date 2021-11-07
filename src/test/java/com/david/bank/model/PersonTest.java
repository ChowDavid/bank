package com.david.bank.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    public void test(){
        Person person = new Person();
        person.setAccounts(null);
        person.setFirstName("firstName");
        person.setLastName("lastName");
        person.setUserName("userName");
        Assertions.assertNull(person.getAccounts());
        Assertions.assertEquals("firstName",person.getFirstName());
        Assertions.assertEquals("lastName",person.getLastName());
        Assertions.assertEquals("userName",person.getUserName());
    }

}