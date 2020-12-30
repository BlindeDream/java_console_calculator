package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.lang.Math;
import java.util.StringTokenizer;

public class Calculator {
    private DataSet data;
    private SingleDataSet singleData;
    private Scanner scanner;
    private boolean isSingleDataSet;

    public Calculator(Scanner scan, boolean isSingle){
        scanner = scan;
        isSingleDataSet = isSingle;
        if (isSingleDataSet){
            singleData = new SingleDataSet(scanner);
            data = null;
        } else {
            data = new DataSet(scanner);
            singleData = null;
        }
    }

    public Calculator(DataSet newData){
        scanner = newData.getScanner();
        data = newData;
    }

    public Calculator() {

    }


    public double newCalculation(){
        System.out.println("Неверная операция. Повторите ввод. ");
        if (isSingleDataSet){
            singleData = new SingleDataSet(scanner);
        } else {
            data = new DataSet(scanner);
        }
        return calculate();
    }

    public double calculate(){
        if (isSingleDataSet && singleData.isOperationWrong() || !isSingleDataSet && data.isOperationWrong()){
            return newCalculation();
        } else {
            if (isSingleDataSet){
                double num1 = singleData.getNum1();
                switch(singleData.getOperation()){
                    case('1'):
                        return Math.sin(num1);
                    case('2'):
                        return Math.cos(num1);
                    case('3'):
                        return Math.log10(num1);
                    case('4'):
                        return Math.sin(Math.toRadians(num1));
                    case('5'):
                        return Math.cos(Math.toRadians(num1));
                    default:
                        return newCalculation();
                }

            } else {
                double num1 = data.getNum1();
                double num2 = data.getNum2();
                switch(data.getOperation()){
                    case('+'):
                        return num1+num2;
                    case('-'):
                        return num1-num2;
                    case('*'):
                        return num1*num2;
                    case('/'):
                        return num1/num2;

                    default:
                        return newCalculation();
                }
            }
        }
    }
    public void roundcalc() {//в RoundedCalculator мы вводим действие,затем кол-во цифр после запятой и, сначала, считаем результат,

        BufferedReader d = new BufferedReader(new InputStreamReader(System.in));
        String sIn;

        try {
            System.out.println("Введте выражение для расчета. Поддерживаются цифры, операции +,-,*,/ и приоритеты в виде скобок ( и ):");
            sIn = d.readLine();
            sIn = opn(sIn);
            System.out.println(calculate(sIn));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Преобразовать строку в обратную польскую нотацию
     *
     * @param sIn Входная строка
     * @return Выходная строка в обратной польской нотации
     */
    private static String opn(String sIn) throws Exception {
        StringBuilder sbStack = new StringBuilder(""), sbOut = new StringBuilder("");

        char cIn, cTmp;

        for (int i = 0; i < sIn.length(); i++) {
            cIn = sIn.charAt(i);
            if (isOp(cIn)) {
                while (sbStack.length() > 0) {
                    cTmp = sbStack.substring(sbStack.length() - 1).charAt(0);
                    if (isOp(cTmp) && (opPrior(cIn) <= opPrior(cTmp))) {
                        sbOut.append(" ").append(cTmp).append(" ");
                        sbStack.setLength(sbStack.length() - 1);
                    } else {
                        sbOut.append(" ");
                        break;
                    }
                }
                sbOut.append(" ");
                sbStack.append(cIn);
            } else if ('(' == cIn) {
                sbStack.append(cIn);
            } else if (')' == cIn) {
                cTmp = sbStack.substring(sbStack.length() - 1).charAt(0);
                while ('(' != cTmp) {
                    if (sbStack.length() < 1) {
                        throw new Exception("Ошибка разбора скобок. Проверьте правильность выражения.");
                    }
                    sbOut.append(" ").append(cTmp);
                    sbStack.setLength(sbStack.length() - 1);
                    cTmp = sbStack.substring(sbStack.length() - 1).charAt(0);
                }
                sbStack.setLength(sbStack.length() - 1);
            } else {
                // Если символ не оператор - добавляем в выходную последовательность
                sbOut.append(cIn);
            }
        }

        // Если в стеке остались операторы, добавляем их в входную строку
        while (sbStack.length() > 0) {
            sbOut.append(" ").append(sbStack.substring(sbStack.length() - 1));
            sbStack.setLength(sbStack.length() - 1);
        }

        return sbOut.toString();
    }

    /**
     * Функция проверяет, является ли текущий символ оператором
     */
    private static boolean isOp(char c) {
        switch (c) {
            case '-':
            case '+':
            case '*':
            case '/':

                return true;
        }
        return false;
    }

    /**
     * Возвращает приоритет операции
     *
     * @param op char
     * @return byte
     */
    private static byte opPrior(char op) {
        switch (op) {
            case '*':
            case '/':
                return 2;
        }
        return 1; // Тут остается + и -
    }

    /**
     * Считает выражение, записанное в обратной польской нотации
     *
     * @param sIn
     * @return double result
     */
    private static double calculate(String sIn) throws Exception {
        double dA = 0, dB = 0;
        String sTmp;
        Deque<Double> stack = new ArrayDeque<Double>();
        StringTokenizer st = new StringTokenizer(sIn);
        while (st.hasMoreTokens()) {
            try {
                sTmp = st.nextToken().trim();
                if (1 == sTmp.length() && isOp(sTmp.charAt(0))) {
                    if (stack.size() < 2) {
                        throw new Exception("Неверное количество данных в стеке для операции " + sTmp);
                    }
                    dB = stack.pop();
                    dA = stack.pop();
                    switch (sTmp.charAt(0)) {
                        case '+':
                            dA += dB;
                            break;
                        case '-':
                            dA -= dB;
                            break;
                        case '/':
                            dA /= dB;
                            break;
                        case '*':
                            dA *= dB;
                            break;


                        default:
                            throw new Exception("Недопустимая операция " + sTmp);
                    }
                    stack.push(dA);
                } else {
                    dA = Double.parseDouble(sTmp);
                    stack.push(dA);
                }
            } catch (Exception e) {
                throw new Exception("Недопустимый символ в выражении");
            }
        }

        if (stack.size() > 1) {
            throw new Exception("Количество операторов не соответствует количеству операндов");
        }

        return stack.pop();
    }

}


