package homeworks.hw1;

public class Main {
    public static void main(String[] args) {
        FractionNumber num = new FractionNumber(6,9);
        System.out.println(num);
        System.out.println(new FractionNumber(-6,-9));
        System.out.println(new FractionNumber(6,-9));
        System.out.println(new FractionNumber(-6,9));
        FractionNumber tmp = num.add(new FractionNumber(1, 3));
        System.out.println(tmp);
        tmp = num.subtract(new FractionNumber(2,3));
        System.out.println(tmp);
        tmp = num.multiply(new FractionNumber(0, 78));
        System.out.println(tmp);
        tmp = num.divide(new FractionNumber(0, 890));
        System.out.println(tmp);
    }
}
