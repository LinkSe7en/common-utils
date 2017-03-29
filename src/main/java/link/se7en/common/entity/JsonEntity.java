package link.se7en.common.entity;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import link.se7en.common.utils.BeanUtil;

/**
 * Created by Link on 2017/3/27.
 * Json实体。提供转换为JSON对象或JSON字符串的方法。
 */
public abstract class JsonEntity {

    final protected static Gson GSON = new Gson();

    /**
     * 转为Json字符串
     * @return Json字符串
     */
    public String toJsonStr() {
        return GSON.toJson(this,this.getClass());
    }

    /**
     * 指定类转为Json字符串
     * @param clazz 指定的类
     * @return Json字符串
     */
    public String toJsonStr(Class<? extends JsonEntity> clazz) {
        return GSON.toJson(this,clazz);
    }

    /**
     * 转换为Json对象
     * @return json对象
     */
    public JSONObject toJson() {
        return JSONObject.parseObject(toJsonStr());
    }

    /**
     * 指定类转为Json对象
     * @param clazz 指定的类
     * @return Json对象
     */
    public JSONObject toJson(Class<? extends JsonEntity> clazz) {
        return JSONObject.parseObject(GSON.toJson(this,clazz));
    }

    public static <T extends JsonEntity> T fromJsonStr(String jsonStr, Class<T> beanClass) throws ReflectiveOperationException {
        return BeanUtil.jsonToBean(jsonStr, beanClass);
    }
}
