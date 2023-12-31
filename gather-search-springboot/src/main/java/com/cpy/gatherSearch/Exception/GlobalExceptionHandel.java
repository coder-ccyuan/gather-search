package com.cpy.gatherSearch.Exception;

import com.cpy.gatherSearch.common.BaseResponse;
import com.cpy.gatherSearch.common.StatuesCode;
import com.cpy.gatherSearch.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandel {
    /**
     * 通用异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(CommonException.class)
    public BaseResponse commonExceptionHandler(CommonException e){
        log.error("commonException: "+e.getMessage(),e);
        return ResultUtils.error(e.getCode(),e.getMessage(),e.getDescription());
    }

    /**
     * 系统异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse RuntimeExceptionHandler(CommonException e){
        log.error("runtimeException: "+e.getMessage(),e);
        return ResultUtils.error(StatuesCode.SYSTEM_EXCEPTION);
    }
}
