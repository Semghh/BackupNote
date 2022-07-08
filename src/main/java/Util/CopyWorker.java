package Util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CopyWorker {

    private int dirCount = 0;
    private int fileCount = 0;

    public void dfsCopy(File source, File dest) throws IOException {
        if (source.isDirectory()){
            File dir = new File(dest,source.getName());
            if (dir.mkdir()) {dirCount++;}
            File[] files = source.listFiles();
            for (int i = 0; i < files.length; i++) {
                dfsCopy(files[i],dir);
            }
        }else {
            File file = new File(dest, source.getName());
            deleteIfExist(file);
            Files.copy(source.toPath(),file.toPath());
            fileCount++;
        }
    }
    public static void deleteIfExist(File file){
        if (file.exists()) {
            file.delete();
        }
    }

    @Override
    public String toString() {
        return "成功拷贝"+dirCount+"个文件夹"+
                "\n"+
                "成功拷贝"+fileCount+"个文件";
    }
}
