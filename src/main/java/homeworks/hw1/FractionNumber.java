package homeworks.hw1;

public class FractionNumber {

    private long numerator;
    private long denominator;

    public static long gcd(long a, long b) {
        long d = 0;
        if (a < 0) {
            a *= -1;
        }

        if (b < 0) {
            b *= -1;
        }

        while (b != 0 && a != 0) {
            if (a > b) {
                a %= b;
            } else {
                b %= a;
            }
            d = a + b;
        }
        return d;
    }


    public static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    public FractionNumber(long numerator, long denominator) {
        if (numerator == 0) {
            this.numerator = 0;
            this.denominator = 1;
        }
        if (numerator < 0 && denominator < 0) {
            this.numerator = numerator * -1;
            this.denominator = denominator * -1;
        } else if (numerator > 0 && denominator < 0) {
            this.denominator = denominator * -1;
            this.numerator = numerator * -1;
            //throw new RuntimeException("If you want to create negative fraction number, please set \"-\" in numerator");
        } else if (denominator > 0 && numerator != 0) {
            this.numerator = numerator / gcd(numerator, denominator);
            this.denominator = denominator / gcd(numerator, denominator);
        } else if (denominator == 0) {
            throw new RuntimeException("Denominator equals to zero");
        }

    }

    public String toString() {
        String fraction = "";
        if (FractionNumber.this.numerator < FractionNumber.this.denominator && FractionNumber.this.numerator > 0) {
            fraction = numerator + "/" + denominator;
        } else if (FractionNumber.this.numerator % FractionNumber.this.denominator == 0) {
            long result = numerator / denominator;
            fraction = "" + result;
        } else if (FractionNumber.this.numerator > FractionNumber.this.denominator && FractionNumber.this.numerator % FractionNumber.this.denominator != 0) {
            long result = numerator / denominator;
            long result2 = numerator % denominator;
            fraction = result + " " + result2 + "/" + denominator;
        } else if (FractionNumber.this.numerator < 0 && Math.abs(FractionNumber.this.numerator) < FractionNumber.this.denominator) {
            long result = Math.abs(numerator / denominator);
            long result2 = Math.abs(numerator % denominator);
            fraction = numerator + "/" + denominator;
        } else if (FractionNumber.this.numerator < FractionNumber.this.denominator && FractionNumber.this.numerator % FractionNumber.this.denominator != 0) {
            long result = Math.abs(numerator / denominator);
            long result2 = Math.abs(numerator % denominator);
            fraction = "-" + result + " " + result2 + "/" + denominator;
        }

        return fraction;
    }

    public static FractionNumber multiply(FractionNumber a, FractionNumber b) {
        return new FractionNumber(a.numerator * b.numerator, a.denominator * b.denominator);
    }

    public static FractionNumber divide(FractionNumber a, FractionNumber b) {
        if (b.numerator != 0) {
            return new FractionNumber(a.numerator * b.denominator, a.denominator * b.numerator);
        } else {
            throw new RuntimeException("Denominator equals to zero");
        }
    }

    public static FractionNumber add(FractionNumber a, FractionNumber b) {
        return new FractionNumber((lcm(a.denominator, b.denominator) / a.denominator) * a.numerator + (lcm(a.denominator, b.denominator) / b.denominator) * b.numerator, lcm(a.denominator, b.denominator));
    }

    public static FractionNumber subtract(FractionNumber a, FractionNumber b) {
        return new FractionNumber((lcm(a.denominator, b.denominator) / a.denominator) * a.numerator - (lcm(a.denominator, b.denominator) / b.denominator) * b.numerator, lcm(a.denominator, b.denominator));
    }

}
