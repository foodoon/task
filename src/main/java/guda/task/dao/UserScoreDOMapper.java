package guda.task.dao;

import guda.task.dao.domain.UserScoreDO;
import guda.task.dao.domain.UserScoreDOCriteria;
import java.util.List;

public interface UserScoreDOMapper {
    int countByExample(UserScoreDOCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(UserScoreDO record);

    int insertSelective(UserScoreDO record);

    List<UserScoreDO> selectByExample(UserScoreDOCriteria example);

    UserScoreDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserScoreDO record);

    int updateByPrimaryKey(UserScoreDO record);
}