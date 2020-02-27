package homeworks.hw1;

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
            long gcd = Math.abs(reduce(this.numerator, this.denominator));
            System.out.println(this.numerator + " - " + this.denominator);
            this.numerator = this.numerator / gcd;
            this.denominator = Math.abs(this.denominator / gcd);
            System.out.println(this.numerator + " - " + this.denominator);

        }

    }

    public FractionNumber add(FractionNumber fraction) {
        long a1 = this.numerator;
        long a2 = this.denominator;
        long b1 = fraction.numerator;
        long b2 = fraction.denominator;
        System.out.println(a1 + " - " + b1+"  "+a2+" - "+b2);
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
        //System.out.println(a1 + "-" + b1+"  "+a2+"-"+b2);
        return new FractionNumber((a1 * b1), (a2 * b2));
    }

    public FractionNumber divide(FractionNumber fraction) {
        long a1 = this.numerator;
        long a2 = this.denominator;
        long b1 = fraction.numerator;
        long b2 = fraction.denominator;
        System.out.println(a1 + "- " + b1+"  "+a2+" - "+b2+"   "+(a1 * b2));
        if ( (a2 * b1) < 0) {
            return new FractionNumber(-1 * (a1 * b2), (a2 * b1));
        } else {
            return new FractionNumber((a1 * b2), (a2 * b1));
        }

    }

    @Override
    public String toString() {
        if (this.denominator == 1) {
        return String.valueOf(this.numerator);
        } else if (Math.abs(this.numerator) > Math.abs(this.denominator)) {
            long intpart = this.numerator / this.denominator;
            long fraction = Math.abs(this.numerator % this.denominator);
            return intpart + " " + fraction + "/" + this.denominator;
        } else return this.numerator + " / " + this.denominator;
    }

    /*greatest common denominator */
    private static long reduce(long a, long b) {
        // this.sign = (this.numerator < 0) || (this.denominator < 0) ? -1 : 1;
        return b == 0 ? a : reduce(b, a % b);
    }


}
