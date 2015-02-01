package core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;

/**
 * URL处理类
 * Created by liuzhengyang on 2015/2/1.
 */
public class URLProcessor {



    private URL url;
    private String fileName;

    public URLProcessor(URL url) {
        this.url = url;
    }

    public String getFileName(){
        return fileName;
    }

    public String getFileName(HttpURLConnection connection) throws UnsupportedEncodingException, URISyntaxException {
        String encode = "utf-8"; // 默认为utf-8
        String content_type = connection.getHeaderField("Content-Type");
        if(content_type!=null){
            encode = content_type.substring(content_type.indexOf("charset=") + "charset=".length()).trim();
            if(encode.endsWith("\"")){
                encode = encode.substring(0, encode.length() - 1);
            }
        }
        // asert connection doesn't follow redirect and is the final resource location
        String disposition = connection.getHeaderField("Content-Disposition");
        if(disposition != null){
            System.out.println(" charset " + encode);
            System.out.println(" disposition " + disposition);
            disposition = URLDecoder.decode(disposition, encode);
            String filename = disposition.substring(disposition.indexOf("filename") + "filename".length());
            System.out.println("filename = " + filename);
            if(filename.startsWith("*=")){
                return filename.substring("*=UTF-8''".length());
            }else{
                return filename.substring(1);
            }
        }
        return connection.getURL().toURI().getPath().substring(connection.getURL().toURI().getPath().lastIndexOf("/") + 1);
    }


    public HttpURLConnection getFinalConnection() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // 不重定向
        connection.setInstanceFollowRedirects(false);
        connection.connect();
        if(connection.getResponseCode() == HttpURLConnection.HTTP_MOVED_PERM || connection.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP){
            String location = connection.getHeaderField("Location");
            System.out.println(location);

            return (HttpURLConnection)new URL(location).openConnection();
        }else{
            return connection;
        }
    }
}
