package core;

/**
 * Created by Administrator on 2015/2/10.
 */
public class SimpleResourceInfo implements ResourceInfo {

    private String path;
    private int length;

    public SimpleResourceInfo(){

    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public SimpleResourceInfo(String path, int length){
        this.path = path;
        this.length = length;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public int length() {
        return length;
    }
}
