package cn.akira.mapper;

import cn.akira.pojo.VerifyCode;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VerifyCodeMapper {
    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(VerifyCode record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(VerifyCode record);
}