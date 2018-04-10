package com.jerry.wiremock;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * Created with IntelliJ IDEA.
 * User: Jerry
 * Date: 2018/4/3
 * Time: 16:34
 * Description:
 */
public class MockServer {

    public static void main(String[] args) throws IOException {
        configureFor("36.41.187.107", 8062);
        // 清空之前的配置
        removeAllMappings();

        mock("/order/1", "01");
        mock("/order/2", "02");
    }

    private static void mock(String url, String file) throws IOException {

        ClassPathResource resource = new ClassPathResource("mock/response/" + file + ".txt");
        String content = StringUtils.join(FileUtils.readLines(resource.getFile(), "UTF-8"), "\n");

        stubFor(get(urlPathEqualTo(url)).willReturn(aResponse()
                .withBody(content)
                .withStatus(200)));
    }
}
