package store.sophi.xjr.enums.base;

import lombok.Getter;

/**
 * @program: spring-boot-ai-face-starter
 * @description: 活体检测控制枚举
 * @author: xjr
 * @create: 2020-06-11 10:45
 **/
@Getter
public enum LivenessControlEnum {

    NONE("NONE","不进行控制"),

    LOW("LOW","较低的活体要求"),

    NORMAL("NORMAL","一般的活体要求"),

    HIGH("HIGH","较高的活体要求");

    private String key="liveness_control";

    private String code;

    private String message;

    LivenessControlEnum(String code,String message){
        this.code=code;
        this.message=message;
    }
}
