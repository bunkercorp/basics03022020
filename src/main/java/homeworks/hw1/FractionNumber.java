package homeworks.hw1;

public class FractionNumber {
    private long numerator;
    private long denominator;

    public FractionNumber(long numerator, long denominator) {
        if (denominator == 0) {
            throw new RuntimeException("Denominator equals to zero");
        }
        if (denominator < 0) {
            numerator *= -1;
            denominator *= -1;
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public FractionNumber add(FractionNumber number) {
        return new FractionNumber(this.numerator * number.denominator + number.numerator * this.denominator,
                this.denominator * number.denominator);
    }

    public FractionNumber sub(FractionNumber number) {
        return new FractionNumber(this.numerator * number.denominator - number.numerator * this.denominator,
                this.denominator * number.denominator);
    }

    public FractionNumber mply(FractionNumber number) {
        return new FractionNumber(this.numerator * number.numerator,
                this.denominator * number.denominator);
    }

    public FractionNumber divide(FractionNumber number) {
        return new FractionNumber(this.numerator * number.denominator,
                this.denominator * number.numerator);
    }

    private void reduceFractionNumbers(long numerator, long denominator) { //found in the internet)
        int count = 0;
        for (int i = 2; i <= numerator; i++) {
            if (numerator % i == 0) {
                for (int j = 2; j <= denominator; j++) {
                    if (denominator % j == 0 && j == i) {
                        count = j;
                    }
                }
            }
        }
        if (count != 0) {
            this.denominator = denominator / count;
            this.numerator = numerator / count;
        } else {
            this.denominator = denominator;
            this.numerator = numerator;
        }
    }

    @Override
    public String toString() {
        if (numerator < denominator) {
            return String.format("%d/%d", numerator, denominator);
        } else if (numerator > denominator && denominator != 1) {
            return String.format("%d %d/%d",
                    numerator / denominator, numerator % denominator, denominator);
        } else {
            return String.format("%d", numerator / denominator);
        }
    }
}


