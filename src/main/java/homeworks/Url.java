package hw12;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Url {
    private static int defPortHttp = 80;
    private static int defPortHttps = 443;
    private boolean security;
    private int port;
    private String domain;
    private String path;
    private Map<String, String> params;
    private HashMap<String, String> authority;
    private String fragment;

    private Url(boolean security, HashMap authority, int port, String domain, String path, HashMap param, String fragment) {
        this.security = security;
        this.port = port;
        this.authority = authority;
        this.path = path;
        this.params = param;
        this.domain = domain;
        this.fragment = fragment;
    }

    public static class Composer {
        private int port;
        private boolean security;
        private String domain;
        private String path = null;
        private HashMap<String, String> params = new HashMap<>();
        private HashMap<String, String> authority = new HashMap<>();
        private String fragment = null;

        private void isCorrectString(String string, String message){
            if (string.isEmpty())
                throw new IllegalArgumentException("empty - "+message);

            String PATTERN = "[a-zA-Z0-9-_]*$";//for example
            if (!string.matches(PATTERN))
                throw new IllegalArgumentException(message);
        }

        public Composer isSecure(boolean security) {
            if (security) {
                if (this.port == 0) {
                    this.port = 443;
                }
            } else {
                if (this.port == 0) {
                    this.port = 80;
                }
            }

            this.security = security;

            return this;
        }

        public Composer authority(String user) {
            isCorrectString(user,"user string - authority");
            if (this.authority.isEmpty())
                this.authority.put(user, null);
            return this;
        }

        public Composer authority(String user, String password) {
            isCorrectString(user,"user string - authority");
            isCorrectString(password,"password string - authority");
            if (this.authority.isEmpty())
                this.authority.put(user, password);
            return this;
        }

        public Composer port(int port) {
            if(port>0 & port<65535)
                this.port = port;
            else
                throw new IllegalArgumentException("Port must be in range 0...65535");
            return this;
        }

        public Composer path(String path) {
            isCorrectString(path,"path string");
            if (this.path == null)
                this.path = path;
            return this;
        }


        public Composer path(String... path) {
            for (String c : path) {
                isCorrectString(c,"one of path string");
                if (this.path == null)
                    this.path = c;
                else
                    this.path = this.path + "/" + c;
            }
            return this;
        }

        public Composer path(Collection<String> path) {
            for (String c : path) {
                isCorrectString(c,"one of path string collection");
                if (this.path == null)
                    this.path = c;
                else
                    this.path = this.path + "/" + c;
            }
            return this;
        }

        public Composer param(String param) {
            if (this.params.isEmpty()) {
                //decompile strig to name and value
                //this.params.put(param.substring(0, param.indexOf('=')), param.substring(param.indexOf('=') + 1, param.length()));
                isCorrectString(param,"one param");
                this.params.put(param,param);
            }

            return this;
        }

        public Composer param(String nameParam, String valParam) {
            isCorrectString(nameParam,"string name param");
            isCorrectString(valParam,"string val param");
            if (this.params.isEmpty())
                this.params.put(nameParam, valParam);
            return this;
        }

        public Composer params(HashMap<String, String> params) {
            if (this.params.isEmpty())
                for (Map.Entry<String, String> entry : params.entrySet()){
                    isCorrectString(entry.getKey(),"param hash map key");
                    isCorrectString(entry.getValue(),"param hash map value");
                    this.params.put(entry.getKey(), entry.getValue());
                }

            return this;
        }

        public Composer fragment(String fragment) {
            isCorrectString(fragment,"fragment");
            if (this.fragment == null)
                this.fragment = fragment;
            return this;
        }

        public Composer(String domain) {
            String DOMAIN_NAME_PATTERN = "^((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$";
            if (domain.matches(DOMAIN_NAME_PATTERN))
                this.domain = domain;
            else
                throw new IllegalArgumentException("wrong.domain.name.is.detected");
                //this.domain = "wrong.domain.name.is.detected";
        }

        public Url compose() {
            if(this.port == 0&!this.security)
                    this.port = 80;



            return new Url(this.security, this.authority, this.port, this.domain, this.path, this.params, this.fragment);
        }


    }

    @Override
    public String toString() {
        String result = security ? "https://" : "http://";

        if (!authority.isEmpty())
            for (String key : authority.keySet()) {
                result += "" + key;
                if (authority.get(key) != null)
                    result += ":" + authority.get(key);
                result += "@";
            }

        if (domain != null)
            result += domain;

        if ((port != defPortHttps & security) || (port != defPortHttp & !security))
            result += ":" + port;

        //result+="/";

        if (path != null)
            result += "/" + path;

        if (!params.isEmpty()) {
            int currPosition = 1;
            result += "?";
            for (String key : params.keySet()) {
                result += key + "=" + params.get(key);
                if (params.size() != currPosition) {
                    result += "&";
                    currPosition++;
                }
            }
        }

        if (fragment != null)
            result += "#" + fragment;

        return result;
    }
}
