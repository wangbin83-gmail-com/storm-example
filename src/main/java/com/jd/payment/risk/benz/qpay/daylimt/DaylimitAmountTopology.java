package com.jd.payment.risk.benz.qpay.daylimt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;

import com.jd.payment.risk.benz.qpay.daylimt.persist.SpringAppContextUtils;

public class DaylimitAmountTopology {

	protected static Logger lgr = LoggerFactory
			.getLogger(DaylimitAmountTopology.class);

	public static void main(String[] args) {
		lgr.info("TopologyDaylimitAmount start up ...........");
		try {
			// 初始化Spring ApplicationContext
			SpringAppContextUtils.init();

			// Topology definition
			TopologyBuilder builder = new TopologyBuilder();
			builder.setSpout("amountSpout", new DayLimitAmountSpout(), 5);
			builder.setBolt("amountBolt", new DayLimitAmountBolt(), 6)
					.shuffleGrouping("amountSpout");
			builder.setBolt("frequencyBolt", new DayLimitFrequencyBolt(), 6)
					.shuffleGrouping("amountBolt");

			// Configuration
			Config conf = new Config();
			conf.setDebug(false);
			conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 20);
			if (args != null && args.length > 0) {
				conf.setNumWorkers(3);
				StormSubmitter.submitTopology(args[0], conf,
						builder.createTopology());
				lgr.info(" ################ TopologyDaylimitAmount main 002 submited. ");
			} else {
				conf.setMaxTaskParallelism(3);
				LocalCluster cluster = new LocalCluster();
				cluster.submitTopology("TopologyDaylimitAmount", conf,
						builder.createTopology());
				lgr.info(" ################ TopologyDaylimitAmount main 002 submited-local. ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		lgr.info(new Date().toString());
	}

}
