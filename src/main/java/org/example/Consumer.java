package org.example;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import redis.clients.jedis.JedisPooled;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Consumer {

  private final static String QUEUE_NAME = "skiersQueue";
  //private ip address for rmq instance
  private final static String RABBITMQ_URL = "172.31.16.52";
  private final static Integer POOL_SIZE = 200;


  private final static ConcurrentHashMap<String, CopyOnWriteArrayList<String>> map = new ConcurrentHashMap<>();

  public static void main(String[] argv) throws Exception {
    System.out.println("Program started");

    JedisPooled jedis = new JedisPooled("localhost", 6379);
    SkierDao skierDao = new SkierDao(jedis);

    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(RABBITMQ_URL);
    Connection connection = factory.newConnection();
    RMQChannelFactory chanFactory = new RMQChannelFactory (connection);
    RMQChannelPool pool = new RMQChannelPool(POOL_SIZE, chanFactory);
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    DataDumper dataDumper = new DataDumper( QUEUE_NAME, pool, skierDao);
    for (int i = 0; i < POOL_SIZE; i++) {
      new Thread(dataDumper).start();
    }
      //redisAsync.awaitAll(futures);
  }
}
