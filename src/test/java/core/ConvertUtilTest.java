package core;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 2015/2/10.
 */
public class ConvertUtilTest {

    private ConvertUtil convertUtil;

    @Before
    public void setUp(){
        convertUtil = new ConvertUtil();
    }

    @Test
    public void testConvertByte(){
        System.out.println(new ConvertUtil().convertSize(21501482L));
    }


    @Test
    public void testAfterPoint(){
        System.out.println(convertUtil.getPoint(12521.235235d,2));
    }
}
