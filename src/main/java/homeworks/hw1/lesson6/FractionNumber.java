package homeworks.hw1.lesson6;

public class FractionNumber {

    private long numerator;
    private long denominator;

    public FractionNumber(long a, long b) {
        this.numerator = a;
        this.denominator = b;
        if (this.denominator == 0) {
            throw new RuntimeException("Denominator equals to zero");
        }
        if (this.denominator != 0) {
            long gcd = reduce(this.numerator, this.denominator);
            this.numerator = this.numerator / gcd;
            this.denominator = this.denominator / gcd;
        }

    }

    public FractionNumber add(FractionNumber fraction) {
        long a1 = this.numerator;
        long a2 = this.denominator;
        long b1 = fraction.numerator;
        long b2 = fraction.denominator;
        System.out.println(b1 + "-" + b2);
        System.out.println(((a1 * b2) + (a2 * b1)) + "-" + (a2 * b2));
        return new FractionNumber(((a1 * b2) + (a2 * b1)), (a2 * b2));
    }

    public FractionNumber subtract(FractionNumber fraction) {
        long a1 = this.numerator;
        long a2 = this.denominator;
        long b1 = fraction.numerator;
        long b2 = fraction.denominator;
        return new FractionNumber(((a1 * b2) - (a2 * b1)), (a2 * b2));
    }

    public FractionNumber multiply(FractionNumber fraction) {
        long a1 = this.numerator;
        long a2 = this.denominator;
        long b1 = fraction.numerator;
        long b2 = fraction.denominator;
        return new FractionNumber((a1 * b1), (a2 * b2));
    }

    public FractionNumber divide(FractionNumber fraction) {
        long a1 = this.numerator;
        long a2 = this.denominator;
        long b1 = fraction.numerator;
        long b2 = fraction.denominator;
        return new FractionNumber((a1 * b2), (a2 * b1));
    }

    @Override
    public String toString() {
        if (this.numerator > this.denominator){
            long intpart = this.numerator/this.denominator;
            long fraction = this.numerator%this.denominator;
            return intpart + " " +fraction+"/"+this.denominator;
        } else if(this.denominator == 1){
            return String.valueOf(this.numerator);
        }
        else return this.numerator + " / " + this.denominator;
    }

    /*greatest common denominator */
    private static long reduce(long a, long b) {
        //if (a < 0) a = -1 * a;
       // if (b < 0) b = -1 * b;
        return b == 0 ? a : reduce(b, a % b);
    }


}
