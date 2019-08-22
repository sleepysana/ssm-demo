package cn.akira.mapper;

import cn.akira.pojo.UserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
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
}