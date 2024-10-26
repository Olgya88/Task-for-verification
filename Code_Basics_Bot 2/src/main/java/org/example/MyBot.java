package org.example;

import net.thauvin.erik.crypto.CryptoPrice;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.apache.commons.lang3.StringUtils.isNumeric;

// Мы строим бота на основе готовой библиотеке, после этого появится ошибка,
// нужно кликнуть на ней и применить implement methods и нажать ОК
public class MyBot extends TelegramLongPollingBot {

   public MyBot () {
       super("7767321499:AAFf_upqABloA19y9RL8PX7nivBlMVFIBOw");
   }

   // Метод который вызывается автоматически
    @Override
    public void onUpdateReceived(Update update) {

       // когда пользователь пишет сообшение телеграм присваивает ему уникальный номер
        var chatId = update.getMessage().getChatId();

        //Для того что б бот понимал что ему пишут
        var text = update.getMessage().getText();

        try {
            // для ответа пользователю
        var message = new SendMessage();
        // настройка сообщения, кому его отправлять
        message.setChatId(chatId);

        //Проверка что написали боту
        if (text.equals("/start")) {
            message.setText("Привет!");
        } else if (text.equals("btc")) {
            // Отправляем изображение
            sendPicture(chatId, "bitcoin-btc-logo.png");
            var price = CryptoPrice.spotPrice("BTC");
            message.setText("BTC price: " + price.getAmount().doubleValue());

        } else if (text.equals("eth")) {
            sendPicture(chatId, "ethereum-eth-logo.png");
            var price = CryptoPrice.spotPrice("ETH");
            message.setText("ETH price: " + price.getAmount().doubleValue());

        } else if (text.equals("doge")) {
            sendPicture(chatId, "dogecoin-doge-logo.png");
            var price = CryptoPrice.spotPrice("DOGE");
            message.setText("DOGE price: " + price.getAmount().doubleValue());

        } else if (text.equals("/all")) {
            var btcPrice = CryptoPrice.spotPrice("BTC").getAmount().doubleValue();
            var ethPrice = CryptoPrice.spotPrice("ETH").getAmount().doubleValue();
            var dogePrice = CryptoPrice.spotPrice("DOGE").getAmount().doubleValue();

            message.setText("BTC: " + btcPrice + "   ETH: " + ethPrice + "   DOGE: " + dogePrice);

        //Добавим метод для проверки есть ли введенный текст числом
        } else if (isNumeric(text)) {
            double amountInUsd = Double.parseDouble(text);
            double btcPrice = CryptoPrice.spotPrice("BTC").getAmount().doubleValue();
            double ethPrice = CryptoPrice.spotPrice("ETH").getAmount().doubleValue();
            double dogePrice = CryptoPrice.spotPrice("DOGE").getAmount().doubleValue();

            double btcAmount = amountInUsd / btcPrice;
            double ethAmount = amountInUsd / ethPrice;
            double dogeAmount = amountInUsd / dogePrice;

            message.setText(String.format("На суму %.2f USD:\nBTC: %.6f\nETH: %.6f\nDOGE: %.2f",
                    amountInUsd, btcAmount, ethAmount, dogeAmount));

        } else if (text.matches("\\b(btc|eth|doge)\\b \\d+")) {
            handleCryptoCalculation(chatId, text, message);



        }   else {
            // Что написать
            message.setText("Я не знаю что скачать");
        }


            // Специальный метод из библиотеке в который передаются сообщения на отправку
            // появится ошибка, при ее автоматическом исправлении появится блок ошибок  try
            execute(message);
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
     // Метод для отправки логотипа валюты
     void sendPicture(Long chatId, String name) throws Exception {
       var photo = getClass().getClassLoader().getResourceAsStream(name);
       var message = new SendPhoto();
       message.setChatId(chatId);
       message.setPhoto(new InputFile(photo, name));
       execute(message);
    }

    // Метод для выполнения команды btc 300
    private void handleCryptoCalculation(Long chatId, String text, SendMessage message) throws Exception {
        String[] parts = text.split(" ");
        String cryptoType = parts[0];
        double amountInUsd = Double.parseDouble(parts[1]);
        double cryptoPrice = CryptoPrice.spotPrice(cryptoType.toUpperCase()).getAmount().doubleValue();
        double cryptoAmount = amountInUsd / cryptoPrice;
        message.setText(String.format("На сумму %.2f USD можно купить %.6f %s.",
                amountInUsd, cryptoAmount, cryptoType.toUpperCase()));
    }

    // Метод который возвращает имя бота
    @Override
    public String getBotUsername() {
        return "Code_Basics_Bot";
    }
}
