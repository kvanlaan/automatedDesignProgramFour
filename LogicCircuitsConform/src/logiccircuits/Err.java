package logiccircuits;

public class Err {
    public static RuntimeException toss(String format, Object... o) {
        String er = String.format(format,o);
        return new RuntimeException(er);
    }
}
