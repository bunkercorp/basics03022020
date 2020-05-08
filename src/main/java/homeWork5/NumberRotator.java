package homeWork5;/*

Take a number: 56789. Rotate left, you get 67895.
Keep the first digit in place and rotate left the other digits: 68957.
Keep the first two digits in place and rotate the other ones: 68579.
Keep the first three digits and rotate left the rest: 68597.
 Now it is over since keeping the first four it remains only one digit which rotated is itself.

You have the following sequence of numbers:
56789 -> 67895 -> 68957 -> 68579 -> 68597
and you must return the greatest: 68957.

Calling this function max_rot (or maxRot or ... depending on the language)
max_rot(56789) should return 68957

*/


public class NumberRotator {
    public static void main(String[] args) {
        System.out.println(rotate(-1341));
    }

    /*
    Решение без использования перевода в String на примере числа 56789 из условия
    1. Формула number = number / numberLength + (number % numberLength) * 10;
    number = 56789;
    numberLength = вычисляю в каком десятке находится число
    И так вернемся к формуле а вернее к ее первой части
    number / numberLength
    56789 / 10000 = 5,6789 но так как это int то получаем нашу 5
    Вторая часть формулы
    (number % numberLength) * 10
    (56789 % 10000) * 10 = 67890
    Теперь мы просто складываем 1 с вторйо частью и получаем 67895
    Еще нам необходимо количество итераций
    Math.ceil(Math.log10(number))
    Теперб вторая формула
     number = (number / numberLength) * numberLength + ((number % (numberLength / 10)) * 10)
                    + (number - (number / numberLength * numberLength)) / (numberLength / 10);
     Рассмотрим первую часть
     (number / numberLength) * numberLength
     (67890 / 10000) * 10000 = 60000
     Вторая часть
     ((number % (numberLength / 10)) * 10)
     ((67890 % (10000 / 10)) * 10 ) = 8950
     Прибавляем 1 и 2 части 60000 + 8950 = 68950 нам осталось только получить 7 и положить в конец
     Третья часть
       (number - (number / numberLength * numberLength)) / (numberLength / 10)
       (67890 - (67890 / 10000 * 10000)) / (10000 / 10);
       (67890 - 60000) / 1000 = 7
       Теперь мы получили 7 осталось это прибавить к 68950 = 68957
       И так мы можем делать стокль раз ксколько длина нашего числа уменьшая numberLength
       каждый раз / 10;
    */
    public static long rotate(long number) {
        int numberLength = 1;
        number = Math.abs(number);
        // вычисляю в каком десятке находится число
        // numberLength = Math.pow(10, Math.ceil(Math.log10(number))), и не нужны циклы
        for (int i = 1; i < Math.ceil(Math.log10(number)); i++) {
            numberLength *= 10;
        }
        // вообще, мутирование входных параметров является вредной привычкой. Ну ,или ты действительно знаешь что делаешь.
        number = number / numberLength + (number % numberLength) * 10;
        long biggestNumber = number;
        for (int i = 0; i < Math.ceil(Math.log10(number)) - 1; i++) {
          // здесь я вижу дубликаты вычислений number / numberLength и  numberLength / 10
            // так и просятся в отдельные переменные
            number = (number / numberLength) * numberLength + ((number % (numberLength / 10)) * 10)
                    + (number - (number / numberLength * numberLength)) / (numberLength / 10);
            numberLength /= 10;
            if (number > biggestNumber) {
                biggestNumber = number;
            }
        }
        return biggestNumber;
    }
}
