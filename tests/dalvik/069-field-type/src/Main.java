
/**
 * Create some objects and store them into an instance field.
 */
public class Main {
    /**
     * Entry point.
     */
    public static void main(String[] args) {
        Holder holder = new Holder();

        Blah blah = new Blah();

        /* strictly speaking, this should fail */
        holder.mValue = blah;

        System.out.println("Assignment was allowed");

        /* try to use the reference; should fail */
        try {
            holder.mValue.run();
            System.err.println("ERROR: did not get expected ICCE");
        } catch (IncompatibleClassChangeError icce) {
            System.out.println("Got expected IncompatibleClassChangeError");
        }

        /* for fun, verify that it's the "alternate" type */
        //Comparable cmpx = holder.mValue;      /* compiler rejects */
        Comparable cmp = (Comparable) holder.mValue;
        cmp.compareTo(cmp);

        System.out.println("Done");
    }
}
