package guda.task.dao;

import guda.task.dao.domain.OperateLogDO;
import guda.task.dao.domain.OperateLogDOCriteria;
import java.util.List;

public interface OperateLogDOMapper {
    int countByExample(OperateLogDOCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(OperateLogDO record);

    int insertSelective(OperateLogDO record);

    List<OperateLogDO> selectByExample(OperateLogDOCriteria example);

    OperateLogDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OperateLogDO record);

    int updateByPrimaryKey(OperateLogDO record);
}