package link.se7en.common.test;

import com.alibaba.fastjson.JSONObject;
import link.se7en.common.entity.BeanForTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

import static link.se7en.common.utils.BeanUtil.csvToBean;
import static link.se7en.common.utils.BeanUtil.jsonToBean;

/**
 * Created by Link on 2017/3/29.
 */
public class TestBeanUtil {

    @Test
    public void csvTest() throws ReflectiveOperationException {
        Map<Integer,String> bind = new TreeMap<>();

        bind.put(0,"s");
        bind.put(1,"i");
        bind.put(2,"l");
        bind.put(3,"f");
        bind.put(4,"d");
        bind.put(5,"b");
        bind.put(6,"by");
        bind.put(7,"c");

        BeanForTest bean = csvToBean("abc,123,123123123,111.111,1231.123123123,true,1,a", ",", BeanForTest.class, bind);
        System.out.println(bean);// 在这里打断点，可以看到bean的属性已被注入！
    }

    @Test
    public void jsonTest() throws ReflectiveOperationException {
        JSONObject json = new JSONObject();
        json.put("s", RandomStringUtils.random(RandomUtils.nextInt(3,8),true,true));
        json.put("i", RandomUtils.nextInt(0,10000));
        json.put("l", RandomUtils.nextLong(0L,10000000L));
        json.put("f", RandomUtils.nextFloat(0.0f,10000.0f));
        json.put("d", RandomUtils.nextDouble(0.0d,10000000.0d));
        json.put("b", RandomUtils.nextInt(0,1) == 0);
        json.put("by", RandomUtils.nextBytes(1)[0]);
        json.put("c", RandomStringUtils.random(1).charAt(0));

        String jsonStr = json.toString();

        System.out.println(jsonStr);

        BeanForTest bean = jsonToBean(jsonStr,BeanForTest.class);
        System.out.println(bean);// 在这里打断点，可以看到bean的属性已被注入！
    }
}
