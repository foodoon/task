package guda.task.dao;

import guda.task.dao.domain.AccountDO;
import guda.task.dao.domain.AccountDOCriteria;
import java.util.List;

public interface AccountDOMapper {
    int countByExample(AccountDOCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(AccountDO record);

    int insertSelective(AccountDO record);

    List<AccountDO> selectByExample(AccountDOCriteria example);

    AccountDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AccountDO record);

    int updateByPrimaryKey(AccountDO record);
}