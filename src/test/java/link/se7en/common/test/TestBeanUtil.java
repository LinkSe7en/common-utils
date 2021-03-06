package link.se7en.common.test;

import com.alibaba.fastjson.JSONObject;
import link.se7en.common.entity.BeanForTest;
import link.se7en.common.entity.CsvFieldBind;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import static link.se7en.common.utils.BeanUtil.csvToBean;
import static link.se7en.common.utils.BeanUtil.jsonToBean;

/**
 * Created by Link on 2017/3/29.
 */
public class TestBeanUtil {

    @Test
    public void csvTest() {
        CsvFieldBind bind = new CsvFieldBind();

        bind.bind(0,"s").bind(1,"i").bind(2,"l").bind(3,"f").bind(4,"d")
                .bind(5,"b").bind(6,"by").bind(7,"c");

        BeanForTest bean = csvToBean("abc,123,123123123,111.111,1231.123123123,true,1,a", ",", BeanForTest.class, bind);
        System.out.println(bean);// 在这里打断点，可以看到bean的属性已被注入！
    }

    @Test
    public void jsonTest() {
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

    @Test
    public void jsonEntityTest() {
        BeanForTest bean = new BeanForTest();

        bean.setS(RandomStringUtils.random(RandomUtils.nextInt(3,8),true,true));
        bean.setI(RandomUtils.nextInt(0,10000));
        bean.setL(RandomUtils.nextLong(0L,10000000L));
        bean.setF(RandomUtils.nextFloat(0.0f,10000.0f));
        bean.setD(RandomUtils.nextDouble(0.0d,10000000.0d));
        bean.setB(RandomUtils.nextInt(0,1) == 0);
        bean.setBy(RandomUtils.nextBytes(1)[0]);
        bean.setC(RandomStringUtils.random(1).charAt(0));

        String jsonStr = bean.toJsonStr();

        System.out.println(jsonStr);

        BeanForTest newBean = jsonToBean(jsonStr,BeanForTest.class);
        System.out.println(newBean);// 在这里打断点，可以看到bean的属性已被注入！
    }
}
