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
import java.util.function.Supplier;

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
        return  stringRedisTemplate.hasKey(redisKey);
    }

}
