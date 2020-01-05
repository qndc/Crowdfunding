package com.atguigu.atcrowdfunding.bean;

public class AliPayBean
{
  private String out_trade_no;
  private String total_amount;
  private String subject;
  private String body;
  private String timeout_express = "10m";
  private String product_code = "FAST_INSTANT_TRADE_PAY";
  private String trade_no;
  private String refund_amount;
  private String refund_reason;
  private String out_request_no;
  
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
