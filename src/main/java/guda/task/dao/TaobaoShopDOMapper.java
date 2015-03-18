package guda.task.dao;

import guda.task.dao.domain.TaobaoShopDO;
import guda.task.dao.domain.TaobaoShopDOCriteria;
import java.util.List;

public interface TaobaoShopDOMapper {
    int countByExample(TaobaoShopDOCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(TaobaoShopDO record);

    int insertSelective(TaobaoShopDO record);

    List<TaobaoShopDO> selectByExample(TaobaoShopDOCriteria example);

    TaobaoShopDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TaobaoShopDO record);

    int updateByPrimaryKey(TaobaoShopDO record);
}