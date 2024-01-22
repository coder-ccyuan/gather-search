package com.cpy.gatherSearch.model.dto.post;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
@Data
public class PostUpdateRequest {
    /**
     * id
     */
    private Long id;
    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;
    /**
     * 用户Id
     */
    private Long userId;
    /**
     * 标签列表（json 数组）
     */
    private String tags;
    /**
     * 更新时间
     */
    private Date updateTime;
}
