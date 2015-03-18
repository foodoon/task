package guda.task.biz.impl;

import java.util.ArrayList;
import java.util.List;

import guda.task.biz.query.UserScoreQuery;
import guda.task.biz.vo.UserScoreVO;
import guda.task.dao.TaskListDOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import guda.task.biz.UserScoreBiz;
import guda.task.dao.UserScoreDOMapper;
import guda.task.dao.domain.UserScoreDO;
import guda.task.dao.domain.UserScoreDOCriteria;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public class UserScoreBizImpl implements UserScoreBiz{

    private final static Logger logger = LoggerFactory.getLogger(UserScoreBizImpl.class);

    @Autowired
    private UserScoreDOMapper userScoreDOMapper;
    @Autowired
    private TaskListDOMapper taskListDOMapper;

    public BizResult detail(long id) {
        BizResult bizResult = new BizResult();
        try{
            UserScoreDO userScoreDO = userScoreDOMapper.selectByPrimaryKey(id);
            bizResult.data.put("userScoreDO", userScoreDO);
            bizResult.success = true;
        }catch(Exception e){
            logger.error("query UserScore error",e);
        }
        return bizResult;
}

    public BizResult list(UserScoreQuery userScoreQuery) {
        BizResult bizResult = new BizResult();
        try {
            UserScoreDOCriteria userScoreDOCriteria = new UserScoreDOCriteria();
            userScoreDOCriteria.setStartRow(userScoreQuery.getStartRow());
            userScoreDOCriteria.setPageSize(userScoreQuery.getPageSize());
            userScoreDOCriteria.createCriteria().andUserIdEqualTo(userScoreQuery.getUserId());
            userScoreDOCriteria.setOrderByClause(" gmt_created desc");
            int totalCount = userScoreDOMapper.countByExample(userScoreDOCriteria);
            userScoreQuery.setTotalCount(totalCount);
            List<UserScoreDO> userScoreDOList = userScoreDOMapper.selectByExample(userScoreDOCriteria);
            List<UserScoreVO> userScoreVOList = new ArrayList<UserScoreVO>(userScoreDOList.size());
            for(UserScoreDO userScoreDO:userScoreDOList){
                UserScoreVO userScoreVO = new UserScoreVO();
                userScoreVO.setUserScoreDO(userScoreDO);
                userScoreVO.setTaskListDO(taskListDOMapper.selectByPrimaryKey(userScoreDO.getTaskId()));
                userScoreVOList.add(userScoreVO);
            }
            bizResult.data.put("userScoreVOList", userScoreVOList);
            bizResult.data.put("query", userScoreQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view UserScore list error", e);
        }
            return bizResult;
     }

    public BizResult delete(long id) {
        BizResult bizResult = new BizResult();
        try {
            userScoreDOMapper.deleteByPrimaryKey(id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("delete userScore error", e);
        }
        return bizResult;
    }

    public BizResult create(UserScoreDO userScoreDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = userScoreDOMapper.insert(userScoreDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("create UserScore error", e);
        }
        return bizResult;
    }

    public BizResult update(UserScoreDO userScoreDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = userScoreDOMapper.updateByPrimaryKeySelective(userScoreDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("update UserScore error", e);
        }
        return bizResult;
    }

    }
