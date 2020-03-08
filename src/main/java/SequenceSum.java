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

    public static String showSequence(int in) {
        String result = "";
        for (int i = 0; i <= in; i++) result = (result.length() == 0) ? result + i : result + '+' + i;
        result = result + " = " + (((in % 2) == 0) ? (in*in/2 + in/2) : (in*(in/2 + 1)));
        return result;
    }//showSequence
}
