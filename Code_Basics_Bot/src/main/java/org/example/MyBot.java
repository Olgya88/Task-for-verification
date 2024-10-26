package org.example;

import net.thauvin.erik.crypto.CryptoPrice;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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

       // когда пользователь пишет сооьшение телеграм присваивает ему уникальный номер
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
            var price = CryptoPrice.spotPrice("BTC");
            message.setText("BTC price: " + price.getAmount().doubleValue());

        } else if (text.equals("eth")) {
            var price = CryptoPrice.spotPrice("ETH");
            message.setText("ETH price: " + price.getAmount().doubleValue());

        } else if (text.equals("doge")) {
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

    // Метод который возвращает имя бота
    @Override
    public String getBotUsername() {
        return "Code_Basics_Bot";
    }
}
