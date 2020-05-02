package hw12.urlHelpers;

import hw12.urlExeption.UrlException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlHelpers {

    public static String removeSpacesInPath(String path) throws UnsupportedEncodingException {
        return URLEncoder.encode(path, "UTF-8").replace("+", "%20");
    }
}
