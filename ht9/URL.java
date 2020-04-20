import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//
public class URL {

    private String protocol = null;
    private String logIn = null;
    private String passWord = null;
    private String domName = null;
    private int port = -1;
    private String path = null;
    private String param = null;
    private String fragment = null;

    public URL(String protocol, String logIn, String passWord, String domName, int port, String path, String param, String fragment) {
        this.protocol = protocol;
        this.logIn = logIn;
        this.passWord = passWord;
        this.domName = domName;
        this.port = port;
        this.path = path;
        this.param = param;
        this.fragment = fragment;
    }

    public static class Composer {

        private String protocol = null;
        private String logIn = null;
        private String passWord = null;
        private String domName = null;
        private int port = -1;
        private String path = null;
        private String param = null;
        private String fragment = null;

        public Composer(String arg) {
            System.out.print("Composer(String) called. ");
            if (arg == null || arg.equals("")) {this.domName = arg;} else {
                int port = -1;
                final String argRegEx = "((([\\w]|:|-){1,63})\\.){2,127}";
                boolean DomNameValid = arg.length() <= 253 && arg.concat(".").matches(argRegEx);

                if (DomNameValid) {

                    final String ThreeDigits = "([0-9]{1,3})";//part of IP
                    final String SixDigits = "(:([0-9]{1,6}))?";//port
                    final String LooksLikeIP = ThreeDigits + "\\." + ThreeDigits + "\\." + ThreeDigits + "\\." + ThreeDigits + SixDigits;
                    Pattern likep = Pattern.compile(LooksLikeIP);
                    Matcher likem = likep.matcher(arg);
                    boolean LikeIP = likem.matches();

                    try {
                        port = (arg.indexOf(':') > 0) ? Integer.parseInt(arg.substring(arg.indexOf(':') + 1)) : -1;
                    } catch (NumberFormatException nfe) {
                        System.out.println("NumberFormatException: " + nfe.getMessage());
                    }

                    if (LikeIP) {
                        System.out.println("Host looks like IP");

                        final String zeroTo255 = "([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])";
                        final String portRegEx = "(:([0-5]?[0-9]{1,4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-6]))?";
                        final String IPRegEx = zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255 + "\\." + zeroTo255 + portRegEx;
                        Pattern p = Pattern.compile(IPRegEx);
                        Matcher m = p.matcher(arg);
                        boolean IPValid = m.matches();

                        if (IPValid) {

                            final String greyRegEx = "((192.168(\\.([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])){2})|(10(\\.([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])){3})|(100.64(\\.([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])){2})|(172.(1[6-9]|2[0-9]|3[0-2])(\\.([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])){2}))" + portRegEx;
                            boolean grey = arg.matches(greyRegEx);

                            if (grey) {
                                System.out.println("Error: grey IP");
                                this.domName = "-1";
                            } else if (port != -1) {
                                this.domName = arg.substring(0, arg.indexOf(':'));
                                this.port(port);
                            } else this.domName = arg;
                        } else {//DomNameNotValid
                            this.domName = "-1";
                            System.out.println("Error: invalid IP or port");
                        }//ИП неправильный
                    } else {//LikeIP
                        if (port != -1) {
                            this.domName = arg.substring(0, arg.indexOf(':'));
                            this.port(port);
                        } else this.domName = arg;
                    }//NotLikeIP
                } else { //DomNameValid
                    this.domName = "-1";
                    System.out.println("Error: Invalid (sub)domain");
                }
            }
        }

        public Composer isSecure(boolean arg) {
            System.out.print("Protocol setter called. ");
            this.protocol = (arg) ? "https" : "http";
            return this;
        }

        public Composer port(int arg) {
            System.out.print("Port(int) called. ");
            if ((arg >= 0) & (arg <= 65535)) this.port = arg;
            else {
                this.port = -1;
                System.out.println("Error: zero to 65535 port allowed");
            }
            return this;
        }

        public Composer path(String arg) {
            System.out.print("Path(String) called. ");
            final String argRegEx = "^/?((([\\w]|-|_|~|!|\\$|&|'|\\(|\\)|\\*|\\+|,;|=|@){1,127})/){1,127}/?";
            boolean PathValid = (arg != null) && (arg.length() <= 2000) && ((arg.charAt(arg.length() - 1) == '/') ? arg : arg.concat("/")).matches(argRegEx);
            if (PathValid) this.path = (arg.charAt(0) == '/') ? arg.substring(1, arg.length() - 1) : arg;
            else {
                this.path = "-1";
                System.out.println("Error: invalid path");
            }
            return this;
        }

