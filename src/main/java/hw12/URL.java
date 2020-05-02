package hw12;

import hw12.urlExeption.UrlException;
import hw12.urlHelpers.UrlHelpers;
import hw12.urlHelpers.UrlValidation;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class URL {
    private boolean isSecure;
    private String host;
    private int port;
    private String path;
    private String param;
    private String authority;
    private String fragment;

    private URL(Composer composer) {
        this.isSecure = composer.isSecure;
        this.host = composer.host;
        this.port = composer.port;
        this.path = composer.path;
        this.param = composer.param;
        this.authority = composer.authority;
        this.fragment = composer.fragment;
    }

    public static class Composer {
        private boolean isSecure = false;
        private String host;
        private int port = 80;
        private String path;
        private String param;
        private String authority;
        private String fragment;

        public Composer(String host) throws UrlException {
            setHost(host);
        }

        public void setHost(String host) throws UrlException {
            if (UrlValidation.VALID_HOST.matcher(host).find() ||
                    UrlValidation.VALID_IP_V4_HOST.matcher(host).find())
                this.host = host.toLowerCase();
            else
                throw new UrlException("Invalid host name :" + host);
        }

        public Composer isSecure(boolean secure) {
            isSecure = secure;
            if (isSecure && port == 80)
                port = 443;
            return this;
        }

        public Composer port(int port) throws UrlException {
            if (port >= UrlValidation.MIN_PORT || port <= UrlValidation.MAX_PORT)
                this.port = port;
            else
                throw new UrlException("Invalid port number : " + port);
            return this;
        }

        public Composer path(String path) throws UrlException, UnsupportedEncodingException {
            path = URLEncoder.encode(path, "UTF-8").replace("+", "%20");
            if (UrlValidation.FORBIDDEN_PATH.matcher(path).find())
                throw new UrlException("Invalid path :" + path);
            this.path = "/" + path.toLowerCase();
            return this;
        }

        public Composer path(String... path) throws UrlException, UnsupportedEncodingException {
            this.path = "";
            for (String a : path) {
                a = UrlHelpers.removeSpacesInPath(a);
                if (UrlValidation.FORBIDDEN_PATH.matcher(a).find())
                    throw new UrlException("Invalid path :" + path);
                else
                    this.path += "/" + a.toLowerCase();
            }
            return this;
        }

        public Composer path(List<String> path) throws UrlException, UnsupportedEncodingException {
            for (String a : path) {
                a = UrlHelpers.removeSpacesInPath(a);
                if (UrlValidation.FORBIDDEN_PATH.matcher(a).find())
                    throw new UrlException("Invalid path :" + path);
                else
                    this.path += "/" + a.toLowerCase();
            }
            return this;
        }

        public Composer param(String param) throws UrlException {
            if (param == null)
                throw new UrlException("Invalid parameter");
            this.param = "/" + param;
            return this;
        }

        public Composer param(String key, String value) throws UrlException {
            if (key == null || value == null)
                throw new UrlException("Invalid key or value ");
            this.param = "?" + key + "=" + value;
            return this;
        }

        public Composer params(Map<String, String> param) {
            this.param = "?";
            this.param = param.entrySet().stream().map(e -> String.format("%s=%s", e.getKey(), e.getValue())).
                    collect(Collectors.joining("&"));
            return this;
        }

        public Composer authority(String username) throws UrlException {
            if (username == null)
                throw new UrlException("Invalid username value ");
            this.authority = username + "@";
            return this;
        }

        public Composer authority(String username, String password) throws UrlException {
            if (username == null || password == null)
                throw new UrlException("Invalid username or password ");
            this.authority = String.format("%s:%s@", username, password);
            return this;
        }

        public Composer fragment(String fragment) throws UrlException {
            if (fragment == null)
                throw new UrlException("Invalid fragment");
            this.fragment = "#" + fragment;
            return this;
        }

        public URL compose() {
            return new URL(this);
        }
    }

    public boolean isSecure() {
        return isSecure;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getPath() {
        return path;
    }

    public String getParam() {
        return param;
    }

    public String getAuthority() {
        return authority;
    }

    public String getFragment() {
        return fragment;
    }

    @Override
    public String toString() {
        String url = isSecure ? "https://" : "http://";
        if (authority != null)
            url += authority + host + ":" + port;
        else
            url += host + ":" + port;
        if (path != null)
            url += path;
        if (param != null)
            url += param;
        if (fragment != null)
            url += param;
        return url;
    }
}
