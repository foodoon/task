package guda.task.biz.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import guda.task.biz.BankBiz;
import guda.task.dao.BankDOMapper;
import guda.task.dao.domain.BankDO;
import guda.task.dao.domain.BankDOCriteria;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public class BankBizImpl implements BankBiz{

    private final static Logger logger = LoggerFactory.getLogger(BankBizImpl.class);

    @Autowired
    private BankDOMapper bankDOMapper;

    public BizResult detail(long id) {
        BizResult bizResult = new BizResult();
        try{
            BankDO bankDO = bankDOMapper.selectByPrimaryKey(id);
            bizResult.data.put("bankDO", bankDO);
            bizResult.success = true;
        }catch(Exception e){
            logger.error("query Bank error",e);
        }
        return bizResult;
}

    public BizResult list(BaseQuery baseQuery) {
        BizResult bizResult = new BizResult();
        try {
            BankDOCriteria bankDOCriteria = new BankDOCriteria();
            bankDOCriteria.setStartRow(baseQuery.getStartRow());
            bankDOCriteria.setPageSize(baseQuery.getPageSize());
            int totalCount = bankDOMapper.countByExample(bankDOCriteria);
            baseQuery.setTotalCount(totalCount);
            List<BankDO> bankDOList = bankDOMapper.selectByExample(bankDOCriteria);
            bizResult.data.put("bankDOList", bankDOList);
            bizResult.data.put("query", baseQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view Bank list error", e);
        }
            return bizResult;
     }

    public BizResult delete(long id) {
        BizResult bizResult = new BizResult();
        try {
            bankDOMapper.deleteByPrimaryKey(id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("delete bank error", e);
        }
        return bizResult;
    }

    public BizResult create(BankDO bankDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = bankDOMapper.insert(bankDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("create Bank error", e);
        }
        return bizResult;
    }

    public BizResult update(BankDO bankDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = bankDOMapper.updateByPrimaryKeySelective(bankDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("update Bank error", e);
        }
        return bizResult;
    }

    }
