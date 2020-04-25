import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class URL {
    private String host;
    private String port;
    private String path;
    private String param;
    private String fragment;
    private String authority;

    private URL(String host, String path, String port, String param, String fragment, String authority) {
        this.host = host;
        this.path = path;
        this.port = port;
        this.param = param;
        this.fragment = fragment;
        this.authority = authority;
    }

    public String toString() {

        if (this.port == null) {
            this.port = "";
        }
        if (this.path == null) {
            this.path = "";
        }
        if (this.param == null) {
            this.param = "";
        }
        if (this.fragment == null) {
            this.fragment = "";
        }
        if (this.authority == null) {
            this.authority = "";
        }

        return Composer.isSecure(false) + "://" + authority + host + port + path + param + fragment;

    }

    public static class Composer {
        private String host;
        private String port;
        private String path;
        private String param;
        private String fragment;
        private String authority;

        public Composer(String host) {
            this.host = hostFormat(host);
        }

        private static String encodeValue(String value) {
            try {
                return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
            } catch (UnsupportedEncodingException exception) {
                throw new RuntimeException(exception.getCause());
            }
        }

        public static String isSecure(boolean enterHereTrueOrFalse) {
            return enterHereTrueOrFalse ? "https" : "http";
        }

        public static String hostFormat(String host) {
            String str = host.trim();
            if (!str.matches("^([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}")) {
                throw new IllegalArgumentException("not valid host name");
            }
            return str;
        }

        private String port(int port) {
            if (port >= 1 && port <= 65535) {
                return ":" + port;
            } else {
                throw new IllegalArgumentException("not valid port set");
            }
        }

        public String path(String path) {
            String str = path.trim();
            if (str.contains("\\") || str.contains("/") || str.contains(":")
                    || str.contains("?") || str.contains("*") || str.contains("\"")
                    || str.contains("<") || str.contains(">") || str.contains("|")) {
                throw new IllegalArgumentException("not valid symbols in path");
            } else if (str.equals("")) {
                throw new IllegalArgumentException("not valid path, directory can't be unnamed");
            }
            return "/" + encodeValue(str.toLowerCase());
        }

        public String path(String... strings) {
            for (int i = 0; i < strings.length; i++) {
                String str = "" + strings[i];
                String newStr = str.trim();
                if (str.trim().equals("")) {
                    throw new IllegalArgumentException("not valid path, directory can't be unnamed");
                } else if (str.contains("\\") || str.contains("/") || str.contains(":")
                        || str.contains("?") || str.contains("*") || str.contains("\"")
                        || str.contains("<") || str.contains(">") || str.contains("|")) {
                    throw new IllegalArgumentException("not valid symbols in path");
                }
                strings[i] = "" + newStr;
            }

            String path = Arrays.toString(strings).
                    replace("[", "").
                    replace(", ", "/").
                    replace("]", "");


            return "/" + encodeValue(path.toLowerCase()).replace("%2F", "/");
        }

        public String path(Collection<String> strings) {
            Object[] arrayPath = strings.toArray();
            for (int i = 0; i < arrayPath.length; i++) {
                String str = "" + arrayPath[i];
                String newStr = str.trim();
                if (str.trim().equals("")) {
                    throw new IllegalArgumentException("not valid path, directory can't be unnamed");
                } else if (str.contains("\\") || str.contains("/") || str.contains(":")
                        || str.contains("?") || str.contains("*") || str.contains("\"")
                        || str.contains("<") || str.contains(">") || str.contains("|")) {
                    throw new IllegalArgumentException("not valid symbols in path");
                }
                arrayPath[i] = "" + newStr;
            }

            String path = Arrays.toString(arrayPath).
                    replace("[", "").
                    replace(", ", "/").
                    replace("]", "");

            return "/" + encodeValue(path.toLowerCase()).replace("%2F", "/");
        }

        public String params(String param) {
            String formatParam = param.trim();
            if (!formatParam.matches("([^=]+)=([^&|;]+)")) {
                throw new IllegalArgumentException("not valid parameters");
            } else if (param.trim().equals("")) {
                throw new IllegalArgumentException("parameters not set");
            }
            return "/?" + encodeValue(formatParam.toLowerCase()).replace("%3D", "=");
        }

        public String params(String key, String value) {
            String formatKey = key.trim();
            String formatValue = value.trim();
            if (key.trim().equals("") || value.trim().equals("")) {
                throw new IllegalArgumentException("set the valid parameters");
            }
            String param = formatKey + "=" + formatValue;
            if (!param.matches("([^=]+)=([^&|;]+)")) {
                throw new IllegalArgumentException("not valid parameters");
            }

            return "/?" + encodeValue(formatKey.toLowerCase()) + "=" + encodeValue(formatValue.toLowerCase());
        }

        public String params(Map<String, String> params) {
            String param = params.toString().
                    replace("{", "").
                    replace("}", "");

            String[] splittedParam = param.split(", ");
            for (int i = 0; i < splittedParam.length; i++) {
                if (!splittedParam[i].matches("([^=]+)=([^&|;]+)")) {
                    throw new RuntimeException("not valid parameters");
                }
            }

            String query = param.replace(", ", "&");
            return "/?" + encodeValue(query.toLowerCase()).replace("%3D", "=").replace("%26", "&");
        }

        public String fragment(String fragment) {

            return "/#" + encodeValue(fragment.toLowerCase());
        }

        public String authority(String userId) {

            if (userId.trim().equals("")) {
                throw new IllegalArgumentException("user id is empty");
            }

            if (userId.trim().length() > 20 || !userId.trim().matches("^[a-zA-Z0-9-_]+$|^[a-zA-Z0-9-_]+\\.[a-zA-Z0-9-_]+$")) {
                throw new IllegalArgumentException("username not valid");
            }

            return userId + "@";
        }

        public String authority(String userId, String password) {
            if (userId.trim().equals("")) {
                throw new IllegalArgumentException("user id is empty");
            }
            if (userId.trim().length() > 20 || !userId.trim().matches("^[a-zA-Z0-9-_]+$|^[a-zA-Z0-9-_]+\\.[a-zA-Z0-9-_]+$")) {
                throw new IllegalArgumentException("username not valid");
            }
            if (password.trim().equals("")) {
                throw new IllegalArgumentException("password not set");
            }

            if (password.trim().length() < 4) {
                throw new IllegalArgumentException("Password length min 4 symbols");
            }
            if (password.trim().contains(" ")) {
                throw new IllegalArgumentException("not valid password");
            }

            return userId.trim() + ":" + password.trim() + "@";

        }


        public Composer setPort(int port) {
            this.port = port(port);
            return this;
        }

        public Composer setPath(String path) {
            this.path = path(path);
            return this;
        }

        public Composer setPath(String... path) {
            this.path = path(path);
            return this;
        }


        public Composer setPath(Collection<String> path) {
            this.path = path(path);
            return this;
        }

        public Composer setParams(String param) {
            this.param = params(param);
            return this;
        }

        public Composer setParams(String key, String value) {
            this.param = params(key, value);
            return this;
        }

        public Composer setParams(Map<String, String> params) {
            this.param = params(params);
            return this;
        }

        public Composer setFragment(String fragment) {
            this.fragment = fragment(fragment);
            return this;
        }

        public Composer setAuthority(String userId) {
            this.authority = authority(userId);
            return this;
        }

        public Composer setAuthority(String userId, String password) {
            this.authority = authority(userId, password);
            return this;
        }

        public URL compose() {
            return new URL(this.host, this.path, this.port, this.param, this.fragment, this.authority);
        }

    }
}
