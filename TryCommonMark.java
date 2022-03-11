import java.util.ArrayList;

import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

class WordCountVisitor extends AbstractVisitor {
    public int wordCount = 0;

    @Override
    public void visit(Text text) {
        // This is called for all Text nodes. Override other visit methods for other node types.

        // Count words (this is just an example, don't actually do it this way for various reasons).
        wordCount += text.getLiteral().split("\\W+").length;

        // Descend into children (could be omitted in this case because Text nodes don't have children).
        visitChildren(text);
    }
}

class LinkCountVisitor extends AbstractVisitor {
    public int linkCount = 0;
    public ArrayList<Link> links = new ArrayList<Link>();

    @Override
    public void visit(Link link) {

        linkCount ++;
        links.add(link);
        visitChildren(link);
    }
}

class TryCommonMark {
    public static void main(String[] args) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse("This is *Sparta*");
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        System.out.println(renderer.render(document));  // "<p>This is <em>Sparta</em></p>\n"

        Node node = parser.parse("Example\n=======\n\nSome more text");
        WordCountVisitor visitor = new WordCountVisitor();
        node.accept(visitor);
        System.out.println(visitor.wordCount);  // 4        
    
        Node node2 = parser.parse("# Title [a link!](https://something.com)");
        LinkCountVisitor linkVisitor = new LinkCountVisitor();
        node2.accept(linkVisitor);
        System.out.println(linkVisitor.linkCount);
        for(Link l : linkVisitor.links) {
            System.out.println(l);
        }
    
    }
}