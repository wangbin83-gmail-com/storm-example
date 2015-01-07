package com.jd.payment.risk.benz.qpay.daylimt;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import com.alibaba.fastjson.JSONObject;
import com.jd.payment.risk.benz.qpay.daylimt.model.QPayDayModel;
import com.jd.payment.risk.benz.qpay.daylimt.persist.SpringAppContextUtils;
import com.jd.payment.risk.benz.qpay.mq.MsgQueue;

/**
 * 
 * <p>
 * Title: DayLimitAmountSpout
 * </p>
 * <p>
 * Description: 日限额的数据流源头
 * </p>
 * <p>
 * Company: ChinaBank
 * </p>
 * 
 * @ClassName: DayLimitAmountSpout
 * @author wywangyong
 * @date 2013年11月19日 下午2:43:22
 * @version 2.6
 */
@SuppressWarnings("serial")
public class DayLimitAmountSpout extends BaseRichSpout {

	protected Logger lgr = LoggerFactory.getLogger(DayLimitAmountSpout.class);

	protected static final long sleepTime = 20L;

	private SpoutOutputCollector _collector;

	private MsgQueue queue = null;
	
	@Override
	public void open(@SuppressWarnings("rawtypes") Map conf,
			TopologyContext context, SpoutOutputCollector collector) {
		_collector = collector;
		queue = SpringAppContextUtils.getSpringContext().getBean(MsgQueue.class);
	}

	@Override
	public void nextTuple() {

		lgr.info("DayLimitAmountSpout .... get MQ data start....");

		
		try{
			
		String wgReqJson =  queue.take();

		if (StringUtils.isEmpty(wgReqJson)) {
			return;
		}
		
		
		
		lgr.info("messge=== " + wgReqJson);
		// 组织完毕的数据
		QPayDayModel model = JSONObject.parseObject(wgReqJson,
				QPayDayModel.class);
		
		// 只有快捷 成功交易和退款交易需要Storm处理
		if ("030102".equals(model.getInterfaceName())
				|| "030104".equals(model.getInterfaceName())) {

			_collector.emit(new Values(model.getBankCardNo(), model));
			lgr.info("DayLimitAmountSpout emit Tuple .........");
		}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {

		declarer.declare(new Fields("bankCardNo", "tradeModel"));
	}

}
