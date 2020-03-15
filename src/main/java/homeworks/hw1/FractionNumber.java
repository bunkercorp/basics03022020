package homeworks.hw1;

public class FractionNumber {
    private final long numerator;
    private final long denumerator;

    public FractionNumber(long num, long denum){
        if ( denum == 0) {
            throw new RuntimeException("Denominator equals to zero");
        }

        if (denum < 0) {
            num = -num;
            denum = -denum;
        }
        int[] dividors = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37,
                41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83,
                89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139,
                149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199};
        for(int i = 0; i<dividors.length && (num >= dividors[i] || denum >= dividors[i]); i++) {
            int div = dividors[i];
            if(num % div == 0 && denum % div == 0) {
                num = num / div;
                denum = denum / div;
                i--;
            }
        }
        numerator = num;
        denumerator = denum;
    }
    public FractionNumber add(FractionNumber other){
        return new FractionNumber((numerator * other.denumerator) + (other.numerator * denumerator),
                                denumerator * other.denumerator);
    }
    public FractionNumber subtract(FractionNumber other){
        return new FractionNumber((numerator * other.denumerator) - (other.numerator * denumerator),
                denumerator * other.denumerator);
    }
    public FractionNumber multiply(FractionNumber other){
        return new FractionNumber(numerator * other.numerator, denumerator * other.denumerator);
    }
    public FractionNumber divide(FractionNumber other){
        return new FractionNumber(numerator * other.denumerator, other.numerator * denumerator);
    }

    @Override
    public String toString() {
        if (denumerator == 1) {
            return String.format("%d", numerator);
        } else if (Math.abs(numerator) > denumerator) {
            return String.format("%d %d/%d", numerator / denumerator, Math.abs(numerator) % denumerator, denumerator);
        } else {
            return String.format("%d/%d", numerator, denumerator);
        }
    }
}
