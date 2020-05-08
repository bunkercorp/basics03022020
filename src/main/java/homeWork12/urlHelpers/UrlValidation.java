package homeWork12.urlHelpers;

import java.util.regex.Pattern;

public class UrlValidation {
    public static final int MIN_PORT = 0;
    public static final int MAX_PORT = 65536;
    public static final Pattern VALID_HOST = Pattern.compile("^(?!\\-)(?:[a-zA-Z\\d\\-]{0,62}[a-zA-Z\\d]\\.){1,126}(?!\\d+)[a-zA-Z\\d]{1,63}$");
    public static final Pattern VALID_IP_V4_HOST = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
    public static final Pattern FORBIDDEN_PATH = Pattern.compile("[<>:\"\\/\\\\|?*\\x00-\\x1F]|^(?:aux|con|clock\\$|nul|prn|com[1-9]|lpt[1-9])$");



}
