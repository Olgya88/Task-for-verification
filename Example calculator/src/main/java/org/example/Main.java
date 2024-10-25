package org.example;

import net.thauvin.erik.crypto.CryptoPrice;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        var scanner = new Scanner(System.in).useLocale(Locale.US);

        //System.out.println("What is BTS price today?");
       //var rate = scanner.nextDouble();

        var rate = CryptoPrice.spotPrice("BTC");
        System.out.println("BTC price is" +rate.getAmount());

        while (true) {
            System.out.println("How many dollars do you have?");
            var dollars = scanner.nextDouble();

            if (dollars == 0)
                break;

            var result = dollars / rate.getAmount().doubleValue();
            System.out.println("result = " + result);

        }

    }
}
