package store.sophi.xjr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: spring-boot-ai-face-starter
 * @description: 人脸数据对象模型
 * @author: xjr
 * @create: 2020-05-31 01:04
 **/
@Data
@ConfigurationProperties(prefix = "ai.face",ignoreUnknownFields = true,ignoreInvalidFields = true)
@AllArgsConstructor
public class AiFaceModel {

    private String appId;


    private String apiKey;


    private String secretKey;


}
