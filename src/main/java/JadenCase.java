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
      if (phrase==""  || phrase==null){
          return null;
      }

      String temp = phrase.strip();
      char[] inputChar = phrase.toCharArray();
      temp = temp.substring(0, 1).toUpperCase() + temp.substring(1);
      for (int i = 0; i < phrase.length(); i++) {
          boolean isWord=((inputChar[i]>='a')&&(inputChar[i]<='z')) || ((inputChar[i]>='A')&&(inputChar[i]<='Z'));
          boolean isCorrectSymbol = (inputChar[i]=='_') || (inputChar[i]=='-') || (inputChar[i]=='`');
          boolean isNumber = (inputChar[i]>='0')&&(inputChar[i]<='9');
          if (!(isWord||isCorrectSymbol||isNumber)) {
              if( i == (phrase.length()-1)){
                  temp = temp.substring(0, i) + temp.substring(i , i + 1).toUpperCase();
              } else {
                  temp = temp.substring(0, i+1) + temp.substring(i+1, i + 2).toUpperCase() + temp.substring(i + 2);
              }
          }
      }
      return temp;

    }
}
