package link.se7en.common.utils;


import com.alibaba.fastjson.JSONObject;
import link.se7en.common.entity.JsonEntity;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Link on 2017/3/28.
 *
 */
public class BeanUtil {

    /**
     * csv转Bean
     *
     * 目标bean的成员只支持基础类型的包装类：
     * Integer、Long、Float、Double、Boolean、Byte、Character和String
     * 且必须要有setter方法（setXXX）
     *
     * @param csvLine csv行
     * @param splitSymbol 分隔符
     * @param beanClass bean类对象
     * @param bind 绑定Map。每个Entry的key是该字段在csv中从0开始的序号，而value是字段名
     * @param <T> Bean类型泛型
     * @return 构造的bean
     * @throws ReflectiveOperationException 反射相关异常
     */
    public static <T> T csvToBean(String csvLine, String splitSymbol, Class<T> beanClass, Map<Integer,String> bind)
            throws ReflectiveOperationException {
        String[] split = csvLine.split(splitSymbol);

        T instance = beanClass.newInstance();
        for(Map.Entry<Integer,String> fieldInfo : bind.entrySet()) {
            Integer index = fieldInfo.getKey();
            String fieldName = fieldInfo.getValue();

            // 获取字段类对象
            Class fieldClass = beanClass.getDeclaredField(fieldName).getType();

            // 获取字符串值
            String stringValue = split[index];

            // 获取对应的setter方法
            Method setterMethod = beanClass.getMethod(toSetterMethodName(fieldName), fieldClass);
            if(setterMethod == null) continue;

            // 执行对应的setter方法。已通过测试！
            invokeSetter(instance,setterMethod,fieldClass,stringValue);
        }

        return instance;
    }

    /**
     * json转bean
     *
     * 目标bean的成员只支持基础类型的包装类：
     * Integer、Long、Float、Double、Boolean、Byte、Character和String
     * 且必须要有setter方法（setXXX）
     *
     * @param jsonStr json字符串
     * @param beanClass bean类型
     * @param bind 绑定Map。每个Entry的key是该字段在csv中从0开始的序号，而value是字段名
     * @param <T> Bean类型泛型
     * @return 构造的bean
     * @throws ReflectiveOperationException 反射相关异常
     */
    public static <T> T jsonToBean(String jsonStr,Class<T> beanClass, Map<String,String> bind) throws ReflectiveOperationException {
        JSONObject json = JSONObject.parseObject(jsonStr);

        T instance = beanClass.newInstance();
        for (Map.Entry<String,String> entry : bind.entrySet()) {
            String jsonField = entry.getKey();
            String beanField = entry.getValue();

            // 获取字符串值
            String stringValue = json.get(jsonField).toString();

            // 获取字段类对象
            Class fieldClass = beanClass.getDeclaredField(beanField).getType();

            // 获取对应的setter方法
            Method setterMethod = beanClass.getMethod(toSetterMethodName(beanField), fieldClass);
            if(setterMethod == null) continue;

            // 执行对应的setter方法。已通过测试！
            invokeSetter(instance,setterMethod,fieldClass,stringValue);
        }
        return instance;
    }

    /**
     * json转bean。json有的字段，bean必须要有！否则会抛异常
     * 限定bean只能是JsonEntity的子类
     *
     * 目标bean的成员只支持基础类型的包装类：
     * Integer、Long、Float、Double、Boolean、Byte、Character和String
     * 且必须要有setter方法（setXXX）
     *
     * @param jsonStr json字符串
     * @param beanClass bean类型
     * @param <T> Bean类型泛型。为了安全必须是JsonEntity的子类
     * @return 构造的bean
     * @throws ReflectiveOperationException 反射相关异常
     */
    public static <T extends JsonEntity> T jsonToBean(String jsonStr, Class<T> beanClass) throws ReflectiveOperationException {
        JSONObject json = JSONObject.parseObject(jsonStr);

        T instance = beanClass.newInstance();
        for (Map.Entry<String,Object> entry : json.entrySet()) {
            String jsonField = entry.getKey();
            String stringValue = entry.getValue().toString();

            // 获取字段类对象
            Class fieldClass = beanClass.getDeclaredField(jsonField).getType();

            // 获取对应的setter方法
            Method setterMethod = beanClass.getMethod(toSetterMethodName(jsonField), fieldClass);
            if(setterMethod == null) continue;

            // 执行对应的setter方法。已通过测试！
            invokeSetter(instance,setterMethod,fieldClass,stringValue);
        }
        return instance;
    }

    private static void invokeSetter(Object instance,Method setterMethod,Class fieldClass,String stringValue) throws ReflectiveOperationException {
        try {
            if (fieldClass.equals(String.class)) {
                setterMethod.invoke(instance, stringValue);
            } else if (fieldClass.equals(Integer.class)) {
                setterMethod.invoke(instance, Integer.parseInt(stringValue));
            } else if (fieldClass.equals(Long.class)) {
                setterMethod.invoke(instance, Long.parseLong(stringValue));
            } else if (fieldClass.equals(Float.class)) {
                setterMethod.invoke(instance, Float.parseFloat(stringValue));
            } else if (fieldClass.equals(Double.class)) {
                setterMethod.invoke(instance, Double.parseDouble(stringValue));
            } else if (fieldClass.equals(Boolean.class)) {
                setterMethod.invoke(instance, Boolean.parseBoolean(stringValue));
            } else if (fieldClass.equals(Byte.class)) {
                setterMethod.invoke(instance, Byte.parseByte(stringValue));
            } else if (fieldClass.equals(Character.class)) {
                setterMethod.invoke(instance, stringValue.charAt(0));
            }
        } catch (NumberFormatException nfe) {
            String className = setterMethod.getDeclaringClass().getSimpleName();
            String methodName = setterMethod.getName();

            throw new IllegalArgumentException(className + "." + methodName + " can not resolve type "
                    + fieldClass.getName() + ". please check your bind map",nfe);
        }
    }

    private static String toSetterMethodName(String fieldName) {
        char cs[] = new char[fieldName.length()];
        fieldName.getChars(0,fieldName.length(),cs,0);
        cs[0] = Character.toString(cs[0]).toUpperCase().charAt(0);

        return "set" + String.valueOf(cs);
    }

}
