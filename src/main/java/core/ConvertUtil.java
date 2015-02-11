package core;

/**
 * Created by Administrator on 2015/2/10.
 */
public class ConvertUtil {

    /**
     * 将byte数转化为可读数
     * @param length byte
     * @return
     */
    public String convertSize(long length){
        if(length <= 0){
            return "0KB";
        }
        String[] units = new String[]{"b", "K", "M", "G"};
        StringBuilder stringBuilder = new StringBuilder();
        long temp = length;
        int count = 0;
        while(temp>0){
            count++;
            temp/=1024;
        }
        double result = length;
        for(int i = 0; i < count - 1; i++){
            result/=1024;
        }
        String res = result + "";
        // 小数点位置
        int posOfPoint = res.indexOf(".") ;
        return res.substring(0, posOfPoint + 2)+ units[count - 1];
    }

    /**
     * 要转化的数后几位小数格式
     * @param d
     * @param afterDigits
     * @return
     */
    public String getPoint(double d, int afterDigits){
        String s = String.valueOf(d);
        int pos = s.indexOf(".");
        int end = pos + afterDigits + 1 > s.length() ? s.length() : pos + afterDigits + 1;
        return s.substring(0, end);
    }


}
