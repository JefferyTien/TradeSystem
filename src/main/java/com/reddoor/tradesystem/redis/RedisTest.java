package com.reddoor.tradesystem.redis;

import redis.clients.jedis.Jedis;

public class RedisTest {
    private String testParam = "";

    public static void redisTester(){
        Jedis jedis = new Jedis("localhost", 6379, 100000);
        int i = 0;
        try {
            long start = System.currentTimeMillis();
            while(true){
                long end = System.currentTimeMillis();
                if (end -start >=1000){  // 当大于等于1000毫秒(相当于1秒)时,结束操作
                    break;
                }
                i++;
                jedis.set("test"+i,i+"");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            jedis.close();
        }
        System.out.println("redis每秒操作"+i+"次");
    }

    public static void main(String[] args) {
        System.out.println("OMG");
        RedisTest.redisTester();

    }
}
