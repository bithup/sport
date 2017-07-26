package com.xgh.sportsite.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * SysUnits 
 *
 * @author h2y
 *
 * @time:2016-01-12 18:24:34
 *
 * @Email:
 */

public class SysUnits implements Serializable{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;	
	
    public static final String key = "keySysUnits";
	//单位ID编码
    private long id;
	//处理分布式部署问题，数据关联全部使用nid进行关联
    private long nid;
	//单位父级id
    private long parentId;
	//单位父级nid
    private long parentNid;
	//行业id
    private long instId;
	//行业nid
    private long instNid;
	//行业编码
    private String instCode;
	//单位编码
    private String unitCode;
	//单位类型：1：行业平台、2：省级代理、3：地方代理、10国家级机构，11省级机构，12市县级机构
    private int unitType;
	//1平台类、2服务类、3销售类、4其他
    private int unitKind;
	//区域编码
    private String zoneCode;
	//单位域名
    private String unitDomain;
	//单位名称 和营业执照一致
    private String unitName;
	//单位简称
    private String shortName;
	//单位注册日期
    private Date regDate;
	//单位停用日期
    private Date stopDate;
	//单位用户总数
    private long userCount;
	//单位状态： 包括注册，初审，复审，核准，停用初审，停用复审，停用核准
    private String unitStatus;
	//管理URL
    private String adminUrl;
	//portal URL
    private String portalUrl;
	//单位地址 和营业执照一致
    private String unitAddress;
	//电话区号
    private String telAreaCode;
	//电话
    private String tel;
	//服务电话
    private String telService;
	//传真
    private String fax;
	//单位法人
    private String legalPerson;
	//单位法人手机
    private String legalPersonMobile;
	//logo地址，相对位置，在正常使用的时候在前边加上对应的服务url即可访问
    private String logoPath;
	//logo的url地址
    private String logoUrl;
	//版本号
    private int version;
	//排序
    private long ord;
	//创建时间
    private Date createDate;
	//更新时间
    private Date updateDate;
	//状态字段 1正常，0锁定，-1删除
    private int status;
	//备注
    private String remark;

	public SysUnits(){
		super();
	}

	public SysUnits(long id){
		super();
		this.id = id;
	}

