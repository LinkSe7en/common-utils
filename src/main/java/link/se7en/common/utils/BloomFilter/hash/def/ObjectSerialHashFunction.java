package link.se7en.common.utils.BloomFilter.hash.def;

import link.se7en.common.utils.BloomFilter.hash.HashFunction;
import link.se7en.common.utils.ByteArrayUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Link on 2017/3/30.
 */
public class ObjectSerialHashFunction implements HashFunction {
    private static final long serialVersionUID = -5397006823326549352L;

    @Override
    public Integer getHash(Object val, Integer limit) {
        if( !(val instanceof Serializable) )  return Math.abs(val.hashCode()) % limit;

        Serializable ser  = (Serializable) val;

        byte[] bytes = ByteArrayUtil.toByte(ser);

        List<Integer> ints = new ArrayList<>();

        for (int i = 0 ; i < (bytes.length / 4 ) ; i++ ) {
            byte longBytes[] = new byte[4];
            System.arraycopy(bytes, 4*i ,longBytes,0, (bytes.length - (4*i)) % 4);
            ints.add(ByteArrayUtil.toInt(longBytes));
        }

        long fin = 0;

        for(int i : ints) {
            fin = ( Math.abs(fin + Math.abs(i)) + 0x1BABA ) % (0xBABE) ;
        }
        return (int)(fin % limit);
    }
}
