import java.util.*;

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

    private URL(String tempDomain, boolean tempProtocol, int tempPort, String tempPath, String tempParam, String tempAuthority, String tempFragment) {
        domain = tempDomain;
        protocol = tempProtocol;
        port = tempPort;
        path = tempPath;
        param = tempParam;
        authority = tempAuthority;
        fragment = tempFragment;
    }

    public static class Composer {

        private String domain_build;
        private boolean protocol_build;
        private int port_build = 0;
        private String path_build = null;
        private String param_build = null;
        private String authority_build = null;
        private String fragment_build = null;


        public Composer(String domain) {
            if (!domain.equals(""))
                this.domain_build = domain;
            else
                throw new IllegalArgumentException("Empty domain.");
        }

        public Composer isSecure(boolean protocol) {
            this.protocol_build = protocol;
            return this;
        }

        public Composer setPort(int port) {
            if (port > 0 && port < 65535)
                this.port_build = port;
            else
                throw new IllegalArgumentException("Port wrong: " + port);
            return this;
        }

        public Composer setPath(String path) {
            if (this.path_build == null) {
                if (!path.equals(""))
                    this.path_build = slashPath(path);
                else
                    throw new IllegalArgumentException("Empty path");
            }
            return this;
        }

        public Composer setPath(String... paths) {
            StringBuilder stringBuilder = new StringBuilder();
            int count = 0;

            if (this.path_build == null) {
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
                this.path_build = stringBuilder.toString();
            }
            return this;
        }

        public Composer setPath(Collection<String> paths) {
            StringBuilder stringBuilder = new StringBuilder();
            int count = 0;

            if (this.path_build == null) {
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
                this.path_build = stringBuilder.toString();
            }
            return this;
        }


        public Composer setParam(String param) {
            if (this.param_build == null) {
                if (!param.equals("")) {
                    if (!param.startsWith("?"))
                        this.param_build = param;
                    else
                        this.param_build = param.substring(1);
                } else
                    throw new IllegalArgumentException("Not valid parameter");
            }
            return this;
        }

        public Composer setParam(String arg, String value) {
            StringBuilder stringBuilder = new StringBuilder();

            if (this.param_build == null) {
                if (!arg.equals("")) {
                    stringBuilder.append(arg);
                    if (!value.equals("")) {
                        stringBuilder.append("=").append(value);
                        this.param_build = stringBuilder.toString();
                    } else
                        throw new IllegalArgumentException("Not valid parameter");
                } else
                    throw new IllegalArgumentException("Not valid parameter");
            }
            return this;
        }

        public Composer setParams(Map<String, String> params) {
            StringBuilder stringBuilder = new StringBuilder();

            if (this.param_build == null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    if (!entry.getKey().equals("")) {
                        stringBuilder.append(entry.getKey());
                        if (!entry.getValue().equals("")) {
                            stringBuilder.append("=").append(entry.getValue());
                        } else
                            throw new IllegalArgumentException("Not valid parameter");
                    } else
                        throw new IllegalArgumentException("Not valid parameter");
                }
                this.param_build = stringBuilder.toString();
            }
            return this;
        }

        public Composer setAuthority(String login) {
            if (this.authority_build == null) {
                if (!login.equals("")) {
                    if (!login.endsWith("@"))
                        this.authority_build = login;
                    else
                        this.authority_build = login.substring(0, login.length() - 1);
                } else
                    throw new IllegalArgumentException("Not valid authority");
            }
            return this;
        }

        public Composer setAuthority(String login, String password) {
            StringBuilder stringBuilder = new StringBuilder();

            if (this.authority_build == null) {
                if (!login.equals("")) {
                    stringBuilder.append(login);
                    if (!password.equals("")) {
                        stringBuilder.append(":").append(password);
                        this.authority_build = stringBuilder.toString();
                    } else
                        throw new IllegalArgumentException("Not valid authority");
                } else
                    throw new IllegalArgumentException("Not valid authority");
            }
            return this;
        }

        public Composer setFragment(String fragment) {
            if (this.fragment_build == null) {
                if (!fragment.equals("")) {
                    if (!fragment.startsWith("#"))
                        this.fragment_build = fragment;
                    else
                        this.fragment_build = fragment.substring(1);
                } else
                    throw new IllegalArgumentException("Empty fragment");
            }
            return this;
        }

        public URL compose() {
            String DOMAIN_NAME_PATTERN = "^((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$";
            if (this.domain_build != null) {
                if (!this.domain_build.matches(DOMAIN_NAME_PATTERN))
                    throw new IllegalArgumentException("Wrong domain");
            }

            if (this.port_build == 0 & !this.protocol_build)
                this.port_build = 80;
            else if (this.port_build == 0 & this.protocol_build)
                this.port_build = 443;

            String PATH_NAME_PATTERN = "[a-zA-Z0-9-._]*";
            if (this.path_build != null) {
                if (!this.path_build.endsWith("/")) {
                    List<String> listPaths = Arrays.asList(this.path_build.split("/"));
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
            if (this.fragment_build != null) {
                if (!this.fragment_build.matches(FRAGMENT_NAME_PATTERN))
                    throw new IllegalArgumentException("Wrong fragment");
            }

            return new URL(this.domain_build,
                    this.protocol_build,
                    this.port_build,
                    this.path_build,
                    this.param_build,
                    this.authority_build,
                    this.fragment_build);
        }

        private String slashPath(String path) {
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
        String result = protocol ? "https://" : "http://";

        if (authority != null)
            result = result + authority + "@";

        if (domain != null)
            result = result + domain;

        if ((port != defaultHttpsPort & protocol) || (port != defaultHttpPort & !protocol))
            result = result + ":" + port;

        if (path != null)
            result = result + "/" + path;

        if (param != null)
            result = result + "?" + param;

        if (fragment != null)
            result = result + "#" + fragment;


        return result;
    }
}
