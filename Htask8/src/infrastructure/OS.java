package infrastructure;

enum OS {
    WINDOWS,
    LINUX,
    MAC;

    public static OS current(){
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName == null) throw new RuntimeException ("os.name not found"); else
        if (osName.startsWith("windows")) return OS.WINDOWS; else
        if ((osName.indexOf("nux") >= 0) ||
                (osName.indexOf("nix") >= 0) ||
                (osName.indexOf("bun") >= 0)) return OS.LINUX; else
        if (osName.startsWith("mac")) return OS.MAC; else return null;
    }//current
}//enum OS
