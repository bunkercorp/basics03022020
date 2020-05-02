package hw12;

import hw12.urlExeption.UrlException;

import javax.print.attribute.HashAttributeSet;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) throws MalformedURLException, UrlException, UnsupportedEncodingException {
        List<String> list = Arrays.asList("af sd", "afsdf");
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("id", "345435");
        map.put("name", "sfsdf");
        map.put("second", "45354");


        URL url = new URL.Composer("255.168.0.1").isSecure(true).
                authority("alex", "Test123").
                path("qwerty", "asdf").param("name", "Alex").
                fragment("fragment").
                compose();
        System.out.println(url.toString());

    }
}
