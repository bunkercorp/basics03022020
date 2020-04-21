
public class FractionNumber {

    Long numerator;
    Long denominator;

    public FractionNumber(Long numerator, Long denominator) {

        if (denominator == 0)
            throw new RuntimeException("Denominator equals to zero");

        this.numerator = numerator;
        this.denominator = denominator;

        reduce();
    }

    private FractionNumber reduce() {

        Long num = numerator;
        Long denom = denominator;

        while (denom != 0) {
            Long tmp = num % denom;
            num = denom;
            denom = tmp;
        }

        numerator /= num;
        denominator /= num;

        return this;
    }

    @Override
    public String toString() {

        String result = "";
        if (denominator == 1) {
            result = String.format("%d", numerator); //для дробей со знаменателем 1
        } else if (numerator > denominator) {
            result = String.format("%d %d/%d", numerator / denominator, numerator % denominator, denominator);
            ; //для неправильных дробей
        } else {
            result = String.format("%d/%d", numerator, denominator); //для правильных дробей
        }
        return result;
    }

    public FractionNumber add(FractionNumber fn) {
        Long d = this.denominator;
        this.denominator *= fn.denominator;
        this.numerator *= fn.denominator;
        fn.denominator *= d;
        fn.numerator *= d;

        this.numerator += fn.numerator;
        reduce();
        return this;
    }

    public FractionNumber subtract(FractionNumber fn) {

        Long d = this.denominator;
        this.denominator *= fn.denominator;
        this.numerator *= fn.denominator;

        fn.denominator *= d;
        fn.numerator *= d;

        this.numerator -= fn.numerator;
        reduce();
        return this;
    }

    public FractionNumber multiply(FractionNumber fn) {

        this.numerator *= fn.numerator;
        this.denominator *= fn.denominator;
        reduce();
        return this;
    }


    public FractionNumber divide(FractionNumber fn) {

        this.numerator *= fn.denominator;
        this.denominator *= fn.numerator;
        reduce();
        return this;
    }

}
