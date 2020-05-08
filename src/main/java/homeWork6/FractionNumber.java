package homeWork6;

public class FractionNumber {
    private long numerator;
    private long denominator;

    public FractionNumber(long numerator, long denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("Denominator equals to zero");
        }
        fractionReduction(numerator, denominator);
    }

    public FractionNumber add(FractionNumber number) {
        return (number.denominator == this.denominator) ?
                new FractionNumber(this.numerator + number.numerator,
                        this.denominator) :
                new FractionNumber(this.numerator * number.denominator
                        + number.numerator * this.denominator,
                        this.denominator * number.denominator);
    }
// тебе не хотелось сочинить общую для этих двух методов логику?
    public FractionNumber subtract(FractionNumber number) {
        return (number.denominator == this.denominator) ?
                new FractionNumber(this.numerator - number.numerator,
                        this.denominator) :
                new FractionNumber(this.numerator * number.denominator
                        - number.numerator * this.denominator,
                        this.denominator * number.denominator);
    }

    public FractionNumber multiply(FractionNumber number) {
        return new FractionNumber(this.numerator * number.numerator,
                this.denominator * number.denominator);
    }

    public FractionNumber divide(FractionNumber number) {
        return new FractionNumber(this.numerator * number.denominator,
                this.denominator * number.numerator);
    }
// этот сервис инстанциям не нужен, он нужен классу
    private void fractionReduction(long numerator, long denominator) {
        int factor1 = 0;
        for (int i = 2; i <= numerator; i++) {
            if (numerator % i == 0) {
                for (int j = 2; j <= denominator; j++) {
                    if (denominator % j == 0 && j == i) {
                        factor1 = j;
                    }
                }
            }
        }
        /*
        * this.denominator = denominator
        * this.numerator = numerator
        * if(factor1 != 0){
         * this.denominator*= factor1
         * this.numerator*= factor1
        * }
        * */
        if (factor1 != 0) {
            this.denominator = denominator / factor1;
            this.numerator = numerator / factor1;
        } else {
            this.denominator = denominator;
            this.numerator = numerator;
        }
    }

    @Override
    public String toString() {
        if (numerator < denominator) {
            // игнорируем условие ДЗ по мелочам, да? ;)
            return String.format("numerator : %d, denominator : %d", numerator, denominator);
        } else if (numerator > denominator && denominator != 1) {
            return String.format("integer : %d, numerator : %d, denominator : %d",
                    numerator / denominator, numerator % denominator, denominator);
        } else {
            return String.format("integer: %d", numerator / denominator);
        }
    }
}
