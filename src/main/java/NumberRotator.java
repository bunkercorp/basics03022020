/*

Take a number: 56789. Rotate left, you get 67895.
Keep the first digit in place and rotate left the other digits: 68957.
Keep the first two digits in place and rotate the other ones: 68579.
Keep the first three digits and rotate left the rest: 68597. Now it is over since keeping the first four it remains only one digit which rotated is itself.

You have the following sequence of numbers:
56789 -> 67895 -> 68957 -> 68579 -> 68597
and you must return the greatest: 68957.

Calling this function max_rot (or maxRot or ... depending on the language)
max_rot(56789) should return 68957

*/


public class NumberRotator {

    public static long rotate (long number) {
         int length = String.valueOf(number).length();
         String phrase = String.valueOf(number);
         String[] rotatearr = new String [length];
         String endnumber = "";
         String tmp = "";
            System.out.println(phrase);
            for (int i=0;i<=length-1;i++) {
                rotatearr[i] = "";

                System.out.println(phrase.charAt(i));

                for (int j = i+1; j<=length-1;j++) {
                    //System.out.println(j);
                    rotatearr[i] += phrase.charAt(j);
                    tmp +=phrase.charAt(j);
                }
                for(int j=0; j< i+1; j++){
                    rotatearr[i] += phrase.charAt(j);
                }
               // tmp +=phrase.charAt(i);
                System.out.println( "tmp:"+tmp+" - "+rotatearr[i] );
                tmp = "";
            }
        return number;
    }
}
