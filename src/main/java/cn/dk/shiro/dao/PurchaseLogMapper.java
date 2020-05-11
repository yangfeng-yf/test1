package cn.dk.shiro.dao;

import cn.dk.shiro.domain.PurchaseLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PurchaseLogMapper {
    int deleteByPrimaryKey(Integer countId);

    List<PurchaseLog> findAll();

    List<PurchaseLog> findByCondi(@Param("param1") String param, @Param("vlaue1") String value);

    int insert(PurchaseLog record);

    /**
     * 通过编号 修改状态和审批人编号
     * @param countId
     * @param pStatus
     * @param approverId
     * @return
     */
    int updatePStatusAndApproverIdByPrimaryKey(@Param("countId") Integer countId, @Param("pStatus")Integer pStatus, @Param("approverId") Integer approverId);

    int insertSelective(PurchaseLog record);

    PurchaseLog selectByPrimaryKey(Integer countId);

    int updateByPrimaryKeySelective(PurchaseLog record);

    int updateByPrimaryKey(PurchaseLog record);
}