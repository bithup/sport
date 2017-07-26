package com.xgh.sportsite.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by CQ on 2016/12/12.
 */
public class Activity implements Serializable {

    /**
     * 唯一标示
     **/
    private long id;

    /**
     * 代理id
     **/
    private long unitId = 0;

    /**
     * 创建人id
     **/
    private long userId;

    /**
     * 活动名称
     **/
    private String activityName;

    /**
     * 活动类型（0：个人  1：团体）
     **/
    private int activityType
            ;

    /**
     * 活动主办方
     **/
    private String activityOrganizer;

    /**
     * 活动人数
     **/
    private int activityCount;

    /**
     * 活动地点
     **/
    private String activityAddress;

    /**
     * 活动运动项id
     **/
    private long sportId;

    /**
     * 活动场馆id
     **/
    private long venueId;

    /**
     * 活动费用
     **/
    private Double activityPrice = 0.0;

    /**
     * 活动介绍
     **/
    private String activityIntroduce;

    /**
     * 活动联系人
     **/
    private String activityContacts;

    /**
     * 活动联系人电话
     **/
    private String contactsPhone;

    /**
     * 活动图片路径
     **/
    private String activityPath;

    /**
     * 活动图片绝对路径
     **/
    private String activityRealPath;

    /**
     * 活动申明
     **/
    private String declares="";

    /**
     * 是否加入热门活动（0：否  1：是）
     **/
    private int isRecommend;

    /**
     * 收费方式（0：收费  1：免费  2：AA制）
     **/
    private int isFree;

    /**
     * 活动开始时间
     **/
    private Date startDate;

    /**
     * 活动结束日期
     **/
    private Date endDate;

    /**
     * 活动报名截止日期
     **/
    private Date enrollDate;

    /**
     * 创建时间
     **/
    private Date createDate;

    /**
     * 修改时间
     **/
    private Date updateDate;

    /**
     * 排序
     **/
    private int ord = 0;

    /**
     * 是否通过审核（0：未审核  1：通过  2：未通过）
     **/
    private int isCheck;

    /**
     * 状态（-1：已删除  0：正常  1：未删除）
     **/
    private int status;

    /**
     * 区域id
     **/
    private long data1;

    /**
     * 备用字段2
     **/
    private long data2;

    /**
     * 备用字段3
     **/
    private long data3;

    /**
     * 备用字段4
     **/
    private Double data4;

    /**
     * 备用字段5
     **/
    private Double data5;

    /**
     * 备用字段6
     **/
    private int data6;

    /**
     * 备用字段7
     **/
    private int data7;

    /**
     * 备用字段8
     **/
    private int data8;

    /**
     * 区域编码
     **/
    private String data9;

    /**
     * 备用字段10
     **/
    private String data10;

    /**
     * 备用字段11
     **/
    private String data11;


    public Activity() {
        super();
    }

    public Activity(long id) {
        super();
        this.id = id;
    }

