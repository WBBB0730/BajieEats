package cn.edu.szu.Bajie.util;

import cn.edu.szu.Bajie.constant.CacheTimeInterval;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author Whitence
 * @description: TODO
 * @date 2022/10/8 11:04
 */
@Data
public class CacheService {

    private StringRedisTemplate stringRedisTemplate;

    private final ValueOperations<String, String> stringStringValueOperations;



    public CacheService(StringRedisTemplate stringRedisTemplate) {

        this.stringRedisTemplate=stringRedisTemplate;

        stringStringValueOperations = this.stringRedisTemplate.opsForValue();
    }

    /**
     * 缓存字符串
     * @param redisKey
     * @param supplier
     * @param interval
     * @return
     */

    public  String getByCacheStr(String redisKey,
                                 Supplier<String> supplier,
                                 CacheTimeInterval interval) {

        String result = stringStringValueOperations.get(redisKey);

        if(StrUtil.isNotBlank(result)){
            return result;
        }
        String data = supplier.get();
        if(StrUtil.isNotBlank(data)&&!"null".equals(data)){
            stringStringValueOperations.set(redisKey,data,interval.getTime(),interval.getTimeUnit());
        }
        return data;
    }

    /**
     * 缓存Object
     * @param redisKey
     * @param supplier
     * @param _class
     * @param interval
     * @param <T>
     * @return
     */

    public <T> T getByCacheObj(String redisKey,
                               Supplier<T> supplier,
                               Class<T> _class,
                               CacheTimeInterval interval) {

        return JSONObject.parseObject(
                getByCacheStr(
                        redisKey,
                        () -> JSONObject.toJSONString(supplier.get()),
                        interval),
                _class);
    }

    /**
     * 获取缓存数据Obj
     * @param redisKey
     * @param _class
     * @param <T>
     * @return
     */

    public <T> T getObj(String redisKey,Class<T> _class){

        String data = stringStringValueOperations.get(redisKey);

        return JSONObject.parseObject(data,_class);
    }

    /**
     * 设置对象
     * @param redisKey
     * @param obj
     * @param timeInterval
     * @param <T>
     */
    public <T> void setObj(String redisKey,T obj,CacheTimeInterval timeInterval){
        stringStringValueOperations.set(
                redisKey,
                JSONObject.toJSONString(obj),
                timeInterval.getTime(),
                timeInterval.getTimeUnit()
        );
    }


    /**
     * 缓存List
     * @param redisKey
     * @param supplier
     * @param _class
     * @param interval
     * @param <T>
     * @return
     */

    public <T> List<T> getByCacheList(String redisKey,
                                      Supplier<List<T>> supplier,
                                      Class<T> _class,
                                      CacheTimeInterval interval) {
        return JSONArray.parseArray(
                getByCacheStr(
                        redisKey,
                        () -> JSONArray.toJSONString(supplier.get()),
                        interval),
                _class);
    }

    /**
     * 获取缓存数据list
     * @param redisKey
     * @param _class
     * @param <T>
     * @return
     */
    public <T> List<T> getList(String redisKey,
                               Class<T> _class) {
        String data = stringStringValueOperations.get(redisKey);
        return JSONArray.parseArray(
                data,
                _class);
    }

    public <T> void setList(String redisKey,
                            List<T> list,
                            CacheTimeInterval interval){
        stringStringValueOperations.set(
                redisKey,
                JSONArray.toJSONString(list),
                interval.getTime(),
                interval.getTimeUnit()
        );
    }

    /**
     * 单个删除
     * @param redisKey
     */
    public void delete(String redisKey){
        stringRedisTemplate.delete(redisKey);
    }


    /**
     * 检查键是否存在
     * @param redisKey
     * @return
     */

    public boolean keyExits(String redisKey){
        return Boolean.TRUE.equals(stringRedisTemplate.hasKey(redisKey));
    }

    /**
     * 向队列中加入字符串数据
     * @param key
     * @param value
     */

    public void lPush(String key,String value){
        stringRedisTemplate.opsForList().leftPush(key,value);
    }

    /**
     * 队列中弹出字符串数据
     * @param key
     * @return
     */

    public String rPop(String key){
        return stringRedisTemplate.opsForList().rightPop(key);
    }

    /**
     * 推入实体数据
     * @param key
     * @param obj
     * @param <T>
     */

    public <T> void lPushObj(String key,T obj){
        lPush(
                key,
                JSONObject.toJSONString(obj)
        );
    }

    /**
     * 推出实体数据
     * @param key
     * @param _class
     * @param <T>
     * @return
     */
    public <T> T rPopObj(String key,Class<T> _class){
        return JSONObject.parseObject(rPop(key),_class);
    }

    /**
     * 获取消息队列数据
     * @param key
     * @param _class
     * @param start
     * @param end
     * @param <T>
     * @return
     */
    public <T> List<T> range(String key,int start,int end,Class<T> _class){
        return Objects.requireNonNull(stringRedisTemplate
                        .opsForList()
                        .range(key, start, end))
                .stream()
                .map((str)->JSONObject.parseObject(str,_class))
                .collect(Collectors.toList());
    }
}
