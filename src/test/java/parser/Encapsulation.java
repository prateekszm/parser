package parser;

public class Encapsulation {
    private int a;
    private int b;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    private int c;
}
class Org{
    public static void main(String[] args) {
        Encapsulation e=new Encapsulation();
        e.setA(2);
        System.out.println(e.getA());
    }
}