package com.david.bank.repositories;

import com.david.bank.model.BankAccount;
import com.david.bank.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person,Long> {
    Person findByUserName(String userName);
    @Query("Select accounts from Person person JOIN  person.accounts accounts where person.userName = ?1")
    List<BankAccount> getAllAccountByUserName(String userName);
}
