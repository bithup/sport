package com.xgh.sportsite.entity;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class Remark implements Serializable {

	/****/
	private long id;

	/**订单Id**/
	private long orderId;

	/**类型（0：场馆  1：教练  2：活动）**/
	private int type;

	/**场馆id、教练id、活动id**/
	private long dataId;

	/**评价内容**/
	private String content;

	/**评价类型（0：好评  1：中评  2：差评）**/
	private int kind;

	/**创建时间**/
	private Date createDate;

	/**修改时间**/
	private Date updateDate;

	/**状态（-1：已删除  0：正常  1：未删除）**/
	private int status;

	/**备用字段1**/
	private String data1;

	/**备用字段2**/
	private long data2;

	/**备用字段3**/
	private int data3;

	/**备用字段4**/
	private Double data4;


	public Remark() { super(); }

	public Remark(long id) {
	 super();
	 this.id=id;
	}

	public Remark(long id,long orderId,int type,long dataId,String content,int kind,Date createDate,Date updateDate,int status,String data1,long data2,int data3,Double data4){
		super();
		this.id = id;
		this.orderId = orderId;
		this.type = type;
		this.dataId = dataId;
		this.content = content;
		this.kind = kind;
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

	public void setOrderId(Long orderId){
		this.orderId = orderId;
	}

	public Long getOrderId(){
		return this.orderId;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setKind(Integer kind){
		this.kind = kind;
	}

	public Integer getKind(){
		return this.kind;
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
