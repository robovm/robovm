package tests.api.java.util.support;

public class A implements I {
    private static P pp = new P();

    public A() {
        pp.setClazz(getClass());
    }

    public String find(String key) {
        return pp.findProp(key);
    }
}
