

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep {

    List<String> grep(
            final boolean regex,
            final boolean ignoreRegister,
            final boolean invert,
            final String word,
            final String charsetInput) {

        final File input = new File(charsetInput);

        try (final BufferedReader br = new BufferedReader(new FileReader(input))) {
            return findLines(br, ignoreRegister, regex, invert, word);
        } catch (IOException e) {
            System.err.println("Error from reading file\n" + e.getMessage());
        }

        return null;
    }

    public List<String> findLines (
            final BufferedReader bufferedReader,
            boolean ignoreRegister,
            boolean regex,
            boolean invert,
            String word) throws IOException {

        final List<String> result = new ArrayList<>();

        String line;
        String found = null;

        while ((line = bufferedReader.readLine()) != null) {
            if (ignoreRegister && line.toUpperCase().contains(word.toUpperCase())) {
                found = line;
            } else if (line.contains(word)) {
                found = line;
            } else if (regex) {
                Pattern pattern = Pattern.compile(word);
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
