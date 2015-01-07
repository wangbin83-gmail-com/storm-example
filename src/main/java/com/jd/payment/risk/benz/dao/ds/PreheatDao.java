package com.jd.payment.risk.benz.dao.ds;

import java.util.Map;

import com.jd.payment.common.annotation.MyBatisRepository;

/**
 * <br/>Title: PreheatDao
 * <br/>Description:预热数据
 * <br/>Company: ChinaBank
 * <br/>ClassName: PreheatDao
 * <br/>ProjectName: benz
 * <br/>author qinhaihong
 * <br/>date 2013年11月20日 上午10:03:00
 * <br/>version 1.0.0
 */
@MyBatisRepository
public interface PreheatDao {
	
	/**
	 * <br/>Description: 提供记录数
	 * @param map
	 * @return 
	 * @author qinhaihong
	 * @date 2013年11月20日 上午10:12:11
	 */
	int select(Map<String, String> map);
	
	int update(Map<String, String> map);

	int insert(Map<String, String> map);

}
