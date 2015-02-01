package core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 下载线程
 * Created by liuzhengyang on 2015/2/1.
 */
public class DownloadThread implements Runnable {

    private RandomAccessFile randomAccessFile;
    private File file;

    private InputStream inputStream;
    private String url_str = null;

    private int start;
    private String name;
    private int end;
    // 当前下载位置
    private int currentPos;

    public int getStart() {
        return start;
    }

    public String getName() {
        return name;
    }

    public int getCurrentPos() {
        return currentPos;
    }

    public DownloadThread(File file, int start, int end, String url_str){
        this.name = file.getName() + " " + start;
        this.file = file;
        this.start = start;
        this.end = end;
        this.url_str = url_str;
        this.currentPos = start;
    }


    @Override
    public void run() {
        try {
            URL url = new URL(url_str);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("Range", "bytes=" + start + "-" + end);
            connection.connect();
            System.out.println(connection.getResponseCode());
            inputStream = connection.getInputStream();

            byte[] buffer = new byte[1024];
            int hasRead = 0;
            randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(start);
            while((hasRead = inputStream.read(buffer))!=-1){
                randomAccessFile.write(buffer, 0, hasRead);
                currentPos += hasRead;
            }
            System.out.println("Thread" + this.getName() + " finish");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            ResourceUtil.closeResource(randomAccessFile, inputStream);
        }
    }
}
