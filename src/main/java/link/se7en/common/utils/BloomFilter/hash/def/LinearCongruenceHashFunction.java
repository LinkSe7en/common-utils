package link.se7en.common.utils.BloomFilter.hash.def;


import link.se7en.common.utils.BloomFilter.hash.HashFunction;

import java.util.Random;

/**
 * Created by Link on 2016/5/20.
 * 线性同余Hash
 */
public class LinearCongruenceHashFunction implements HashFunction {

    private static final long serialVersionUID = -1534242152988638363L;

    @Override
    public Integer getHash(Object val, Integer limit) {
        // 这是java字节码的魔术签名 :D
        Random r = new Random(0xCAFEBABE);

        byte b[] = val.toString().getBytes();

        Long ret = 0L;

        for(byte i : b) {
            ret += Math.abs(new Random(i << (Math.abs(r.nextInt() % 8))).nextInt());
        }
        return (int)(ret % limit);
    }
}
