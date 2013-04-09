package tests.api.java.util.support;

import java.util.ResourceBundle;

public class P {
    private Class c;

    public void setClazz(Class c) {
        this.c = c;
    }

    public String findProp(String key) {
        return findProp(this.c, key);
    }

    private String findProp(Class cls, String key) {
        String ret = null;
        try {
            ResourceBundle b = ResourceBundle.getBundle(cls.getName());
            ret = (String)b.getObject(key);
        } catch (Exception e) {
        }
        if (ret == null && !cls.equals(Object.class) && !cls.isPrimitive()) {
            ret = findProp(cls.getSuperclass(), key);
        }
        return ret;
    }
}
