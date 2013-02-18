
/**
 * Trivial class; must implement an interesting interface.
 */
public class Blah implements Comparable {
    public int compareTo(Object another) {
        System.out.println("In compareTo");
        return 0;
    }
}
