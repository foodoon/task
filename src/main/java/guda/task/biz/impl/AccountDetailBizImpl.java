package guda.task.biz.impl;

import java.util.ArrayList;
import java.util.List;

import guda.task.biz.TaskListBiz;
import guda.task.biz.query.AccountDetailQuery;
import guda.task.biz.query.AccountQuery;
import guda.task.biz.vo.AccountDetailVO;
import guda.task.dao.domain.AccountDO;
import guda.task.dao.domain.AccountDOCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import guda.task.biz.AccountDetailBiz;
import guda.task.dao.AccountDetailDOMapper;
import guda.task.dao.domain.AccountDetailDO;
import guda.task.dao.domain.AccountDetailDOCriteria;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public class AccountDetailBizImpl implements AccountDetailBiz{

    private final static Logger logger = LoggerFactory.getLogger(AccountDetailBizImpl.class);

    @Autowired
    private AccountDetailDOMapper accountDetailDOMapper;

    @Autowired
    private TaskListBiz taskListBiz;

    public BizResult detail(long id) {
        BizResult bizResult = new BizResult();
        try{
            AccountDetailDO accountDetailDO = accountDetailDOMapper.selectByPrimaryKey(id);
            bizResult.data.put("accountDetailDO", accountDetailDO);
            bizResult.success = true;
        }catch(Exception e){
            logger.error("query AccountDetail error",e);
        }
        return bizResult;
}

    public BizResult list(AccountDetailQuery acountDetailQuery) {
        BizResult bizResult = new BizResult();
        try {
            AccountDetailDOCriteria accountDetailDOCriteria = new AccountDetailDOCriteria();
            accountDetailDOCriteria.createCriteria().andAccountIdEqualTo(acountDetailQuery.getAccountId());
            accountDetailDOCriteria.setStartRow(acountDetailQuery.getStartRow());
            accountDetailDOCriteria.setPageSize(acountDetailQuery.getPageSize());
            accountDetailDOCriteria.setOrderByClause("gmt_created desc");
            int totalCount = accountDetailDOMapper.countByExample(accountDetailDOCriteria);
            acountDetailQuery.setTotalCount(totalCount);
            List<AccountDetailDO> accountDetailDOList = accountDetailDOMapper.selectByExample(accountDetailDOCriteria);
            List<AccountDetailVO> accontDetailVOList = new ArrayList<AccountDetailVO>();
            for(AccountDetailDO accountDetailDO:accountDetailDOList){
                AccountDetailVO accountDetailVO = new AccountDetailVO();
                accountDetailVO.setAccountDetailDO(accountDetailDO);
                accountDetailVO.setTaskListDO(taskListBiz.queryById(accountDetailDO.getTaskId()));
                accontDetailVOList.add(accountDetailVO);
            }
            bizResult.data.put("accountDetailVOList", accontDetailVOList);
            bizResult.data.put("query", acountDetailQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view Account list error", e);
        }
        return bizResult;
     }

    public BizResult delete(long id) {
        BizResult bizResult = new BizResult();
        try {
            accountDetailDOMapper.deleteByPrimaryKey(id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("delete accountDetail error", e);
        }
        return bizResult;
    }

    public BizResult create(AccountDetailDO accountDetailDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = accountDetailDOMapper.insert(accountDetailDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("create AccountDetail error", e);
        }
        return bizResult;
    }

    public BizResult update(AccountDetailDO accountDetailDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = accountDetailDOMapper.updateByPrimaryKeySelective(accountDetailDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("update AccountDetail error", e);
        }
        return bizResult;
    }

    }
