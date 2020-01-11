package cn.dao;

import cn.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("permissionDao")
public interface IPermissionDao {
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findPermissionByRoleId(String roleId)throws Exception;

    @Select("select * from permission")
    List<Permission> findAll()throws Exception;

    @Insert("insert into permission (permissionName,url) values (#{permissionName},#{url})")
    void save(Permission permission) throws Exception;

    @Select("select * from permission where id=#{permissionId}")
    Permission findById(String permissionId) throws Exception;

    @Select("select * from permission where id like '%${findString}%'" +
            " or permissionName like '%${findString}%'" +
            " or url like '%${findString}%'")
    List<Permission> findString(@Param("findString") String findString) throws Exception;;
}
