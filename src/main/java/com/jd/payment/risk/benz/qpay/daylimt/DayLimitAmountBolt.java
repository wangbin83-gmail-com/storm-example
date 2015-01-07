package com.jd.payment.risk.benz.qpay.daylimt;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import com.jd.payment.risk.benz.qpay.daylimt.model.QPayDayModel;
import com.jd.payment.risk.benz.qpay.daylimt.persist.PersistDataUtils;
/**
 * 
 * <p>Title: DayLimitAmountBolt</p>
 * <p>Description:单卡当天累计交易金额 </p>
 * <p>Company: ChinaBank</p>
 * @ClassName: DayLimitAmountBolt
 * @author wywangyong
 * @date 2013年11月19日 下午1:46:12
 * @version 2.6
 */

@SuppressWarnings("serial")
public class DayLimitAmountBolt extends BaseRichBolt {

	protected  Logger lgr = LoggerFactory.getLogger(DayLimitAmountBolt.class);
	
	private OutputCollector _collector ;
	
	@Override
	public void prepare(@SuppressWarnings("rawtypes")Map stormConf, TopologyContext context,
			OutputCollector collector) {
		
		this._collector = collector;
	}

	@Override
	public void execute(Tuple input) {
		lgr.info("###  enter ############### DayLimitAmountBolt's execute(Tuple input) ########");
		
		String bankCardNo = input.getStringByField("bankCardNo");
		QPayDayModel value = (QPayDayModel)input.getValue(1);
		
		//进行数据持久化
		PersistDataUtils.updateAmount(value);
		
//		lgr.info("bankCardNo: [".concat(bankCardNo).concat("],model value [".concat(String.valueOf(value))).concat("]"));
		
		//发送给你下一个Bolt
		_collector.emit(new Values(bankCardNo,value));
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		
		declarer.declare(new Fields("bankCardNo","model"));
	}

}
