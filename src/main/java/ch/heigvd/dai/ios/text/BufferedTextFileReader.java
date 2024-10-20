package ch.heigvd.dai.ios.text;

import ch.heigvd.dai.ios.Readable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that reads text files. This implementation reads the file using a buffered reader around
 * a file reader and processes it sentence by sentence. It manages the reader and the buffered
 * reader properly with a try-with-resources block.
 */
public class BufferedTextFileReader implements Readable {

  @Override
  public String[] read(String filename) {
    List<String> sentences = new ArrayList<>();
    StringBuilder sentence = new StringBuilder();

    try (Reader reader = new FileReader(filename, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(reader)) {

      int c;
      while ((c = br.read()) != -1) {
        char currentChar = (char) c;
        sentence.append(currentChar);

        if (currentChar == '.' || currentChar == '!' || currentChar == '?') {
          sentences.add(sentence.toString().trim());
          sentence.setLength(0);
        }
      }

      if (!sentence.isEmpty()) {
        sentences.add(sentence.toString().trim());
      }

    } catch (IOException e) {
      System.err.println("Error: " + e.getMessage());
    }

    return sentences.toArray(new String[0]);
  }
}
