package guda.task.dao;

import guda.task.dao.domain.TaobaoUserDO;
import guda.task.dao.domain.TaobaoUserDOCriteria;
import java.util.List;

public interface TaobaoUserDOMapper {
    int countByExample(TaobaoUserDOCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(TaobaoUserDO record);

    int insertSelective(TaobaoUserDO record);

    List<TaobaoUserDO> selectByExample(TaobaoUserDOCriteria example);

    TaobaoUserDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TaobaoUserDO record);

    int updateByPrimaryKey(TaobaoUserDO record);
}