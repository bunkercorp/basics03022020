package homeworks.hw1;

public class FractionNumber {

    private long numerator;
    private long denominator;


    public FractionNumber(long numerator, long denominator) {
        if (denominator == 0)
            throw new RuntimeException("Denominator equals to zero");

        this.numerator = numerator;
        this.denominator = denominator;
        reduce();
    }

    private static long calculate(long numerator, long denominator) {
        if (numerator % denominator == 0) {
            return denominator;
        }
        return calculate(denominator, numerator % denominator);
    }

    // ну уж нет. Сервис редюса должен был бы предоставлять сам класс, инстанциям этот метод ни к чему.
    private void reduce() {
        long calculate = calculate(numerator, denominator);
        numerator /= calculate;
        denominator /= calculate;
    }

    public FractionNumber add(FractionNumber fractionNumber) {
        long resultNumerator = (numerator * fractionNumber.denominator) + (fractionNumber.numerator * denominator);
        long resultDenominator = denominator * fractionNumber.denominator;
        return new FractionNumber(resultNumerator, resultDenominator);
    }

    public FractionNumber subtract(FractionNumber fractionNumber) {
        // этот вот минус как-то сильно похож на плюс в методе add. Похоже на общую логику
        long resultNumerator = (numerator * fractionNumber.denominator) - (fractionNumber.numerator * denominator);
        long resultDenominator = denominator * fractionNumber.denominator;
        return new FractionNumber(resultNumerator, resultDenominator);
    }

    public FractionNumber multiply(FractionNumber fractionNumber) {
        long resultNumerator = numerator * fractionNumber.numerator;
        long resultDenominator = denominator * fractionNumber.denominator;
        return new FractionNumber(resultNumerator, resultDenominator);
    }

    public FractionNumber divide(FractionNumber fractionNumber) {
        long resultNumerator = numerator * fractionNumber.denominator;
        long resultDenominator = denominator * fractionNumber.numerator;
        return new FractionNumber(resultNumerator, resultDenominator);
    }

    @Override
    public String toString() {
        // нуловым результат не будет вообще никогда. А вот по твоему ифу такого не скажешь
        String result = null;

        if (denominator == 1) {
            result = String.format("%d", numerator);
        } else if ((numerator < denominator && numerator != 0)) {
            result = String.format("%d/%d", numerator, denominator);
        } else if (numerator > denominator) {
            long temp = numerator / denominator;
            if (temp == 0) {
                result = String.format("%d/%d", numerator, denominator);
            } else {
                long tempNumerator = numerator % denominator;
                result = String.format("%d %d/%d", temp, tempNumerator, denominator);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FractionNumber f1 = new FractionNumber(0, 1005000);
        FractionNumber f2 = new FractionNumber(4, 2);
        FractionNumber f3 = f1.add(f2);
        System.out.println("Result of addition of "
                + f1 + " and " + f2 + " is : " + f3);
        f3 = f1.subtract(f2);
        System.out.println("Result of subtraction of "
                + f1 + " and " + f2 + " is : " + f3);
        f3 = f1.divide(f2);
        System.out.println("Result of division of "
                + f1 + " and " + f2 + " is : " + f3);
        f3 = f1.multiply(f2);
        System.out.println("Result of multiplication of "
                + f1 + " and " + f2 + " is : " + f3);
    }
}
