package link.se7en.common.utils.BloomFilter.hash.def;


import link.se7en.common.utils.BloomFilter.hash.HashFunction;

/**
 * Created by Link on 2016/5/4.
 * 默认提供的Hash函数，返回对象的hashCode % limit
 */
public class JavaHashCode implements HashFunction {
    private static final long serialVersionUID = 2431409153762820875L;

    @Override
    public Integer getHash(Object val, Integer limit) {
        return Math.abs(val.hashCode() % limit);
    }
}
