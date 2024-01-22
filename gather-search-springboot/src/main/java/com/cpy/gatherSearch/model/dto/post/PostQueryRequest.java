package com.cpy.gatherSearch.model.dto.post;

import lombok.Data;

import java.util.Date;

@Data
public class PostQueryRequest {
    /**
     * 标题
     */
    private String title;

    /**
     * 标签列表（json 数组）
     */
    private String tags;
    /**
     * 标题和内容搜索
     */
    private String context;
    /**
     * 点赞数
     */
    private Integer thumbNum;

    /**
     * 收藏数
     */
    private Integer favourNum;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 内容
     */
    private String content;
}
