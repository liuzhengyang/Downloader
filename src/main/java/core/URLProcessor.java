package core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;

/**
 * URL处理类
 * Created by liuzhengyang on 2015/2/1.
 */
public class URLProcessor {

    public URLProcessor(){

    }


    /**
     * 通过url得到资源详细信息
     * @param urlStr
     * @return
     */
    public ResourceInfo getResourceInfo(String urlStr) throws IOException, URISyntaxException {
        SimpleResourceInfo resourceInfo = new SimpleResourceInfo();
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        resourceInfo.setFileName(doGetFileName(connection));
        resourceInfo.setLength(doGetFileLength(connection));
        connection.disconnect();
        return resourceInfo;
    }

    // 得到文件长度
    public long doGetFileLength(HttpURLConnection connection){
        return connection.getContentLength();
    }

    public String doGetFileName(HttpURLConnection connection) throws UnsupportedEncodingException, URISyntaxException {

        // 判断编码encode
        String encode = "utf-8"; // 默认字符编码为utf-8
        String contentType = connection.getHeaderField("Content-Type");
        if(contentType!=null){
            String encodeGet = contentType.substring(contentType.indexOf("charset=") + "charset=".length()).trim();
            if(encodeGet!=null){
                // 取消最后的引号
                encode = encode.substring(0, encode.length() - 1);
            }
        }

        // 如果是采用Content-Disposition方式指明
        String disposition = connection.getHeaderField("Content-Disposition");
        if(disposition != null){
            disposition = URLDecoder.decode(disposition, encode);
            String filename = disposition.substring(disposition.indexOf("filename") + "filename".length());
            if(filename.startsWith("*=")){
                return filename.substring("*=UTF-8''".length());
            }else{
                return filename.substring(1);
            }
        }

        // 其他情况则返回.最后的文件名
        return connection.getURL().toURI().getPath().substring(connection.getURL().getPath().lastIndexOf("/") + 1);
    }

    /**
     * 从url字符串判断所要下载文件在服务器上的名字
     * @param urlStr
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    public static String getFileName(String urlStr) throws IOException, URISyntaxException {
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
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
            if(filename.startsWith("*=")){
                System.out.println("filename = " + filename);
                return filename.substring("*=UTF-8''".length());
            }else{
                System.out.println("filename = " + filename);
                return filename.substring(1);
            }
        }
        return connection.getURL().toURI().getPath().substring(connection.getURL().toURI().getPath().lastIndexOf("/") + 1);
    }


    public static String getFinalConnection(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // 不重定向
        connection.setInstanceFollowRedirects(false);
        connection.connect();
        if(connection.getResponseCode() == HttpURLConnection.HTTP_MOVED_PERM || connection.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP){
            String location = connection.getHeaderField("Location");
            System.out.println(location);

            return location;
        }else{
            return urlStr;
        }
    }
}
