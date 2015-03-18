package guda.task.dao;

import guda.task.dao.domain.TaskPropsDO;
import guda.task.dao.domain.TaskPropsDOCriteria;
import java.util.List;

public interface TaskPropsDOMapper {
    int countByExample(TaskPropsDOCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(TaskPropsDO record);

    int insertSelective(TaskPropsDO record);

    List<TaskPropsDO> selectByExample(TaskPropsDOCriteria example);

    TaskPropsDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TaskPropsDO record);

    int updateByPrimaryKey(TaskPropsDO record);

    int deleteByTaskId(Long taskId);
}