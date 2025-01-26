package com.qwh.entity.vo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;



@Data
public class UserChatVo implements Serializable {
	private static final long serialVersionUID = 1L;

	
	/**
	 * 主键id
	 */
    @TableId
	private Long id;
	/**
	 * 用户名
	 */
					
	private String yonghuming;

	
	/**
	 * 姓名
	 */
					
	private String xingming;

	
	/**
	 * 头像
	 */
					
	private String touxiang;

	
	/**
	 * 状态
	 */
					
	private Integer status;

	/**
	 * 用户id
	 */

	private Long userid;

	/**
	 * 管理员id
	 */

	private Long adminid;

	/**
	 * 提问
	 */

	private String ask;

	/**
	 * 回复
	 */

	private String reply;

	/**
	 * 是否回复
	 */

	private Integer isreply;
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	private Integer wdCount;


}
