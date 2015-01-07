package com.jd.payment.risk.benz.qpay.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MQMessageContainer implements MessageListener{ 

//	public static SynchronousQueue<String> synQueue = new  SynchronousQueue<String>();
//	public static List<String> synQueue = new  ArrayList<String>();
	
	protected Logger _LOG = LoggerFactory.getLogger(MQMessageContainer.class);
	
//	public static SynchronousQueue<String> getQueue(){
//	public static List<String> getQueue(){
		
//		return synQueue;
//	}
	
	private MsgQueue synQueue ;
	
	public void setSynQueue(MsgQueue synQueue) {
		this.synQueue = synQueue;
	}




	@Override
	public void onMessage(Message message) {

		if(null == message){
			return ;
		}
		
//		if(synQueue.size()>0){
//			while(true){
//				System.out.println("synQueue'size "+synQueue.size()+"==================");
//				if(synQueue.size()==0){
//					System.out.println("=================");
//					break;
//				}
//			}
//		}
		
		TextMessage mgs = (TextMessage)message;
		try {
			String msg = mgs.getText();
			try {
				synQueue.put(msg);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("-------------------message-------------"+msg);
//			synQueue.add(msg);
			
		} catch (JMSException ex) {
			_LOG.error("get message from MQ error !", ex);
		}
	}
	
}
