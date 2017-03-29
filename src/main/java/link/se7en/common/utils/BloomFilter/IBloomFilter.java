package link.se7en.common.utils.BloomFilter;

/**
 * Created by Link on 2016/5/4.
 * 布隆过滤器接口
 */
public interface IBloomFilter {
    /**
     * 将元素添加到过滤器中
     * @param val 元素
     */
    void put(Object val);

    /**
     * 过滤器中是否有该元素
     * @param var 元素
     * @return 是否
     */
    boolean contain(Object var);

}
