package guda.task.biz.impl;

import guda.task.biz.AdminUserBiz;
import guda.task.biz.query.AdminUserQuery;
import guda.task.dao.UserDOMapper;
import guda.task.dao.domain.UserDO;
import guda.task.dao.domain.UserDOCriteria;
import guda.tools.web.page.BizResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by well on 2014/12/27.
 */
public class AdminUserBizImpl implements AdminUserBiz {
    private final static Logger logger = LoggerFactory.getLogger(AdminUserBizImpl.class);
    @Autowired
    private UserDOMapper userDOMapper;
    @Override
    public BizResult listUser(AdminUserQuery adminUserQuery) {
        BizResult bizResult = new BizResult();
        try {
            UserDOCriteria userDOCriteria = new UserDOCriteria();
            UserDOCriteria.Criteria criteria = userDOCriteria.createCriteria();
            if(adminUserQuery.getStatus()!=null){
                criteria.andStatusEqualTo(adminUserQuery.getStatus());
            }
            if(StringUtils.hasText(adminUserQuery.getLoginName())){
                criteria.andLoginNameLike("%"+adminUserQuery.getLoginName()+"%");
            }
            if(StringUtils.hasText(adminUserQuery.getTaobaoUserNick())){
                criteria.andTaobaoUserNickLike("%" + adminUserQuery.getTaobaoUserNick() + "%");
            }
            if(StringUtils.hasText(adminUserQuery.getWangwang())){
                criteria.andWangwangLike("%" + adminUserQuery.getWangwang() + "%");
            }
            userDOCriteria.setStartRow(adminUserQuery.getStartRow());
            userDOCriteria.setPageSize(adminUserQuery.getPageSize());
            int totalCount = userDOMapper.countByExample(userDOCriteria);
            adminUserQuery.setTotalCount(totalCount);
            List<UserDO> userDOList = userDOMapper.selectByExample(userDOCriteria);
            bizResult.data.put("userDOList", userDOList);
            bizResult.data.put("query", adminUserQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view User list error", e);
        }
        return bizResult;
    }
}
