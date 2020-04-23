package hw12;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) throws MalformedURLException {
        List<String> list = Arrays.asList("afsd","afsdf");
        URL url = new URL.Composer("good.photography.retr").path(list).compose();
        System.out.println(url.toString());

        // System.out.println(UrlValidation.FORBIDDEN_PATH.matcher("<afsd").find());


    }
}
