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
    public static void main(String[] args) {
        System.out.println(rotate(896219342));
    }

    public static long rotate(long number) {
        int numberLength = 1;
        for (int i = 1; i < Math.ceil(Math.log10(number)); i++) {
            numberLength *= 10;
        }
        number = number / numberLength + (number % numberLength) * 10;
        long biggestNumber = number;
        for (int i = 0; i < 2; i++) {
            // тут может быть ArithmeticException по хорошему взть в try/cathc
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
