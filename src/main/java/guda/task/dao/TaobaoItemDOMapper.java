package guda.task.dao;

import guda.task.dao.domain.TaobaoItemDO;
import guda.task.dao.domain.TaobaoItemDOCriteria;
import java.util.List;

public interface TaobaoItemDOMapper {
    int countByExample(TaobaoItemDOCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(TaobaoItemDO record);

    int insertSelective(TaobaoItemDO record);

    List<TaobaoItemDO> selectByExample(TaobaoItemDOCriteria example);

    TaobaoItemDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TaobaoItemDO record);

    int updateByPrimaryKey(TaobaoItemDO record);
}