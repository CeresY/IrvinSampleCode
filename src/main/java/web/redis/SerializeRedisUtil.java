package web.redis;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/** redis的序列化
 * @author yangzhan 2018年4月19日
 */
@Service(value="serializeRedisUtil")
public class SerializeRedisUtil {
	@Autowired
	private RedisTemplate<Serializable, Serializable> redisTemplate;
	
	/*static {
		redisTemplate = (RedisTemplate<Serializable, Serializable>) ApplicationContextUtil.getBean("redisTemplate");
	}*/

	public void save(final String key, Object value, long liveTime) {

		final byte[] valueBt = SerializeUtil.serialize(value);
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] keyBt = redisTemplate.getStringSerializer().serialize(key);
				connection.set(keyBt, valueBt);
				if(liveTime > 0 ) {
					connection.expire(keyBt, liveTime);
				}
				return null;
			}
		});
	}

	public <T> T get(final String key, Class<T> elementType) {
		return redisTemplate.execute(new RedisCallback<T>() {
			@Override
			public T doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] keyBt = redisTemplate.getStringSerializer().serialize(key);
				if (connection.exists(keyBt)) {
					byte[] valuebytes = connection.get(keyBt);
					@SuppressWarnings("unchecked")
					T value = (T) SerializeUtil.unserialize(valuebytes);
					return value;
				}
				return null;
			}
		});
	}
}
