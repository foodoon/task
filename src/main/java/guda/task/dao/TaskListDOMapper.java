package guda.task.dao;

import guda.task.dao.domain.TaskListDO;
import guda.task.dao.domain.TaskListDOCriteria;
import java.util.List;

public interface TaskListDOMapper {
    int countByExample(TaskListDOCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(TaskListDO record);

    int insertSelective(TaskListDO record);

    List<TaskListDO> selectByExample(TaskListDOCriteria example);

    TaskListDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TaskListDO record);

    int updateByPrimaryKey(TaskListDO record);
}