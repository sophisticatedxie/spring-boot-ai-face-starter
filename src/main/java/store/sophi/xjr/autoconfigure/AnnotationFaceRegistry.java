package store.sophi.xjr.autoconfigure;

import com.baidu.aip.face.AipFace;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;
import store.sophi.xjr.annotations.EnableAiFace;

/**
 * @program: spring-boot-ai-face-starter
 * @description: 注解驱动加载bean
 * @author: xjr
 * @create: 2020-06-01 23:20
 **/
@Slf4j
public class AnnotationFaceRegistry implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private Environment environment;

    @SneakyThrows
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        AnnotationAttributes annotationAttributes=AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(EnableAiFace.class.getName()));
        if (!annotationAttributes.getBoolean("open")){
            beanDefinitionRegistry.removeBeanDefinition("aipFace");
            log.warn("人脸识别组件已注销");
            return;
        }
        String appId=environment.getProperty("ai.face.app-id");
        String apiKey=environment.getProperty("ai.face.app-key");
        String secretKey=environment.getProperty("ai.face.secret-key");
        if (StringUtils.isEmpty(appId) || StringUtils.isEmpty(apiKey) ||StringUtils.isEmpty(secretKey)){
            throw new Exception("配置文件中没有配置百度开发者平台的账号密钥!!");
        }
        BeanDefinition beanDefinition;
        BeanDefinitionBuilder builder;
        try {
            beanDefinition=beanDefinitionRegistry.getBeanDefinition("aiFaceModel");
            beanDefinition.getPropertyValues().getPropertyValue("appId");
        }catch (NoSuchBeanDefinitionException ex){
            builder= BeanDefinitionBuilder.genericBeanDefinition(AipFace.class);
            builder.addConstructorArgValue(appId);
            builder.addConstructorArgValue(apiKey);
            builder.addConstructorArgValue(secretKey);
            builder.setScope("singleton");
            beanDefinitionRegistry.registerBeanDefinition("aipFace",builder.getBeanDefinition());
        }




    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment=environment;
    }
}
