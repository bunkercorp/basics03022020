package homeworks.hw9;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URL {

    private String domain;
    private boolean protocol;
    private int port;
    private String path;
    private String param;
    private String authority;
    private String fragment;
    private int defaultHttpPort = 80;
    private int defaultHttpsPort = 443;

    private URL(String domain, boolean protocol, int port,
                String path, String param, String authority,
                String fragment) {
        this.domain = domain;
        this.protocol = protocol;
        this.port = port;
        this.path = path;
        this.param = param;
        this.authority = authority;
        this.fragment = fragment;
    }

    public static class Composer {
        private String domain;
        private boolean protocol;
        private int port = 0;
        private String path = null;
        private String param = null;
        private String authority = null;
        private String fragment = null;
//        private HashMap<String, String> params = new HashMap<>();
//        private HashMap<String, String> authority = new HashMap<>();


        public Composer(String domain) {
           // "      " \ null ??
            if (!domain.equals(""))
                this.domain = domain;
            else
                throw new IllegalArgumentException("Empty domain.");
        }

        public Composer isSecure(boolean protocol) {
            // по хорошему, поле тоже надо было как-то поименовать навроде isSSL \ isSecure или что то в таком духе. true\false это не протокол
            this.protocol = protocol;
            return this;
        }

        public Composer setPort(int port) {
            if (port > 0 && port < 65535)
                this.port = port;
            else
                throw new IllegalArgumentException("Port wrong: " + port);
            return this;
        }

        public Composer setPath(String path) {
            if (this.path == null) {
                if (!path.equals(""))
                    this.path = slashPath(path);
                else
                    throw new IllegalArgumentException("Empty path");
            }
            return this;
        }

        public Composer setPath(String... paths) {
            StringBuilder stringBuilder = new StringBuilder();
            int count = 0;

            if (this.path == null) {

                // String.join("/", paths). И всё.
                for (String path : paths) {
                    if (!path.equals("")) {
                        String tempPath = slashPath(path);

                        if (count + 1 != paths.length) {
                            stringBuilder.append(tempPath).append("/");
                        } else
                            stringBuilder.append(tempPath);
                        count++;
                    } else
                        throw new IllegalArgumentException("Empty path.");
                }
                this.path = stringBuilder.toString();
            }
            return this;
        }

        public Composer setPath(Collection<String> paths) {
            StringBuilder stringBuilder = new StringBuilder();
            int count = 0;
            if (this.path == null) {
                for (String path : paths) {
                    if (!path.equals("")) {
                        String tempPath = slashPath(path);

                        if (count + 1 != paths.size()) {
                            stringBuilder.append(tempPath).append("/");
                        } else
                            stringBuilder.append(tempPath);
                        count++;
                    } else
                        throw new IllegalArgumentException("Empty path.");
                }
                this.path = stringBuilder.toString();
            }
            return this;
        }


        public Composer setParam(String param) {
            if (this.param == null) {
                // "      " \ null ??
                if (!param.equals("")) {
                    if (!param.startsWith("?"))
                        this.param = param;
                    else
                        this.param = param.substring(1);
                } else
                    throw new IllegalArgumentException("Not valid parameter");
            }
            return this;
        }

        public Composer setParam(String arg, String value) {
            StringBuilder stringBuilder = new StringBuilder();

            if (this.param == null) {
                if (!arg.equals("")) {
                    stringBuilder.append(arg);
                    if (!value.equals("")) {
                        stringBuilder.append("=").append(value);
                        this.param = stringBuilder.toString();
                    } else
                        throw new IllegalArgumentException("Not valid parameter");
                } else
                    throw new IllegalArgumentException("Not valid parameter");
            }
            return this;
        }

        public Composer setParams(Map<String, String> params) {
            StringBuilder stringBuilder = new StringBuilder();

            if (this.param == null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    if (!entry.getKey().equals("")) {
                        stringBuilder.append(entry.getKey());
                        if (!entry.getValue().equals("")) {
                            stringBuilder.append("=").append(entry.getValue());
                        } else
                            // окей, эта библиотека используется на проекте с X строк кода... Я вижу в логах сообщение "Not valid parameter". О чем оно мне говорит? Куда я должен обратить внимание?
                            throw new IllegalArgumentException("Not valid parameter");
                    } else
                        throw new IllegalArgumentException("Not valid parameter");
                }
                this.param = stringBuilder.toString();
            }
            return this;
        }

        public Composer setAuthority(String login) {
            if (this.authority == null) {
                if (!login.equals("")) {
                    if (!login.endsWith("@"))
                        this.authority = login;
                    else
                        this.authority = login.substring(0, login.length() - 1);
                } else
                    throw new IllegalArgumentException("Not valid authority");
            }
            return this;
        }

        public Composer setAuthority(String login, String password) {
            StringBuilder stringBuilder = new StringBuilder();

            if (this.authority == null) {
                if (!login.equals("")) {
                    stringBuilder.append(login);
                    if (!password.equals("")) {
                        stringBuilder.append(":").append(password);
                        this.authority = stringBuilder.toString();
                    } else
                        throw new IllegalArgumentException("Not valid authority");
                } else
                    throw new IllegalArgumentException("Not valid authority");
            }
            return this;
        }

        public Composer setFragment(String fragment) {
            if (this.fragment == null) {
                if (!fragment.equals("")) {
                    if (!fragment.startsWith("#"))
                        this.fragment = fragment;
                    else
                        this.fragment = fragment.substring(1);
                } else
                    throw new IllegalArgumentException("Empty fragment");
            }
            return this;
        }

        public URL compose() {
            String DOMAIN_NAME_PATTERN = "^((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$";
            if (this.domain != null) {
                if (!this.domain.matches(DOMAIN_NAME_PATTERN))
                    throw new IllegalArgumentException("Wrong domain");
            }

            if (this.port == 0 & !this.protocol)
                this.port = 80;
            else if (this.port == 0 & this.protocol)
                this.port = 443;

            String PATH_NAME_PATTERN = "[a-zA-Z0-9-._]*";
            if (this.path != null) {
                if (!this.path.endsWith("/")) {
                    String[] listPaths = this.path.split("/");
                    for (String path : listPaths) {
                        if (!path.equals("")) {
                            if (!path.matches(PATH_NAME_PATTERN))
                                throw new IllegalArgumentException("Wrong path");
                        } else
                            throw new IllegalArgumentException("Wrong path");
                    }
                } else
                    throw new IllegalArgumentException("Wrong path");
            }


            String FRAGMENT_NAME_PATTERN = "[a-zA-Z0-9-_]*$";
            if (this.fragment != null) {
                if (!this.fragment.matches(FRAGMENT_NAME_PATTERN))
                    throw new IllegalArgumentException("Wrong fragment");
            }

            return new URL(this.domain,
                    this.protocol,
                    this.port,
                    this.path,
                    this.param,
                    this.authority,
                    this.fragment);
        }

        private String slashPath(String path) {
//            Pattern trimSlashesPattern = Pattern.compile("^\\/*(.*?)\\/*$");
//           Matcher m = trimSlashesPattern.matcher(path.trim());
//           m.find();
//            String pathTrimmedSlashes   = m.group(1);
//            или как-то так
            String tempPath = path;
                        if (path.startsWith("/") && path.endsWith("/"))
                tempPath = path.substring(1, path.length() - 1);
            else if (path.startsWith("/"))
                tempPath = path.substring(1);
            else if (path.endsWith("/"))
                tempPath = path.substring(0, path.length() - 1);
            return tempPath;
        }
    }

    @Override
    public String toString() {
        // String.format("http%s://", protocol? "s": "")
        String result = protocol ? "https://" : "http://";

        if (authority != null)
            result = result + authority + "@";

        if (domain != null)
            result = result + domain;


        if ((port != defaultHttpsPort & protocol) || (port != defaultHttpPort & !protocol))
            result = result + ":" + port;

        if (path != null)
            result = result + "/" + path;

// а вот если бы param был бы Map<String, String>, чем он и является в жизни, то это было бы просто
// result + "?" + param.entrySet().stream().map(e -> String.format("%s=%s", e.getKey(), e.getValue)).collect(Collectors.joining())
// без всей той логики в setPAram что у тебя там есть
        if (param != null)
            result = result + "?" + param;

        if (fragment != null)
            result = result + "#" + fragment;
        return result;
    }
}

