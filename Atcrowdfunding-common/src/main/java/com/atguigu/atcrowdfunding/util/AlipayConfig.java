package com.atguigu.atcrowdfunding.util;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @Auther: dc
 * @Date: 2019/12/20 10:35
 * @Description:
 */
public class AlipayConfig {
    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016100100641838";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDB0zYcaZJ4Ec9sIQL2mtOI90SPmmO+YpII26ka/+Sj2d58+Lcq8NPeGbS4/c0AOOBRE3IZns2NrbO2fOGwZmcz/ZOTjusyNbfyj62A1fDEcbvpkOdQ/QDnSAM7XCYTTF7lK1j+aVOcrCO00DGMg5/W6kqLVoIvTJW8+cxXDOnabFaTY6gLHIXVOmuUg3JzsbGcrvb6VPBwwxmKnEhvtCyTdqYE0GbJ5oys0vQhlR29lj0KV/ZOWFOWusfVfegb4t41hmwIcvZSbJKPx00SZxYOv07Z/oMz4LuO6XE2/grGhLo5r8JivLWD9Kr6lwC2aDkEixz3mIfhHYPkeApxR5aVAgMBAAECggEATsXTSQrS/z9lyHFGLglQjWrJD3/Up8yRw03a1WFIgBsMpEfE0Yvc33gdDc4AYeW7rNe1WJpEuh7tWe45HyMPMH8PWjoMdnSElO01u5W3a285yrwcdrrMgjKvNZHG27L/Ii5S1drio1sGi/bNHGlmLcc8kpKPCEAc3xe6p2pVJHKL9FsZk3Tb99TA5BmzMch8obJvNvUaC0wozVcFUTb8v59G5exyUk2Q2CEIU/vmd/Ufd2hIcJ1EjgccRefWzIwpdvunPOWTZjkZAf+VvijrvFbKBTbAnluQzf+2D+N6+UhhxucnI0fuzPWV3+eVYuarDLvdAW0DvQa44wzP2UZxxQKBgQDtTPx9lVLrw5csaZicnVbKcTGXvCVhk/8F/z5LhdE0fgLJBepiIt/t0aqhoHe+LSnL/NaMkUGebR2GVjfZxwqV/ynpdwKwqP9ZTotp62aQZ4wIp7HX56MR0q7r495IQhdL1C5KFzM2w8OVsVfLt6EOput+yP5X4deYjlo+pwSd9wKBgQDRGTMylgUfCUJbVJsgAQN7s/E9DSUyanv4dtk1pcSAUdm4U4OaxBKYTdUcpE8+GTyD2PtHEj0wrQ23UsWQV19+zcTSEYWlIkkQNBvRBxX037hP2x3VlkQmMCMlL4IxD3jtCKQUiLaLBBVCe+Ab4D30E5taWfzu/qnF6WeTAUi80wKBgA4wr1yXJFlC3fRi8CRVaE48zJllP9sA7ZwfMktz2hnIe0QoHWb27OscwZZesSpnKvuux+mp+gGORti0qSxVsbVch9YVLsz/nh1qC/4ufHigKIiP9puRl3fGYH1sp+ssQJZhPs9haceZl0WJUFW3SmNE6gov3L3GnIzb3lumwyjrAoGBALxSVvMbfHdMbpA1LhVv9EQod4AF49d5YjgQSmbv6n+ruEw0I1Zze0/i4aqPLbN/JSqWdRG4kpXRdoYCrV7n4mEhUD/mehiVPXqzg84J9LN7/tMlRMSGNtCyECVAmaA4EDBj1OuNieLAhalASkKw/vth4UxJue1CPYO7SIZ05J7zAoGAYL9VhCxdONEzsxJXQfQq4OScGJ5r0yoQcQBu3UXgjmcrqIohQPP7+iBGE6BI/VPa6cpBSzQmD39aONJIRCwNJzT+G1LQacPoPBhfOcZhxcaxc2ZGGEY6yZNVW1E2txU6rF2BK4yZXIMcYJkM9A0OcOKJ1S1j+y4b7GvZPwYqdM8=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsh/o0kalt26/TRgVYY0BUKPgmurniZTP17tAblCZ3OSNMil3RGYlRyLIvxIBte3seiRChZ5hYRkFF5xzV1ognOgP87nhFRQQhwAVRDVY6UKTi9+VXtlmrU5Q8/11ZfBYCKiUDBAPnliU0TqMiXbDI4zcFkx4F7OTUJUeXnqBZD76NGPzuLEI5gprJUFwJlMdHeedcFU8UYvf6DDU6XZrtG6pJ739Ai5g0+2YeBiKftVzAonOBW2xpvlVQLNVgQ2/NlbG/fZGodJ5f632vNSTwlTp8IE62HKHENOVGRwUQA4B09IckNQMUWRvnQ9Iz2UkNCi7tcGSucFJgr2YfkYb5QIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://28r30857o8.zicp.vip/homepage/notifyOrder.do";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://28r30857o8.zicp.vip/homepage/paySuccess.do";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
