package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import redis.clients.jedis.JedisPooled;

public class SkierDao {
    private JedisPooled jedis;

    public SkierDao(JedisPooled jedis) {
        this.jedis = jedis;
        jedis.set("table_name", "skier_stats" );
    }

    public void createSkier(Skier skier){
        JsonObject valueOfSkierID = new JsonObject();
        Gson gson = new Gson();
        JsonObject skierJson = (JsonObject) gson.toJsonTree(skier);
        valueOfSkierID.add("liftID", skierJson.get("liftID"));
        valueOfSkierID.add("time", skierJson.get("time"));
        valueOfSkierID.add("resortID", skierJson.get("resortID"));
        valueOfSkierID.add("seasonID", skierJson.get("seasonID"));
        valueOfSkierID.add("dayID", skierJson.get("dayID"));

        jedis.sadd(skier.getSkierID(), gson.toJson(valueOfSkierID));

        String resortKey = "resortID_" + skier.getResortID();
        String dayKey = "dayID_" + skier.getDayID();
        jedis.sadd(resortKey + "_" + dayKey, skier.getResortID());

    }

}
