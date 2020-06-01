package store.sophi.xjr.autoconfigure;

import com.baidu.aip.face.AipFace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import store.sophi.xjr.model.AiFaceModel;

/**
 * @program: spring-boot-ai-face-starter
 * @description: 自动装配主配置类
 * @author: xjr
 * @create: 2020-05-31 00:47
 **/

@ConditionalOnMissingBean(value = {AipFace.class})
@ConditionalOnProperty(prefix = "ai.face",name = "open",havingValue = "true")
@EnableConfigurationProperties(value = {AiFaceModel.class})
@Slf4j
public class MainConfiguration {

    public void init(){
        log.debug("人脸识别组件开始初始化-----{}",this.getClass().getName());
    }
}
