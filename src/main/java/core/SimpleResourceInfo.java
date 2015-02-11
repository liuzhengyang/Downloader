package core;

/**
 * Created by Administrator on 2015/2/10.
 */
public class SimpleResourceInfo implements ResourceInfo {

    private String path;
    private long length;
    private String fileName;

    public SimpleResourceInfo(){

    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    public SimpleResourceInfo(String path, long length){
        this.path = path;
        this.length = length;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public long length() {
        return length;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public String toString() {
        return "SimpleResourceInfo{" +
                "path='" + path + '\'' +
                ", length=" + length +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
