package org.bot.service;

import org.bot.service.enums.ForecastPeriods;
import org.bot.service.enums.ZodiacSigns;
import org.bot.util.TextOfAnswersUtil;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;

import static org.bot.service.enums.ZodiacSigns.values;

public class InitKeyboard {

    public static ReplyKeyboardMarkup initKeyboardWithZodiacSigns() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();

        int numberOfLines = 3;
        int iterator = 0;
        ZodiacSigns[] zodiacSigns = values();
        for (ZodiacSigns zodiacSign : zodiacSigns) {
            keyboardRow.add(new KeyboardButton(zodiacSign.getTitle()));
            iterator++;
            if (iterator == numberOfLines) {
                keyboardRows.add(keyboardRow);
                keyboardRow = new KeyboardRow();
                iterator = 0;
            }
        }
        replyKeyboardMarkup.setKeyboard(keyboardRows);

        return replyKeyboardMarkup;
    }

    public static ReplyKeyboardMarkup initKeyboardWithPeriods() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();

        int numberOfColumns = 3;
        int iterator = 0;
        ForecastPeriods[] forecastPeriods = ForecastPeriods.values();
        for (ForecastPeriods forecastPeriod : forecastPeriods) {
            keyboardRow.add(new KeyboardButton(forecastPeriod.getTitle()));
            iterator++;
            if (iterator == numberOfColumns) {
                keyboardRows.add(keyboardRow);
                keyboardRow = new KeyboardRow();
                iterator = 0;
            }
        }

        keyboardRow = new KeyboardRow();
        keyboardRow.add(new KeyboardButton(TextOfAnswersUtil.getAnswer("buttonChangeSign")));
        keyboardRows.add(keyboardRow);

        replyKeyboardMarkup.setKeyboard(keyboardRows);

        return replyKeyboardMarkup;
    }
}
