package regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liuzhengyang on 2015/2/1.
 */
public class PatternTest {


    @Test
    public void testRegex(){
        Pattern pattern = Pattern.compile("filename.*=.*");
        Matcher matcher = pattern.matcher("attachment; filename*=UTF-8''%E6%BC%AB%E8%B0%88%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F.pdf");
        System.out.println(matcher.matches());
        System.out.println(matcher.find(0));
        matcher.reset();
        System.out.println(matcher.groupCount());
    }
}
