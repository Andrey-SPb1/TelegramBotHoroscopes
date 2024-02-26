package org.bot.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.objects.User;

import static org.bot.service.UserService.*;

class UserServiceTest {

    @Test
    void addUserIfNotExistTest() {
        User user = new User();
        user.setId(1L);

        addUserIfNotExist(user);
        Assertions.assertTrue(isThereUser(user.getId()));
    }

}