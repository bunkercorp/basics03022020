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
      if (phrase == "" || phrase == null) {

          return null;
      }

      String[] words = phrase.split(" ");

      String newword = "";


      for (int i = 0; i < words.length; i++) {
          String word = words[i];
          newword = newword.concat(word.replaceFirst(String.valueOf(word.toCharArray()[0]), String.valueOf(word.toCharArray()[0]).toUpperCase()));

          if (i < words.length - 1) {

              newword = newword.concat(" ");

          }
      }

      return newword;

  }
}
