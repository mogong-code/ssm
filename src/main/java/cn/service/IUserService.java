package cn.service;

import cn.domain.Role;
import cn.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface IUserService {

    List<UserInfo> findAll(Integer page,Integer pageSize) throws Exception;

    UserInfo findByUsername(String username);

    void saveUser(UserInfo userInfo)throws Exception;

    UserInfo findById(String userId)throws Exception;

    List<Role> findOtherRoles(String userId)throws Exception;

    void addRoleToUser(String userId, String[] roleId)throws Exception;

    List<UserInfo> findString(Integer page, Integer pageSize, String findString)throws Exception;

    void deleteById(String userId) throws Exception;

    void alter(UserInfo userInfo) throws Exception;
}
