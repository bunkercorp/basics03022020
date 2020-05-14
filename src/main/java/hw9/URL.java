package homeworks.hw9;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class URL {
    private boolean security;
    private int port;
    private String path;
    private Map<String, String> params;
    private String authority;
    private String fragment;
    private String domain;
    private static final int portHttp = 80;
    private static final int portHttps = 443;

    private URL(boolean security, String authority, String domain, int port, String path, HashMap param, String fragment){
        this.security = security;
        this.authority = authority;
        this.domain = domain;
        this.port = port;
        this.path = path;
        this.params = param;
        this.fragment = fragment;
    }
    public static class Composer{
        private boolean security;
        private int port;
        private String path = null;
        private HashMap<String, String> params = new HashMap<String, String>();
        private String authority;
        private String fragment = null;
        private String domain;


        private void validate(String str){
            if (str.isEmpty())
                throw new IllegalArgumentException("Empty string!");
            String regEx = "[a-zA-Z0-9-_]*$";
            if (!str.matches(regEx))
                throw new IllegalArgumentException("String is not valid!");
        }

        public Composer isSecure(boolean secure){
            this.security = security;
            return this;
        }
        public Composer authority(String username){
            validate(username);
            if (this.authority.isEmpty())
                this.authority = username;
            return this;
        }
        public Composer authority(String username, String password)throws IllegalArgumentException {
            if (username.equals("")) {
                throw new IllegalArgumentException("Username is empty");
            }
            if (!username.trim().matches("^[a-zA-Z0-9-_]+$|^[a-zA-Z0-9-_]+\\.[a-zA-Z0-9-_]+$")) {
                throw new IllegalArgumentException("Username is not valid");
            }
            if (password.equals("")) {
                throw new IllegalArgumentException("Password is empty");
            }
            this.authority =  username + ":" + password;
            return  this;

        }
        public Composer(String domain) throws IllegalArgumentException{
            String DOMAIN_PATTERN = "^(([a-zA-Z]{1})|([a-zA-Z]{1}[a-zA-Z]{1})|([a-zA-Z]{1}[0-9]{1})|([0-9]{1}[a-zA-Z]{1})|([a-zA-Z0-9][a-zA-Z0-9-_]{1,61}[a-zA-Z0-9]))\\.([a-zA-Z]{2,6}|[a-zA-Z0-9-]{2,30}\\.[a-zA-Z]{2,3})$";
            if(domain.matches(DOMAIN_PATTERN))
                this.domain = domain;
            else throw new IllegalArgumentException("Invalid domain");
        }

        public Composer port(int port){
            if(port > 0 && port< 65535)
                this.port = port;
            else
                throw new IllegalArgumentException("Port must be greater than 0");
            return this;
        }

        public Composer path(String path) throws IllegalArgumentException {
//       String regEx = "(\\/[a-zA-Z0-9-_].*(\\?|$))";
            String regEx = "[a-zA-Z0-9-_]*$";
            if(path.matches(regEx)) {
                this.path = (path.charAt(0) == '/') ? path.substring(1, path.length() - 1) : path;
            } else {
                throw new IllegalArgumentException("This path is not correct");
            }
            return  this;
        }

        public Composer path(String... paths){

            for(String path : paths) {
                validate(path);
                if (this.path == null)
                    this.path = path;
                else
                    this.path = this.path + "/" + path;
            }
            return this;
        }
        public Composer path(Collection<String> pathCollection){
            for(String path : pathCollection) {
                validate(path);
                if (this.path == null)
                    this.path = path;
                else
                    this.path = this.path + "/" + path;
            }
            return this;
        }

        public Composer param(String param){
//        String regEx = "(\\/[a-zA-Z0-9-_].*(\\?|$))";
            validate(param);
            this.params.put(param,param);
            return this;
        }
        public Composer param(String param1, String param2){
            validate(param1);
            validate(param2);
            this.params.put(param1, param2);
            return this;
        }
        public Composer params(HashMap<String,String> params){
            for(Map.Entry<String, String> entry : params.entrySet()){
                validate(entry.getKey());
                validate(entry.getValue());
                this.params.put(entry.getKey(), entry.getValue());
            }
            return  this;
        }

        public Composer fragment(String fragment){
            validate(fragment);
            if(this.fragment == null)
                this.fragment = fragment;
            return this;
        }

        public URL compose(){
            if (port == 0) {
                port = security ? portHttps : portHttp;
            }
            return new URL(this.security, this.authority, this.domain, this.port, this.path, this.params, this.fragment);
        }
    }

    @Override
    public String toString() {
        String result = security ? "https://" : "http://";

        if (authority != null)
            result = result + authority + "@";

        if (domain != null)
            result = result + domain;

        if ((port != portHttps & security) || (port != portHttp & !security))
            result = result + ":" + port;

        if (path != null)
            result = result + "/" + path;

        if (params != null && !params.isEmpty()) {
            result = result + "?";

            for(Map.Entry<String, String> entry : params.entrySet()) {
                result += entry.getKey() + "=" + entry.getValue() + "&";
            }
            result = result.substring(0, result.length() - 2);
        }

        if (fragment != null)
            result = result + "#" + fragment;

        return result;
    }
}