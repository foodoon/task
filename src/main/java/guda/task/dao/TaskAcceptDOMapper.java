package guda.task.dao;

import guda.task.dao.domain.TaskAcceptDO;
import guda.task.dao.domain.TaskAcceptDOCriteria;
import java.util.List;

public interface TaskAcceptDOMapper {
    int countByExample(TaskAcceptDOCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(TaskAcceptDO record);

    int insertSelective(TaskAcceptDO record);

    List<TaskAcceptDO> selectByExample(TaskAcceptDOCriteria example);

    TaskAcceptDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TaskAcceptDO record);

    int updateByPrimaryKey(TaskAcceptDO record);
}