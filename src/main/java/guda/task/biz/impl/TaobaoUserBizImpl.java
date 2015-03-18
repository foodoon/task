package guda.task.biz.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import guda.task.biz.TaobaoUserBiz;
import guda.task.dao.TaobaoUserDOMapper;
import guda.task.dao.domain.TaobaoUserDO;
import guda.task.dao.domain.TaobaoUserDOCriteria;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public class TaobaoUserBizImpl implements TaobaoUserBiz{

    private final static Logger logger = LoggerFactory.getLogger(TaobaoUserBizImpl.class);

    @Autowired
    private TaobaoUserDOMapper taobaoUserDOMapper;

    public BizResult detail(long id) {
        BizResult bizResult = new BizResult();
        try{
            TaobaoUserDO taobaoUserDO = taobaoUserDOMapper.selectByPrimaryKey(id);
            bizResult.data.put("taobaoUserDO", taobaoUserDO);
            bizResult.success = true;
        }catch(Exception e){
            logger.error("query TaobaoUser error",e);
        }
        return bizResult;
}

    public BizResult list(BaseQuery baseQuery) {
        BizResult bizResult = new BizResult();
        try {
            TaobaoUserDOCriteria taobaoUserDOCriteria = new TaobaoUserDOCriteria();
            taobaoUserDOCriteria.setStartRow(baseQuery.getStartRow());
            taobaoUserDOCriteria.setPageSize(baseQuery.getPageSize());
            int totalCount = taobaoUserDOMapper.countByExample(taobaoUserDOCriteria);
            baseQuery.setTotalCount(totalCount);
            List<TaobaoUserDO> taobaoUserDOList = taobaoUserDOMapper.selectByExample(taobaoUserDOCriteria);
            bizResult.data.put("taobaoUserDOList", taobaoUserDOList);
            bizResult.data.put("query", baseQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view TaobaoUser list error", e);
        }
            return bizResult;
     }

    public BizResult delete(long id) {
        BizResult bizResult = new BizResult();
        try {
            taobaoUserDOMapper.deleteByPrimaryKey(id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("delete taobaoUser error", e);
        }
        return bizResult;
    }

    public BizResult create(TaobaoUserDO taobaoUserDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = taobaoUserDOMapper.insert(taobaoUserDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("create TaobaoUser error", e);
        }
        return bizResult;
    }

    public BizResult update(TaobaoUserDO taobaoUserDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = taobaoUserDOMapper.updateByPrimaryKeySelective(taobaoUserDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("update TaobaoUser error", e);
        }
        return bizResult;
    }

    @Override
    public TaobaoUserDO queryByUserId(String userId) {
        try {
            TaobaoUserDOCriteria taobaoUserDOCriteria = new TaobaoUserDOCriteria();
            taobaoUserDOCriteria.createCriteria().andTaobaoUserIdEqualTo(userId);

            List<TaobaoUserDO> taobaoUserDOList = taobaoUserDOMapper.selectByExample(taobaoUserDOCriteria);
            if(taobaoUserDOList.size() > 0){
                return taobaoUserDOList.get(0);
            }
        } catch (Exception e) {
            logger.error("update TaobaoUser error", e);
        }
        return null;
    }

}
