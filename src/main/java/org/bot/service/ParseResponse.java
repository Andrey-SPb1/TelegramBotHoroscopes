package org.bot.service;

import org.bot.Bot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.time.LocalDate;

import static org.bot.service.InitKeyboard.initKeyboardWithPeriods;
import static org.bot.service.InitKeyboard.initKeyboardWithZodiacSigns;
import static org.bot.service.ParseForecast.*;
import static org.bot.service.UserService.*;
import static org.bot.service.ZodiacSignsByDate.*;
import static org.bot.service.ZodiacSignsByDate.getZodiacSignByDate;
import static org.bot.service.enums.ForecastPeriods.*;
import static org.bot.service.enums.ZodiacSigns.getZodiacSignByStr;
import static org.bot.service.enums.ZodiacSigns.isZodiacSign;
import static org.bot.util.TextOfAnswersUtil.getAnswer;

public class ParseResponse {

    private static final Logger log = LoggerFactory.getLogger(ParseResponse.class);

    public static String parseMessage(Message msg, String userFirstName, Bot bot) {
        addUserIfNotExist(msg.getFrom());

        if(!isThereZodiacSign(msg)) {
            return selectZodiacSign(msg, userFirstName, bot);
        } else {
            return selectPeriod(msg, userFirstName, bot);
        }
    }

    private static String selectZodiacSign(Message msg, String userFirstName, Bot bot) {
        Long userId = msg.getFrom().getId();
        String textMsg = msg.getText();

        log.info("{} sent: {}", userFirstName, msg.getText());

        bot.setKeyboard(initKeyboardWithZodiacSigns());
        if(textMsg.equals("/start")) {
            return getAnswer("start");
        }
        else if (isZodiacSign(textMsg)) {
            bot.setKeyboard(initKeyboardWithPeriods());
            addUser(userId, getZodiacSignByStr(textMsg));
            return getAnswer("afterSelectingZodiacSign");
        }
        else if (isDateFormat(textMsg)) {
            LocalDate date = dateConvertFromString(textMsg);
            if(date != null) {
                bot.setKeyboard(initKeyboardWithPeriods());
                addUser(userId, getZodiacSignByDate(date));
                return getAnswer("afterSelectingZodiacSign");
            } else {
                return getAnswer("invalidDate");
            }
        } else if (isPeriod(textMsg)) {
            return getAnswer("chooseNewSign");
        } else {
            return getAnswer("invalidMessage");
        }
    }

    private static String selectPeriod(Message msg, String userFirstName, Bot bot) {
        Long userId = msg.getFrom().getId();
        String textMsg = msg.getText();

        log.info("{} sent: {}", userFirstName, msg.getText());

        bot.setKeyboard(initKeyboardWithPeriods());
        if(textMsg.equals(getAnswer("buttonChangeSign")) || textMsg.equals("/start")) {
            bot.setKeyboard(initKeyboardWithZodiacSigns());
            UserService.addUser(userId, null);
            return getAnswer("chooseNewSign");
        } else if (isPeriod(textMsg)) {
            return getForecast(getZodiacSignById(userId).getTitle(), textMsg);
        } else {
            return getAnswer("invalidMessage");
        }
    }
}
