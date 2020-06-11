package store.sophi.xjr.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import store.sophi.xjr.enums.response.ResultEnum;


/**
 * @program: spring-boot-ai-face-starter
 * @description: 自定义异常
 * @author: xjr
 * @create: 2020-05-31 01:15
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ApiException extends Exception{
    private ResultEnum resultEnum;

    public ApiException(ResultEnum resultEnum) {
        super(resultEnum.getChimsg());
    }


}