    public Activity(long id, long unitId, long userId, String activityName, int activityType, String activityOrganizer, int activityCount, String activityAddress, long sportId, long venueId, Double activityPrice, String activityIntroduce, String activityContacts, String contactsPhone, String activityPath, String activityRealPath, String declares, int isRecommend, int isFree, Date startDate, Date endDate, Date enrollDate, Date createDate, Date updateDate, int ord, int isCheck, int status, long data1, long data2, long data3, Double data4, Double data5, int data6, int data7, int data8, String data9, String data10, String data11) {
        super();
        this.id = id;
        this.unitId = unitId;
        this.userId = userId;
        this.activityName = activityName;
        this.activityType = activityType;
        this.activityOrganizer = activityOrganizer;
        this.activityCount = activityCount;
        this.activityAddress = activityAddress;
        this.sportId = sportId;
        this.venueId = venueId;
        this.activityPrice = activityPrice;
        this.activityIntroduce = activityIntroduce;
        this.activityContacts = activityContacts;
        this.contactsPhone = contactsPhone;
        this.activityPath = activityPath;
        this.activityRealPath = activityRealPath;
        this.declares = declares;
        this.isRecommend = isRecommend;
        this.isFree = isFree;
        this.startDate = startDate;
        this.endDate = endDate;
        this.enrollDate = enrollDate;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.ord = ord;
        this.isCheck = isCheck;
        this.status = status;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
        this.data4 = data4;
        this.data5 = data5;
        this.data6 = data6;
        this.data7 = data7;
        this.data8 = data8;
        this.data9 = data9;
        this.data10 = data10;
        this.data11 = data11;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Long getUnitId() {
        return this.unitId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityName() {
        return this.activityName;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    public Integer getActivityType() {
        return this.activityType;
    }

    public void setActivityOrganizer(String activityOrganizer) {
        this.activityOrganizer = activityOrganizer;
    }

    public String getActivityOrganizer() {
        return this.activityOrganizer;
    }

    public void setActivityCount(Integer activityCount) {
        this.activityCount = activityCount;
    }

    public Integer getActivityCount() {
        return this.activityCount;
    }

    public void setActivityAddress(String activityAddress) {
        this.activityAddress = activityAddress;
    }

    public String getActivityAddress() {
        return this.activityAddress;
    }

    public void setSportId(Long sportId) {
        this.sportId = sportId;
    }

    public Long getSportId() {
        return this.sportId;
    }

    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }

    public Long getVenueId() {
        return this.venueId;
    }

    public void setActivityPrice(Double activityPrice) {
        this.activityPrice = activityPrice;
    }

    public Double getActivityPrice() {
        return this.activityPrice;
    }

    public void setActivityIntroduce(String activityIntroduce) {
        this.activityIntroduce = activityIntroduce;
    }

    public String getActivityIntroduce() {
        return this.activityIntroduce;
    }

    public void setActivityContacts(String activityContacts) {
        this.activityContacts = activityContacts;
    }

    public String getActivityContacts() {
        return this.activityContacts;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public String getContactsPhone() {
        return this.contactsPhone;
    }

    public void setActivityPath(String activityPath) {
        this.activityPath = activityPath;
    }

    public String getActivityPath() {
        return this.activityPath;
    }

    public void setActivityRealPath(String activityRealPath) {
        this.activityRealPath = activityRealPath;
    }

    public String getActivityRealPath() {
        return this.activityRealPath;
    }

    public void setDeclares(String declares) {
        this.declares = declares;
    }

    public String getDeclares() {
        return this.declares;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Integer getIsRecommend() {
        return this.isRecommend;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public Integer getIsFree() {
        return this.isFree;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public Date getEnrollDate() {
        return this.enrollDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setOrd(Integer ord) {
        this.ord = ord;
    }

    public Integer getOrd() {
        return this.ord;
    }

    public void setIsCheck(Integer isCheck) {
        this.isCheck = isCheck;
    }

    public Integer getIsCheck() {
        return this.isCheck;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setData1(Long data1) {
        this.data1 = data1;
    }

    public Long getData1() {
        return this.data1;
    }

    public void setData2(Long data2) {
        this.data2 = data2;
    }

    public Long getData2() {
        return this.data2;
    }

    public void setData3(Long data3) {
        this.data3 = data3;
    }

    public Long getData3() {
        return this.data3;
    }

    public void setData4(Double data4) {
        this.data4 = data4;
    }

    public Double getData4() {
        return this.data4;
    }

    public void setData5(Double data5) {
        this.data5 = data5;
    }

    public Double getData5() {
        return this.data5;
    }

    public void setData6(Integer data6) {
        this.data6 = data6;
    }

    public Integer getData6() {
        return this.data6;
    }

    public void setData7(Integer data7) {
        this.data7 = data7;
    }

    public Integer getData7() {
        return this.data7;
    }

    public void setData8(Integer data8) {
        this.data8 = data8;
    }

    public Integer getData8() {
        return this.data8;
    }

    public void setData9(String data9) {
        this.data9 = data9;
    }

    public String getData9() {
        return this.data9;
    }

    public void setData10(String data10) {
        this.data10 = data10;
    }

    public String getData10() {
        return this.data10;
    }

    public void setData11(String data11) {
        this.data11 = data11;
    }

    public String getData11() {
        return this.data11;
    }

}
