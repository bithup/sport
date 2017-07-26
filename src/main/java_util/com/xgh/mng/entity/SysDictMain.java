package com.xgh.mng.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * SysDictMain
 *
 * @author h2y
 *         <p/>
 *         time:2015-12-24 18:12:27
 *         <p/>
 *         Gmail:
 */

public class SysDictMain implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	public static final String key = "keySysDictMain";

	private long id;

	private long nid;

	private long parentId;

	private long parentNid;

	private String dictPrefix;

	private String dictCode;

	private String dictName;

	private String dictType;

	private String dictValue;

	private long isSys;

	private long isUserConf;

	private int isMate;

	private int isExtends;

	private long ord;

	private Date createDate;

	private Date updateDate;

	private int status;

	private String remark;

	public SysDictMain() {
		super();
	}

	public SysDictMain(long id) {
		super();
		this.id = id;
	}

	public SysDictMain(long id, long nid, long parentId, long parentNid, String dictPrefix, String dictCode, String dictName, String dictType, String dictValue, long isSys, long isUserConf, int isMate, int isExtends, long ord, Date createDate, Date updateDate, int status, String remark) {
		super();
		this.id = id;
		this.nid = nid;
		this.parentId = parentId;
		this.parentNid = parentNid;
		this.dictPrefix = dictPrefix;
		this.dictCode = dictCode;
		this.dictName = dictName;
		this.dictType = dictType;
		this.dictValue = dictValue;
		this.isSys = isSys;
		this.isUserConf = isUserConf;
		this.isMate = isMate;
		this.isExtends = isExtends;
		this.ord = ord;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.status = status;
		this.remark = remark;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getNid() {
		return nid;
	}

	public void setNid(long nid) {
		this.nid = nid;
	}


	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}


	public long getParentNid() {
		return parentNid;
	}

	public void setParentNid(long parentNid) {
		this.parentNid = parentNid;
	}


	public String getDictPrefix() {
		return dictPrefix;
	}

	public void setDictPrefix(String dictPrefix) {
		this.dictPrefix = dictPrefix;
	}


	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}


	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}


	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}


	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}


	public long getIsSys() {
		return isSys;
	}

	public void setIsSys(long isSys) {
		this.isSys = isSys;
	}


	public long getIsUserConf() {
		return isUserConf;
	}

	public void setIsUserConf(long isUserConf) {
		this.isUserConf = isUserConf;
	}


	public int getIsMate() {
		return isMate;
	}

	public void setIsMate(int isMate) {
		this.isMate = isMate;
	}


	public int getIsExtends() {
		return isExtends;
	}

	public void setIsExtends(int isExtends) {
		this.isExtends = isExtends;
	}


	public long getOrd() {
		return ord;
	}

	public void setOrd(long ord) {
		this.ord = ord;
	}


	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String toString() {
		return "id:" + id + "\t" + "nid:" + nid + "\t" + "parentId:" + parentId + "\t" + "parentNid:" + parentNid + "\t" + "dictPrefix:" + dictPrefix + "\t" + "dictCode:" + dictCode + "\t" + "dictName:" + dictName + "\t" + "dictType:" + dictType + "\t" + "dictValue:" + dictValue + "\t" + "isSys:" + isSys + "\t" + "isUserConf:" + isUserConf + "\t" + "isMate:" + isMate + "\t" + "isExtends:" + isExtends + "\t" + "ord:" + ord + "\t" + "createDate:" + createDate + "\t" + "updateDate:" + updateDate + "\t" + "status:" + status + "\t" + "remark:" + remark;
	}
}