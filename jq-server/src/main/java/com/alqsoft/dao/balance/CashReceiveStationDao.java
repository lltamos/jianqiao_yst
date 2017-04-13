package com.alqsoft.dao.balance;


import java.util.Date;

import javax.persistence.LockModeType;


import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.alqsoft.entity.cashreceivestation.CashReceiveStation;


/**
 * 健桥提现记录dao
 * @Description: TODO
 * @author Shen.joe
 * @e-mail sudiluo_java@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年10月17日 上午9:33:05
 * 
 */

public interface CashReceiveStationDao extends BaseDao<CashReceiveStation> {

/*	@Query("select c.status from CashReceiveStation as c where c.merSeqId=:id")
	public Integer getStatusByMerSeqId(@Param("id")String merSeqId);

	@Query("select c.id from CashReceiveStation as c where c.merSeqId=:id")
	public Long getIDByMerSeqId(@Param("id")String merSeqId);
	
	@Query("from CashReceiveStation as c where c.merSeqId=:id")
	public CashReceiveStation getCashReceiveStationByMerSeqId(@Param("id")String mer_seq_id);
	
	@Lock(value = LockModeType.PESSIMISTIC_WRITE)
	@Query("from CashReceiveStation as o where o.id=:id")
	public CashReceiveStation getRowLock(@Param("id")Long id);
*/

	/***
	 * 根据当前日期查询猎头今日总提现金额 网银在线 京东代付专用
	 * @param supplierid
	 * @return
	 */
	@Query("SELECT SUM(c.money) FROM CashReceiveStation AS c WHERE c.cssType=1 AND c.cssId=:hunterid AND (c.status=1 OR c.status=2) AND (c.signDate IS NOT NULL  OR c.chkValue IS NOT NULL) AND c.createTime =:createTime AND c.money>0")
	public Long getTodayCashMoneyByDate(@Param("hunterid")Integer hunterid,@Param("createTime")Date createTime);

	/**
	 * 根据订单号获取记录ID
	 */
	@Query("select c.id from CashReceiveStation as c where c.merSeqId=:merSeqId")
	public Long getIDByMerSeqId(@Param("merSeqId")String merSeqId);
	
	/**
	 * 提现加锁
	 */
	@Lock(value = LockModeType.PESSIMISTIC_WRITE)
	@Query("from CashReceiveStation as o where o.id=:id")
	public CashReceiveStation getRowLock(@Param("id")Long id);

	@Query("from CashReceiveStation as c where c.cssId=:customerId")
	public CashReceiveStation getCashReceiveStationByCustomerId(@Param("customerId") Long customerId);
	/**
	 * 回调查看加锁
	 * @author Yaowei
	 * @param  
	 * @return CashReceiveStation
	 * @Time 2017年3月27日
	 */
	@Lock(value = LockModeType.PESSIMISTIC_WRITE)
	@Query("from CashReceiveStation as c where c.merSeqId=:custOrderId")
	public CashReceiveStation getCashReceiveStationByCustOrderId(@Param("custOrderId") String custOrderId);
	
	
	

}
