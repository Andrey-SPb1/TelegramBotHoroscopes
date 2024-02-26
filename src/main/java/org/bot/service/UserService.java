package org.bot.service;

import org.bot.service.enums.ZodiacSigns;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private static final Map<Long, ZodiacSigns> USERS = new HashMap<>();

    public static boolean isThereZodiacSign(Message msg) {
        return USERS.get(msg.getFrom().getId()) != null;
    }

    public static void addUserIfNotExist(User user) {
        Long id = user.getId();

        if(isNewUser(id)) {
            log.info("There is a new user - {}", user);
            USERS.put(id, null);
        }
    }

    public static void addUser(Long id, ZodiacSigns sign) {
        USERS.put(id, sign);
    }

    public static boolean isThereUser(Long id) {
        return USERS.containsKey(id);
    }

    public static ZodiacSigns getZodiacSignById(Long id) {
        return USERS.get(id);
    }

    private static boolean isNewUser(Long id) {
        return !USERS.containsKey(id);
    }
}
