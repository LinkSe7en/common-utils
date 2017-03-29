package link.se7en.common.entity;

/**
 * Created by Link on 2017/3/28.
 */
public class BeanForTest extends JsonEntity {
    private String s;
    private Integer i;
    private Long l;
    private Float f;
    private Double d;
    private Boolean b;
    private Byte by;
    private Character c;

    public void setS(String s) {
        this.s = s;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public void setL(Long l) {
        this.l = l;
    }

    public void setF(Float f) {
        this.f = f;
    }

    public void setD(Double d) {
        this.d = d;
    }

    public void setB(Boolean b) {
        this.b = b;
    }

    public void setBy(Byte by) {
        this.by = by;
    }

    public void setC(Character c) {
        this.c = c;
    }

    public String getS() {
        return s;
    }

    public Integer getI() {
        return i;
    }

    public Long getL() {
        return l;
    }

    public Float getF() {
        return f;
    }

    public Double getD() {
        return d;
    }

    public Boolean getB() {
        return b;
    }

    public Byte getBy() {
        return by;
    }

    public Character getC() {
        return c;
    }
}
