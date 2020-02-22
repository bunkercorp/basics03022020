package FractionPacage;

public class FractionNumber {
    private long numerator;
    private long denominator;

    public FractionNumber(long numeratorInit, long denominatorInit) {
        if(denominatorInit==0)
            throw new RuntimeException("Denominator equals to zero");
        this.numerator = numeratorInit / nod(numeratorInit, denominatorInit);
        this.denominator = denominatorInit / nod(numeratorInit, denominatorInit);
    }

    public FractionNumber add(FractionNumber inputFN) {
        // a/b + c/d
        long a = this.numerator;
        long b = this.denominator;
        long c = inputFN.numerator;
        long d = inputFN.denominator;
        long nok = nok(b, d);
        long newNumerator = a * (nok / b) + c * (nok / d);
        return new FractionNumber(newNumerator, nok);
    }

    public FractionNumber subtract(FractionNumber inputFN) {
        long a = inputFN.numerator;
        long b = inputFN.denominator;
        FractionNumber temp = new FractionNumber((-1) * a, b);
        return this.add(temp);
    }

    private static long nod(long a, long b) {
        if (a < 0) a *= -1;
        if (b < 0) b *= -1;

        if (b == 0) {
            return a;
        } else {
            return nod(b, a % b);
        }
    }

    private static long nok(long a, long b) {
        return (a * b) / nod(a, b);
    }

    public FractionNumber multiply(FractionNumber inputFN) {
        long a = this.numerator;
        long b = this.denominator;
        long c = inputFN.numerator;
        long d = inputFN.denominator;
        return new FractionNumber(a * c, b * d);
    }

    public FractionNumber divide(FractionNumber inputFN) {
        long a = this.numerator;
        long b = this.denominator;
        long c = inputFN.numerator;
        long d = inputFN.denominator;
        return new FractionNumber(a * d, b * c);
    }

    @Override
    public String toString() {
        if (this.numerator % this.denominator == 0)
            return String.format("%d", this.numerator / this.denominator);
        if (this.numerator >= this.denominator || this.numerator * (-1) >= this.denominator)
            if (this.numerator < 0)
                return String.format("%d %d/%d", this.numerator / this.denominator, this.numerator * (-1) - this.denominator * (this.numerator * (-1) / this.denominator), this.denominator);
            else
                return String.format("%d %d/%d", this.numerator / this.denominator, this.numerator - this.denominator * (this.numerator / this.denominator), this.denominator);
        return String.format("%d/%d", this.numerator, this.denominator);
    }
}
