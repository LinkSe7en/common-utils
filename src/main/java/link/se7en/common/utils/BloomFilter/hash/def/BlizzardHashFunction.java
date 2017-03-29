package link.se7en.common.utils.BloomFilter.hash.def;


import link.se7en.common.utils.BloomFilter.hash.HashFunction;

/**
 * Created by Link on 2016/5/20.
 * 暴雪Hash
 */
public class BlizzardHashFunction implements HashFunction {
    private static final long serialVersionUID = 751684290832605813L;

    private long cryptTable[] = new long[0x500];

    public BlizzardHashFunction() {
        long seed = 0x00100001, index1 = 0, index2 = 0, i;

        for( index1 = 0; index1 < 0x100; index1++ )
        {
            for( index2 = index1, i = 0; i < 5; i++, index2 += 0x100 )
            {
                long temp1, temp2;
                seed = (seed * 125 + 3) % 0x2AAAAB;
                temp1 = (seed & 0xFFFF) << 0x10;
                seed = (seed * 125 + 3) % 0x2AAAAB;
                temp2 = (seed & 0xFFFF);
                cryptTable[(int)index2] = ( temp1 | temp2 );
            }
        }
    }

    @Override
    public Integer getHash(Object val, Integer limit) {
        char s[] = val.toString().toUpperCase().toCharArray();

        long seed1 = 0x7FED7FED,seed2 = 0xEEEEEEEE;

        int ch = 0;

        for(char i : s) {
            ch = (int)i;

            seed1 = cryptTable[((s.length % 3) << 8) + ch] ^ (seed1 + seed2);

            seed2 = ch + seed1 + seed2 + (seed2 << 5) + 3;
        }

        return (int)(Math.abs(seed1) % limit);
    }
}
