package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        hello();
        readAnswer();
    }

    // Метод 1
    static void hello() {
        // шаг 1 Приветствие
        System.out.println("Привет!");
        System.out.println("Как твои дела?");
    }

    // Метод 2
    static void readAnswer() {
        // шаг 2 Получение ответа от пользователя
        var scanner = new Scanner(System.in);
        var answer = scanner.nextLine();

        // вывод на консоль
        System.out.println("Он ответил: " + answer);
    }
}
