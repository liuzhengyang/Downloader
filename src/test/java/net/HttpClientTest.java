package net;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by liuzhengyang on 2015/2/1.
 */
public class HttpClientTest {

    private static final Logger logger = LogManager.getLogger(HttpClientTest.class);

    @Test
    public void testRequestExecution() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://3g.renren.com/ep.do?c=9100210");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        try{
            logger.info(response.getAllHeaders());
            logger.info(response.getStatusLine());
        }finally {
            response.close();
        }
    }
}
