package com.jd.payment.risk.benz.qpay.daylimt;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

import com.jd.payment.risk.benz.qpay.daylimt.model.QPayDayModel;
import com.jd.payment.risk.benz.qpay.daylimt.persist.PersistDataUtils;

@SuppressWarnings("serial")
public class DayLimitFrequencyBolt 	extends BaseRichBolt {

		protected  Logger lgr = LoggerFactory.getLogger(DayLimitFrequencyBolt.class);
		
		@SuppressWarnings("rawtypes")
		@Override
		public void prepare(Map stormConf, TopologyContext context,
				OutputCollector collector) {
			
			
		}

		@Override
		public void execute(Tuple input) {
			
			lgr.info("###  enter ############### DayLimitFrequencyBolt execute(Tuple input) ########");

//			String bankCardNo = input.getStringByField("bankCardNo");
			QPayDayModel value = (QPayDayModel)input.getValue(1);
			
			//进行数据持久化
			PersistDataUtils.updateFrequency(value); 
			
//			lgr.info("bankCardNo: [".concat(bankCardNo).concat("],model value [".concat(value.toString())).concat("]"));
			lgr.info("########## DayLimitFrequencyBolt's execute over.............");
		}

		@Override
		public void declareOutputFields(OutputFieldsDeclarer declarer) {
			
			
		}
}
