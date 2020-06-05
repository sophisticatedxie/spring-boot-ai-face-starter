package store.sophi.xjr.enums;

/**
 * @program: spring-boot-ai-face-starter
 * @description: 百度错误码结果枚举
 * @author: xjr
 * @create: 2020-06-01 11:01
 **/

public enum ResultEnum {

    SUCCESS(0,"成功","SUCCESS"),

    UNKNOWN_ERROES(1,"服务器内部错误，请再次请求","Unknown error"),

    UNSUPPORTED_OPENAPI_METHOD(3,"调用的API不存在，请检查请求URL后重新尝试，一般为URL中有非英文字符，如“-”，可手动输入重试","Unsupported openapi method"),

    OPEN_API_REQUEST_LIMIT_REACHED(4,"集群超限额，请再次请求","Open api request limit reached"),

    NO_PERMISSION_TO_ACCESS_DATA(6,"无权限访问该用户数据，创建应用时未勾选相关接口","No permission to access data"),

    GET_SERVICE_TOKEN_FAILED(13,"获取token失败","Get service token failed"),

    IAM_CERTIFICATION_FAILED(14,"IAM 鉴权失败","IAM Certification failed"),

    APP_NOT_EXSITS_OR_CREATE_FAILED(15,"应用不存在或者创建失败","app not exsits or create failed"),

    OPEN_API_DAILY_REQUEST_LIMIT_REACHED(17,"每天请求量超限额","Open api daily request limit reached"),

    OPEN_API_QPS_REQUEST_LIMIT_REACHED(18,"QPS超限额","Open api qps request limit reached"),

    OPEN_API_TOTAL_REQUEST_LIMIT_REACHED(19,"请求总量超限额","Open api total request limit reached"),

    INVALID_PARAMETER(100,"无效的access_token参数，请检查后重新尝试","Invalid parameter"),

    ACCESS_TOKEN_INVALID_OR_NO_LONGER_VALID(110,"access_token无效","Access token invalid or no longer valid"),

    ACCESS_TOKEN_EXPIRED(111,"access token过期","Access token expired"),

    PARAM_IS_NULL(222001,"必要参数未传入","param[] is null"),

    PARAM_FORMAT_ERROR(222002,"参数格式错误","param[start] format error"),

    PARAM_LENGTH_FORMAT_ERROR(222003,"参数格式错误","param[length] format error"),

    PARAM_IDLIST_FORMAT_ERROR(222004,"参数格式错误","param[op_app_id_list] format error"),

    PARAM_GROUP_ID_LIST_FORMAT_ERROR(222005,"参数格式错误","param[group_id_list] format error"),

    GROUP_ID_FORMAT_ERROR(222006,"参数格式错误","group_id format error"),

    UID_FORMAT_ERROR(222007,"参数格式错误","uid format error"),

    FACE_ID_FORMAT_ERROR(222008,"参数格式错误","face_id format error"),

    QUALITY_CONF_FORMAT_ERROR(222009,"参数格式错误","quality_conf format error"),

    USER_INFO_FORMAT_ERROR(222010,"参数格式错误","user_info format error"),

    PARAM_UID_LIST__FORMAT_ERROR(222011,"参数格式错误","param[uid_list] format error"),

    PARAM_OP_APP_ID__FORMAT_ERROR(222012,"参数格式错误","param[op_app_id] format error"),

    PARAM_IMAGE_FORMAT_ERROR(222013,"参数格式错误","param[image] format error"),

    PARAM_APP_ID__FORMAT_ERROR(222014,"参数格式错误","param[app_id] format error"),

    PARAM_IMAGE_TYPE__FORMAT_ERROR(222015,"参数格式错误","param[image_type] format error"),

    PARAM_MAX_FACE_NUM__FORMAT_ERROR(222016,"参数格式错误","param[max_face_num] format error"),

    PARAM_FACE_FIELD__FORMAT_ERROR(222017,"参数格式错误","param[face_field] format erro"),

    PARAM_USER_ID__FORMAT_ERROR(222018,"参数格式错误","param[user_id] format error"),

    PARAM_QUALITY_CONTROL__FORMAT_ERROR	(222019,"参数格式错误","param[quality_control] format error"),

    PARAM_LIVENESS_CONTROL__FORMAT_ERROR(222020,"参数格式错误","param[liveness_control] format error"),

    PARAM_MAX_USER_NUM__FORMAT_ERROR(222021,"参数格式错误","param[max_user_num]\n" +
            "format error"),

    PARAM_ID_CARD_NUMBER__FORMAT_ERROR(222022,"参数格式错误","param[id_card_number] format error"),

    PARAM_NAME__FORMAT_ERROR(222023,"参数格式错误","param[name] format error"),

    PARAM_FACE_TYPE__FORMAT_ERROR(222024,"参数格式错误","param[face_type] format error"),

    PARAM_FACE_TOKEN__FORMAT_ERROR(222025,"参数格式错误","param[face_token] format error"),

    PARAM_MAX_STAR_NUM__FORMAT_ERROR(222026,"参数格式错误","param[max_star_num] format error"),

    NETWORK_NOT_AVAILABLE(222201,"服务端请求失败","network not available"),

    PIC_NOT_HAS_FACE(222202,"图片中没有人脸","pic not has face"),

    IMAGE_CHECK_FAIL(222203,"无法解析人脸","image check fail"),

    IMAGE_URL_DOWNLOAD_FAIL(222204,"从图片的url下载图片失败","image_url_download_fail"),

    NETWORK_NOT_AVAILABLEL(222205,"服务端请求失败","network not availablel"),

