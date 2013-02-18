import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.Set;

public class Main {
    static public void main(String[] args) throws Exception {
        // These charsets must be provided; anything else is optional.
        List<String> standardCharsets = Arrays.asList("US-ASCII", "ISO-8859-1",
                "UTF-8", "UTF-16BE", "UTF-16LE", "UTF-16");

        SortedMap<String, Charset> all = Charset.availableCharsets();
        Set<String> needed = new HashSet<String>(standardCharsets);
        for (Map.Entry<String, Charset> e : all.entrySet()) {
            String canonicalName = e.getKey();
            needed.remove(canonicalName);
        }
        System.out.println("Missing: " + needed);
    }
}
