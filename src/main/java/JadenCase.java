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
    public static String toJadenCase (String str) {
        String result = "";
        if ((str != null) && (str.length() > 0)) {
            for (int i = 0; i < str.length(); i++) {
                char current = str.charAt(i);
                if (((i == 0) || ((i > 0) && ((str.charAt(i - 1) == ' ') || (str.charAt(i - 1) == '\t')))) && (current >= 'a') && (current <= 'z')) current = (char) ((int) current - 32);
                else if ((i > 0) && (str.charAt(i - 1) != ' ') && (current >= 'A') && (current <= 'Z')) current = (char) ((int) current + 32);
                result = result + current;
            }
        } else result =  null;
        return(result);
    }//toJadenCase
}
