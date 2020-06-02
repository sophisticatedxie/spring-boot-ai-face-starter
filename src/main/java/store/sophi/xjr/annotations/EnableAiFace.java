package store.sophi.xjr.annotations;

import org.springframework.context.annotation.Import;
import store.sophi.xjr.autoconfigure.AnnotationFaceRegistry;
import store.sophi.xjr.model.AiFaceModel;
import store.sophi.xjr.util.AiFaceTemplate;

import java.lang.annotation.*;

/**
 * @program: spring-boot-ai-face-starter
 * @description: 人脸功能开启控制注解
 * @author: xjr
 * @create: 2020-05-31 01:03
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(value = {AnnotationFaceRegistry.class})
public @interface EnableAiFace {

    boolean open() default true;

    Class<? extends AiFaceTemplate> executor() default AiFaceTemplate.class;



}
