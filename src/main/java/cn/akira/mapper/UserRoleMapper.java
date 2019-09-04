package cn.akira.mapper;

import cn.akira.pojo.UserRole;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface UserRoleMapper {
    /**
     * insert record to table
     * @param userRole the record
     * @return insert count
     */
    int insert(UserRole userRole);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(UserRole record);

    List<UserRole> queryAll();

    UserRole queryAllById(@RequestParam("id") Integer id);

    int updateAllById(UserRole userRole);

}