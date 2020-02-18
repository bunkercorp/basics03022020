/*
Jaden Smith, the son of Will Smith, is the star of films such as The Karate Kid (2010) and After Earth (2013).
Jaden is also known for some of his philosophy that he delivers via Twitter.
When writing on Twitter, he is known for almost always capitalizing every word.

Your task is to convert strings to how they would be written by Jaden Smith.
The strings are actual quotes from Jaden Smith, but they are not capitalized in the same way he originally typed them.

Example:

Not Jaden-Cased: "How can mirrors be real if our eyes aren't real"
Jaden-Cased:     "How Can Mirrors Be Real If Our Eyes Aren't Real"

Note that the Java version expects a return value of null for an empty string or null.

*/
public class JadenCase {

  public static String toJadenCase(String phrase) {
      String convertedString = phrase;
        if(convertedString != null && phrase.length() !=0 ) {
            convertedString = "";
            boolean flag = false;
            for (int index = 0; index < phrase.length(); ) {
                if (index == 0 || flag == true) {
                    convertedString += (String.valueOf(phrase.charAt(index))).toUpperCase();
                    flag = false;
                } else if (phrase.charAt(index) == ' ') {
                    convertedString += String.valueOf(phrase.charAt(index));
                    flag = true;
                } else {
                    convertedString += String.valueOf(phrase.charAt(index)).toLowerCase();
                }
                index++;
            }
        }
        else {
            convertedString = null;
        }
      System.out.println( convertedString );
      return convertedString;
    }
}
