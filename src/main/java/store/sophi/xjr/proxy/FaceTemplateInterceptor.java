package store.sophi.xjr.proxy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import store.sophi.xjr.enums.ResultEnum;
import store.sophi.xjr.exception.ApiException;
import store.sophi.xjr.util.AiFaceTemplate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program: spring-boot-ai-face-starter
 * @description: 人脸模板工具拦截类
 * @author: xjr
 * @create: 2020-06-03 09:51
 **/
@Setter
@Getter
public class FaceTemplateInterceptor<T extends AiFaceTemplate> implements MethodInterceptor {
    T baseTemplate;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        baseTemplate.checkAip();
        Object result;
        try {
            result=method.invoke(baseTemplate,objects);
            if (method.getReturnType()==String.class){
                JSONObject json= JSON.parseObject((String) result);
                if (!json.getInteger("error_code").equals(0)){
                    throw new ApiException(ResultEnum.getByCode(json.getInteger("error_code")));
                }
            }
        }catch (InvocationTargetException ex){
            throw  new Exception(ex.getMessage());
        }
        return result;
    }

    public  static AiFaceTemplate  createProxyObj(MethodInterceptor interceptor,Class<? extends AiFaceTemplate> clazz){
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(interceptor);
        return (AiFaceTemplate) enhancer.create();
    }


}
