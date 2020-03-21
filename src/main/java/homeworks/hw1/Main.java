package homeworks.hw1;

public class Main {
    public static void main(String[] args) {
        FractionNumber number = new FractionNumber(2,4);
        FractionNumber number1 = new FractionNumber(0,4);
        FractionNumber number2 = number.divide(number1);
        System.out.println(number2);
    }
}
