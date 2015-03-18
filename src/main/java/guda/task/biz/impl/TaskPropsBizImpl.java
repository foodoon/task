package guda.task.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import guda.task.biz.TaskPropsBiz;
import guda.task.dao.TaskPropsDOMapper;
import guda.task.dao.domain.TaskPropsDO;
import guda.task.dao.domain.TaskPropsDOCriteria;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public class TaskPropsBizImpl implements TaskPropsBiz{

    private final static Logger logger = LoggerFactory.getLogger(TaskPropsBizImpl.class);

    @Autowired
    private TaskPropsDOMapper taskPropsDOMapper;

    public BizResult detail(long id) {
        BizResult bizResult = new BizResult();
        try{
            TaskPropsDO taskPropsDO = taskPropsDOMapper.selectByPrimaryKey(id);
            bizResult.data.put("taskPropsDO", taskPropsDO);
            bizResult.success = true;
        }catch(Exception e){
            logger.error("query TaskProps error",e);
        }
        return bizResult;
}

    public BizResult list(BaseQuery baseQuery) {
        BizResult bizResult = new BizResult();
        try {
            TaskPropsDOCriteria taskPropsDOCriteria = new TaskPropsDOCriteria();
            taskPropsDOCriteria.setStartRow(baseQuery.getStartRow());
            taskPropsDOCriteria.setPageSize(baseQuery.getPageSize());
            int totalCount = taskPropsDOMapper.countByExample(taskPropsDOCriteria);
            baseQuery.setTotalCount(totalCount);
            List<TaskPropsDO> taskPropsDOList = taskPropsDOMapper.selectByExample(taskPropsDOCriteria);
            bizResult.data.put("taskPropsDOList", taskPropsDOList);
            bizResult.data.put("query", baseQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view TaskProps list error", e);
        }
            return bizResult;
     }

    public BizResult delete(long id) {
        BizResult bizResult = new BizResult();
        try {
            taskPropsDOMapper.deleteByPrimaryKey(id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("delete taskProps error", e);
        }
        return bizResult;
    }

    public BizResult create(TaskPropsDO taskPropsDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = taskPropsDOMapper.insert(taskPropsDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("create TaskProps error", e);
        }
        return bizResult;
    }

    public BizResult update(TaskPropsDO taskPropsDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = taskPropsDOMapper.updateByPrimaryKeySelective(taskPropsDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("update TaskProps error", e);
        }
        return bizResult;
    }

    @Override
    public Map<String, String> queryByTaskId(long taskId) {
        TaskPropsDOCriteria taskPropsDOCriteria = new TaskPropsDOCriteria();
        taskPropsDOCriteria.createCriteria().andTaskIdEqualTo(taskId);
        List<TaskPropsDO> taskPropsDOs = taskPropsDOMapper.selectByExample(taskPropsDOCriteria);
        Map<String,String> map = new HashMap<String,String>(taskPropsDOs.size() *2);
        for(TaskPropsDO taskPropsDO:taskPropsDOs){
            map.put(taskPropsDO.getRequireKey(),taskPropsDO.getRequireValue());
        }
        return map;
    }

    @Override
    public void deleteByTaskId(long taskId) {
        taskPropsDOMapper.deleteByTaskId(taskId);
    }

}
