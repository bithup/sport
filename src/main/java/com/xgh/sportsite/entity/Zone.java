package com.xgh.sportsite.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Zone 
 *
 * @author duanxg
 *
 * @time:2016-02-18 11:53:33
 *
 * @Email:
 */

public class Zone implements Serializable{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
	
    public static final String key = "keyZone";
	//
    private long id;
	//处理分布式部署问题，数据关联全部使用nid进行关联
	private long nid = 0;
	//区域编码
    private String code;
	//区域名称
    private String name;
	//
    private String pid;
	//
    private String pcode;
	//
    private String preFix;
	//
    private String level;
	//
    private String isLast;
	//
    private Double longitude;
	//
    private Double latitude;
	//
    private String location;
	//
    private String spellName;
	//
    private String firSpellName;
	//排序
	private long ord = 1;
	//创建时间
    private Date createDate;
	//更新时间
    private Date updateDate;
	//状态字段 1正常，0锁定，-1删除
	private int status = 1;
	//备注
    private String remark;

	public Zone(){
		super();
	}

	public Zone(long id){
		super();
		this.id = id;
	}

	public Zone(long id,long nid,String code,String name,String pid,String pcode,String preFix,String level,String isLast,Double longitude,Double latitude,String location,String spellName,String firSpellName,long ord,Date createDate,Date updateDate,int status,String remark){
		super();
		this.id = id;
		this.nid = nid;
		this.code = code;
		this.name = name;
		this.pid = pid;
		this.pcode = pcode;
		this.preFix = preFix;
		this.level = level;
		this.isLast = isLast;
		this.longitude = longitude;
		this.latitude = latitude;
		this.location = location;
		this.spellName = spellName;
		this.firSpellName = firSpellName;
		this.ord = ord;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.status = status;
		this.remark = remark;
	}
  
    public long getId(){
      return id;
    }
    
    public void setId(long id){
      this.id = id;
    }

    public long getNid(){
      return nid;
    }
    
    public void setNid(long nid){
      this.nid = nid;
    }


    public String getCode(){
      return code;
    }
    
    public void setCode(String code){
      this.code = code;
    }


    public String getName(){
      return name;
    }
    
    public void setName(String name){
      this.name = name;
    }


    public String getPid(){
      return pid;
    }
    
    public void setPid(String pid){
      this.pid = pid;
    }


    public String getPcode(){
      return pcode;
    }
    
    public void setPcode(String pcode){
      this.pcode = pcode;
    }


    public String getPreFix(){
      return preFix;
    }
    
    public void setPreFix(String preFix){
      this.preFix = preFix;
    }


    public String getLevel(){
      return level;
    }
    
    public void setLevel(String level){
      this.level = level;
    }


    public String getIsLast(){
      return isLast;
    }
    
    public void setIsLast(String isLast){
      this.isLast = isLast;
    }


    public Double getLongitude(){
      return longitude;
    }
    
    public void setLongitude(Double longitude){
      this.longitude = longitude;
    }


    public Double getLatitude(){
      return latitude;
    }
    
    public void setLatitude(Double latitude){
      this.latitude = latitude;
    }


    public String getLocation(){
      return location;
    }
    
    public void setLocation(String location){
      this.location = location;
    }


    public String getSpellName(){
      return spellName;
    }
    
    public void setSpellName(String spellName){
      this.spellName = spellName;
    }


    public String getFirSpellName(){
      return firSpellName;
    }
    
    public void setFirSpellName(String firSpellName){
      this.firSpellName = firSpellName;
    }


    public long getOrd(){
      return ord;
    }
    
    public void setOrd(long ord){
      this.ord = ord;
    }


    public Date getCreateDate(){
      return createDate;
    }
    
    public void setCreateDate(Date createDate){
      this.createDate = createDate;
    }


    public Date getUpdateDate(){
      return updateDate;
    }
    
    public void setUpdateDate(Date updateDate){
      this.updateDate = updateDate;
    }


    public int getStatus(){
      return status;
    }
    
    public void setStatus(int status){
      this.status = status;
    }


    public String getRemark(){
      return remark;
    }
    
    public void setRemark(String remark){
      this.remark = remark;
    }

    public String toString(){
		return "id:"+id+"\t"+"nid:"+nid+"\t"+"code:"+code+"\t"+"name:"+name+"\t"+"pid:"+pid+"\t"+"pcode:"+pcode+"\t"+"preFix:"+preFix+"\t"+"level:"+level+"\t"+"isLast:"+isLast+"\t"+"longitude:"+longitude+"\t"+"latitude:"+latitude+"\t"+"location:"+location+"\t"+"spellName:"+spellName+"\t"+"firSpellName:"+firSpellName+"\t"+"ord:"+ord+"\t"+"createDate:"+createDate+"\t"+"updateDate:"+updateDate+"\t"+"status:"+status+"\t"+"remark:"+remark;
    }
}