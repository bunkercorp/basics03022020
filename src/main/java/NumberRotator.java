/*

Take a number: 56789. Rotate left, you get 67895.
Keep the first digit in place and rotate left the other digits: 68957.
Keep the first two digits in place and rotate the other ones: 68579.
Keep the first three digits and rotate left the rest: 68597. Now it is over since keeping the first four it remains only one digit which rotated is itself.

You have the following sequence of numbers:
56789 -> 67895 -> 68957 -> 68579 -> 68597
and you must return the greatest: 68957.

Calling this function max_rot (or maxRot or ... depending on the language)
max_rot(56789) should return 68957

*/


public class NumberRotator {

    public static long rotate(long n) {
        // n = -1 ;)
        String value = String.valueOf(n);
        // при n < 10 этот код выполнится. Что не имеет ни малейшего смысла.
        for (int i = 0; i < value.length() - 1; i++) {
            value = value.substring(0, i) + value.substring(i + 1) + value.charAt(i);
            if (Long.parseLong(value) > n) {
                // мутировать входной параметр - плохая привычка. Или ты точно знаешь что делаешь.
                n = Long.parseLong(value);
            }
        }

        return n;

    }


    public static void main(String[] argv) {

        System.out.println(rotate(56789));

    }
}
