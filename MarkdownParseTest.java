import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.*;

public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void testGetLinks() throws IOException {

        //needs to be run using
        //javac -cp ".;lib\junit-4.13.2.jar;lib\hamcrest-core-1.3.jar" 	MarkdownParseTest.java
        //java -cp ".;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore MarkdownParseTest

        Path filename = Path.of("test-file2.md");
        String contents = Files.readString(filename);
        assertEquals(MarkdownParse.getLinks(contents), List.of("https://something.com", "some-page.html"));

        filename = Path.of("test-file.md");
        contents = Files.readString(filename);
        assertEquals(MarkdownParse.getLinks(contents), List.of("https://something.com", "some-page.html", "hello"));

        filename = Path.of("test-file3.md");
        contents = Files.readString(filename);
        assertEquals(MarkdownParse.getLinks(contents), List.of());

        filename = Path.of("test-file4.md");
        contents = Files.readString(filename);
        assertEquals(MarkdownParse.getLinks(contents), List.of("firstlinelink"));

        filename = Path.of("test-file9.md");
        contents = Files.readString(filename);
        assertEquals(MarkdownParse.getLinks(contents), List.of("some-page.html"));

        //tests snippets, week 8
        //snippet1
        filename = Path.of("snippet1.md");
        contents = Files.readString(filename);
        //assertEquals(List.of("url.com", "`google.com", "google.com", "ucsd.edu"), 
        //    MarkdownParse.getLinks(contents));

        //snippet2
        filename = Path.of("snippet2.md");
        contents = Files.readString(filename);
        //assertEquals(List.of("a.com", "a.com(())", "example.com"), 
        //    MarkdownParse.getLinks(contents));        

        //snippet3
        filename = Path.of("snippet3.md");
        contents = Files.readString(filename);
        assertEquals(List.of("https://www.twitter.com", "https://ucsd-cse15l-w22.github.io/", 
            "https://cse.ucsd.edu/"), MarkdownParse.getLinks(contents));        

    }

    /*
    @Test
    public void testGetLinks() {
        boolean exceptionThrown = false;
        Path filename = Path.of("test-file2.md");
        String contents;
        try {
            contents = Files.readString(filename);
        } catch (IOException e) {
            exceptionThrown = true;
        }
        assertEquals(false, exceptionThrown);
    }
    */
}
