package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        var scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введіть витрату пального (л/100 км):");
            var fuelConsumption = scanner.nextDouble();
            if (fuelConsumption == 0)
                break;

        System.out.println("Введіть відстань, яку проїхало авто (км): ");
        var distance = scanner.nextDouble();
        if (distance == 0)
            break;

            var fuelUsed = (fuelConsumption * distance) / 100;
            System.out.println("Кількість витраченого пального: " + fuelUsed + " літрів");
        }

    }
}
