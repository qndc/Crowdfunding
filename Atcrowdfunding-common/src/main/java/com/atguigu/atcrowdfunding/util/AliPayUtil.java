package com.atguigu.atcrowdfunding.util;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.*;
import com.atguigu.atcrowdfunding.bean.AliPayBean;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Auther: dc
 * @Date: 2019/12/19 19:24
 * @Description:
 */
@Component
public class AliPayUtil {

    private static  AlipayClient alipayClient ;

    static {
        //获得初始化的AlipayClient
        alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
    }

    /**
     * 	支付
     * @param bean
     * @return
     * @throws IOException
     * @throws AlipayApiException
     */
    public static String pay(AliPayBean bean) throws IOException, AlipayApiException {
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        alipayRequest.setBizContent(JSON.toJSONString(bean));
        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        return  result;
    }

    /**
     *	 退款
     * @param bean
     * @return
     * @throws IOException
     * @throws AlipayApiException
     */
    public static String Refund(AliPayBean bean) throws IOException,AlipayApiException{
        //设置请求参数
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
        alipayRequest.setBizContent(JSON.toJSONString(bean));
        //请求
        String result = alipayClient.execute(alipayRequest).getBody();
        return  result;
    }

    /**
     * 	交易查询
     * @param
     * @return
     * @throws IOException
     * @throws AlipayApiException
     */
    public static String queryPay(AliPayBean bean) throws IOException,AlipayApiException{
        //设置请求参数
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
        alipayRequest.setBizContent(JSON.toJSONString(bean));
        //请求
        String result = alipayClient.execute(alipayRequest).getBody();
        return result;
    }

    /**
     * 	退款查询
     * @param bean
     * @return
     * @throws AlipayApiException
     */
    public static String queryRefund(AliPayBean bean) throws AlipayApiException{
        //设置请求参数
        AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();
        alipayRequest.setBizContent(JSON.toJSONString(bean));
        //请求
        String result = alipayClient.execute(alipayRequest).getBody();
        return result;
    }

    /**
     * 	关闭交易
     * @param request
     * @return
     * @throws IOException
     * @throws AlipayApiException
     */
    public static String close(HttpServletRequest request) throws IOException,AlipayApiException{
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();
        //商户订单号，商户网站订单系统中唯一订单号
        String out_trade_no = new String(request.getParameter("WIDTCout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //支付宝交易号
        String trade_no = new String(request.getParameter("WIDTCtrade_no").getBytes("ISO-8859-1"),"UTF-8");
        //请二选一设置

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," +"\"trade_no\":\""+ trade_no +"\"}");

        //请求
        String result = alipayClient.execute(alipayRequest).getBody();

        return result;
    }
}
