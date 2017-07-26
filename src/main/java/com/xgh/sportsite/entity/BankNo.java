package com.xgh.sportsite.entity;
import java.io.Serializable;
import java.util.Date;


/**
 *
 * 用户绑定银行卡表
 *
 **/
@SuppressWarnings("serial")
public class BankNo implements Serializable {

    /****/
    private long id;

    /**会员id**/
    private long memberId=0;

    /**银行卡号**/
    private String cardNo;

    /**默认绑定银行卡号**/
    private int defaultBindingBank=0;

    /**状态1正常，0删除**/
    private int status;

    /****/
    private Date createTime;

    /****/
    private Date updateTime;

    /**银行卡绑定电话**/
    private String telPhone;

    /**冗余字段**/
    private String data1;

    /**冗余字段**/
    private String data2;

    /**冗余字段3**/
    private String data3;

    /**冗余字段4**/
    private String data4;


    public BankNo() { super(); }

    public BankNo(long id) {
        super();
        this.id=id;
    }

    public BankNo(long id,long memberId,String cardNo,int defaultBindingBank,int status,Date createTime,Date updateTime,String telPhone,String data1,String data2,String data3,String data4){
        super();
        this.id = id;
        this.memberId = memberId;
        this.cardNo = cardNo;
        this.defaultBindingBank = defaultBindingBank;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.telPhone = telPhone;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
        this.data4 = data4;

    }
    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setMemberId(Long memberId){
        this.memberId = memberId;
    }

    public Long getMemberId(){
        return this.memberId;
    }

    public void setCardNo(String cardNo){
        this.cardNo = cardNo;
    }

    public String getCardNo(){
        return this.cardNo;
    }

    public void setDefaultBindingBank(Integer defaultBindingBank){
        this.defaultBindingBank = defaultBindingBank;
    }

    public Integer getDefaultBindingBank(){
        return this.defaultBindingBank;
    }

    public void setStatus(Integer status){
        this.status = status;
    }

    public Integer getStatus(){
        return this.status;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public Date getCreateTime(){
        return this.createTime;
    }

    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

    public Date getUpdateTime(){
        return this.updateTime;
    }

    public void setTelPhone(String telPhone){
        this.telPhone = telPhone;
    }

    public String getTelPhone(){
        return this.telPhone;
    }

    public void setData1(String data1){
        this.data1 = data1;
    }

    public String getData1(){
        return this.data1;
    }

    public void setData2(String data2){
        this.data2 = data2;
    }

    public String getData2(){
        return this.data2;
    }

    public void setData3(String data3){
        this.data3 = data3;
    }

    public String getData3(){
        return this.data3;
    }

    public void setData4(String data4){
        this.data4 = data4;
    }

    public String getData4(){
        return this.data4;
    }

}
