package com.xgh.sportsite.entity;
import java.io.Serializable;
import java.util.Date;


/**
 *
 *
 *
 **/
@SuppressWarnings("serial")
public class House implements Serializable {

    /**主键id**/
    private long id;

    /**收藏人id**/
    private long memberId;

    /**类型（0：场馆  1：教练  2:活动）**/
    private int type;

    /**场馆id、教练id、活动id**/
    private long dataId;

    /**创建时间**/
    private Date createDate;

    /**修改时间**/
    private Date updateDate;

    /**状态（0：未收藏  1：收藏）**/
    private int status;

    /**备用字段1**/
    private String data1;

    /**备用字段2**/
    private long data2;

    /**备用字段3**/
    private int data3;

    /**备用字段4**/
    private Double data4;


    public House() { super(); }

    public House(long id) {
        super();
        this.id=id;
    }

    public House(long id,long memberId,int type,long dataId,Date createDate,Date updateDate,int status,String data1,long data2,int data3,Double data4){
        super();
        this.id = id;
        this.memberId = memberId;
        this.type = type;
        this.dataId = dataId;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.status = status;
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

    public void setType(Integer type){
        this.type = type;
    }

    public Integer getType(){
        return this.type;
    }

    public void setDataId(Long dataId){
        this.dataId = dataId;
    }

    public Long getDataId(){
        return this.dataId;
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

    public void setData1(String data1){
        this.data1 = data1;
    }

    public String getData1(){
        return this.data1;
    }

    public void setData2(Long data2){
        this.data2 = data2;
    }

    public Long getData2(){
        return this.data2;
    }

    public void setData3(Integer data3){
        this.data3 = data3;
    }

    public Integer getData3(){
        return this.data3;
    }

    public void setData4(Double data4){
        this.data4 = data4;
    }

    public Double getData4(){
        return this.data4;
    }

}