	public SysUnits(long id,long nid,long parentId,long parentNid,long instId,long instNid,String instCode,String unitCode,int unitType,int unitKind,String zoneCode,String unitDomain,String unitName,String shortName,Date regDate,Date stopDate,long userCount,String unitStatus,String adminUrl,String portalUrl,String unitAddress,String telAreaCode,String tel,String telService,String fax,String legalPerson,String legalPersonMobile,String logoPath,String logoUrl,int version,long ord,Date createDate,Date updateDate,int status,String remark){
		super();
		this.id = id;
		this.nid = nid;
		this.parentId = parentId;
		this.parentNid = parentNid;
		this.instId = instId;
		this.instNid = instNid;
		this.instCode = instCode;
		this.unitCode = unitCode;
		this.unitType = unitType;
		this.unitKind = unitKind;
		this.zoneCode = zoneCode;
		this.unitDomain = unitDomain;
		this.unitName = unitName;
		this.shortName = shortName;
		this.regDate = regDate;
		this.stopDate = stopDate;
		this.userCount = userCount;
		this.unitStatus = unitStatus;
		this.adminUrl = adminUrl;
		this.portalUrl = portalUrl;
		this.unitAddress = unitAddress;
		this.telAreaCode = telAreaCode;
		this.tel = tel;
		this.telService = telService;
		this.fax = fax;
		this.legalPerson = legalPerson;
		this.legalPersonMobile = legalPersonMobile;
		this.logoPath = logoPath;
		this.logoUrl = logoUrl;
		this.version = version;
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


    public long getParentId(){
      return parentId;
    }
    
    public void setParentId(long parentId){
      this.parentId = parentId;
    }


    public long getParentNid(){
      return parentNid;
    }
    
    public void setParentNid(long parentNid){
      this.parentNid = parentNid;
    }


    public long getInstId(){
      return instId;
    }
    
    public void setInstId(long instId){
      this.instId = instId;
    }


    public long getInstNid(){
      return instNid;
    }
    
    public void setInstNid(long instNid){
      this.instNid = instNid;
    }


    public String getInstCode(){
      return instCode;
    }
    
    public void setInstCode(String instCode){
      this.instCode = instCode;
    }


    public String getUnitCode(){
      return unitCode;
    }
    
    public void setUnitCode(String unitCode){
      this.unitCode = unitCode;
    }


    public int getUnitType(){
      return unitType;
    }
    
    public void setUnitType(int unitType){
      this.unitType = unitType;
    }


    public int getUnitKind(){
      return unitKind;
    }
    
    public void setUnitKind(int unitKind){
      this.unitKind = unitKind;
    }


    public String getZoneCode(){
      return zoneCode;
    }
    
    public void setZoneCode(String zoneCode){
      this.zoneCode = zoneCode;
    }


    public String getUnitDomain(){
      return unitDomain;
    }
    
    public void setUnitDomain(String unitDomain){
      this.unitDomain = unitDomain;
    }


    public String getUnitName(){
      return unitName;
    }
    
    public void setUnitName(String unitName){
      this.unitName = unitName;
    }


    public String getShortName(){
      return shortName;
    }
    
    public void setShortName(String shortName){
      this.shortName = shortName;
    }


    public Date getRegDate(){
      return regDate;
    }
    
    public void setRegDate(Date regDate){
      this.regDate = regDate;
    }


    public Date getStopDate(){
      return stopDate;
    }
    
    public void setStopDate(Date stopDate){
      this.stopDate = stopDate;
    }


    public long getUserCount(){
      return userCount;
    }
    
    public void setUserCount(long userCount){
      this.userCount = userCount;
    }


    public String getUnitStatus(){
      return unitStatus;
    }
    
    public void setUnitStatus(String unitStatus){
      this.unitStatus = unitStatus;
    }


    public String getAdminUrl(){
      return adminUrl;
    }
    
    public void setAdminUrl(String adminUrl){
      this.adminUrl = adminUrl;
    }


    public String getPortalUrl(){
      return portalUrl;
    }
    
    public void setPortalUrl(String portalUrl){
      this.portalUrl = portalUrl;
    }


    public String getUnitAddress(){
      return unitAddress;
    }
    
    public void setUnitAddress(String unitAddress){
      this.unitAddress = unitAddress;
    }


    public String getTelAreaCode(){
      return telAreaCode;
    }
    
    public void setTelAreaCode(String telAreaCode){
      this.telAreaCode = telAreaCode;
    }


    public String getTel(){
      return tel;
    }
    
    public void setTel(String tel){
      this.tel = tel;
    }


    public String getTelService(){
      return telService;
    }
    
    public void setTelService(String telService){
      this.telService = telService;
    }


    public String getFax(){
      return fax;
    }
    
    public void setFax(String fax){
      this.fax = fax;
    }


    public String getLegalPerson(){
      return legalPerson;
    }
    
    public void setLegalPerson(String legalPerson){
      this.legalPerson = legalPerson;
    }


    public String getLegalPersonMobile(){
      return legalPersonMobile;
    }
    
    public void setLegalPersonMobile(String legalPersonMobile){
      this.legalPersonMobile = legalPersonMobile;
    }


    public String getLogoPath(){
      return logoPath;
    }
    
    public void setLogoPath(String logoPath){
      this.logoPath = logoPath;
    }


    public String getLogoUrl(){
      return logoUrl;
    }
    
    public void setLogoUrl(String logoUrl){
      this.logoUrl = logoUrl;
    }


    public int getVersion(){
      return version;
    }
    
    public void setVersion(int version){
      this.version = version;
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
		return "id:"+id+"\t"+"nid:"+nid+"\t"+"parentId:"+parentId+"\t"+"parentNid:"+parentNid+"\t"+"instId:"+instId+"\t"+"instNid:"+instNid+"\t"+"instCode:"+instCode+"\t"+"unitCode:"+unitCode+"\t"+"unitType:"+unitType+"\t"+"unitKind:"+unitKind+"\t"+"zoneCode:"+zoneCode+"\t"+"unitDomain:"+unitDomain+"\t"+"unitName:"+unitName+"\t"+"shortName:"+shortName+"\t"+"regDate:"+regDate+"\t"+"stopDate:"+stopDate+"\t"+"userCount:"+userCount+"\t"+"unitStatus:"+unitStatus+"\t"+"adminUrl:"+adminUrl+"\t"+"portalUrl:"+portalUrl+"\t"+"unitAddress:"+unitAddress+"\t"+"telAreaCode:"+telAreaCode+"\t"+"tel:"+tel+"\t"+"telService:"+telService+"\t"+"fax:"+fax+"\t"+"legalPerson:"+legalPerson+"\t"+"legalPersonMobile:"+legalPersonMobile+"\t"+"logoPath:"+logoPath+"\t"+"logoUrl:"+logoUrl+"\t"+"version:"+version+"\t"+"ord:"+ord+"\t"+"createDate:"+createDate+"\t"+"updateDate:"+updateDate+"\t"+"status:"+status+"\t"+"remark:"+remark;
    }
}