package store.sophi.xjr.autoconfigure;

import com.baidu.aip.face.AipFace;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
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
import store.sophi.xjr.model.AiFaceModel;
import store.sophi.xjr.util.AiFaceTemplate;

/**
 * @program: spring-boot-ai-face-starter
 * @description: 注解驱动加载bean
 * @author: xjr
 * @create: 2020-06-01 23:20
 **/
@Slf4j
public class AnnotationFaceRegistry implements ImportBeanDefinitionRegistrar, EnvironmentAware, BeanFactoryAware {

    private Environment environment;

    private BeanFactory beanFactory;

    @SneakyThrows
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        AnnotationAttributes annotationAttributes=AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(EnableAiFace.class.getName()));
        if (!annotationAttributes.getBoolean("open")){
            beanDefinitionRegistry.removeBeanDefinition("aipFace");
            log.warn("人脸识别组件已注销");
            return;
        }
        String appId=environment.getProperty("ai.face.appId");
        String apiKey=environment.getProperty("ai.face.apiKey");
        String secretKey=environment.getProperty("ai.face.secretKey");
        if (StringUtils.isEmpty(appId) || StringUtils.isEmpty(apiKey) ||StringUtils.isEmpty(secretKey)){
            throw new Exception("配置文件中没有配置百度开发者平台的账号密钥!!");
        }
        BeanDefinition beanDefinition=BeanDefinitionBuilder.genericBeanDefinition(AipFace.class).getBeanDefinition();
        beanDefinition.setScope("singleton");
        try {
            AiFaceModel faceModel=beanFactory.getBean(AiFaceModel.class);
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(faceModel.getAppId());
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(faceModel.getApiKey());
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(faceModel.getSecretKey());
        }catch (NoSuchBeanDefinitionException ex){
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(appId);
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(apiKey);
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(secretKey);
        }
        beanDefinitionRegistry.registerBeanDefinition("aipFace",beanDefinition);
        Class<? extends AiFaceTemplate> templateClass=annotationAttributes.getClass("executor");
        BeanDefinitionBuilder templateBuilder=BeanDefinitionBuilder.genericBeanDefinition(templateClass);
        templateBuilder.setScope("singleton");
        BeanDefinition templatesDefinition=templateBuilder.getBeanDefinition();
        templatesDefinition.setDependsOn("aipFace");
        beanDefinitionRegistry.registerBeanDefinition("aiFaceTemplate",templatesDefinition);
        log.info("人脸识别组件初始化完毕");


    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment=environment;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory=beanFactory;
    }
}
