package com.david.bank;

import com.david.bank.controller.BankAccountController;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {
    @Autowired
    private BankAccountController controller;

    @Test
    public void contextLoads(){
        assertThat(controller).isNotNull();
    }
}
