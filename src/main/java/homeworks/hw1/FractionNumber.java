package homeworks.hw1;

public class FractionNumber {
    private long num;
    private long den;

    public FractionNumber(long num, long den) {
        if (den == 0) {
            throw new RuntimeException("Denominator equals to zero");
        }
        if (den < 0) {
            num = -num;
            den = -den;
        }
        this.num = num;
        this.den = den;
        // Этот код используется только здесь. Может, имеет смысл передизайнить слегка?
        reduce();
    }

    private long calculate(long num, long den) {
        if (num % den == 0) {
            return den;
        }
        return calculate(den, num % den);
    }

    private void reduce() {
        long calculate = calculate(num, den);
        num /= calculate;
        den /= calculate;
    }

    public FractionNumber add(FractionNumber r) {
        return new FractionNumber(this.num * r.den + r.num * this.den, this.den * r.den);
    }

    public FractionNumber subtract(FractionNumber r) {
        return new FractionNumber(this.num * r.den - r.num * this.den, this.den * r.den);
    }

    public FractionNumber multiply(FractionNumber r) {
        return new FractionNumber(this.num * r.num, this.den * r.den);
    }

    public FractionNumber divide(FractionNumber r) {
        return new FractionNumber(this.num * r.den, this.den * r.num);
    }

    @Override
    public String toString() {
        String fraction = "";
        if (FractionNumber.this.num < FractionNumber.this.den && FractionNumber.this.num > 0) {
            fraction = String.format("%d/%d", num, den);
            // FractionNumber.this.num % FractionNumber.this.den == 0 так и просится в переменную.
            // тогда бы это выглядело как if(var){} else if(num > den && !var){}
            // сейчас же в коде есть лишнее вычисление
        } else if (FractionNumber.this.num % FractionNumber.this.den == 0) {
            fraction = String.format("%d",num / den);
        } else if (FractionNumber.this.num > FractionNumber.this.den && FractionNumber.this.num % FractionNumber.this.den != 0) {
            fraction = String.format("%d %d/%d", num / den, num % den, den);
        } else if (FractionNumber.this.num < 0 && Math.abs(FractionNumber.this.num) < FractionNumber.this.den) {
            fraction = String.format("%d/%d", num, den);
        }
        return fraction;
    }
}