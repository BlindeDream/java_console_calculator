package com.company;

import com.sun.deploy.net.MessageHeader;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Scanner;

public class Main {

    public static ArrayList<Double> list = new ArrayList<Double>();

    public static void printResult(Calculator calculator, Scanner scan) {
        System.out.print("Результат: ");
        System.out.println((calculator.calculate()));
    }

    public static Calculator menu(Scanner scan) {
        System.out.println("Доступны следующие операции:");
        System.out.println("1) sin,cos");
        System.out.println("2) simple calculator");
        System.out.println("3) rounded calculator ");
        System.out.print("Выберите тип операции: ");
        int choise = scan.nextInt();
        Calculator calc;
        System.out.println();
        switch (choise) {
            case (1):

                System.out.println("1) sin ");
                System.out.println("2) sin to Radians");
                System.out.println("3) cos");
                System.out.println("4) cos to Radians");

                calc = new Calculator(scan, true); //isSingle == true
                return calc;

            case (2):

                System.out.println(" + ");
                System.out.println(" - ");
                System.out.println(" * ");
                System.out.println(" / ");


                calc = new Calculator(scan, false); //isSingle == false
                return calc;

            case 3: {
                System.out.println("\nRoundedCalculator");//при втором пункте меню выводится калькулятор,который задает кол-во цифр после запятой и округляет результат
                Calculator r_calculator = new Calculator();//обьявляем обьект второго калькулятора
                r_calculator.roundcalc();
            }//также и метод round,который задает кол-во цифр после запятой,а затем округляет результат


            default:

                return menu(scan);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Calculator calculator = menu(scan);
        printResult(calculator, scan);

    }
}

