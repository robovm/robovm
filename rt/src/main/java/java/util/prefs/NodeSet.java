package java.util.prefs;

import java.util.ArrayList;
import java.util.Iterator;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class NodeSet implements NodeList {

    ArrayList<Node> list = new ArrayList<Node>();

    public NodeSet(Iterator<Node> nodes) {
        while(nodes.hasNext()) {
            list.add(nodes.next());
        }
    }

    public int getLength() {
        return list.size();
    }

    public Node item(int index) {
        Node result = null;
        try {
            result = list.get(index);
        } catch(IndexOutOfBoundsException ioobe) {
            // TODO log this event?
            return null;
        }

        return result;
    }
}
