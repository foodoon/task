package guda.task.dao;

import guda.task.dao.domain.BankDO;
import guda.task.dao.domain.BankDOCriteria;
import java.util.List;

public interface BankDOMapper {
    int countByExample(BankDOCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(BankDO record);

    int insertSelective(BankDO record);

    List<BankDO> selectByExample(BankDOCriteria example);

    BankDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BankDO record);

    int updateByPrimaryKey(BankDO record);
}