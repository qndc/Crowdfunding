package com.atguigu.atcrowdfunding.bean;

public class AliPayBean
{
  private String out_trade_no;	//商户订单号
  private String total_amount;	//订单总金额
  private String subject;		//订单标题
  private String body;			//订单描述
  private String timeout_express = "10m";	//该笔订单允许的最晚付款时间
  private String product_code = "FAST_INSTANT_TRADE_PAY";	//销售产品码
  private String trade_no;	//支付宝交易号
  private String refund_amount;	//退款金额，该金额不能大于订单金额,单位为元
  private String refund_reason;	//退款的原因说明
  private String out_request_no;	//标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
  
  public String getOut_trade_no()
  {
    return this.out_trade_no;
  }
  
  public void setOut_trade_no(String out_trade_no)
  {
    this.out_trade_no = out_trade_no;
  }
  
  public String getTotal_amount()
  {
    return this.total_amount;
  }
  
  public void setTotal_amount(String total_amount)
  {
    this.total_amount = total_amount;
  }
  
  public String getSubject()
  {
    return this.subject;
  }
  
  public void setSubject(String subject)
  {
    this.subject = subject;
  }
  
  public String getBody()
  {
    return this.body;
  }
  
  public void setBody(String body)
  {
    this.body = body;
  }
  
  public String getTimeout_express()
  {
    return this.timeout_express;
  }
  
  public void setTimeout_express(String timeout_express)
  {
    this.timeout_express = timeout_express;
  }
  
  public String getProduct_code()
  {
    return this.product_code;
  }
  
  public void setProduct_code(String product_code)
  {
    this.product_code = product_code;
  }
  
  public String getTrade_no()
  {
    return this.trade_no;
  }
  
  public void setTrade_no(String trade_no)
  {
    this.trade_no = trade_no;
  }
  
  public String getRefund_amount()
  {
    return this.refund_amount;
  }
  
  public void setRefund_amount(String refund_amount)
  {
    this.refund_amount = refund_amount;
  }
  
  public String getRefund_reason()
  {
    return this.refund_reason;
  }
  
  public void setRefund_reason(String refund_reason)
  {
    this.refund_reason = refund_reason;
  }
  
  public String getOut_request_no()
  {
    return this.out_request_no;
  }
  
  public void setOut_request_no(String out_request_no)
  {
    this.out_request_no = out_request_no;
  }
}
