package com.xgh.mng.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * SysDictDetail
 *
 * @author h2y
 *         <p/>
 *         time:2015-12-24 18:12:27
 *         <p/>
 *         Gmail:
 */

public class SysDictDetail implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	public static final String key = "keySysDictDetail";

	private long id;

	private long nid;

	private long unitId;

	private long unitNid;

	private long dictMainId;

	private long dictMainNid;

	private String code;

	private String value;

	private long ord;

	private Date createDate;

	private Date updateDate;

	private int status;

	private String remark;

	public SysDictDetail() {
		super();
	}

	public SysDictDetail(long id) {
		super();
		this.id = id;
	}

	public SysDictDetail(long id, long nid, long unitId, long unitNid, long dictMainId, long dictMainNid, String code, String value, long ord, Date createDate, Date updateDate, int status, String remark) {
		super();
		this.id = id;
		this.nid = nid;
		this.unitId = unitId;
		this.unitNid = unitNid;
		this.dictMainId = dictMainId;
		this.dictMainNid = dictMainNid;
		this.code = code;
		this.value = value;
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


	public long getUnitId() {
		return unitId;
	}

	public void setUnitId(long unitId) {
		this.unitId = unitId;
	}


	public long getUnitNid() {
		return unitNid;
	}

	public void setUnitNid(long unitNid) {
		this.unitNid = unitNid;
	}


	public long getDictMainId() {
		return dictMainId;
	}

	public void setDictMainId(long dictMainId) {
		this.dictMainId = dictMainId;
	}


	public long getDictMainNid() {
		return dictMainNid;
	}

	public void setDictMainNid(long dictMainNid) {
		this.dictMainNid = dictMainNid;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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
		return "id:" + id + "\t" + "nid:" + nid + "\t" + "unitId:" + unitId + "\t" + "unitNid:" + unitNid + "\t" + "dictMainId:" + dictMainId + "\t" + "dictMainNid:" + dictMainNid + "\t" + "code:" + code + "\t" + "value:" + value + "\t" + "ord:" + ord + "\t" + "createDate:" + createDate + "\t" + "updateDate:" + updateDate + "\t" + "status:" + status + "\t" + "remark:" + remark;
	}
}