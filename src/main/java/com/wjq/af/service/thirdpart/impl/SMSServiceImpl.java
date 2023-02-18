package com.wjq.af.service.thirdpart.impl;

import com.wjq.af.dto.request.thirdpart.SMSSendDtoReq;
import com.wjq.af.dto.request.thirdpart.SMSValidateDtoReq;
import com.wjq.af.service.thirdpart.SMSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 短信 服务实现类
 *
 * @author yixihan
 * @date 2023/2/18 11:21
 */
@Slf4j
@Service
public class SMSServiceImpl implements SMSService {

//    @Resource
//    private SMSProp smsProp;

//    @Resource
//    private CodeProp codeProp;
//
//    @Resource
//    private CodeServiceImpl codeService;

//    @Resource
//    private TemplateSmsService templateSmsService;
//
//
//    @Override
//    public void send(SMSSendDtoReq dtoReq) {
//
//        SMSTemplateEnums smsType = SMSTemplateEnums.valueOf (dtoReq.getType ());
//        String keyName = getRedisKey (dtoReq.getMobile (), smsType);
//        String code = codeService.getCode(keyName);
//        String templateId = templateSmsService.getSMSTemplateId (smsType.getId ());
//
//        try{
//            // 实例化一个认证对象，入参需要传入腾讯云账户 secretId，secretKey,此处还需注意密钥对的保密
//            // 密钥可前往https://console.cloud.tencent.com/cam/capi网站进行获取
//            Credential cred = new Credential(smsProp.getSecretId (), smsProp.getSecretKey ());
//            // 实例化一个http选项，可选的，没有特殊需求可以跳过
//            HttpProfile httpProfile = new HttpProfile();
//            httpProfile.setEndpoint("sms.tencentcloudapi.com");
//            // 实例化一个client选项，可选的，没有特殊需求可以跳过
//            ClientProfile clientProfile = new ClientProfile();
//            clientProfile.setHttpProfile(httpProfile);
//            // 实例化要请求产品的client对象,clientProfile是可选的
//            SmsClient client = new SmsClient(cred, "ap-guangzhou", clientProfile);
//            // 实例化一个请求对象,每个接口都会对应一个request对象
//            SendSmsRequest req = new SendSmsRequest();
//            String[] phoneNumberSet1 = {dtoReq.getMobile ()};
//            req.setPhoneNumberSet(phoneNumberSet1);
//            req.setSmsSdkAppId(smsProp.getSmsSdkAppId ());
//            req.setSignName(smsProp.getSignName ());
//            req.setTemplateId(templateId);
//            String[] templateParamSet1 = {code, String.valueOf (codeProp.getTimeOut ())};
//            req.setTemplateParamSet(templateParamSet1);
//            // 返回的resp是一个SendSmsResponse的实例，与请求对象对应
//            SendSmsResponse resp = client.SendSms(req);
//            log.info ("response : {}", SendSmsResponse.toJsonString(resp));
//            SendStatus sendStatus = resp.getSendStatusSet ()[0];
//
//            Assert.isFalse ("Ok".equals (sendStatus.getCode ()), BizCodeEnum.SMS_SEND_ERR);
//        } catch (TencentCloudSDKException e) {
//            log.error (e.getMessage ());
//            throw new BizException (BizCodeEnum.SMS_SEND_ERR);
//        }
//    }
//
//    @Override
//    public void validate(SMSValidateDtoReq dtoReq) {
//        // 生成 keyName
//        SMSTemplateEnums smsType = SMSTemplateEnums.valueOf (dtoReq.getMobileType ());
//        String keyName = getRedisKey (dtoReq.getMobile (), smsType);
//        codeService.validate (keyName, dtoReq.getCode ());
//    }

//    /**
//     * 获取 redis key
//     *
//     * @param mobile 手机号
//     * @param smsType 手机类型 {@link SMSTemplateEnums}
//     * @return redis key
//     */
//    private String getRedisKey (String mobile, SMSTemplateEnums smsType) {
//        String key;
//        switch (smsType) {
//            case LOGIN:
//                key = String.format (smsProp.getLoginKey (), mobile);
//                break;
//            case REGISTER:
//                key = String.format (smsProp.getRegisterKey (), mobile);
//                break;
//            case RESET_PASSWORD:
//                key = String.format (smsProp.getUpdatePasswordKey (), mobile);
//                break;
//            case COMMON:
//                key = String.format (smsProp.getCommonKey (), mobile);
//                break;
//            default:
//                throw new BizException (BizCodeEnum.PARAMS_VALID_ERR);
//        }
//
//        return key;
//    }
    
    @Override
    public void send(SMSSendDtoReq dtoReq) {
    
    }
    
    @Override
    public void validate(SMSValidateDtoReq dtoReq) {
    
    }
}