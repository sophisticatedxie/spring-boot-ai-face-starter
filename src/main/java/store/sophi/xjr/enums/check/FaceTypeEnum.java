package store.sophi.xjr.enums.check;

import lombok.Getter;

/**
 * @program: spring-boot-ai-face-starter
 * @description: 人脸类型枚举
 * @author: xjr
 * @create: 2020-06-11 10:41
 **/
@Getter
public enum FaceTypeEnum {

    LIVE("LIVE","生活照"),

    IDCARD("IDCARD","身份证芯片照"),

    WATERMARK("WATERMARK","水印证件照"),

    CERT("CERT","证件照片");
    private String key="face_type";

    private String code;

    private String message;

    FaceTypeEnum(String code,String message){
        this.code=code;
        this.message=message;
    }


}
