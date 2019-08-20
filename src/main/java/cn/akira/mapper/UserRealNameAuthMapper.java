package cn.akira.mapper;

import cn.akira.pojo.UserRealNameAuth;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRealNameAuthMapper {
    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(UserRealNameAuth record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(UserRealNameAuth record);
}