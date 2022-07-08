package Util;


import java.util.Iterator;
import java.util.LinkedList;

public class SystemUtil {

    public static LinkedList<String> supportOS = new LinkedList<>();

    static {
        supportOS.add("windows");
    }

    public static void isSupportSystem() throws NotSupportOS {
        String osName = System.getProperty("os.name").toLowerCase();
        if (!support(osName)) {
            throw new NotSupportOS("不支持的操作系统类型...");
        }
    }

    public static class NotSupportOS extends Exception {

        public NotSupportOS(String message) {
            super(message);
        }
    }

    public static boolean support(String osName) {

        Iterator<String> iterator = supportOS.iterator();

        while (iterator.hasNext()) {
            String next = iterator.next();
            if (osName.toLowerCase().contains(next))
                return true;
        }
        return false;
    }
}
