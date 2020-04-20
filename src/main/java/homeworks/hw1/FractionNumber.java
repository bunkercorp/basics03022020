package homeworks.hw1;

public class FractionNumber {

    private long numerator;
    private long denominator;

    public FractionNumber(long a, long b) {
        this.numerator = a;
        this.denominator = b;
        // этот иф стоило бы поставить вотпрям в самое начало, так как при этой ситуации не нужно никаких присваиваний, что у тебя вверху
        // на быстродействии не особо скажется, но чистота формы будет выше
        if (this.denominator == 0) {
            throw new RuntimeException("Denominator equals to zero");
        }
        // мне тут даже идея подсвечивает что этот иф не нужен, все сработает и так, так как если верхний иф сработает, то сюда не доходим, а если нет  - то доходим
        if (this.denominator != 0) {
            long gcd = Math.abs(reduce(this.numerator, this.denominator));
            System.out.println(this.numerator + " - " + this.denominator);
            this.numerator = this.numerator / gcd;
            this.denominator = Math.abs(this.denominator / gcd);
            System.out.println(this.numerator + " - " + this.denominator);

        }

    }

    public FractionNumber add(FractionNumber fraction) {
      // а толку с этих присваиваний?
        long a1 = this.numerator;
        long a2 = this.denominator;
        long b1 = fraction.numerator;
        long b2 = fraction.denominator;
        System.out.println(a1 + " - " + b1+"  "+a2+" - "+b2);
        return new FractionNumber(((a1 * b2) + (a2 * b1)), (a2 * b2));
    }
    // тут полшага не хватает ,чтобы общую для add \ subtract логику вывести в приватный метод
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
        //(a1 * b2), (a2 * b1) просятся в отдельные переменные
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
            // String.format . Просто оставлю здесь ;)
            return intpart + " " + fraction + "/" + this.denominator;
        } else return this.numerator + " / " + this.denominator;
    }

    /*greatest common denominator */
    private static long reduce(long a, long b) {
        // this.sign = (this.numerator < 0) || (this.denominator < 0) ? -1 : 1;
        return b == 0 ? a : reduce(b, a % b);
    }


}
