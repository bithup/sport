package com.xgh.sportsite.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * FileData

 * @author h2y

 * time:2016-01-04 23:27:56

 * Gmail:
 */

public class FileData implements Serializable{

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

    public static final String key = "keyFileData";

    private long id;

    private long nid;

    private long instId;

    private long instNid;

    private String instCode;

    private long unitId;

    private long unitNid;

    private String unitCode;

    private String dataCode;

    private int dataType;

    private long dataId;

    private int dataVersion;

    private String path;

    private String relativePath;

    private String fileName;

    private String oldName;

    private long fileSize;

    private String fileSuffix;

    private int status;

    private Date createDate;

    private int ord;

    private int type;

    //额外的数据处理
    private String url;

	public FileData(){
		super();
	}

	public FileData(long id){
		super();
		this.id = id;
	}

	public FileData(long id, long nid, long instId, long instNid, String instCode, long unitId, long unitNid, String unitCode, String dataCode, int dataType, long dataId, int dataVersion, String path, String relativePath, String fileName, String oldName, long fileSize, String fileSuffix, int status, Date createDate, int ord,int type){
		super();
		this.id = id;
		this.nid = nid;
		this.instId = instId;
		this.instNid = instNid;
		this.instCode = instCode;
		this.unitId = unitId;
		this.unitNid = unitNid;
		this.unitCode = unitCode;
		this.dataCode = dataCode;
		this.dataType = dataType;
		this.dataId = dataId;
		this.dataVersion = dataVersion;
		this.path = path;
		this.relativePath = relativePath;
		this.fileName = fileName;
		this.oldName = oldName;
		this.fileSize = fileSize;
		this.fileSuffix = fileSuffix;
		this.status = status;
		this.createDate = createDate;
		this.ord = ord;
        this.type = type;
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


    public long getUnitId(){
      return unitId;
    }

    public void setUnitId(long unitId){
      this.unitId = unitId;
    }


    public long getUnitNid(){
      return unitNid;
    }

    public void setUnitNid(long unitNid){
      this.unitNid = unitNid;
    }


    public String getUnitCode(){
      return unitCode;
    }

    public void setUnitCode(String unitCode){
      this.unitCode = unitCode;
    }


    public String getDataCode(){
      return dataCode;
    }

    public void setDataCode(String dataCode){
      this.dataCode = dataCode;
    }


    public int getDataType(){
      return dataType;
    }

    public void setDataType(int dataType){
      this.dataType = dataType;
    }


    public long getDataId(){
      return dataId;
    }

    public void setDataId(long dataId){
      this.dataId = dataId;
    }


    public int getDataVersion(){
      return dataVersion;
    }

    public void setDataVersion(int dataVersion){
      this.dataVersion = dataVersion;
    }


    public String getPath(){
      return path;
    }

    public void setPath(String path){
      this.path = path;
    }


    public String getRelativePath(){
      return relativePath;
    }

    public void setRelativePath(String relativePath){
      this.relativePath = relativePath;
    }


    public String getFileName(){
      return fileName;
    }

    public void setFileName(String fileName){
      this.fileName = fileName;
    }


    public String getOldName(){
      return oldName;
    }

    public void setOldName(String oldName){
      this.oldName = oldName;
    }


    public long getFileSize(){
      return fileSize;
    }

    public void setFileSize(long fileSize){
      this.fileSize = fileSize;
    }


    public String getFileSuffix(){
      return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix){
      this.fileSuffix = fileSuffix;
    }


    public int getStatus(){
      return status;
    }

    public void setStatus(int status){
      this.status = status;
    }


    public Date getCreateDate(){
      return createDate;
    }

    public void setCreateDate(Date createDate){
      this.createDate = createDate;
    }


    public int getOrd(){
      return ord;
    }

    public void setOrd(int ord){
      this.ord = ord;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String toString(){
		return "id:"+id+"\t"+"nid:"+nid+"\t"+"instId:"+instId+"\t"+"instNid:"+instNid+"\t"+"instCode:"+instCode+"\t"+"unitId:"+unitId+"\t"+"unitNid:"+unitNid+"\t"+"unitCode:"+unitCode+"\t"+"dataCode:"+dataCode+"\t"+"dataType:"+dataType+"\t"+"dataId:"+dataId+"\t"+"dataVersion:"+dataVersion+"\t"+"path:"+path+"\t"+"relativePath:"+relativePath+"\t"+"fileName:"+fileName+"\t"+"oldName:"+oldName+"\t"+"fileSize:"+fileSize+"\t"+"fileSuffix:"+fileSuffix+"\t"+"status:"+status+"\t"+"createDate:"+createDate+"\t"+"ord:"+ord;
    }

}