        public Composer path(String... args) {
            System.out.print("Path(VarArgs) called. ");
            final String argRegEx = "^/?(([\\w]|-|_|~|!|\\$|&|'|\\(|\\)|\\*|\\+|,|;|=|@){1,127})/?";
            String res = "";

            for (String arg : args) {
                boolean PathsWordsValid = arg != null && ((arg.charAt(arg.length() - 1) == '/') ? arg : arg.concat("/")).matches(argRegEx);
                if (PathsWordsValid) res += "/" + arg;
                else {
                    res = "-1";
                    System.out.println("Error: invalid path");
                    break;
                }
            }
            if (res.length() > 2000) {
                res = "-1";
                System.out.println("Error: invalid path. to long");
            }
            this.path = res;
            return this;
        }

        public Composer path(Collection<String> args) {
            System.out.print("Path(Collection) called. ");
            final String argRegEx = "^/?(([\\w]|-|_|~|!|\\$|&|'|\\(|\\)|\\*|\\+|,|;|=|@){1,127})/?";
            String res = "";

            for (String arg : args) {
                boolean PathsWordsValid = arg != null && arg.matches(argRegEx);
                if (PathsWordsValid) {
                    res += "/" + arg;
                } else {
                    res = "-1";
                    System.out.println("Error: invalid path");
                    break;
                }
            }
            if (res.length() > 2000) {
                res = "-1";
                System.out.println("Error: invalid path. to long");
            }
            this.path = res;
            return this;
        }

        public Composer param(String arg) {
            System.out.print("Query(String) called. ");
            final String argRegEx = "^\\??((([\\w]|-|_|\\*|\\.){1,127}[=]([\\w]|-|_|\\*|\\.|%){0,127}?)[;&]){1,127}";
            boolean ParamValid = arg != null && (arg.length() <= 2000) && ((arg.charAt(arg.length() - 1) == '&') ? arg : arg.concat("&")).matches(argRegEx);
            if (ParamValid) this.param = arg;
            else {
                this.param = "-1";
                System.out.println("Error: invalid query");
            }
            return this;
        }

        public Composer param(String arg, String arg2) {
            System.out.print("Query(String String) called. ");
            boolean nullArg = arg == null || arg2 == null;
            String res = "";
            char separator = '&';//';'
            if (nullArg) {
                res = "-1";
                System.out.println("Error: NULL argument not allowed for " + Thread.currentThread().getStackTrace()[2].getMethodName());
            } else {
                final String argRegEx = "^/?(([\\w]|-|_|~|!|\\$|&|'|\\(|\\)|\\*|\\+|,|;|=|@){1,127})/?";
                String ArgSeparator = "([.,;/ ])";

                for (int i = 0; i<arg2.length()-1; i++)
                    if (Character.toString(arg2.charAt(i)).matches(ArgSeparator) & Character.toString(arg2.charAt(i+1)).matches(ArgSeparator))
                        arg2 = arg2.substring(0, i + 1) + (char)94 + arg2.substring(i + 1);

                String[] keys = arg.split(ArgSeparator);
                String[] values = arg2.split(ArgSeparator);
                if (keys.length == values.length) for (int i = 0; i < keys.length; i++)
                    if (keys[i].matches(argRegEx) && (values[i].matches(argRegEx) || values[i].equals(Character.toString((char) 94)))) {
                        res += keys[i] + "=" + ((!values[i].equals(Character.toString((char) 94))) ? values[i] : "") + separator;
                    } else {
                        res = "-1";
                        System.out.println("Error: invalid query");
                        break;
                    }
            }
            this.param = (res.charAt(res.length() - 1) == separator) ? res.substring(0, res.length() - 1) : res;
            return this;
        }

        public Composer params(Map<String, String> arg) {
            System.out.print("Query(Map) called. ");
            final String argRegEx = "^/?(([\\w]|-|_|~|!|\\$|&|'|\\(|\\)|\\*|\\+|,|;|=|@){1,127})/?";
            String res = "";
            char separator = '&';
            for (Map.Entry<String, String> item : arg.entrySet()) {
                if (item.getKey().matches(argRegEx) && ((item.getValue()!=null && !item.getValue().equals("") && item.getValue().matches(argRegEx)) || (item.getValue()==null || item.getValue().equals("")))) {
                    res += item.getKey() + "=" + ((item.getValue() !=null &&  !item.getValue().equals("")) ? item.getValue() : "") + separator;
                } else {
                    res = "-1";
                    System.out.println("Error: invalid query");
                    break;
                }
            }
            this.param = (res.charAt(res.length() - 1) == separator) ? res.substring(0, res.length() - 1) : res;
            return this;
        }

