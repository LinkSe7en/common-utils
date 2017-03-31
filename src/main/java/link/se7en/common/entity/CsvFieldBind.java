package link.se7en.common.entity;

import java.util.HashMap;

/**
 * Created by Link on 2017/3/30.
 */
public class CsvFieldBind extends HashMap<Integer,String> {
    private static final long serialVersionUID = -1914415852044481582L;

    public CsvFieldBind() {
        super();
    }

    public CsvFieldBind bind(Integer csvIndex, String field) {
        super.put(csvIndex,field);
        return this;
    }
}
