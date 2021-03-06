package cn.service.impl;

import cn.dao.ISysLogDao;
import cn.domain.SysLog;
import cn.service.ISysLogService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sysLogService")
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    ISysLogDao sysLogDao;

    public void save(SysLog sysLog) throws Exception{
        sysLogDao.save(sysLog);
    }

    public List<SysLog> findAll(Integer page,Integer pageSize) throws Exception {
        PageHelper.startPage(page,pageSize);
        return sysLogDao.findAll();
    }

    @Override
    public List<SysLog> findString(Integer page, Integer pageSize, String findString) throws Exception{
        PageHelper.startPage(page,pageSize);
        return sysLogDao.findString(findString);
    }
}
