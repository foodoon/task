package guda.task.dao;

import guda.task.dao.domain.UserScoreStatDO;
import guda.task.dao.domain.UserScoreStatDOCriteria;
import java.util.List;

public interface UserScoreStatDOMapper {
    int countByExample(UserScoreStatDOCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(UserScoreStatDO record);

    int insertSelective(UserScoreStatDO record);

    List<UserScoreStatDO> selectByExample(UserScoreStatDOCriteria example);

    UserScoreStatDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserScoreStatDO record);

    int updateByPrimaryKey(UserScoreStatDO record);
}