package homeworks.hw9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args){
//        List<String> newPath = new ArrayList<String>();
//        newPath.add("hhhh");
//        newPath.add("ggg");
        URL url = new URL.Composer("aaaaa.com")
                .isSecure(false)
                .authority("kate", "1234567")
                .port(900)
                .path("aaaa")
                .param("yuh788")
                .param("p1", "v1")
                .fragment("121")
                .compose();
        System.out.println(url);
    }
}
