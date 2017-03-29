package link.se7en.common.utils.BloomFilter.hash.def;


import link.se7en.common.utils.BloomFilter.hash.HashFunction;

/**
 * Created by Link on 2016/5/4.
 */
public class ByteStrHashFunction implements HashFunction {
    private static final long serialVersionUID = -7901317172871102484L;

    @Override
    public Integer getHash(Object val, Integer limit) {
        byte bytes[] = val.toString().getBytes();

        StringBuilder ret = new StringBuilder();

        for(byte b : bytes) {
            ret.append(b);
        }

        return Math.abs(ret.toString().hashCode()) % limit;
    }
}
