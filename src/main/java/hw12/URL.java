package hw12;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;


public class URL {
    private boolean isSecure = false;
    private String host;
    private int port;
    private String path;
    private String param;
    private String authority;
    private String fragment;

    private URL(Composer composer) throws MalformedURLException {
        this.isSecure = composer.isSecure;
        this.host = composer.host;
        this.port = composer.port;
        this.path = composer.path;
        this.param = composer.param;
        this.authority = composer.authority;
        this.fragment = composer.fragment;
    }

    public static class Composer {
        private boolean isSecure;
        private String host;
        private int port;
        private String path = "";
        private String param = "";
        private String authority;
        private String fragment;

        public Composer(String host) throws MalformedURLException {
            setHost(host);
        }

        public void setHost(String host) throws MalformedURLException {
            if (UrlValidation.VALID_HOST.matcher(host).find())
                this.host = host.toLowerCase();
            else
                throw new MalformedURLException("Invalid host name :" + host);
        }

        public Composer isSecure(boolean secure) {
            isSecure = secure;
            return this;
        }

        public Composer port(int port) throws MalformedURLException {
            if (port < UrlValidation.MIN_PORT || port > UrlValidation.MAX_PORT)
                throw new MalformedURLException("Invalid port number : " + port);
            this.port = port;
            return this;
        }

        public Composer path(String path) throws MalformedURLException {

            if (UrlValidation.FORBIDDEN_PATH.matcher(path).find())
                throw new MalformedURLException("Invalid path :" + path);
            this.path = "/" + path.toLowerCase();
            return this;
        }

        public Composer path(String... path) throws MalformedURLException {
            for (String a : path) {
                if (UrlValidation.FORBIDDEN_PATH.matcher(a).find())
                    throw new MalformedURLException("Invalid path :" + path);
                else
                    this.path += "/" + a.toLowerCase();
            }
            return this;
        }

        public Composer path(List<String> path) throws MalformedURLException{
            for (String a: path) {
                if (UrlValidation.FORBIDDEN_PATH.matcher(a).find())
                    throw new MalformedURLException("Invalid path :" + path);
                else
                    this.path += "/" + a.toLowerCase();
            }
            return this;
        }

        public Composer param(String param) {
            this.param = param;
            return this;
        }

        public Composer param(String key, String value){
            return this;
        }

        public Composer params(Map<String,String> params){
            return this;
        }

        public Composer authority(String username) {
            this.authority = authority;
            return this;
        }

        public Composer authority(String username, String password) {
            this.authority = authority;
            return this;
        }

        public Composer fragment(String fragment) {
            this.fragment = fragment;
            return this;
        }

        public URL compose() throws MalformedURLException {
            return new URL(this);
        }
    }

    @Override
    public String toString() {
        return "URL{" +
                "isSecure=" + isSecure +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", path='" + path + '\'' +
                ", param='" + param + '\'' +
                ", authority='" + authority + '\'' +
                ", fragment='" + fragment + '\'' +
                '}';
    }
}
