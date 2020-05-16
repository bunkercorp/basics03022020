package homeworks.hw9;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<String> myList = new ArrayList<>();
        myList.add("pathHERE");
        myList.add("andHERE");

        URL url1 = new URL.Composer("hillel.ua")
                .setAuthority("123456", "cat123")
                .setParam("sun")
                .setPort(610)
                .setPath(myList)
                .compose();

        URL url2 = new URL.Composer("lms.com")
                .isSecure(true)
                .setAuthority("cat123", "123456")
                .setPort(610)
                .setPath(myList)
                .setFragment("moon")
                .compose();

        System.out.println(url1);
        System.out.println(url2);
    }
}