package core;

import java.io.Closeable;

/**
 * Created by liuzhengyang on 2015/1/31.
 */
public class ResourceUtil {
    public static void closeResource(Closeable... closeables){
        if(closeables != null){
            for(Closeable closeable : closeables){
                if(closeable!=null){
                    try {
                        closeable.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
