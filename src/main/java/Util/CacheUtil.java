package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;


public class CacheUtil {

    public static boolean createPathCache(String sourceDir, String destDir) throws IOException {
        File file = new File(getCachePath());
        Path path = file.toPath();
        Files.createDirectories(path.getParent());

        if (safeCreate(file)){
            try (FileOutputStream fops = new FileOutputStream(file)) {
                fops.write((sourceDir + "\n" + destDir).getBytes(StandardCharsets.UTF_8));
            }
        }
        System.out.println("生成缓存成功");
        return true;
    }


    public static boolean cacheExist() {
        return new File(getCachePath()).exists();
    }


    public static String[] getCache() throws NoSuchFieldException, IOException {
        File file = new File(getCachePath());
        if (!file.exists())
            throw new NoSuchFieldException("找不到" + getCachePath());

        try(FileInputStream fileInputStream = new FileInputStream(file)){
            byte[] buffer = new byte[1024];
            int read = fileInputStream.read(buffer);

            String[] res = new String(buffer, 0, read, StandardCharsets.UTF_8).split("\n");
            System.out.println(Arrays.toString(res));
            return res;
        }
    }

    public static String getCachePath() {
        return "C:\\Users\\" + getUserName() + "\\AppData\\Local\\copyNote\\cache";
    }

    static String getUserName() {
        return System.getProperty("user.name");
    }

    public static boolean safeCreate(File file) throws IOException {
        CopyWorker.deleteIfExist(file);
        Files.createFile(file.toPath());
        return file.exists();
    }
}
