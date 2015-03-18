package guda.task.biz.impl;

import java.util.ArrayList;
import java.util.List;

import guda.task.biz.TaskAcceptBiz;
import guda.task.biz.enums.TaskAcceptStatusEnum;
import guda.task.biz.enums.TaskStatusEnum;
import guda.task.biz.query.TaskQuery;
import guda.task.biz.vo.TaskViewForSellerVO;
import guda.task.dao.TaskAcceptDOMapper;
import guda.task.dao.domain.TaskAcceptDO;
import guda.task.dao.domain.TaskAcceptDOCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import guda.task.biz.TaskListBiz;
import guda.task.dao.TaskListDOMapper;
import guda.task.dao.domain.TaskListDO;
import guda.task.dao.domain.TaskListDOCriteria;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public class TaskListBizImpl implements TaskListBiz{

    private final static Logger logger = LoggerFactory.getLogger(TaskListBizImpl.class);

    @Autowired
    private TaskListDOMapper taskListDOMapper;
    @Autowired
    private TaskAcceptDOMapper taskAcceptDOMapper;

    public BizResult detail(long id) {
        BizResult bizResult = new BizResult();
        try{
            TaskListDO taskListDO = taskListDOMapper.selectByPrimaryKey(id);
            bizResult.data.put("taskListDO", taskListDO);
            bizResult.success = true;
        }catch(Exception e){
            logger.error("query TaskList error",e);
        }
        return bizResult;
}

    public BizResult list(TaskQuery taskQuery) {
        BizResult bizResult = new BizResult();
        try {
            TaskListDOCriteria taskListDOCriteria = new TaskListDOCriteria();
            taskListDOCriteria.setStartRow(taskQuery.getStartRow());
            taskListDOCriteria.setPageSize(taskQuery.getPageSize());
            TaskListDOCriteria.Criteria criteria = taskListDOCriteria.createCriteria();
            if(taskQuery.getStatus()>=0){
                criteria.andStatusEqualTo(taskQuery.getStatus());
            }
            if(taskQuery.getSellerId()!=null){
                criteria.andSellerIdEqualTo(taskQuery.getSellerId());
            }
            int totalCount = taskListDOMapper.countByExample(taskListDOCriteria);
            taskQuery.setTotalCount(totalCount);
            List<TaskListDO> taskListDOList = taskListDOMapper.selectByExample(taskListDOCriteria);
            bizResult.data.put("taskListDOList", taskListDOList);
            bizResult.data.put("query", taskQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view TaskList list error", e);
        }
            return bizResult;
     }

    public BizResult delete(long id) {
        BizResult bizResult = new BizResult();
        try {
            taskListDOMapper.deleteByPrimaryKey(id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("delete taskList error", e);
        }
        return bizResult;
    }

    public BizResult create(TaskListDO taskListDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = taskListDOMapper.insert(taskListDO);
            bizResult.data.put("count", count);
            bizResult.data.put("id", taskListDO.getId());
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("create TaskList error", e);
        }
        return bizResult;
    }

    public BizResult update(TaskListDO taskListDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = taskListDOMapper.updateByPrimaryKeySelective(taskListDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("update TaskList error", e);
        }
        return bizResult;
    }

    @Override
    public TaskListDO queryById(long id) {
        return taskListDOMapper.selectByPrimaryKey(id);
    }

    @Override
    public BizResult listIng(TaskQuery taskQuery) {
        BizResult bizResult = new BizResult();
        try {
            TaskListDOCriteria taskListDOCriteria = new TaskListDOCriteria();
            taskListDOCriteria.setStartRow(taskQuery.getStartRow());
            taskListDOCriteria.setPageSize(taskQuery.getPageSize());
            TaskListDOCriteria.Criteria criteria = taskListDOCriteria.createCriteria();

            criteria.andSellerIdEqualTo(taskQuery.getSellerId());
            criteria.andStatusBetween(TaskStatusEnum.PUBLISH.getValue(),TaskStatusEnum.SIGN.getValue());
            int totalCount = taskListDOMapper.countByExample(taskListDOCriteria);
            taskQuery.setTotalCount(totalCount);
            List<TaskListDO> taskListDOList = taskListDOMapper.selectByExample(taskListDOCriteria);
            List<TaskViewForSellerVO> taskViewForSellerVOList = new ArrayList<TaskViewForSellerVO>(taskListDOList.size());
            for(TaskListDO taskListDO:taskListDOList){
                TaskViewForSellerVO taskViewForSellerVO = new TaskViewForSellerVO();
                taskViewForSellerVO.setTaskListDO(taskListDO);
                if(taskListDO.getStatus().intValue() >= TaskStatusEnum.TAOBAO_STATUS.getValue()){
                    TaskAcceptDO taskAcceptDO = queryValidAccept(taskListDO.getId());
                    taskViewForSellerVO.setTaskAcceptDO(taskAcceptDO);
                }
                taskViewForSellerVOList.add(taskViewForSellerVO);
            }
            bizResult.data.put("taskListDOList", taskViewForSellerVOList);
            bizResult.data.put("query", taskQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view TaskList list error", e);
        }
        return bizResult;
    }

    @Override
    public BizResult listHis(TaskQuery taskQuery) {
        BizResult bizResult = new BizResult();
        try {
            TaskListDOCriteria taskListDOCriteria = new TaskListDOCriteria();
            taskListDOCriteria.setStartRow(taskQuery.getStartRow());
            taskListDOCriteria.setPageSize(taskQuery.getPageSize());
            TaskListDOCriteria.Criteria criteria = taskListDOCriteria.createCriteria();

            criteria.andSellerIdEqualTo(taskQuery.getSellerId());
            criteria.andStatusBetween(TaskStatusEnum.BREAK.getValue(), TaskStatusEnum.SUCCESS.getValue());
            int totalCount = taskListDOMapper.countByExample(taskListDOCriteria);
            taskQuery.setTotalCount(totalCount);
            List<TaskListDO> taskListDOList = taskListDOMapper.selectByExample(taskListDOCriteria);
            bizResult.data.put("taskListDOList", taskListDOList);
            bizResult.data.put("query", taskQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view TaskList list error", e);
        }
        return bizResult;
    }

    @Override
    public BizResult listPublishForBuyer(TaskQuery taskQuery) {
        BizResult bizResult = new BizResult();
        try {
            TaskListDOCriteria taskListDOCriteria = new TaskListDOCriteria();
            taskListDOCriteria.setStartRow(taskQuery.getStartRow());
            taskListDOCriteria.setPageSize(taskQuery.getPageSize());
            TaskListDOCriteria.Criteria criteria = taskListDOCriteria.createCriteria();
            criteria.andStatusEqualTo(TaskStatusEnum.PUBLISH.getValue());
            int totalCount = taskListDOMapper.countByExample(taskListDOCriteria);
            taskQuery.setTotalCount(totalCount);
            List<TaskListDO> taskListDOList = taskListDOMapper.selectByExample(taskListDOCriteria);
            bizResult.data.put("taskListDOList", taskListDOList);
            bizResult.data.put("query", taskQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view TaskList list error", e);
        }
        return bizResult;
    }

    public TaskAcceptDO queryValidAccept(long taskId) {
        TaskAcceptDOCriteria taskAcceptDOCriteria = new TaskAcceptDOCriteria();
        taskAcceptDOCriteria.createCriteria().andStatusNotEqualTo(TaskAcceptStatusEnum.BREAK.getValue())
                .andTaskIdEqualTo(taskId);
        List<TaskAcceptDO> taskAcceptDOs = taskAcceptDOMapper.selectByExample(taskAcceptDOCriteria);
        if (taskAcceptDOs.size() == 1) {
            return taskAcceptDOs.get(0);
        } else if (taskAcceptDOs.size() > 1) {
            throw new RuntimeException("rows more than one taskid:" + taskId);
        }
        return null;
    }

}
