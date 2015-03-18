package guda.task.biz.impl;

import java.util.ArrayList;
import java.util.List;

import guda.task.biz.query.AccountQuery;
import guda.task.biz.vo.AccountDetailVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import guda.task.biz.AccountBiz;
import guda.task.dao.AccountDOMapper;
import guda.task.dao.domain.AccountDO;
import guda.task.dao.domain.AccountDOCriteria;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public class AccountBizImpl implements AccountBiz{

    private final static Logger logger = LoggerFactory.getLogger(AccountBizImpl.class);

    @Autowired
    private AccountDOMapper accountDOMapper;

    public BizResult detail(long id) {
        BizResult bizResult = new BizResult();
        try{
            AccountDO accountDO = accountDOMapper.selectByPrimaryKey(id);
            bizResult.data.put("accountDO", accountDO);
            bizResult.success = true;
        }catch(Exception e){
            logger.error("query Account error",e);
        }
        return bizResult;
}

    public BizResult list(AccountQuery accountQuery) {
        BizResult bizResult = new BizResult();
        try {
            AccountDOCriteria accountDOCriteria = new AccountDOCriteria();
            accountDOCriteria.createCriteria().andUserIdEqualTo(accountQuery.getUserId());
            accountDOCriteria.setStartRow(accountQuery.getStartRow());
            accountDOCriteria.setPageSize(accountQuery.getPageSize());
            int totalCount = accountDOMapper.countByExample(accountDOCriteria);
            accountQuery.setTotalCount(totalCount);
            List<AccountDO> accountDOList = accountDOMapper.selectByExample(accountDOCriteria);
            bizResult.data.put("accountDOList", accountDOList);
            bizResult.data.put("query", accountQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view Account list error", e);
        }
            return bizResult;
     }

    public BizResult delete(long id) {
        BizResult bizResult = new BizResult();
        try {
            accountDOMapper.deleteByPrimaryKey(id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("delete account error", e);
        }
        return bizResult;
    }

    public BizResult create(AccountDO accountDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = accountDOMapper.insert(accountDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("create Account error", e);
        }
        return bizResult;
    }

    public BizResult update(AccountDO accountDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = accountDOMapper.updateByPrimaryKeySelective(accountDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("update Account error", e);
        }
        return bizResult;
    }

    @Override
    public AccountDO queryByUserId(long userId) {
        AccountDOCriteria accountDOCriteria = new AccountDOCriteria();
        accountDOCriteria.createCriteria().andUserIdEqualTo(userId);
        List<AccountDO> accountDOs = accountDOMapper.selectByExample(accountDOCriteria);
        if(accountDOs.size() == 1){
            return accountDOs.get(0);
        }
        return null;
    }

}
