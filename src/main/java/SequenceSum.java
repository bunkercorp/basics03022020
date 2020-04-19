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
        if (value < 0) {
            return value + "<0";
        } else if (value == 0) {
            return value + "=0";
        } else {

            StringBuilder sb = new StringBuilder();
            int sum = 0;
            for (int i = 0; i <= value; i++) {
                sum += i;
                // а если цикл бежал бы с единицы ,а в sb лежал бы "0", то в этом ифе не было бы нужды, так как можно было бы добавить знак равенства после цикла
                if (i == value) {
                   // append(" = ")
                    sb.append(i)
                            .append(" ")
                            .append("=")
                            .append(" ")
                            .append(sum);
                } else {
                    sb.append(i).append("+");
                }
            }
            return sb.toString();
        }
    }
}
