package fr.karam.data.type.redis;

import fr.karam.data.entity.Identifiable;
import fr.karam.data.entity.SerializableEntity;
import fr.karam.data.entity.bson.EntityDocument;
import fr.karam.data.type.DataFetcher;
import fr.karam.data.type.DataType;
import org.bson.Document;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class RedisFetcher extends DataFetcher<RedisCredentials> {

    private JedisPool jedisPool;

    public RedisFetcher(RedisCredentials credentials) {
        super(DataType.REDIS, credentials);
    }

    @Override
    public void init() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(5120);
        jedisPoolConfig.setMaxIdle(1000);
        jedisPoolConfig.setJmxEnabled(true);

        this.jedisPool = new JedisPool(jedisPoolConfig,
                this.credentials.getIp(),
                this.credentials.getPort(),
                2000,
                this.credentials.getPassword(),
                this.credentials.getDatabase());
    }

    private void process(Consumer<? super Jedis> action) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            if (jedis != null) {
                action.accept(jedis);
            }
        }
    }
    @Override
    public EntityDocument get(String key, Identifiable<?> entityID) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return EntityDocument.of(Document.parse(jedis.hget(key, entityID.getIdentifier().toString())));
        }
    }

    @Override
    public void set(String key, Identifiable<?> entityID, EntityDocument entity) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            jedis.hset(key, entityID.getIdentifier().toString(), entity.toJson());
        }
    }

    @Override
    public <T> List<T> getAllID(String key, Class<T> clazz) {
        try (Jedis jedis = this.jedisPool.getResource()) {
            return jedis.hgetAll(key).keySet().stream().map(clazz::cast).toList();
        }
    }
}
