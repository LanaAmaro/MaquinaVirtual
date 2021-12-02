package util;

class Color {
    public static final String RED     = "[31m";
    public static final String GREEN   = "[32m";
    public static final String YELLOW  = "[33m";
    public static final String BLUE    = "[34m";
    public static final String MAGENTA = "[35m";
    public static final String CYAN    = "[36m";
};


public class Colors {
    
    private static String colors(String _color, String _string) {
        return (char)27 + _color + _string + (char)27 + "[0m" + "";
    }

    public static String red(String _string) {
        return colors(Color.RED, _string);
    } 

    public static String green(String _string) {
        return colors(Color.GREEN, _string);
    }

    public static String yellow(String _string) {
        return colors(Color.YELLOW, _string);
    }

    public static String blue(String _string) {
        return colors(Color.BLUE, _string);
    }

    public static String magenta(String _string) {
        return colors(Color.MAGENTA, _string);
    }

    public static String cyan(String _string) {
        return colors(Color.CYAN, _string);
    }
}
