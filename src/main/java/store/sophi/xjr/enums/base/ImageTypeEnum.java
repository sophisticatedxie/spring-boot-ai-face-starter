package store.sophi.xjr.enums.base;

import lombok.Getter;

/**
 * @program: spring-boot-ai-face-starter
 * @description: 图片类型枚举
 * @author: xjr
 * @create: 2020-06-11 09:52
 **/
@Getter
public enum ImageTypeEnum {

    BASE64("BASE64"),

    URL("URL"),

    FACE_TOKEN("FACE_TOKEN");


    private String code="image_type";

    private String value;

    ImageTypeEnum(String value){
       this.value=value;
    }




}
