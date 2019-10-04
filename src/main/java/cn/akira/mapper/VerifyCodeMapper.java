package cn.akira.mapper;

import cn.akira.pojo.VerifyCode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

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
    
    VerifyCode queryAllByFileName(@RequestParam("fileName") String fileName);

    int deleteByEmail(@RequestParam("email") String email);

    VerifyCode queryAllByBindEmail(@RequestParam("email") String email);
}