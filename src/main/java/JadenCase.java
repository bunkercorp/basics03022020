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
      // phrase == null || phrase.isEmpty()
      if (phrase==""  || phrase==null){
          return null;
      }
    // Излишне. Нас всего лишь просят капитализировать слова в строке, а это потенциально еще и изменение ее длины
      String temp = phrase.strip();
      char[] inputChar = phrase.toCharArray();
      // Мутировать что-либо, связанное со входными данными так себе идея в плане читаемости, КМК
      // но раз угодно, то вот еще вариант String.format("%c%s", Character.toUpperCase(temp.charAt(0)),temp.substring(1))
      temp = temp.substring(0, 1).toUpperCase() + temp.substring(1);
      for (int i = 0; i < phrase.length(); i++) {
        // Character.isAlphabetic(inputChar[i])
          boolean isWord=((inputChar[i]>='a')&&(inputChar[i]<='z')) || ((inputChar[i]>='A')&&(inputChar[i]<='Z'));
          boolean isCorrectSymbol = (inputChar[i]=='_') || (inputChar[i]=='-') || (inputChar[i]=='`');
        // Character.isDigit()
          boolean isNumber = (inputChar[i]>='0')&&(inputChar[i]<='9');
          if (!(isWord||isCorrectSymbol||isNumber)) {
            // представим, что phrase.length() ==1000000... Таким образом, для каждого символа в строке мы гоняем процессор еще одной проверкой, которая имеет смысл только в самом конце
              // т.е., есть вариант бежать по циклу i < phrase.length() - 1 и потом отдельно добавлять в конец строки
              if( i == (phrase.length()-1)){
                  temp = temp.substring(0, i) + temp.substring(i , i + 1).toUpperCase();
              } else {
                  // стринг формат определенно более самодокументируется, чем такая конкатенация
                  temp = temp.substring(0, i+1) + temp.substring(i+1, i + 2).toUpperCase() + temp.substring(i + 2);
              }
          }
      }
      return temp;

    }
}
