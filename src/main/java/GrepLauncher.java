import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

public class GrepLauncher {
    private Grep grep;

    @Option(name = "-r", usage = "Search expression")
    private boolean regex;

    @Option(name = "-v", usage = "Inverts the filter condition")
    private boolean invert;

    @Option(name = "-i", usage = "Ignore word case")
    private boolean ignoreRegister;

    @Argument(required = true, usage = "Word or phrase to search for")
    private String word;

    @Argument(required = true, index = 1, usage = "The name of the file in which you are looking for")
    private String inputName;



    public static void main(String[] args) throws IOException {
        new GrepLauncher().doGrep(args);
    }

    private void doGrep(String[] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        }  catch(CmdLineException e) {
            System.err.println("java Grep [options...] argument...");
            parser.printUsage(System.err);
            System.err.println();
            System.err.println("  Example: grep [-r] [-i] [-v] word inputname.txt");
            return;
        }

        Grep grep = new Grep(regex,ignoreRegister,invert,word,inputName);
        for (String line: grep.grep()) {
            System.out.println(line);
        }

    }

}