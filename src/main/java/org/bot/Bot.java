package org.bot;

import org.bot.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static org.bot.service.ParseResponse.*;

public class Bot extends TelegramLongPollingBot {

    private final String BOT_TOKEN = PropertiesUtil.get("bot.token");
    private final String BOT_NAME = PropertiesUtil.get("bot.name");

    private static final Logger log = LoggerFactory.getLogger(Bot.class);

    private ReplyKeyboardMarkup keyboard;

    @Override
    public void onUpdateReceived(Update update) {
        try{
            if(update.hasMessage() && update.getMessage().hasText()) {
                Message inMess = update.getMessage();
                String chatId = inMess.getChatId().toString();

                String response = parseMessage(inMess, inMess.getFrom().getFirstName(), this);

                SendMessage outMess = new SendMessage();
                outMess.setParseMode(ParseMode.HTML);
                outMess.setReplyMarkup(keyboard);
                outMess.setChatId(chatId);
                outMess.setText(response);

                execute(outMess);
            }
        } catch (TelegramApiException e) {
            log.error("Telegram API error: {}", e.toString());
            e.printStackTrace();
        }
    }

    public void setKeyboard(ReplyKeyboardMarkup keyboard) {
        this.keyboard = keyboard;
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}