    RTSE_SERVICE_RETURN_FAIL(222206,"服务端请求失败","rtse service return fail"),

    MATCH_USER_IS_NOT_FOUND(222207,"未找到匹配的用户","match user is not found"),

    THE_NUMBER_OF_IMAGE_IS_INCORRECT(222208,"图片的数量错误","the number of image is incorrect"),

    FACE_TOKEN_NOT_EXIST(222209,"face token不存在","face token not exist"),

    ADD_FACE_FAIL(222300,"人脸图片添加失败","add face fail"),

    GET_FACE_FAIL(222301,"获取人脸图片失败","get face fail"),

    SYSTEM_ERROR(222302,"服务端请求失败","system error"),

    GET_FACE_FAILD(222303,"获取人脸图片失败","get face fail"),

    GROUP_IS_NOT_EXIST(223100,"操作的用户组不存在","group is not exist"),

    GROUP_IS_ALREADY_EXIST(223101,"该用户组已存在","group is already exist"),

    USER_IS_ALREADY_EXIST(223102,"该用户已存在","user is already exist"),

    USER_IS_NOT_EXIST(223103,"找不到该用户","user is not exist"),

    GROUP_LIST_IS_TOO_LARGE(223104,"group_list包含组数量过多","group_list is too large"),

    FACE_IS_ALREADY_EXIST(223105,"该人脸已存在","face is already exist"),

    FACE_IS_NOT_EXIST(223106,"该人脸不存在","face is not exist"),

    UID_LIST_IS_TOO_LARGE(223110,"uid_list包含数量过多","uid_list is too large"),

    DST_GROUP_IS_NOT_EXIST(223111,"目标用户组不存在","dst group is not exist"),

    QUALITY_CONF_FORMAT_ERRORD(223112,"quality_conf格式不正确","quality_conf format error"),

    FACE_IS_COVERED(223113,"人脸有被遮挡","face is covered"),

    FACE_IS_FUZZY(223114,"人脸模糊","face is fuzzy"),

    FACE_LIGHT_IS_NOT_GOOD(223115,"人脸光照不好","face light is not good"),

    INCOMPLETE_FACE(223116,"人脸不完整","incomplete face"),

    APP_LIST_IS_TOO_LARGE(223117,"app_list包含app数量过多","app_list is too large"),

    QUALITY_CONTROL_ERROR(223118,"质量控制项错误","quality control error"),

    LIVENESS_CONTROL_ITEM_ERROR(223119,"活体控制项错误","liveness control item error"),

    LIVENESS_CHECK_FAIL(223120,"活体检测未通过","liveness check fail"),

    LEFT_EYE_IS_OCCLUSION(223121,"质量检测未通过 左眼遮挡程度过高","left eye is occlusion"),

    RIGHT_EYE_IS_OCCLUSION(223122,"质量检测未通过 右眼遮挡程度过高","right eye is occlusion"),

    LEFT_CHEEK_IS_OCCLUSION(223123,"质量检测未通过 左脸遮挡程度过高","left cheek is occlusion"),

    RIGHT_CHEEK_IS_OCCLUSION(223124,"质量检测未通过 右脸遮挡程度过高","right cheek is occlusion"),

    CHIN_CONTOUR_IS_OCCLUSION(223125,"质量检测未通过 下巴遮挡程度过高","chin contour is occlusion"),

    NOSE_IS_OCCLUSION(223126,"质量检测未通过 鼻子遮挡程度过高","nose is occlusion"),

    MOUTH_IS_OCCLUSION(223127,"质量检测未通过 嘴巴遮挡程度过高","mouth is occlusion"),

    POLICE_PICTURE_IS_NONE_OR_LOW_QUALITY(222350,"公安网图片不存在或质量过低","police picture is none or low quality"),

    ID_NUMBER_AND_NAME_NOT_MATCH_OR_ID_NUMBER_NOT_EXIST(222351,"身份证号与姓名不匹配或该身份证号不存在","id number and name not match or id number not exist"),

    NAME_FORMAT_ERROR(222352,"身份证名字格式错误","name format error"),

    ID_NUMBER_FORMAT_ERROR(222353,"身份证号码格式错误","id number format error"),

    ID_NUMBER_NOT_EXIST(222354,"公安库里不存在此身份证号","id number not exist"),

    POLICE_PICTURE_NOT_EXIST(222355,"身份证号码正确，公安库里没有对应的照片","police picture not exist"),

    INVALID_NAME_OR_ID_NUMBER(222360,"身份证号码或名字非法（公安网校验不通过）","invalid name or id number"),

    SYSTEM_BUSY(222900,"系统繁忙","system busy"),

    NOT_INITIALIZING(-1,"人脸识别组件没有正确初始化","not initializing");





    private int code;

    private String Chimsg;

    private String enMsg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getChimsg() {
        return Chimsg;
    }

    public void setChimsg(String chimsg) {
        Chimsg = chimsg;
    }

    public String getEnMsg() {
        return enMsg;
    }

    public void setEnMsg(String enMsg) {
        this.enMsg = enMsg;
    }

    ResultEnum(int code, String Chimsg, String enMsg){
        this.code=code;
        this.Chimsg=Chimsg;
        this.enMsg=enMsg;
    }

    public static ResultEnum getByCode(int code){
        ResultEnum[] enmus=ResultEnum.values();
        for (int i = 0; i <enmus.length ; i++) {
            if (enmus[i].getCode()==code){
                return enmus[i];
            }
        }
        return null;
    }


}
