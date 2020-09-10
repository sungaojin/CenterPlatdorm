package com.byt.daemon.elastic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.byt.daemon.elastic.entity.ExecutionLog;
import com.byt.daemon.elastic.mapper.ExecutionLogMapper;
import com.byt.daemon.elastic.service.ExecutionLogService;
import org.springframework.stereotype.Service;

/**
 * 任务日志处理
 *
 * @author liyang
 * @date 2020/02/22
 */
@Service("executionLogService")
public class ExecutionLogServiceImpl extends ServiceImpl<ExecutionLogMapper, ExecutionLog> implements ExecutionLogService {

}
