

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {
    private final boolean regex;
    private final boolean ignoreRegister;
    private final boolean invert;
    private final String word;
    private final String inputName;

    Grep(final boolean regex,
         final boolean ignoreRegister,
         final boolean invert,
         final String word,
         final String inputName){
        this.regex = regex;
        this.ignoreRegister = ignoreRegister;
        this.invert = invert;
        this.word = word;
        this.inputName = inputName;

    }

    List<String> grep() {

        final File input = new File(inputName);

        try (final BufferedReader br = new BufferedReader(new FileReader(input))) {
            return findLines(br);
        } catch (IOException e) {
            System.err.println("Error from reading file\n" + e.getMessage());
        }

        return null;
    }

    private List<String> findLines(final BufferedReader bufferedReader) throws IOException {

        final List<String> result = new ArrayList<>();

        String line;
        String found = null;
        Pattern pattern = Pattern.compile(word);

        while ((line = bufferedReader.readLine()) != null) {
            if (ignoreRegister && line.toUpperCase().contains(word.toUpperCase())) {
                found = line;
            } else if (line.contains(word)) {
                found = line;
            } else if (regex) {

                Matcher matcher = pattern.matcher(line);

                if (matcher.find()) {
                    found = line;
                }
            }

            if (invert) {
                if (found == null) {
                    found = line;
                } else {
                    found = null;
                }
            }

            if (found != null) {
                result.add(line);
            }

            found = null;
        }


        return result;
    }
}
