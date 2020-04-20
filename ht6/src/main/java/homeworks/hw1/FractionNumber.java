package main;

public class FractionNumber {
    long numerator;
    long denominator;

    public FractionNumber (long numerator, long denominator){
        if (denominator == 0) throw new RuntimeException("Denominator equals to zero"); else this.denominator = denominator;
        this.numerator = numerator;
        this.toSimple();
    } //constructor 1

    public FractionNumber (long numerator){
        this.numerator = numerator;
        this.denominator = 1;
    } //constructor 2

    public FractionNumber (){
        this.denominator = 1;
        this.numerator = 0;
    } //constructor 3

    public FractionNumber add ( FractionNumber arg){
        FractionNumber result = new FractionNumber();
        toCommonDenominator(this, arg);
        result.denominator = this.denominator;
        result.numerator = this.numerator + arg.numerator;
        result.toSimple();
        this.toSimple();
        arg.toSimple();
        return(result);
    } //add

    public FractionNumber subtract (FractionNumber arg){
        FractionNumber result = new FractionNumber();
        toCommonDenominator(this, arg);
        result.denominator = this.denominator;
        result.numerator = this.numerator - arg.numerator;
        result.toSimple();
        this.toSimple();
        arg.toSimple();
        return(result);
    } //subtract

    public FractionNumber multiply (FractionNumber arg){
        FractionNumber result = new FractionNumber();
        result.denominator = this.denominator * arg.denominator;
        result.numerator = this.numerator * arg.numerator;
        result.toSimple();
        return(result);
    } //multiply

    public FractionNumber divide (FractionNumber arg){
        FractionNumber result = new FractionNumber();
        result.denominator = this.denominator * arg.numerator;
        result.numerator = this.numerator * arg.denominator;
        result.toSimple();
        return(result);
    } //divide

    @Override
    public String toString() {
        String str = "";
        if (this.numerator == 0) str = "0"; else
        if (this.denominator == 1) str = String.format("%d", this.numerator); else
        if ((this.denominator != 1) & (this.numerator != 0) & (this.numerator/this.denominator == 0)) str = String.format("%d/%d", this.numerator, this.denominator); else
        if (this.numerator/this.denominator != 0)
            str = String.format("%d %d/%d",
                    this.numerator/this.denominator,
                    (this.numerator % this.denominator < 0) ? -(this.numerator % this.denominator) : (this.numerator % this.denominator),
                    this.denominator);
        return (str);
    }

    private void toSimple() {
        if (this.denominator < 0) {this.numerator *= -1; this.denominator *= -1;}
        long greatest_common_factor = 1;
        for (int i = 1; ((i <= this.numerator) || (i <= this.denominator)); i++) if ((this.denominator % i == 0) && (this.numerator % i == 0)) greatest_common_factor = (i > greatest_common_factor) ? i : greatest_common_factor;
        this.numerator /= greatest_common_factor;
        this.denominator /= greatest_common_factor;
    } //toSimple

    private static void toCommonDenominator(FractionNumber arg1, FractionNumber arg2){
        long arg2Den = arg2.denominator;
        arg2.denominator = arg2.denominator * arg1.denominator;
        arg2.numerator = arg2.numerator * arg1.denominator;
        arg1.numerator = arg2Den * arg1.numerator;
        arg1.denominator = arg2Den * arg1.denominator;
    } //toCommonDenominator
} //class FractionNumber

