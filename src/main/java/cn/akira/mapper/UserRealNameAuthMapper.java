package cn.akira.mapper;

import cn.akira.pojo.UserRealNameAuth;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface UserRealNameAuthMapper {

    UserRealNameAuth queryAllById(@RequestParam("id") Integer id);

    List<UserRealNameAuth> queryAll();

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