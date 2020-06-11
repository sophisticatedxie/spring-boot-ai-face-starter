package store.sophi.xjr.util;

import cn.hutool.core.codec.Base64Encoder;
import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import com.google.common.base.Joiner;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import store.sophi.xjr.enums.base.ImageTypeEnum;
import store.sophi.xjr.enums.base.LivenessControlEnum;
import store.sophi.xjr.enums.check.FaceFieldEnum;
import store.sophi.xjr.enums.check.FaceNumEnum;
import store.sophi.xjr.enums.check.FaceTypeEnum;
import store.sophi.xjr.enums.response.ResultEnum;
import store.sophi.xjr.exception.ApiException;
import store.sophi.xjr.model.RequestMap;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: spring-boot-ai-face-starter
 * @description: 人脸操作工具类
 * @author: xjr
 * @create: 2020-05-31 01:07
 **/
@Slf4j
@Getter
@Setter
public class AiFaceTemplate implements InitializingBean {



    @Autowired(
            required = false
    )
    protected AipFace aipFace;


    protected final ThreadLocal<RequestMap> requestMap=ThreadLocal.<RequestMap>withInitial(RequestMap::new);

    /**
     *
     * 人脸对比，返回相似度
     * @param img1: base64
     * @param img2: base64
     * @author xiejiarong
     * @date 2020年06月03日 9:24
     */
    public String compare(String img1, String img2) {
        MatchRequest req1 = new MatchRequest(img1, "BASE64");
        MatchRequest req2 = new MatchRequest(img2, "BASE64");
        ArrayList<MatchRequest> requests = new ArrayList();
        requests.add(req1);
        requests.add(req2);
        JSONObject res = this.aipFace.match(requests);
        return res.toString(2);
    }

    /**
     *
     * 人脸对比，返回相似度
     * @param img1: 图片流
     * @param  img2 图片流
     * @author xiejiarong
     * @date 2020年06月03日 9:25
     */
    public String compare(MultipartFile img1,MultipartFile img2){
        String[] imgs=stream2Base64(img1,img2);
        return this.compare(imgs[0],imgs[1]);
    }

    /**
     *
     *人脸检测
     * @param img:  人脸图片
     * @return String
     * @author xiejiarong
     * @date 2020年06月04日 18:32
     */
    private String checkFace(String img,ImageTypeEnum imageType) {
        return this.aipFace.detect(img, imageType.getValue(), requestMap.get()).toString(2);
    }

    /**
     *
     * @description 根据图片文件流检测人脸
     * @param img: 图片流
     * @return String 检测结果json字符串
     * @author xiejiarong
     * @date 2020年06月08日 15:59
     */
    public String checkFace(MultipartFile img, ImageTypeEnum imageType, FaceNumEnum faceNum, FaceTypeEnum faceType, LivenessControlEnum livenessControl, FaceFieldEnum... fieldEnums) {
        String imgBase64=this.stream2Base64(img);
        List<FaceFieldEnum> requestList=null;
        String requestStr="";
        if (fieldEnums!=null){
            requestList=Arrays.asList(fieldEnums);
            requestStr=Joiner.on(",").join(requestList);
        }
        requestMap.get()
        .lambdaCombine(faceNum,FaceNumEnum::getKey,FaceNumEnum::getValue)
        .lambdaCombine(faceType,FaceTypeEnum::getKey,FaceTypeEnum::getCode)
        .lambdaCombine(livenessControl,LivenessControlEnum::getKey,LivenessControlEnum::getCode);
        if (!StringUtils.isEmpty(requestStr)){
            requestMap.get().put("face_field",requestStr);
        }
       return this.checkFace(imgBase64,imageType);
    }

    private String stream2Base64(MultipartFile file) {
        InputStream inputStream=null;
        byte[] strByte=null;
        try {
            inputStream = file.getInputStream();
            strByte = new byte[inputStream.available()];
            inputStream.read(strByte);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Base64Encoder.encode(strByte);
    }

    private  String[] stream2Base64(MultipartFile ... files){
        List<MultipartFile> list=Arrays.asList(files);
        return list.stream().map(this::stream2Base64).collect(Collectors.toList()).toArray(new String[files.length]);
    }

    public void checkAip() throws ApiException {
        if (this.aipFace == null) {
            throw new ApiException(ResultEnum.NOT_INITIALIZING);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("人脸操作工具类初始化结束,注入人脸客户端信息：{}", this.aipFace);
    }

    /**
     *
     * @description 移除请求map，防止线程池并发问题
     * @author xiejiarong
     * @date 2020年06月08日 17:03
     */
    public final void removeRequestMap(){
        this.requestMap.remove();
    }


}
