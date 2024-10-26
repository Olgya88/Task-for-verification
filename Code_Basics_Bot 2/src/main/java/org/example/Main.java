package org.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) throws Exception {

        // Специальная строчка которая позволяет регистрировать ботов на серверах Телеграм
        var api = new TelegramBotsApi(DefaultBotSession.class);

        // Просьба зарегистрировать нового бота (для создания файла MyBot.java
        // нажимаем правой кнопкой мыши на org.example и выбираем New, а потом java class
        api.registerBot(new MyBot());



    }
}

