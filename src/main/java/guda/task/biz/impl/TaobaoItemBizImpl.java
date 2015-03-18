package guda.task.biz.impl;

import java.util.List;

import guda.task.biz.query.TaobaoItemQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import guda.task.biz.TaobaoItemBiz;
import guda.task.dao.TaobaoItemDOMapper;
import guda.task.dao.domain.TaobaoItemDO;
import guda.task.dao.domain.TaobaoItemDOCriteria;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public class TaobaoItemBizImpl implements TaobaoItemBiz{

    private final static Logger logger = LoggerFactory.getLogger(TaobaoItemBizImpl.class);

    @Autowired
    private TaobaoItemDOMapper taobaoItemDOMapper;

    public BizResult detail(long id) {
        BizResult bizResult = new BizResult();
        try{
            TaobaoItemDO taobaoItemDO = taobaoItemDOMapper.selectByPrimaryKey(id);
            bizResult.data.put("taobaoItemDO", taobaoItemDO);
            bizResult.success = true;
        }catch(Exception e){
            logger.error("query TaobaoItem error",e);
        }
        return bizResult;
}

    public BizResult list(TaobaoItemQuery taobaoItemQuery) {
        BizResult bizResult = new BizResult();
        try {
            TaobaoItemDOCriteria taobaoItemDOCriteria = new TaobaoItemDOCriteria();
            taobaoItemDOCriteria.setStartRow(taobaoItemQuery.getStartRow());
            taobaoItemDOCriteria.setPageSize(taobaoItemQuery.getPageSize());
            taobaoItemDOCriteria.createCriteria().andTaobaoUserIdEqualTo(taobaoItemQuery.getTaobaoUserId());
            int totalCount = taobaoItemDOMapper.countByExample(taobaoItemDOCriteria);
            taobaoItemQuery.setTotalCount(totalCount);
            List<TaobaoItemDO> taobaoItemDOList = taobaoItemDOMapper.selectByExample(taobaoItemDOCriteria);
            bizResult.data.put("taobaoItemDOList", taobaoItemDOList);
            bizResult.data.put("query", taobaoItemQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view TaobaoItem list error", e);
        }
            return bizResult;
     }

    public BizResult delete(long id) {
        BizResult bizResult = new BizResult();
        try {
            taobaoItemDOMapper.deleteByPrimaryKey(id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("delete taobaoItem error", e);
        }
        return bizResult;
    }

    public BizResult create(TaobaoItemDO taobaoItemDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = taobaoItemDOMapper.insert(taobaoItemDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("create TaobaoItem error", e);
        }
        return bizResult;
    }

    public BizResult update(TaobaoItemDO taobaoItemDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = taobaoItemDOMapper.updateByPrimaryKeySelective(taobaoItemDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("update TaobaoItem error", e);
        }
        return bizResult;
    }

    @Override
    public TaobaoItemDO queryById(long id) {
        return taobaoItemDOMapper.selectByPrimaryKey(id);
    }

    @Override
    public TaobaoItemDO queryByItemId(long itemId) {
        TaobaoItemDOCriteria taobaoItemDOCriteria = new TaobaoItemDOCriteria();
        taobaoItemDOCriteria.createCriteria().andNumIidEqualTo(itemId);
        List<TaobaoItemDO> taobaoItemDOs = taobaoItemDOMapper.selectByExample(taobaoItemDOCriteria);
        if(taobaoItemDOs.size()>0){
            return taobaoItemDOs.get(0);
        }
        return null;
    }


}
