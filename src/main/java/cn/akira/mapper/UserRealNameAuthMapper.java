package cn.akira.mapper;

import cn.akira.pojo.UserRealNameAuth;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

@Mapper
public interface UserRealNameAuthMapper {
    /**
     * insert record to table
     *
     * @param userRealNameAuth the record
     * @return insert count
     */
    int insert(UserRealNameAuth userRealNameAuth);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(UserRealNameAuth record);


    UserRealNameAuth queryInfoByCidAndCertType(
           String cid,
           String certType
    );


}