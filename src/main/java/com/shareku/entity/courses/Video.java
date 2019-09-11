package com.shareku.entity.courses;


import lombok.Data;

/**
 * 视频类
 * @author guodongbin
 * @date 2014-09-28 17:12
 *
 */
@Data
public class Video {
	private Integer videoId; // 视频ID
	private Integer courseId; //所属课程ID
	private String filePath; //视频存放路径
	
	public Integer getVideoId() {
		return videoId;
	}
	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
