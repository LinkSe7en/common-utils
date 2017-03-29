package link.se7en.common.utils.BloomFilter.hash.def;


import link.se7en.common.utils.BloomFilter.hash.HashFunction;

/**
 * Created by Link on 2016/5/4.
 * 默认提供的Hash函数。返回字符串长度 % limit
 */
public class StringSizeHashFunction implements HashFunction {

    private static final long serialVersionUID = 6013006220507030791L;

    @Override
    public Integer getHash(Object val, Integer limit) {
        return val.toString().length() % limit;
    }
}
