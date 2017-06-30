package gq.wh.generator;

/**
 * Created by mr-guo on 2017/6/26.
 */
public class Bijection5x5 {
    byte[] coding;
    byte[] invcoding;

    public Bijection5x5(){
        coding = new byte[32];
        invcoding = new byte[32];
    }

    public byte[] getCoding() {
        return coding;
    }

    public byte[] getInvcoding() {
        return invcoding;
    }

    public void setCoding(byte[] coding) {
        this.coding = coding;
    }

    public void setInvcoding(byte[] invcoding) {
        this.invcoding = invcoding;
    }
}
