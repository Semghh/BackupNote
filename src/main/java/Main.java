import Util.CopyWorker;
import Util.SystemUtil;

import java.io.File;

import static Util.CacheUtil.*;

public class Main {





    public static void main(String[] args) throws Exception {

        String sourceDir = "C:\\Users\\SemgHH\\Desktop\\笔记";
        String destDir = "D:\\笔记备份";

        SystemUtil.isSupportSystem();

        if (args!=null && args.length==2){
            //user input args
            sourceDir = args[0];
            destDir = args[1];
            createPathCache(sourceDir,destDir);
        }else if (cacheExist()){
            //if exist cache
            String[] cache = getCache();
            sourceDir = cache[0];
            destDir = cache[1];
            System.out.println("使用缓存...");
        }
        System.out.println("source dir : "+ sourceDir);
        System.out.println("dest dir path : " + destDir);

        CopyWorker copyWorker = new CopyWorker();
        long start = System.currentTimeMillis();
        copyWorker.dfsCopy(new File(sourceDir),new File(destDir));
        long end = System.currentTimeMillis();
        System.out.println("备份完成!");
        System.out.println("花费"+ (end-start)+ " ms");
        System.out.println(copyWorker.toString());
    }

}