        public Composer authority(String arg) {
            System.out.print("Authority(String) called. ");
            final String argRegEx = "^(([\\w]|-|_|~|!|\\$|&|'|\\(|\\)|\\*|\\+|,|;|=|@){2,25})";
            if (arg != null && arg.matches(argRegEx)) this.logIn = arg;
            else {
                this.logIn = "-1";
                System.out.println("Error: Invalid User Name");
            }
            return this;
        }

        public Composer authority(String arg, String arg2) {
            System.out.print("Authority(String String) called. ");
            final String argRegEx = "^(([\\w]|-|_|~|!|\\$|&|'|\\(|\\)|\\*|\\+|,|;|=|@){2,25})";
            boolean logInOK = arg!=null && arg.matches(argRegEx);
            boolean passWordOK = arg2!=null && arg2.length() >= 8 && arg2.matches(argRegEx);
            if (logInOK && passWordOK) {
                this.logIn = arg;
                this.passWord = arg2;
            } else {
                this.logIn = "-1";
                this.passWord = "-1";
                System.out.println("Error: Invalid User Name or PassWord");
            }
            return this;
        }

        public Composer fragment(String arg) {
            System.out.print("Fragment(String) called. ");
            final String argRegEx = "^#?(([\\w]|-|_|~|!|\\$|&|'|\\(|\\)|\\*|\\+|,|;|=|@){1,127})";
            if (arg != null && arg.matches(argRegEx)) this.fragment = (arg.charAt(0) != '#') ? arg : arg.substring(1);
            else {
                this.fragment = "-1";
                System.out.println("Error: Invalid fragment");
            }
            return this;
        }

        public URL compose() {
            if ((port < 0) || (protocol.equals("-1")) || (logIn.equals("-1")) || (passWord.equals("-1")) || (domName.equals("-1")) || (path.equals("-1")) || (param.equals("-1")) || (fragment.equals("-1")))
                System.out.println("\n\nWarning: URL looks broken");
            boolean mandatoryFieldsOK = (protocol != null && !protocol.equals("-1")) && ((domName != null && !domName.equals("-1")) || (path != null && !path.equals("-1")));
            boolean totalLenghtOK = (
                    ((protocol != null) ? protocol.length() : 0) +
                            ((logIn != null) ? logIn.length() : 0) +
                            ((passWord != null) ? passWord.length() : 0) +
                            ((domName != null) ? domName.length() : 0) +
                            ((port > 0) ? (Integer.toString(port).length()) : 0) +
                            ((path != null) ? path.length() : 0) +
                            ((param != null) ? param.length() : 0) +
                            ((fragment != null) ? fragment.length() : 0)
            ) <= 2083;
            boolean urlTreeErr = (domName == null && ((port > 0) || (logIn != null)));

            if (mandatoryFieldsOK && totalLenghtOK && !urlTreeErr)
                return new URL(protocol, logIn, passWord, domName, port, path, param, fragment);
            else {
                System.out.print("Error: " + ((protocol != null && protocol != "-1") ? "" : "protocol ") + ((logIn != null) ? "" : "logIn ") + ((passWord != null) ? "" : "passWord ") + ((domName != null && domName !="-1") ? "" : "domName ") + ((path != null && path !="-1") ? "" : "path ") + ((param != null) ? "" : "param ") + ((fragment != null) ? "" : "fragment ") + " field is NULL value. OR URL to long.  ");
                return null;
            }
        }
    }//Composer

    @Override
    public String toString() {
        String str = "";
        str = this.protocol + ":" + ((this.domName!=null && !this.domName.equals("")) ? "//" : "")
                + ((this.logIn != null) ? this.logIn + ((this.passWord != null) ? ":" + ((this.passWord != "-1") ? "PassWord" : "-1") : "") + "@" : "")
                + ((this.domName!=null) ? ( this.domName + ((this.port != 80) ? ":" + this.port : "")) : (""))
                + ((this.path != null) ? ((this.domName!=null && !this.domName.equals(""))?"/":"") + this.path : "")
                + ((this.param != null) ? "?" + this.param : "")
                + ((this.fragment != null) ? "#" + this.fragment : "");
        return (str);
    }//toString
}//URL
