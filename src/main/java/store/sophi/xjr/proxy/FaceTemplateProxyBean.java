package store.sophi.xjr.proxy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import store.sophi.xjr.util.AiFaceTemplate;

/**
 * @program: spring-boot-ai-face-starter
 * @description: 代理配置类
 * @author: xjr
 * @create: 2020-06-03 09:39
 **/

public class FaceTemplateProxyBean<T extends  AiFaceTemplate> implements FactoryBean<T>, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private Class<? extends AiFaceTemplate> proxyTarget;

    public FaceTemplateProxyBean(Class proxyTarget){
        this.proxyTarget=proxyTarget;
    }


    @Override
    public T getObject() throws Exception {
        AiFaceTemplate template=applicationContext.getAutowireCapableBeanFactory().createBean(proxyTarget);
        template.init();
        FaceTemplateInterceptor methodInterceptor=new FaceTemplateInterceptor();
        methodInterceptor.setBaseTemplate(template);
        return (T) FaceTemplateInterceptor.createProxyObj(methodInterceptor,proxyTarget);
    }

    @Override
    public Class<?> getObjectType() {
        return proxyTarget;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }


}
