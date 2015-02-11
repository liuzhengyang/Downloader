package core;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by Administrator on 2015/2/10.
 */
public class URLProcessorTest {

    private URLProcessor urlProcessor;

    @Before
    public void setUp(){
        urlProcessor = new URLProcessor();
    }

    @Test
    public void testGetHeader() throws IOException, URISyntaxException {
        System.out.println(urlProcessor.getResourceInfo("http://3g.renren.com/ep.do?c=9100210"));
    }
}
