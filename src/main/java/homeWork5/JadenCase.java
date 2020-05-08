package homeWork5;/*
Jaden Smith, the son of Will Smith, is the star of films such as The Karate Kid (2010)
 and After Earth (2013).
Jaden is also known for some of his philosophy that he delivers via Twitter.
When writing on Twitter, he is known for almost always capitalizing every word.

Your task is to convert strings to how they would be written by Jaden Smith.
The strings are actual quotes from Jaden Smith, but they are
not capitalized in the same way he originally typed them.

Example:

Not Jaden-Cased: "How can mirrors be real if our eyes aren't real"
Jaden-Cased:     "How Can Mirrors Be Real If Our Eyes Aren't Real"

Note that the Java version expects a return value of null for an empty string or null.

*/

public class JadenCase {
    public static String toJadenCase(String phrase) {
       // а вот phrase == null || phrase.equals("") не плодит NPE вообще никогда ;)
        // ну, или phrase.isEmpty() в случае этой задачи
        try {
            // NPE здесь потенциально плодит именно вызов phrase.equals
            if (phrase.equals("") || phrase.equals(null)) {
                return null;
            } else {
                char[] array = phrase.toCharArray();
                boolean isFound = false;
                for (int i = 0; i < array.length; i++) {
                    if (!isFound && isWord(array[i])) {
                        array[i] = Character.toUpperCase(array[i]);
                        isFound = true;
                    } else if (!isWord(array[i])) {
                        isFound = false;
                    }
                }
                return String.valueOf(array);
            }

        } catch (NullPointerException e) {
            return null;
        }
    }

    public static boolean isWord(char symbol) {
        if (Character.isDigit(symbol) || Character.isLetter(symbol)
                || symbol == '-' || symbol == '_' || symbol == '`') {
            return true;
        }
        return false;
    }
}
