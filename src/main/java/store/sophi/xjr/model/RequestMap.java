package store.sophi.xjr.model;

import java.util.HashMap;
import java.util.function.Function;

/**
 * @program: spring-boot-ai-face-starter
 * @description: 请求map封装
 * @author: xjr
 * @create: 2020-06-11 10:22
 **/

public class RequestMap extends HashMap<String,String> {


    public <T extends Enum> RequestMap lambdaCombine(T t,Function<? super T,? extends String> key,Function<? super T,? extends String> value){
        if (t==null){
            return this;
        }
        super.put(key.apply(t),value.apply(t));
        return this;
    }

}
