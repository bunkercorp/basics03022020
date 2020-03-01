import homeworks.hw1.FractionNumber;

public class FractionNumberTest {

    public static void main(String[] args) {

        FractionNumber num1 = new FractionNumber(1L, 2L);
        FractionNumber num2 = new FractionNumber(1L, 1L);
        printResult(num1, num2);

        FractionNumber num3 = new FractionNumber(2L, 1L);
        FractionNumber num4 = new FractionNumber(1L, 2L);
        printResult(num3, num4);

        FractionNumber num5 = new FractionNumber(1L, 2L);
        FractionNumber num6 = new FractionNumber(1L, 0L);
        printResult(num5, num6);

    }

    public static void printResult(FractionNumber num1, FractionNumber num2) {
        System.out.println(num1.add(num2).toString());
        System.out.println(num1.subtract(num2).toString());
        System.out.println(num1.multiply(num2).toString());
        System.out.println(num1.divide(num2).toString());
    }

}
