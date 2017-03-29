package link.se7en.common.utils.BloomFilter.hash;

import java.io.Serializable;

/**
 * Created by Link on 2016/5/4.
 * 布隆过滤器的Hash函数接口
 */
public interface HashFunction extends Serializable {
    Integer getHash(Object val, Integer limit);
}
