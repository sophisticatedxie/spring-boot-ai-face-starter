package store.sophi.xjr.autoconfigure;

import com.baidu.aip.face.AipFace;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;
import store.sophi.xjr.annotations.EnableAiFace;
import store.sophi.xjr.proxy.FaceTemplateProxyBean;
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
            log.warn("人脸识别功能没有开启");
            return;
        }
        String appId=annotationAttributes.getString("appId");
        String apiKey=annotationAttributes.getString("apiKey");
        String secretKey=annotationAttributes.getString("secretKey");
        if (StringUtils.isEmpty(appId)
        || StringUtils.isEmpty(apiKey)
        || StringUtils.isEmpty(secretKey)){
             appId=environment.getProperty("ai.face.appId");
             apiKey=environment.getProperty("ai.face.apiKey");
             secretKey=environment.getProperty("ai.face.secretKey");
            if (StringUtils.isEmpty(appId) || StringUtils.isEmpty(apiKey) ||StringUtils.isEmpty(secretKey)){
                throw new Exception("配置文件中没有配置百度开发者平台的账号密钥!!");
            }
        }
        BeanDefinition beanDefinition=BeanDefinitionBuilder.genericBeanDefinition(AipFace.class).getBeanDefinition();
        beanDefinition.setScope("singleton");
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(appId);
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(apiKey);
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(secretKey);
        beanDefinitionRegistry.registerBeanDefinition("aipFace",beanDefinition);
        Class<? extends AiFaceTemplate> templateClass=annotationAttributes.getClass("executor");
        BeanDefinitionBuilder templateBuilder=BeanDefinitionBuilder.genericBeanDefinition(templateClass);
        GenericBeanDefinition genericBeanDefinition= (GenericBeanDefinition) templateBuilder.getBeanDefinition();
        genericBeanDefinition.setDependsOn("aipFace");
        genericBeanDefinition.setBeanClass(FaceTemplateProxyBean.class);
        genericBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue(templateClass.getName());
        beanDefinitionRegistry.registerBeanDefinition("aiFaceTemplate",genericBeanDefinition);
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
