package link.se7en.common.utils.BloomFilter.hash.def;


import link.se7en.common.utils.BloomFilter.hash.HashFunction;
import link.se7en.common.utils.MsgDigestUtil;

/**
 * Created by Link on 2016/5/9.
 * 比特化SHA1 Hash函数
 */
public class BytesSHA1HashFunction implements HashFunction {
    private static final long serialVersionUID = 3682178362183969103L;

    @Override
    public Integer getHash(Object val, Integer limit) {
        StringBuilder sb = new StringBuilder();
        for (Byte b : MsgDigestUtil.getSHA1(val.toString()).getBytes()) {
            sb.append(b);
        }

        return Math.abs(sb.toString().hashCode()) % limit;
    }
}
