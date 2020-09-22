package com.online.operation.pojo;


import com.online.operation.common.orm.BaseEntity;

/**
 * 课程章节
 */
public class CourseSection extends BaseEntity {

	private static final long serialVersionUID = -7261405404725335316L;

	/**
	*归属课程id
	**/
	private Integer courseId;

	/**
	*父章节id，（只有2级）
	**/
	private Integer parentId;

	/**
	*章节名称
	**/
	private String name;

	/**
	*排序
	**/
	private Integer sort;

	/**
	*时长
	**/
	private String time;

	/**
	*未上架（0）、上架（1）
	**/
	private Integer onsale;
	
	/**
	 * 视频url
	 */
	private String videoUrl;

	public Integer getCourseId(){
		return courseId;
	}
	public void setCourseId(Integer courseId){
		this.courseId = courseId;
	}

	public Integer getParentId(){
		return parentId;
	}
	public void setParentId(Integer parentId){
		this.parentId = parentId;
	}

	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}

	public Integer getSort(){
		return sort;
	}
	public void setSort(Integer sort){
		this.sort = sort;
	}

	public String getTime(){
		return time;
	}
	public void setTime(String time){
		this.time = time;
	}

	public Integer getOnsale(){
		return onsale;
	}
	public void setOnsale(Integer onsale){
		this.onsale = onsale;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	
}

