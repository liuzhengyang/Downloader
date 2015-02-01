package core;


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 下载类
 * Created by liuzhengyang on 2015/1/30.
 */
public class Downloader {

    //下载文件url地址
    private String url;

    private String downloadFileName;

    // 当前下载位置
    private int currentPos;
    // 上一次下载位置
    private int lastPos;

    private int fileSize;

    private RandomAccessFile randomAccessFile;


    public Downloader(){
        this(null);
    }

    public Downloader(String url){
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDownloadFileName() {
        return downloadFileName;
    }

    public void setDownloadFileName(String downloadFileName) {
        this.downloadFileName = downloadFileName;
    }

    // 从url中获取最后一个/后的字符串作为文件名
    private void extractFileName(){

        int posOfSlash = this.getUrl().lastIndexOf("/");
        this.setDownloadFileName(this.getUrl().substring(posOfSlash + 1));
    }

    public void download() throws IOException {
        if(url == null || "".equals(url)){
            System.out.println("URL不能为空");
            return ;
        }
        if(downloadFileName == null){
            // 如未设置下载名，则按照url链接最后的名字确定
            extractFileName();
            System.out.println(getDownloadFileName());
        }
        doDownload();
    }

    private void doDownload() throws IOException {
        URL url = null;
        try {
            url = new URL(getUrl());
        } catch (MalformedURLException e) {
            System.out.println("url格式不正确");
            e.printStackTrace();
        }
        HttpURLConnection connection_temp = (HttpURLConnection) url.openConnection();
        connection_temp.connect();
        this.fileSize = connection_temp.getContentLength();
        connection_temp.disconnect();
        System.out.println("下载文件大小为" + this.fileSize + " bytes");

        File file = new File(getDownloadFileName());

        List<DownloadThread> downloadTask = new ArrayList<>();
        List<Thread> downloadThreadList = new ArrayList<>();

        int threadNum = 20;
        int each_size = this.fileSize / threadNum;
        for(int i = 0; i < threadNum; i ++){
            int start = each_size * i;
            int end;
            if(i < threadNum - 1) {
                end = start + each_size;
            }else{
                end = this.fileSize % threadNum == 0 ? start + each_size : this.fileSize;
            }

            DownloadThread downloadThread = new DownloadThread(file, start, end, getUrl());
            Thread thread = new Thread(downloadThread);
            downloadTask.add(downloadThread);
            downloadThreadList.add(thread);
        }

        long start = System.currentTimeMillis();
        downloadThreadList.forEach(e -> e.start());
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                lastPos = currentPos;
                currentPos = 0;
                for(DownloadThread downloadThread : downloadTask){
                    currentPos += downloadThread.getCurrentPos() - downloadThread.getStart();
                }
                System.out.println(" -- speed -- " + (currentPos - lastPos)/1000 + " KB/s");
            }
        }, 0, 1000);
        for(Thread t : downloadThreadList){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();

        long cost = end - start;
        System.out.println(" Download cost " + cost/1000 + " seconds");
        System.out.println(" Average speed " + (double)fileSize/cost + " KB/s");
        ResourceUtil.closeResource(randomAccessFile);




    }


}