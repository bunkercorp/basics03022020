package infra;

import java.util.Arrays;
import java.util.function.Function;

public enum OS {
    WINDOWS(".exe", s -> s.contains("win")),
    LINUX("_nix", s -> Arrays.stream(new String[]{"nix", "nux", "aix"}).anyMatch(s::contains)),
    MAC("_mac", s -> s.contains("mac"));

    public final String driverFilenameEnding;
    private final Function<String, Boolean> predicate;

    private OS(String fne, Function<String, Boolean> p) {
        this.driverFilenameEnding = fne;
        this.predicate = p;
    }

    private static class UnrecognizedOSException extends RuntimeException {
        UnrecognizedOSException(String foundOSName) {
            super(String.format("OS '%s' is not in list of known operating systems", foundOSName));
        }
    }

    public static OS current() {
        final String currentOsName = System.getProperty("os.name").toLowerCase();
        for (OS candidate : OS.values()) {
            if (candidate.predicate.apply(currentOsName)) {
                return candidate;
            }
        }
        throw new UnrecognizedOSException(currentOsName);
    }
}
