package guda.task.biz.impl;

import guda.task.biz.TaskAcceptBiz;
import guda.task.biz.TaskPropsBiz;
import guda.task.biz.enums.TaskAcceptStatusEnum;
import guda.task.biz.query.TaskAcceptQuery;
import guda.task.biz.vo.TaskAcceptVO;
import guda.task.common.util.DateHelper;
import guda.task.common.util.TaskProperty;
import guda.task.dao.TaskAcceptDOMapper;
import guda.task.dao.TaskListDOMapper;
import guda.task.dao.UserDOMapper;
import guda.task.dao.domain.*;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TaskAcceptBizImpl implements TaskAcceptBiz {

    public static final int max_accept_count = 3;

    private final static Logger logger = LoggerFactory.getLogger(TaskAcceptBizImpl.class);

    @Autowired
    private TaskAcceptDOMapper taskAcceptDOMapper;
    @Autowired
    private TaskListDOMapper taskListDOMapper;
    @Autowired
    private TaskPropsBiz taskPropsBiz;
    @Autowired
    private UserDOMapper userDOMapper;

    public BizResult detail(long id) {
        BizResult bizResult = new BizResult();
        try {
            TaskAcceptDO taskAcceptDO = taskAcceptDOMapper.selectByPrimaryKey(id);
            bizResult.data.put("taskAcceptDO", taskAcceptDO);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("query TaskAccept error", e);
        }
        return bizResult;
    }

    public BizResult list(BaseQuery baseQuery) {
        BizResult bizResult = new BizResult();
        try {
            TaskAcceptDOCriteria taskAcceptDOCriteria = new TaskAcceptDOCriteria();
            taskAcceptDOCriteria.setStartRow(baseQuery.getStartRow());
            taskAcceptDOCriteria.setPageSize(baseQuery.getPageSize());
            int totalCount = taskAcceptDOMapper.countByExample(taskAcceptDOCriteria);
            baseQuery.setTotalCount(totalCount);
            List<TaskAcceptDO> taskAcceptDOList = taskAcceptDOMapper.selectByExample(taskAcceptDOCriteria);
            bizResult.data.put("taskAcceptDOList", taskAcceptDOList);
            bizResult.data.put("query", baseQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view TaskAccept list error", e);
        }
        return bizResult;
    }

    public BizResult delete(long id) {
        BizResult bizResult = new BizResult();
        try {
            taskAcceptDOMapper.deleteByPrimaryKey(id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("delete taskAccept error", e);
        }
        return bizResult;
    }

    public BizResult create(TaskAcceptDO taskAcceptDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = taskAcceptDOMapper.insert(taskAcceptDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("create TaskAccept error", e);
        }
        return bizResult;
    }

    public BizResult update(TaskAcceptDO taskAcceptDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = taskAcceptDOMapper.updateByPrimaryKeySelective(taskAcceptDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("update TaskAccept error", e);
        }
        return bizResult;
    }

    @Override
    public BizResult listIngForBuyer(TaskAcceptQuery taskAcceptQuery) {
        BizResult bizResult = new BizResult();
        try {
            TaskAcceptDOCriteria taskAcceptDOCriteria = new TaskAcceptDOCriteria();
            taskAcceptDOCriteria.setStartRow(taskAcceptQuery.getStartRow());
            taskAcceptDOCriteria.setPageSize(taskAcceptQuery.getPageSize());
            TaskAcceptDOCriteria.Criteria criteria = taskAcceptDOCriteria.createCriteria();
            criteria.andUserIdEqualTo(taskAcceptQuery.getUserId());
            criteria.andStatusBetween(TaskAcceptStatusEnum.ACCEPT.getValue(), TaskAcceptStatusEnum.SIGN.getValue());
            int totalCount = taskAcceptDOMapper.countByExample(taskAcceptDOCriteria);
            taskAcceptQuery.setTotalCount(totalCount);
            List<TaskAcceptDO> taskAcceptDOList = taskAcceptDOMapper.selectByExample(taskAcceptDOCriteria);
            List<TaskAcceptVO> taskAcceptVOList = new ArrayList<TaskAcceptVO>(taskAcceptDOList.size());
            for (TaskAcceptDO taskAcceptDO : taskAcceptDOList) {
                TaskAcceptVO taskAcceptVO = new TaskAcceptVO();
                taskAcceptVO.setTaskAcceptDO(taskAcceptDO);
                TaskListDO taskListDO = taskListDOMapper.selectByPrimaryKey(taskAcceptDO.getTaskId());
                taskAcceptVO.setTaskListDO(taskListDO);
                taskAcceptVO.setSeller(getTaobaoUserDO(taskListDO.getSellerId()));

                Map<String, String> stringStringMap = taskPropsBiz.queryByTaskId(taskListDO.getId());
                taskAcceptVO.setSearchUrl(stringStringMap.get(TaskProperty.SEARCH_URL));
                taskAcceptVO.setSearchKey(stringStringMap.get(TaskProperty.SEARCH_KEY));
                taskAcceptVO.setSearchResultLocation("大概第" + stringStringMap.get(TaskProperty.SEARCH_RESULT_PAGE) + "页第" + stringStringMap.get(TaskProperty.SEARCH_RESULT_ROW) + "行第"
                        + stringStringMap.get(TaskProperty.SEARCH_RESULT_COL) + "个");
                taskAcceptVOList.add(taskAcceptVO);
            }
            bizResult.data.put("acceptVOList", taskAcceptVOList);
            bizResult.data.put("query", taskAcceptQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view TaskAccept list error", e);
        }
        return bizResult;
    }

    @Override
    public BizResult listHisForBuyer(TaskAcceptQuery taskAcceptQuery) {
        BizResult bizResult = new BizResult();
        try {
            TaskAcceptDOCriteria taskAcceptDOCriteria = new TaskAcceptDOCriteria();
            taskAcceptDOCriteria.setStartRow(taskAcceptQuery.getStartRow());
            taskAcceptDOCriteria.setPageSize(taskAcceptQuery.getPageSize());
            TaskAcceptDOCriteria.Criteria criteria = taskAcceptDOCriteria.createCriteria();
            criteria.andUserIdEqualTo(taskAcceptQuery.getUserId());
            criteria.andStatusBetween(TaskAcceptStatusEnum.BREAK.getValue(), TaskAcceptStatusEnum.SUCCESS.getValue());
            int totalCount = taskAcceptDOMapper.countByExample(taskAcceptDOCriteria);
            taskAcceptQuery.setTotalCount(totalCount);
            List<TaskAcceptDO> taskAcceptDOList = taskAcceptDOMapper.selectByExample(taskAcceptDOCriteria);
            List<TaskAcceptVO> taskAcceptVOList = new ArrayList<TaskAcceptVO>(taskAcceptDOList.size());
            for (TaskAcceptDO taskAcceptDO : taskAcceptDOList) {
                TaskAcceptVO taskAcceptVO = new TaskAcceptVO();
                taskAcceptVO.setTaskAcceptDO(taskAcceptDO);
                TaskListDO taskListDO = taskListDOMapper.selectByPrimaryKey(taskAcceptDO.getTaskId());
                taskAcceptVO.setTaskListDO(taskListDO);
                taskAcceptVO.setSeller(getTaobaoUserDO(taskListDO.getSellerId()));

                Map<String, String> stringStringMap = taskPropsBiz.queryByTaskId(taskListDO.getId());
                taskAcceptVO.setSearchUrl(stringStringMap.get(TaskProperty.SEARCH_URL));
                taskAcceptVO.setSearchKey(stringStringMap.get(TaskProperty.SEARCH_KEY));
                taskAcceptVO.setSearchResultLocation("大概第" + stringStringMap.get(TaskProperty.SEARCH_RESULT_PAGE) + "页第" + stringStringMap.get(TaskProperty.SEARCH_RESULT_ROW) + "行第"
                        + stringStringMap.get(TaskProperty.SEARCH_RESULT_COL) + "个");
                taskAcceptVOList.add(taskAcceptVO);
            }
            bizResult.data.put("acceptVOList", taskAcceptVOList);
            bizResult.data.put("query", taskAcceptQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view TaskAccept list error", e);
        }
        return bizResult;
    }

    @Override
    public TaskAcceptDO queryById(long id) {
        return taskAcceptDOMapper.selectByPrimaryKey(id);
    }

    @Override
    public TaskAcceptDO queryByTaskIdAndStatusInSing(long taskId) {
        TaskAcceptDOCriteria taskAcceptDOCriteria = new TaskAcceptDOCriteria();
        taskAcceptDOCriteria.createCriteria().andStatusEqualTo(TaskAcceptStatusEnum.SIGN.getValue())
                .andTaskIdEqualTo(taskId);
        List<TaskAcceptDO> taskAcceptDOs = taskAcceptDOMapper.selectByExample(taskAcceptDOCriteria);
        if (taskAcceptDOs.size() == 1) {
            return taskAcceptDOs.get(0);
        } else if (taskAcceptDOs.size() > 1) {
            throw new RuntimeException("rows more than one taskid:" + taskId);
        }
        return null;
    }

    @Override
    public boolean canAcceptCheckWeek(long userid) {
        TaskAcceptDOCriteria taskAcceptDOCriteria = new TaskAcceptDOCriteria();
        taskAcceptDOCriteria.createCriteria().andUserIdEqualTo(userid)
        .andStatusNotEqualTo(TaskAcceptStatusEnum.BREAK.getValue()).andGmtCreatedBetween(DateHelper.last7Day(), new Date());
        int i = taskAcceptDOMapper.countByExample(taskAcceptDOCriteria);
        if(i > max_accept_count){
            return false;
        }

        return true;
    }

    @Override
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

    @Override
    public TaskAcceptDO query(long user, long taskId) {
        TaskAcceptDOCriteria taskAcceptDOCriteria = new TaskAcceptDOCriteria();
        taskAcceptDOCriteria.createCriteria().andUserIdEqualTo(user).andTaskIdEqualTo(taskId);
        List<TaskAcceptDO> taskAcceptDOs = taskAcceptDOMapper.selectByExample(taskAcceptDOCriteria);
        if (taskAcceptDOs.size() == 1) {
            return taskAcceptDOs.get(0);
        } else if (taskAcceptDOs.size() > 1) {
            throw new RuntimeException("rows more than one taskid:" + taskId);
        }
        return null;
    }

    private UserDO getTaobaoUserDO(long userid) {
        return userDOMapper.selectByPrimaryKey(userid);
    }


}
