package com.innovation;

import java.util.HashMap;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.google.gson.Gson;

public class SimpleProducer extends Thread {

	String topicName;
	String area;
	String[] ary;
	String id;
	TaxiPojo tp;

	SimpleProducer(String topicName, String area, String id) {
		this.topicName = topicName;
		this.area = area;
		this.id = id;
		tp = new TaxiPojo(id, true);
	}

	public static void main(String[] args) throws Exception {

		// if (args.length == 0) {
		// System.out.println("Enter topic name");
		// return;
		// }
		String topicName = args[0].toString();
		// String topicName = "test";
		String[] area = args[1].toString().split(",");
		// String[] area = "ECITY".split(",");
		for (int i = 0; i < area.length; i++) {
			SimpleProducer sp = new SimpleProducer(topicName, area[i], i + "");
			sp.start();
		}

	}

	public void run() {

		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		while (true) {
			String[] ary = gpsofArea(area).split(":");
			tp.setLatitudeLongi(ary[0], ary[1]);
			String data = new Gson().toJson(tp);
			producer.send(new ProducerRecord<String, String>(topicName, "0", data));
			try {
				Thread.sleep(1000l);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

	}

	public String gpsofArea(String area) {
		if (ary == null || ary.length == 0) {
			ary = map(area);
		}
		String res = "";
		for (int i = 0; i < ary.length; i++) {
			res = ary[randomValues(1, ary.length - 1)];

		}
		return res;
	}

	public int randomValues(int minimum, int maximum) {
		return minimum + (int) (Math.random() * maximum);
	}

	public String[] map(String key) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("KORAMANGALA", Constants.KORAMANGALA);
		map.put("ECITY", Constants.ECITY);
		map.put("HSR", Constants.HSR);

		return map.get(key).split(",");

	}
}