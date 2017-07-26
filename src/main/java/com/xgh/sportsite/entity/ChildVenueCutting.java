package com.xgh.sportsite.entity;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class ChildVenueCutting implements Serializable {

	/**主键id**/
	private long id;

	/**分场馆id**/
	private long childVenueId;

	/**订单id**/
	private long orderId;

	/**时间段**/
	private String timePeriod;

	/**状态（-1：删除  0：空闲  1：已预订）**/
	private int status;

	/**开始时间（日期）**/
	private Date startDate;

	/**结束时间（暂不使用）**/
	private Date endDate;

	/**创建时间**/
	private Date createDate;

	/**修改时间**/
	private Date updateDate;

	/**排序**/
	private long ord;

	/**备用字段1**/
	private String data1;

	/**备用字段2**/
	private long data2;

	/**备用字段3**/
	private int data3;

	/**备用字段4**/
	private Double data4;


	public ChildVenueCutting() { super(); }

	public ChildVenueCutting(long id) {
	 super();
	 this.id=id;
	}

	public ChildVenueCutting(long id,long childVenueId,long orderId,String timePeriod,int status,Date startDate,Date endDate,Date createDate,Date updateDate,long ord,String data1,long data2,int data3,Double data4){
		super();
		this.id = id;
		this.childVenueId = childVenueId;
		this.orderId = orderId;
		this.timePeriod = timePeriod;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.ord = ord;
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

	public void setChildVenueId(Long childVenueId){
		this.childVenueId = childVenueId;
	}

	public Long getChildVenueId(){
		return this.childVenueId;
	}

	public void setOrderId(Long orderId){
		this.orderId = orderId;
	}

	public Long getOrderId(){
		return this.orderId;
	}

	public void setTimePeriod(String timePeriod){
		this.timePeriod = timePeriod;
	}

	public String getTimePeriod(){
		return this.timePeriod;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return this.status;
	}

	public void setStartDate(Date startDate){
		this.startDate = startDate;
	}

	public Date getStartDate(){
		return this.startDate;
	}

	public void setEndDate(Date endDate){
		this.endDate = endDate;
	}

	public Date getEndDate(){
		return this.endDate;
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

	public void setOrd(Long ord){
		this.ord = ord;
	}

	public Long getOrd(){
		return this.ord;
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
