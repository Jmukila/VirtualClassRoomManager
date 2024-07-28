package VirtualClassManager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    public static void log(String message) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = sdf.format(new Date());
        System.out.println(timestamp + " - " + message);
    }
}
