package homeWork12;

import homeWork12.urlExeption.UrlException;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Main {
    public static void main(String[] args) throws MalformedURLException, UrlException, UnsupportedEncodingException {
        List<String> list = Arrays.asList("af sd", "afsdf");
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("id", "345435");
        map.put("name", "sfsdf");
        map.put("second", "45354");


        URL url = new URL.Composer("user-data.hillel.it").isSecure(true).
                authority("alex", "Test123").
                path("qwerty", "asdf").param("name", "Alex").port(556).
                fragment("fragment").
                compose();
        System.out.println(url.toString());

    }
}
