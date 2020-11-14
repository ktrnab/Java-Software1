import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * This program inputs an XML RSS (version 2.0) feed from a given URL and
 * outputs various elements of the feed to the console.
 *
 * @author Put your name here
 *
 */
public final class RSSProcessing {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RSSProcessing() {
    }

    /**
     * Finds the first occurrence of the given tag among the children of the
     * given {@code XMLTree} and return its index; returns -1 if not found.
     *
     * @param xml
     *            the {@code XMLTree} to search
     * @param tag
     *            the tag to look for
     * @return the index of the first child of the {@code XMLTree} matching the
     *         given tag or -1 if not found
     * @requires [the label of the root of xml is a tag]
     * @ensures <pre>
     * getChildElement =
     *  [the index of the first child of the {@code XMLTree} matching the
     *   given tag or -1 if not found]
     * </pre>
     */
    private static int getChildElement(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";
        assert xml.isTag() : "Violation of: the label root of xml is a tag";
        /*
         * TODO: #1 - fill in body
         */

        int num = -1;
        //System.out.println(xml.numberOfChildren());

        int j = 0;

        while (j < xml.numberOfChildren()) {
            if (xml.child(j).isTag()) {
                if (xml.child(j).label().equals(tag)) {

                    num = j;
                    //System.out.println("num: " + num);
                    return num;
                }
            }

            j++;
        }

        return num;
    }

    /**
     * Processes one news item and outputs the title, or the description if the
     * title is not present, and the link (if available) with appropriate
     * labels.
     *
     * @param item
     *            the news item
     * @param out
     *            the output stream
     * @requires [the label of the root of item is an <item> tag] and
     *           out.is_open
     * @ensures out.content = #out.content * [the title (or description) and
     *          link]
     */
    private static void processItem(XMLTree item, SimpleWriter out) {
        assert item != null : "Violation of: item is not null";
        assert out != null : "Violation of: out is not null";
        assert item.isTag() && item.label().equals("item") : ""
                + "Violation of: the label root of item is an <item> tag";
        assert out.isOpen() : "Violation of: out.is_open";
        /*
         * TODO: #3 - fill in body
         */

        int titleFeed = getChildElement(item, "title");
        int linkFeed = getChildElement(item, "link");
        int descriptionFeed = getChildElement(item, "description");

        if (titleFeed != -1) {

            out.println("Title: " + item.child(titleFeed).child(0).label());

        } else if (descriptionFeed != -1) {
            if (item.child(descriptionFeed).child(0).label().equals("")) {
                out.println("No description available");
            } else {
                out.println("Desc: "
                        + item.child(descriptionFeed).child(0).label());
            }
        }

        if (linkFeed != -1) {
            out.println(item.child(linkFeed).child(0).label());
        }

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        /*
         * Open I/O streams.
         */
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Input the source URL.
         */
        out.print("Enter the URL of an RSS 2.0 news feed: ");
        String url = in.nextLine();
        /*
         * Read XML input and initialize XMLTree. If input is not legal XML,
         * this statement will fail.
         */
        XMLTree xml = new XMLTree1(url);
        /*
         * Extract <channel> element.
         */
        XMLTree channel = xml.child(0);
        //out.println(channel.toString());

        //title
        String title = "title";
        int titleNum = 0;
        titleNum = getChildElement(channel, "title");
        out.println(channel.child(titleNum));

        //link
        String link = "link";
        int linkNum = 0;
        linkNum = getChildElement(channel, "link");
        out.println(channel.child(linkNum));

        //description
        String desc = "description";
        int descNum = 0;
        descNum = getChildElement(channel, desc);
        out.println(channel.child(descNum));

        /*
         * TODO: #4 - for each item, output title (or description, if title is
         * not available) and link (if available)
         */

        for (int j = 0; j < channel.numberOfChildren(); j++) {
            if (channel.child(j).isTag()) {
                if (channel.child(j).label().equals("item")) {
                    XMLTree item = channel.child(j);
                    processItem(item, out);
                }
            }
        }

        /*
         * Close I/O streams.
         */
        in.close();
        out.close();
    }

}