package core;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by liuzhengyang on 2015/2/1.
 */
public class DownloaderTest {

    @Test
    public void testDownload() throws IOException {

//        Downloader downloader = new Downloader("http://mirrors.koehn.com/apache/tomcat/tomcat-8/v8.0.18/bin/apache-tomcat-8.0.18.zip");
//        Downloader downloader = new Downloader("http://127.0.0.1:8080/download/Wildlife.wmv");
//        Downloader downloader = new Downloader("http://211wangxiao.com/main/main/index.jsp");
        Downloader downloader = new Downloader("http://mirrors.koehn.com/apache/tomcat/tomcat-8/v8.0.18/bin/apache-tomcat-8.0.18.zip");
        long start = System.currentTimeMillis();
        downloader.setDownloadFileName("tomcat.zip");
        downloader.download();
        long end = System.currentTimeMillis();
        System.out.println("Download " + downloader.getDownloadFileName() + " cost " + (end-start) + " milli seconds");
    }


    /**
     * 判断两个文件是否相等
     */
    @Test
    public void compareFile(){
        File file1 = new File("G:\\apache-tomcat-8.0.17\\webapps\\download/Lighthouse.jpg");
        File file2 = new File("C:\\Users\\liuzhengyang\\IdeaProjects\\apiHack/Lighthouse.jpg");

        try(
                InputStream inputStream1 = new FileInputStream(file1);
                InputStream inputStream2 = new FileInputStream(file2)
        ){
            int c;
            while((c = inputStream1.read()) == inputStream2.read()){
//                System.out.println(c);
                if(c == -1){
                    break;
                }
            }
            if(c!=-1){
                System.out.println(file1.getName() + " content not equals to " + file2.getName());
            }else{
                System.out.println("Equals");
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}