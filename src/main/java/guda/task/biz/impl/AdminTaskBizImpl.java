package guda.task.biz.impl;

import guda.task.biz.AdminTaskBiz;
import guda.task.biz.enums.TaskAcceptStatusEnum;
import guda.task.biz.enums.TaskStatusEnum;
import guda.task.biz.query.AdminTaskQuery;
import guda.task.biz.vo.TaskViewForSellerVO;
import guda.task.dao.TaskAcceptDOMapper;
import guda.task.dao.TaskListDOMapper;
import guda.task.dao.UserDOMapper;
import guda.task.dao.domain.*;
import guda.tools.web.page.BizResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by foodoon on 2014/12/26.
 */
public class AdminTaskBizImpl implements AdminTaskBiz {

    private final static Logger logger = LoggerFactory.getLogger(AdminTaskBizImpl.class);
    @Resource
    private TaskListDOMapper taskListDOMapper;
    @Resource
    private TaskAcceptDOMapper taskAcceptDOMapper;
    @Resource
    private UserDOMapper userDOMapper;

    @Override
    public BizResult listTask(AdminTaskQuery adminTaskQuery) {
        BizResult bizResult = new BizResult();
        try {
            TaskListDOCriteria taskListDOCriteria = new TaskListDOCriteria();
            taskListDOCriteria.setStartRow(adminTaskQuery.getStartRow());
            taskListDOCriteria.setPageSize(adminTaskQuery.getPageSize());
            TaskListDOCriteria.Criteria criteria = taskListDOCriteria.createCriteria();
            if (StringUtils.hasText(adminTaskQuery.getSellerNick())) {
                criteria.andSellerNameLike("%"+adminTaskQuery.getSellerNick()+"%");
            }
            if (adminTaskQuery.getEndDate() != null && adminTaskQuery.getStartDate() != null) {
                criteria.andGmtCreatedBetween(adminTaskQuery.getStartDate(), adminTaskQuery.getEndDate());
            }
            if (adminTaskQuery.getStatus() != null) {
                criteria.andStatusEqualTo(adminTaskQuery.getStatus());
            }

            int totalCount = taskListDOMapper.countByExample(taskListDOCriteria);
            adminTaskQuery.setTotalCount(totalCount);
            List<TaskListDO> taskListDOList = taskListDOMapper.selectByExample(taskListDOCriteria);
            List<TaskViewForSellerVO> taskViewForSellerVOList = new ArrayList<TaskViewForSellerVO>(taskListDOList.size());
            for(TaskListDO taskListDO:taskListDOList){
                TaskViewForSellerVO taskViewForSellerVO = new TaskViewForSellerVO();
                taskViewForSellerVO.setTaskListDO(taskListDO);
                taskViewForSellerVO.setSellerUser(querySeller(taskListDO.getSellerId()));
                if(taskListDO.getStatus().intValue() >= TaskStatusEnum.TAOBAO_STATUS.getValue()){
                    TaskAcceptDO taskAcceptDO = queryValidAccept(taskListDO.getId());
                    taskViewForSellerVO.setTaskAcceptDO(taskAcceptDO);
                    if(taskAcceptDO!=null){
                        Long userId = taskAcceptDO.getUserId();
                        taskViewForSellerVO.setAcceptUser(queryUser(userId));
                    }
                }

                taskViewForSellerVOList.add(taskViewForSellerVO);
            }
            bizResult.data.put("taskListDOList", taskViewForSellerVOList);
            bizResult.data.put("query", adminTaskQuery);
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

    public UserDO queryUser(long userid) {
        UserDOCriteria userDOCriteria = new UserDOCriteria();
        userDOCriteria.createCriteria().andIdEqualTo(userid);
        List<UserDO> userDOList = userDOMapper.selectByExample(userDOCriteria);
        if (userDOList.size() == 1) {
            return userDOList.get(0);
        } else if (userDOList.size() > 1) {
            throw new RuntimeException("rows more than one taskid:" + userid);
        }
        return null;
    }

    public UserDO querySeller(long userid) {
        return  userDOMapper.selectByPrimaryKey(userid);
       
    }
}
