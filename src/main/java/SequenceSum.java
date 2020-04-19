/*

Description:
We want to generate a function that computes the series starting from 0 and ending until the given number following the sequence:
0 1 3 6 10 15 21 28 36 45 55 ....
which is created by
0, 0+1, 0+1+2, 0+1+2+3, 0+1+2+3+4, 0+1+2+3+4+5, 0+1+2+3+4+5+6, 0+1+2+3+4+5+6+7 etc..

Input: LastNumber
Output: series and result

Example:

Input: 6
Output: 0+1+2+3+4+5+6 = 21

Input: -15
Output: -15 < 0

Input: > 0
Output: 0 = 0

*/

public class SequenceSum {

    public static String showSequence(int value) {

        int sum = 0;
        String result = "";
        if (value < 0) {

            System.out.print(value + " < 0");

            return "";
        }

        for (int i = 0; i <= value; i++) {
           // result = String.format("%s%d", result, i)
            String v = String.valueOf(i);
            result = result.concat(v);
            // предположим, что value = Integer.MAX_VALUE. Этот иф выполнится больше двух миллиардов раз
            // в то время как можно было просто после этого цикла добавить знак равенства и не иметь этой проверки
            if (i != value) {
                result = result.concat("+");
            } else result = result.concat(" = ");

            sum += i;

        }
    // return String.format("%s = %d", result, sum)
        result = result.concat(String.valueOf(sum));

        return result;

    }

    public static void main(String[] argv) {

        System.out.println(showSequence(6));

    }
}


