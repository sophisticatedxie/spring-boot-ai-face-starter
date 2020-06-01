package store.sophi.xjr.autoconfigure;

import com.baidu.aip.face.AipFace;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

/**
 * @program: spring-boot-ai-face-starter
 * @description: 自动装配主配置类
 * @author: xjr
 * @create: 2020-05-31 00:47
 **/

@ConditionalOnMissingBean(value = {AipFace.class})

public class MainConfiguration {
}
