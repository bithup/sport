package com.xgh.sportsite.entity;
import java.io.Serializable;
import java.util.Date;


/**
 *
 *
 *
 **/
@SuppressWarnings("serial")
public class TotalMemebrUser implements Serializable {

    /****/
    private long id;

    /**登录账号**/
    private String account;

    /**用户密码**/
    private String password;

    /**通过哪个平台注册的1.教育2商城3运动4蓝天绿水5招聘6跑腿**/
    private int type;

    /**冗余字段1**/
    private String data1;

    /**冗余字段2**/
    private String data2;

    /****/
    private Date createDate;

    /****/
    private Date updateDate;

    /****/
    private int status;


    public TotalMemebrUser() { super(); }

    public TotalMemebrUser(long id) {
        super();
        this.id=id;
    }

    public TotalMemebrUser(long id, String account, String password, int type, String data1, String data2, Date createDate, Date updateDate, int status){
        super();
        this.id = id;
        this.account = account;
        this.password = password;
        this.type = type;
        this.data1 = data1;
        this.data2 = data2;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.status = status;

    }
    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setAccount(String account){
        this.account = account;
    }

    public String getAccount(){
        return this.account;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }

    public void setType(Integer type){
        this.type = type;
    }

    public Integer getType(){
        return this.type;
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

    public void setCreateDate(Date createDate){
        this.createDate = createDate;
    }

    public Date getCreateDate(){
        return this.createDate;
    }

    public void setUpdateDate(Date updateDate){
        this.updateDate = updateDate;
    }

    public Date getUpdateDate(){
        return this.updateDate;
    }

    public void setStatus(Integer status){
        this.status = status;
    }

    public Integer getStatus(){
        return this.status;
    }

}
