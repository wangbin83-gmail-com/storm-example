package com.jd.payment.risk.benz.qpay.daylimt.persist;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.jd.payment.risk.benz.dao.ds.PreheatDao;
import com.jd.payment.risk.benz.qpay.daylimt.model.QPayDayModel;

public class PersistDataUtils {

	
	private static PreheatDao getPreheatDao(){
		return SpringAppContextUtils.getSpringContext()
				.getBean("preheatDao",PreheatDao.class);
	}
	/**
	 * 
	 * <p>Description: 更新tbl_qpay_day_amount的数据</p>
	 * @param model
	 * @author wywangyong
	 * @date 2013年11月20日 上午11:43:22
	 */
	public static void updateAmount(QPayDayModel model){
		
		//SELECT SQL
		String sql = amountSelectSql(model);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("sql", sql.toString());
		int count = getPreheatDao().select(map);

		if(count>0){//做更新操作
			updateAmountSql(model);
		}else{//插入操作
			insertAmountSql(model);
		}
	}
	
	/**
	 * 
	 * <p>Description: 更新tbl_qpay_day_frequnency的数据</p>
	 * @param model
	 * @author wywangyong
	 * @date 2013年11月20日 上午11:43:22
	 */
	public static void updateFrequency(QPayDayModel model){
		
		//SELECT SQL
		String sql = frequnencySelectSql(model);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("sql", sql.toString());
		int count = getPreheatDao().select(map);

		if(count>0){//做更新操作
			updateFrequnencySql(model);
		}else{//插入操作
			insertFrequnencySql(model);
		}
	}
	//------------------------------------------------------------------------
	private static void insertFrequnencySql(QPayDayModel model){
		
		StringBuffer insertSQL = new StringBuffer();
		
		insertSQL.append(" INSERT INTO tbl_qpay_day_frequency(bankcard_no, frequency, created_day, created_month, created_year,created) VALUES ");
		insertSQL.append("(")
		         .append("'").append(model.getBankCardNo()).append("',")
		         .append(model.getFrequency()).append(",")
		         .append(model.getCreatedDay()).append(",")
		         .append(model.getCreatedMonth()).append(",")
		         .append(model.getCreatedYear()).append(",")
		         .append("'").append(createDateTime()).append("'")
		         .append(");");
		 
		Map<String, String> map = new HashMap<String, String>();
		map.put("sql", insertSQL.toString());
		getPreheatDao().insert(map);
	}
	private static void updateFrequnencySql(QPayDayModel model){
		
		StringBuffer updateSQL = new StringBuffer();
		
		updateSQL.append(" UPDATE tbl_qpay_day_frequency SET  frequency = frequency +  ");
		updateSQL.append(model.getFrequency()).append(" ")
				 .append(" where 1= 1 ")
		         .append(buildCondSql(model));
		 
		Map<String, String> map = new HashMap<String, String>();
		map.put("sql", updateSQL.toString());
		
		getPreheatDao().update(map);
	}
	private static String frequnencySelectSql(QPayDayModel model){
		
		StringBuffer sql = new StringBuffer("SELECT count(1) FROM tbl_qpay_day_frequency WHERE 1=1 ");
		sql.append(buildCondSql(model));
		return sql.toString();
	}
	/**
	 * 
	 * <p>Description:更新累计交易金额 </p>
	 * @param model
	 * @author wywangyong
	 * @date 2013年11月20日 下午1:22:53
	 */
	private static void updateAmountSql(QPayDayModel model) {
		
		StringBuffer updateSQL = new StringBuffer();
		
		updateSQL.append(" UPDATE tbl_qpay_day_amount SET  amount = amount +  (");
		updateSQL.append(model.getTradeAmount()).append(") ")
				 .append(" where 1= 1 ")
		         .append(buildCondSql(model));
		 
		Map<String, String> map = new HashMap<String, String>();
		map.put("sql", updateSQL.toString());
		getPreheatDao().update(map);
	}
	/**
	 * 
	 * <p>Description: 插入累计金额记录</p>
	 * @param model
	 * @author wywangyong
	 * @date 2013年11月20日 上午11:59:14
	 */
	private  static void insertAmountSql(QPayDayModel model) {
		
		StringBuffer insertSQL = new StringBuffer();
		
		insertSQL.append(" INSERT INTO tbl_qpay_day_amount(bankcard_no, amount, created_day, created_month, created_year,created) VALUES ");
		insertSQL.append("(")
		         .append("'").append(model.getBankCardNo()).append("',")
		         .append(model.getTradeAmount()).append(" , ")
		         .append(model.getCreatedDay().intValue()).append(" , ")
		         .append(model.getCreatedMonth().intValue()).append(" , ")
		         .append(model.getCreatedYear().intValue()).append(",")
				 .append("'").append(createDateTime()).append("'")
		  		 .append(");");
		 
		Map<String, String> map = new HashMap<String, String>();
		map.put("sql", insertSQL.toString());
		getPreheatDao().insert(map);
		
		
	}
	/**
	 * 
	 * <p>Description: 累计发生金额SELECT SQL</p>
	 * @param model
	 * @return
	 * @author wywangyong
	 * @date 2013年11月20日 上午11:55:47
	 */
	private static String amountSelectSql(QPayDayModel model) {
		StringBuffer sql = new StringBuffer("SELECT count(1) FROM tbl_qpay_day_amount WHERE 1=1 ");
		sql.append(buildCondSql(model));
		return sql.toString();
	}
	
	private static String buildCondSql(QPayDayModel model) {
		StringBuffer sql = new StringBuffer(50);
		//银行卡号
		if(StringUtils.isNotEmpty(model.getBankCardNo())){
			sql.append(" and bankcard_no ='").append(model.getBankCardNo()).append("' ");
		}
		//条件日期
		if(StringUtils.isNotEmpty(model.getBankCardNo())){
			sql.append(" and created_day = ").append(model.getCreatedDay()).append(" ");
		}
		if(StringUtils.isNotEmpty(model.getBankCardNo())){
			sql.append(" and created_month = ").append(model.getCreatedMonth()).append(" ");
		}
		if(StringUtils.isNotEmpty(model.getBankCardNo())){
			sql.append(" and created_year = ").append(model.getCreatedYear()).append(" ");
		}
		
		return sql.toString();
	}
	
	/**
	 * 
	 * <p>Description:日期格式 </p>
	 * @return
	 * @author wywangyong
	 * @date 2013年11月22日 下午4:00:20
	 */
	private static String createDateTime() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createDate = df.format(new Date());
		return createDate;
	}
}
