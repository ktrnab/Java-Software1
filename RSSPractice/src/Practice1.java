import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Practice1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Practice1() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    /**
     * Output all attributes names and values for the root of the given
     * {@code XMLTree}.
     *
     * @param xt
     *            the {@code XMLTree} whose root's attributes are to be printed
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the label of the root of xt is a tag] and out.is_open
     * @ensures <pre>
     * out.content
     *   #out.content *  [the name and value of each attribute of the root of xt]
     * </pre>
     */
    private static void printRootAttributes(XMLTree xt, SimpleWriter out) {

        //channel
        //feb 2

        out.println("****");

        for (int i = 0; i < xt.numberOfChildren(); i++) {

            for (String names3 : xt.child(i).attributeNames()) {
                out.println(xt.child(i).attributeValue(names3));
            }
        }

        for (int i = 0; i < xt.numberOfChildren(); i++) {

            out.println(xt.child(i).attributeNames());

        }

        if (xt.numberOfChildren() == 0) {

            for (String names2 : xt.attributeNames()) {
                out.println(xt.attributeValue(names2));
            }

            out.println(xt.attributeNames());
        }

    }

    /*
     * if (xt.numberOfChildren() > 0) { for (int i = 0; i <
     * xt.numberOfChildren(); i++) { Iterable<String> name =
     * xt.child(i).attributeNames(); out.println(name);
     *
     * }
     *
     *
     * }
     */

    private static void printMiddleNode(XMLTree xt, SimpleWriter out) {

        int lengthXT = xt.numberOfChildren();
        out.println(lengthXT);

        if (lengthXT % 2 != 0) {

            int even = (lengthXT / 2);
            out.print(xt.child(even).label());
            if (xt.child(even).isTag()) {
                out.println("It's a tag");
                out.println(
                        "Num of children" + xt.child(even).numberOfChildren());

            } else {
                out.println("It's a label");
            }

        } else {

            int odd1 = (lengthXT / 2);
            int odd2 = (lengthXT / 2) - 1;

            if (xt.child(odd1).isTag()) {
                out.println("It's a tag");
                out.println(
                        "Num of children" + xt.child(odd1).numberOfChildren());
            } else {
                out.println("It's a label");
            }

            if (xt.child(odd1).isTag()) {
                out.println("It's a tag");
                out.println(
                        "Num of children" + xt.child(odd2).numberOfChildren());
            } else {
                out.println("It's a label");
            }

        }

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Put your main program code here; it may call myMethod as shown
         */

        XMLTree xml = new XMLTree1(
                "http://web.cse.ohio-state.edu/software/2221/web-sw1/"
                        + "extras/instructions/xmltree-model/columbus-weather.xml");

        out.println(xml.toString());

        out.println("is tag: " + xml.isTag());
        out.println("the label: " + xml.label());

        XMLTree results = xml.child(0);
        out.println("results: " + results.label());

        XMLTree channel = results.child(0);
        out.println("first child: " + channel.label());

        out.println("num child: " + channel.numberOfChildren());

        XMLTree title = channel.child(1);

        XMLTree titleText = title.child(0);
        out.println(titleText.label());

        out.println(xml.child(0).child(0).child(1).child(0).label());

        XMLTree astronomy = channel.child(10);
        out.println(astronomy.hasAttribute("sunset"));
        out.println(astronomy.hasAttribute("midday"));

        if (astronomy.hasAttribute("sunset")) {
            out.println(astronomy.attributeValue("sunset"));

        }

        if (astronomy.hasAttribute("midday")) {
            out.println(astronomy.attributeValue("midday"));

        } else {
            out.println("none");
        }

        printMiddleNode(channel, out);
        printRootAttributes(channel, out);

        XMLTree item = channel.child(12);

        XMLTree forecast = item.child(9);
        out.print("////");
        out.print(forecast);
        out.print(forecast.numberOfChildren());

        printRootAttributes(forecast, out);

        //printRootAttributes(forecast, out);

        //out.print(results);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
