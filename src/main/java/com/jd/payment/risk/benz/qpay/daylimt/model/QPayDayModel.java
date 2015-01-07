package com.jd.payment.risk.benz.qpay.daylimt.model;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 
 * <p>Title: QPayDayModel</p>
 * <p>Description: 快捷日交易限额/限频 </p>
 * <p>Company: ChinaBank</p>
 * @ClassName: QPayDayModel
 * @author wywangyong
 * @date 2013年11月19日 下午2:26:29
 * @version 2.6
 */
@SuppressWarnings("serial")
public class QPayDayModel implements Serializable{

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 接口编码
	 */
	private String interfaceName;
	/**
	 * 银行卡号
	 */
	private String bankCardNo;
	/**
	 * 交易额
	 */
	private BigDecimal tradeAmount;
	/**
	 * 交易频次
	 */
	private Integer frequency = 1;
	/**
	 * 交易时间 
	 */
	private Long tradeTime;
	/**
	 * 交易年
	 */
	private Integer createdYear;
	/**
	 * 交易月
	 */
	private Integer createdMonth;
	/**
	 * 交易日
	 */
	private Integer createdDay;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBankCardNo() {
		return bankCardNo;
	}
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	public BigDecimal getTradeAmount() {
		return tradeAmount;
	}
	public void setTradeAmount(BigDecimal tradeAmount) {
		this.tradeAmount = tradeAmount;
	}
	public Integer getFrequency() {
		return frequency;
	}
	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}
	public Long getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(Long tradeTime) {
		this.tradeTime = tradeTime;
	}
	public Integer getCreatedYear() {
		return createdYear;
	}
	public void setCreatedYear(Integer createdYear) {
		this.createdYear = createdYear;
	}
	public Integer getCreatedMonth() {
		return createdMonth;
	}
	public void setCreatedMonth(Integer createdMonth) {
		this.createdMonth = createdMonth;
	}
	public Integer getCreatedDay() {
		return createdDay;
	}
	public void setCreatedDay(Integer createdDay) {
		this.createdDay = createdDay;
	}
	
	@Override
	public String toString() {
	
		return org.apache.commons.lang.builder.ToStringBuilder.reflectionToString(this);
	}
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
}

