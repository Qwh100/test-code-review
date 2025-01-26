package com.qwh.entity.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 */
@Data
public class UserVoucherVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 优惠卷Id
     */
    private Long voucherId;


    /**
     * 代金券标题
     */
    private String title;


    /**
     * 使用规则
     */
    private String rules;


    /**
     * 抵扣金额
     */
    private Long actualValue;

    /**
     * 优惠券类型
     */
    private Integer type;


    /**
     * 数量
     */
    private Integer count;


    /**
     *过期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expiryTime;


    /**
     * 获取时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime addTime;




}
