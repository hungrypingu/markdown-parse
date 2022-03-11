import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import org.commonmark.node.*;
import org.commonmark.parser.Parser;
//import org.commonmark.renderer.html.HtmlRenderer;

import java.io.PrintStream;
import java.io.File;

class LinkCountVisitor extends AbstractVisitor {
    public int linkCount = 0;
    public ArrayList<Link> links = new ArrayList<Link>();
    public ArrayList<String> linkStrings = new ArrayList<String>();

    @Override
    public void visit(Link link) {

        linkCount ++;
        links.add(link);

        linkStrings.add(link.getDestination());

        visitChildren(link);
    }
}


public class CommonMarkdownParse {
    public static ArrayList<String> getLinks(String markdown) throws IOException {
        
        ArrayList<String> links = new ArrayList<String>();

        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        LinkCountVisitor linkVisitor = new LinkCountVisitor();
        document.accept(linkVisitor);

        /*
        for (Link l : linkVisitor.links) {
            links.add(l.getDestination());
        }
        */

        for (String s : linkVisitor.linkStrings) {
            links.add(s);
        }

        return links;
    }
    public static void main(String[] args) throws IOException {
		
        /*
        Path fileName = Path.of(args[0]);
        
        Path fileName = Path.of("test-file.md");

        String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
        */

        File dir = new File("./test-files");
        
        System.out.println("hello world");

        /*
        File my_results = new File("my-results.txt");
        if (my_results.createNewFile()) {
            System.out.println("File created: " + my_results.getName());
        } else {
            System.out.println("File already exists.");
        }
        */


        String cmdp = "common_mdparse.txt";

        Files.writeString(Path.of(cmdp), "");

        System.out.println(Files.readString(Path.of(cmdp)));
        
        System.out.println("hello world");

        //writes each result to the file in the same form as bash script
        String contents;
        ArrayList<String> links = new ArrayList<String>();
        for (String s: dir.list()) {
            if (s.substring(s.length()-2).equals("md")) {

                contents = Files.readString(Path.of("test-files/" + s));
                links = getLinks(contents);

                //adds each new file result to the result file
                Files.writeString(Path.of(cmdp), s + "\n" + links.toString() + "\n", StandardOpenOption.APPEND);

                System.out.println(links.toString());
            }
        }

        System.out.println("\n\nExpected ouput using CommonMark: " + getLinks(Files.readString(Path.of("test-files/510.md"))));
        

        /*
        File my_results = new File("my-results.txt");
        if (my_results.createNewFile()) {
            System.out.println("File created: " + my_results.getName());
        } else {
            System.out.println("File already exists.");
        }

        File dir = new File("./test-files");
        
        Path fileName;
        String contents;
        ArrayList<String> links;
        for (String f : dir.list()) {
            fileName = Path.of(f);

            contents = Files.readString(fileName);
            links = getLinks(contents);
            System.out.println(links);
        }
        */
    }
}
