package link.se7en.common.utils.BloomFilter;

import link.se7en.common.utils.BloomFilter.hash.HashFunction;
import link.se7en.common.utils.PackageUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Link on 2016/5/4.
 * 多个Hash函数的布隆过滤器 IBloomFilter接口的实现
 */

public class MultiHashBloomFilter implements IBloomFilter {

    final private static Integer DEFAULT_LIMIT = 65536;
    final private static String DEFAULT_HASH_FUNCTION_PACKAGE = PackageUtil.getMyPackage() + ".hash.def";

    private Map<String,HashFunction> functions = new HashMap<>();
    private boolean slot[] = null;

    /**
     * 默认的构造函数，只加载默认的Hash函数
     */
    public MultiHashBloomFilter() {
        this(null, true, DEFAULT_LIMIT);
    }

    /**
     * 根据ClassNameList加载Hash函数
     * @param funcClassList Hash函数的类名列表
     * @param loadDefault 是否加载默认的Hash函数
     */
    public MultiHashBloomFilter(List<String> funcClassList,boolean loadDefault) {
        this(funcClassList, loadDefault, DEFAULT_LIMIT);
    }

    /**
     * 根据ClassNameList加载Hash函数，并设置过滤器上限
     * @param funcClassList Hash函数的类名列表
     * @param loadDefault 是否加载默认的Hash函数
     * @param limit 过滤器上限
     */
    public MultiHashBloomFilter(List<String> funcClassList, boolean loadDefault, Integer limit) {
        if( limit > 0 ) {
            initSlot(limit);
        } else {
            initSlot(DEFAULT_LIMIT);
        }
        if(loadDefault)
            loadFunctions(DEFAULT_HASH_FUNCTION_PACKAGE);
        loadFunctions(funcClassList);
    }

    @Override
    public void put(Object val) {
        for(HashFunction function : functions.values()) {
            Integer hash = function.getHash(val, slot.length);

            // System.out.println(val + ":"+ hash);

            slot[hash] = true;
        }
    }

    @Override
    public boolean contain(Object val) {
        for(HashFunction function : functions.values()) {
            if(!slot[function.getHash(val, slot.length)]) return false;
        }
        return true;
    }

    /**
     * 初始化过滤器字节值数组
     * @param limit 过滤器上限
     */
    private void initSlot(Integer limit) {
        if(slot!=null) return;
        slot = new boolean[limit];

        for(int i = 0; i < limit; i++) {
            slot[i] = false;
        }
    }

    /**
     * 根据类名列表加载函数
     * @param functionClasses 函数类名列表
     */
    private void loadFunctions(List<String> functionClasses) {
        if (functionClasses == null || functionClasses.isEmpty()) return;
        for(String className : functionClasses) {
            try {
                Class clazz = Class.forName(className);
                if(validateClass(clazz) && !functions.containsKey(className)) {
                    functions.put(className ,(HashFunction) clazz.newInstance());
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据包名加载函数。只搜索该包下实现HashFunction接口的类，不包括子包。
     * @param packageName 包名
     */
    private void loadFunctions(String packageName) {
        loadFunctions(PackageUtil.getClassName(packageName,false));
    }

    /**
     * 校验Class是否实现了HashFunction接口
     * @param clazz 类对象
     * @return 是否实现
     */
    private static boolean validateClass(Class clazz) {
        return HashFunction.class.isAssignableFrom(clazz);
    }

}
