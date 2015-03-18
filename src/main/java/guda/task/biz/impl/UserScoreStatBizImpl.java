package guda.task.biz.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import guda.task.biz.UserScoreStatBiz;
import guda.task.dao.UserScoreStatDOMapper;
import guda.task.dao.domain.UserScoreStatDO;
import guda.task.dao.domain.UserScoreStatDOCriteria;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public class UserScoreStatBizImpl implements UserScoreStatBiz {

    private final static Logger logger = LoggerFactory.getLogger(UserScoreStatBizImpl.class);

    @Autowired
    private UserScoreStatDOMapper userScoreStatDOMapper;

    public BizResult detail(long id) {
        BizResult bizResult = new BizResult();
        try {
            UserScoreStatDO userScoreStatDO = userScoreStatDOMapper.selectByPrimaryKey(id);
            bizResult.data.put("userScoreStatDO", userScoreStatDO);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("query UserScoreStat error", e);
        }
        return bizResult;
    }

    public BizResult list(BaseQuery baseQuery) {
        BizResult bizResult = new BizResult();
        try {
            UserScoreStatDOCriteria userScoreStatDOCriteria = new UserScoreStatDOCriteria();
            userScoreStatDOCriteria.setStartRow(baseQuery.getStartRow());
            userScoreStatDOCriteria.setPageSize(baseQuery.getPageSize());
            int totalCount = userScoreStatDOMapper.countByExample(userScoreStatDOCriteria);
            baseQuery.setTotalCount(totalCount);
            List<UserScoreStatDO> userScoreStatDOList = userScoreStatDOMapper.selectByExample(userScoreStatDOCriteria);
            bizResult.data.put("userScoreStatDOList", userScoreStatDOList);
            bizResult.data.put("query", baseQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view UserScoreStat list error", e);
        }
        return bizResult;
    }

    public BizResult delete(long id) {
        BizResult bizResult = new BizResult();
        try {
            userScoreStatDOMapper.deleteByPrimaryKey(id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("delete userScoreStat error", e);
        }
        return bizResult;
    }

    public BizResult create(UserScoreStatDO userScoreStatDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = userScoreStatDOMapper.insert(userScoreStatDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("create UserScoreStat error", e);
        }
        return bizResult;
    }

    public BizResult update(UserScoreStatDO userScoreStatDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = userScoreStatDOMapper.updateByPrimaryKeySelective(userScoreStatDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("update UserScoreStat error", e);
        }
        return bizResult;
    }

    @Override
    public UserScoreStatDO queryByUserId(long userId) {
        try {
            UserScoreStatDOCriteria userScoreStatDOCriteria = new UserScoreStatDOCriteria();
            userScoreStatDOCriteria.createCriteria().andUserIdEqualTo(userId);
            List<UserScoreStatDO> userScoreStatDOList = userScoreStatDOMapper.selectByExample(userScoreStatDOCriteria);
            if (userScoreStatDOList.size() == 1) {
                return userScoreStatDOList.get(0);
            }
        } catch (Exception e) {
            logger.error("view UserScoreStat list error", e);
        }
        return null;
    }

}
