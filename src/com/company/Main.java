package com.company;

import java.util.Scanner;

public class Main{
    public static void printResult(Calculator calculator, Scanner scan){
        System.out.print("Результат: ");
        System.out.println(calculator.calculate());
    }

    public static Calculator menu(Scanner scan){
        System.out.println("Доступны следующие операции:");
        System.out.println("1) sin,cos,log");
        System.out.println("2) simple calculator");
        System.out.println("3) rounded calculator");
        System.out.print("Выберите тип операции: ");
        int choise = scan.nextInt();
        Calculator calc;
        System.out.println();
        switch (choise){
            case (1):
                System.out.println("Доступны следующие операции:");
                System.out.println("1) sin");
                System.out.println("2) cos ");
                System.out.println("3) log");
                System.out.println("4) sin to Degrees");
                System.out.println("5) cos to Degrees");
                calc = new Calculator(scan, true); //isSingle == true
                return calc;

            case (2):
                System.out.println("Доступны следующие операции:");
                System.out.println(" + (сложение)");
                System.out.println(" - (вычитание)");
                System.out.println(" * (умножение)");
                System.out.println(" / (деление)");


                calc = new Calculator(scan, false); //isSingle == false
                return calc;

            case (3): {
                System.out.println("\nRoundedCalculator");//при втором пункте меню выводится калькулятор,который задает кол-во цифр после запятой и округляет результат
                Calculator r_calculator = new Calculator();//обьявляем обьект второго калькулятора
                r_calculator.roundcalc();

                calc = new Calculator(scan, false); //isSingle == false
                return calc;
            }
            default:
                System.out.println("Некорректный ввод. Повторите ещё раз: ");
                return menu(scan);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Calculator calculator = menu(scan);
        printResult(calculator, scan);

    }
}


