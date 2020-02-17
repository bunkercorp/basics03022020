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
        String modeSting = null;

        char[] array = null;
               array =  phrase.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (i == 0 || array[i - 1] == ' ' && i < array.length - 1)
                array[i] = Character.toUpperCase(array[i]);
        }
        modeSting = String.valueOf(array);

        return modeSting;
    }
}
