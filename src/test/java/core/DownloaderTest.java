package core;

import com.sun.deploy.net.proxy.ProxyType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.Base64;

/**
 * Created by liuzhengyang on 2015/2/1.
 */
public class DownloaderTest {
    private static final Logger logger = LogManager.getLogger(DownloaderTest.class);

    @Test
    public void testDownload() throws IOException, URISyntaxException {

//        Downloader downloader = new Downloader("http://mirrors.koehn.com/apache/tomcat/tomcat-8/v8.0.18/bin/apache-tomcat-8.0.18.zip");
//        Downloader downloader = new Downloader("http://download-cf.jetbrains.com/upsource/upsource-1.0.12566.zip");
        Downloader downloader = new Downloader("http://bj.dl.baidupcs.com/file/b758144089eb4363674625cc9177f638?bkt=p-4bf175f5731ddd632e2830c06b87e9f6&fid=1026168496-250528-3997412805&time=1422891529&sign=FDTAXERLBH-DCb740ccc5511e5e8fedcff06b081203-SOrVhoE5uj9HLatNndDyr7AVXIk%3D&to=abp&fm=Qin,B,G,e&newver=1&newfm=1&flow_ver=3&sl=81723468&expires=8h&rt=sh&r=305737040&mlogid=225118573&vuk=-&vbdid=2217814841&fin=TreeDBNotes%20Pro%20v4.34.rar&fn=TreeDBNotes%20Pro%20v4.34.rar");
//        Downloader downloader = new Downloader("http://211wangxiao.com/student/exportAllCol.211");
//        Downloader downloader = new Downloader("http://211wangxiao.com/main/main/index.jsp");
//        Downloader downloader = new Downloader("http://dldx.csdn.net/fd.php?i=147444700947175&s=7998650e7d34c4209f18bd333a85e395");
//        Downloader downloader = new Downloader("http://apache.petsads.us/nutch/1.9/apache-nutch-1.9-bin.zip");
        long start = System.currentTimeMillis();
//        downloader.setDownloadFileName("tomcat.zip");
        downloader.download();
        long end = System.currentTimeMillis();
        System.out.println("Download " + downloader.getDownloadFileName() + " cost " + (end-start) + " milli seconds");
    }


    /**
     * 判断两个文件是否相等
     */
    @Test
    public void compareFile(){
        File file1 = new File("G:\\apache-tomcat-8.0.17\\webapps\\download/Wildlife.wmv");
        File file2 = new File("G:\\迅雷下载\\Downloader/Wildlife.wmv");

        // 如果两个文件内容长度不相等则不相同
        if(file1.length() != file2.length()){
            System.out.println(file1.getName() + " content not equals to " + file2.getName());
        }



//        try(
//                InputStream inputStream1 = new FileInputStream(file1);
//                InputStream inputStream2 = new FileInputStream(file2)
//        ){
//            int c;
//            while((c = inputStream1.read()) == inputStream2.read()){
////                System.out.println(c);
//                if(c == -1){
//                    break;
//                }
//            }
//            if(c!=-1){
//                System.out.println(file1.getName() + " content not equals to " + file2.getName());
//            }else{
//                System.out.println("Equals");
//            }
//        }catch (IOException e){
//            e.printStackTrace();
//        }

    }


    @Test
    public void testUrlConnection() throws IOException {
//        URL url = new URL("http://dldx.csdn.net/fd.php?i=147444700947175&s=7998650e7d34c4209f18bd333a85e395");
        URL url = new URL("http://3g.renren.com/ep.do?c=9100210");
//        URL url = new URL("http://www.211wangxiao.com");
//        URL url = new URL("http://localhost:8080/download/test.txt");
//        URL url = new URL("https://r1---sn-q4f7dnsz.googlevideo.com/videoplayback?sver=3&expire=1422793204&signature=B50BCF362BF76B893B3FA20492A87D48081C4814.5F7E27BE15AC5731D6274F9DF2385468D2E1785F&sparams=dur,id,ip,ipbits,itag,mime,mm,ms,mv,pl,requiressl,source,upn,expire&ms=au&id=o-AIbuUSCZ243ytw2c3lRK_hP3izalMTMyFkfl8KVMy6_L&itag=5&mv=u&dur=768.026&ipbits=0&pl=28&key=yt5&mm=31&source=youtube&upn=cDwuW2KvydU&requiressl=yes&fexp=3300101,3300101,3300133,3300133,3300137,3300137,3300161,3300161,3310366,3310366,3310754,3310754,3312177,3312177,900718,902543,907263,927622,930676,930817,934954,9406358,9406391,9406627,942639,943917,947225,948124,949422,952302,952605,952901,955301,957201,957506,959701&mt=1422771388&mime=video/x-flv&ip=107.178.200.220&ratebypass=yes&title=%E5%B1%8C%E4%B8%9D%E7%94%B7%E7%94%9F%E7%AC%AC3%E5%AD%A3%2007%20%E6%B3%95%E5%BC%8F%E6%8C%89%E6%91%A9%E8%8A%B1%E6%A0%B7%E5%A4%9A%20%E5%90%89%E6%B3%BD%E6%98%8E%E6%AD%A5%E8%B6%85%E5%8F%97%E5%AE%A0");
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8087));
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setInstanceFollowRedirects(false);
//        URLConnection connection = url.openConnection();
        connection.connect();

//        connection.connect();
//        logger.info(connection.getRequestProperties());
        connection.getHeaderFields().forEach((k,v) -> System.out.println(k + " = " + v));
        logger.info(connection.getResponseCode());
//        logger.info(connection.getContentType());
//        logger.info(connection.getContent());
//        logger.info(connection.getContentLength());
//        logger.info(connection.getResponseCode());
//        logger.info(connection.getResponseCode());
//        logger.info(connection.getResponseMessage());
//        logger.info(connection.getRequestMethod());
//        logger.info(connection.getInstanceFollowRedirects());
//        logger.info(connection.getHeaderField("Content-Type"));
//        logger.info(connection.getHeaderField("Content-Length"));
//        logger.info(connection.getHeaderField("Content-Disposition"));
//        logger.info(connection.getHeaderField("Location"));
//        String disposition = connection.getHeaderField("Content-Disposition");

//        logger.info(disposition.substring(disposition.indexOf("filename")));
//        logger.info(URLDecoder.decode(disposition, "utf-8"));

    }

}