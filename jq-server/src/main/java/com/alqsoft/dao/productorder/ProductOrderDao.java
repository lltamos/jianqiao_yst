package com.alqsoft.dao.productorder;

import java.util.List;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.ProductOrder;

public interface ProductOrderDao extends BaseDao<ProductOrder>{
	@Query("from ProductOrder  where payRelativeId=:order_id")
	public ProductOrder getDoctorServiceOrderByorderNo(@Param("order_id") String order_id);

	
	@Query("from ProductOrder where id=:id ")
	public ProductOrder getProductOrderById(@Param("id")Long id);

	@Query(value="select * from product_order poi where "
			+ "poi.fee_pay_status1=2 and TIMESTAMPDIFF(SECOND,poi.fee_update_time1,now())>=:shareTime "
			+ "or (poi.fee_pay_status2=2 and TIMESTAMPDIFF(SECOND,poi.fee_update_time2,now())>=:shareTime) "
			+ "or (poi.fee_pay_status3=2 and TIMESTAMPDIFF(SECOND,poi.fee_update_time3,now())>=:shareTime) "
			+ "or (poi.fee_pay_status4=2 and TIMESTAMPDIFF(SECOND,poi.fee_update_time4,now())>=:shareTime) "
			+ "or (poi.fee_pay_status5=2 and TIMESTAMPDIFF(SECOND,poi.fee_update_time5,now())>=:shareTime) "
			+ "order by poi.created_time asc limit :rowNum",nativeQuery=true)
	public List<ProductOrder> getCheckProductOrderShareMoney(@Param("shareTime")Long shareTime,@Param("rowNum")Integer rowNum);
}
