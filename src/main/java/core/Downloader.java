package core;


import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
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

    // 下载文件名
    private String downloadFileName;

    // 下载线程数
    private int threadNumber;

    // 当前下载位置
    private int currentPos;
    // 上一次下载位置
    private int lastPos;

    // 文件大小
    private int fileSize;

    // 是否已已经下载的大小
    private int hasDownloaded;

    // 下载资源
    private ResourceInfo resourceInfo;

    private ConvertUtil convertUtil;

    // 分块任务list
    List<DownloadProcessor> downloadTask = new ArrayList<>();
    // 线程list
    List<Thread> downloadThreadList = new ArrayList<>();

    // 初始时开启的线程数
    int threadNum = 10;

    {
        convertUtil = new ConvertUtil();
    }


    /**
     * 开始下载
     * @throws IOException
     * @throws URISyntaxException
     */
    public void download() throws IOException, URISyntaxException {
        if(url == null || "".equals(url)){
            System.out.println("URL不能为空");
            return ;
        }
        analyzeUrl();
        doDownload();
    }

    /**
     * 解析url获得所要下载内容的信息
     */
    private void analyzeUrl() throws IOException, URISyntaxException {
        URL url = null;
        try {
            url = new URL(getUrl());
        } catch (MalformedURLException e) {
            System.out.println("url格式不正确");
            e.printStackTrace();
        }

        this.downloadFileName = URLProcessor.getFileName(getUrl());

        HttpURLConnection connection_temp = (HttpURLConnection) url.openConnection();
        this.fileSize = connection_temp.getContentLength();
        connection_temp.disconnect();
        System.out.println("下载文件大小为" + convertUtil.convertSize(fileSize));
    }

    private void doDownload() throws IOException {
        File file = new File(getDownloadFileName());
        int each_size = this.fileSize / threadNum;
        for(int i = 0; i < threadNum; i ++){
            int start = each_size * i;
            int end;
            if(i < threadNum - 1) {
                end = start + each_size;
            }else{
                end = this.fileSize % threadNum == 0 ? start + each_size : this.fileSize;
            }

            DownloadProcessor downloadProcessor = new DownloadProcessor(file, start, end, getUrl());
            Thread thread = new Thread(downloadProcessor);
            downloadTask.add(downloadProcessor);
            downloadThreadList.add(thread);
        }

        long start = System.currentTimeMillis();
        downloadThreadList.forEach(e -> e.start());

        // anonymous inner class
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int timeCost = 0;
            @Override
            public void run() {
                int threadNum = 0;
                lastPos = currentPos;
                hasDownloaded = 0;
                currentPos = 0;
                for(DownloadProcessor downloadProcessor : downloadTask){
                    if(!downloadProcessor.isFinish()){
                        threadNum++;
                    }
                    currentPos += downloadProcessor.getCurrentPos() - downloadProcessor.getStart();
                    hasDownloaded += downloadProcessor.getCurrentPos() - downloadProcessor.getStart();
                }
                int currentSpeed =(currentPos - lastPos)/1000;
                String remainTime = currentSpeed <= 0 ? "--" : (fileSize - hasDownloaded)/currentSpeed + "s";
                timeCost++;
                System.out.println(timeCost + " seconds " + threadNum + " threads " + " -- speed -- " + currentSpeed + " KB/s" + "  " + convertUtil.convertSize(hasDownloaded) + "/" + convertUtil.convertSize(fileSize) + " " + convertUtil.getPoint((double)hasDownloaded/fileSize*100, 2) + "% "  + " remain " + remainTime);
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
        System.out.println(" Average speed " + (double) fileSize / cost + " KB/s");

    }


    /**
     * 暂停下载，暂不保存持久化状态到硬盘中
     */
    private void pause(){
//        for()
    }

    /**
     * 恢复下载
     */
    private void resume(){

    }



    public Downloader(){
        this(null);
    }

    public Downloader(String url){
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getDownloadFileName() {
        return downloadFileName;
    }

    public void setDownloadFileName(String downloadFileName) {
        this.downloadFileName = downloadFileName;
    }

}