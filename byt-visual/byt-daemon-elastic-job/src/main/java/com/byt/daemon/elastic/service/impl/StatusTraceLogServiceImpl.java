package com.byt.daemon.elastic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.byt.daemon.elastic.entity.StatusTraceLog;
import com.byt.daemon.elastic.mapper.StatusTraceLogMapper;
import com.byt.daemon.elastic.service.StatusTraceLogService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 任务轨迹处理
 *
 * @author liyang
 * @date 2020/02/22
 */
@Service("statusTraceLogService")
@AllArgsConstructor
public class StatusTraceLogServiceImpl extends ServiceImpl<StatusTraceLogMapper, StatusTraceLog> implements StatusTraceLogService {

}
