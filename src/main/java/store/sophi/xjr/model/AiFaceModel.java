package store.sophi.xjr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @program: spring-boot-ai-face-starter
 * @description: 人脸数据对象模型
 * @author: xjr
 * @create: 2020-05-31 01:04
 **/
@Data
@ConfigurationProperties(prefix = "ai.face")
@Configuration("aiFaceModel")
@Slf4j
public class AiFaceModel  {

    private String appId;


    private String apiKey;


    private String secretKey;

}
