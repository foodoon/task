package guda.task.biz.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import guda.task.biz.TaobaoShopBiz;
import guda.task.dao.TaobaoShopDOMapper;
import guda.task.dao.domain.TaobaoShopDO;
import guda.task.dao.domain.TaobaoShopDOCriteria;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public class TaobaoShopBizImpl implements TaobaoShopBiz{

    private final static Logger logger = LoggerFactory.getLogger(TaobaoShopBizImpl.class);

    @Autowired
    private TaobaoShopDOMapper taobaoShopDOMapper;

    public BizResult detail(long id) {
        BizResult bizResult = new BizResult();
        try{
            TaobaoShopDO taobaoShopDO = taobaoShopDOMapper.selectByPrimaryKey(id);
            bizResult.data.put("taobaoShopDO", taobaoShopDO);
            bizResult.success = true;
        }catch(Exception e){
            logger.error("query TaobaoShop error",e);
        }
        return bizResult;
}

    public BizResult list(BaseQuery baseQuery) {
        BizResult bizResult = new BizResult();
        try {
            TaobaoShopDOCriteria taobaoShopDOCriteria = new TaobaoShopDOCriteria();
            taobaoShopDOCriteria.setStartRow(baseQuery.getStartRow());
            taobaoShopDOCriteria.setPageSize(baseQuery.getPageSize());
            int totalCount = taobaoShopDOMapper.countByExample(taobaoShopDOCriteria);
            baseQuery.setTotalCount(totalCount);
            List<TaobaoShopDO> taobaoShopDOList = taobaoShopDOMapper.selectByExample(taobaoShopDOCriteria);
            bizResult.data.put("taobaoShopDOList", taobaoShopDOList);
            bizResult.data.put("query", baseQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view TaobaoShop list error", e);
        }
            return bizResult;
     }

    public BizResult delete(long id) {
        BizResult bizResult = new BizResult();
        try {
            taobaoShopDOMapper.deleteByPrimaryKey(id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("delete taobaoShop error", e);
        }
        return bizResult;
    }

    public BizResult create(TaobaoShopDO taobaoShopDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = taobaoShopDOMapper.insert(taobaoShopDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("create TaobaoShop error", e);
        }
        return bizResult;
    }

    public BizResult update(TaobaoShopDO taobaoShopDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = taobaoShopDOMapper.updateByPrimaryKeySelective(taobaoShopDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("update TaobaoShop error", e);
        }
        return bizResult;
    }

    }
