package cn.service;

import cn.domain.SysLog;

import java.util.List;

public interface ISysLogService {
    void save(SysLog sysLog) throws Exception;
    List<SysLog> findAll(Integer page, Integer pageSize) throws Exception;

    List<SysLog> findString(Integer page, Integer pageSize, String findString)throws Exception;
}
