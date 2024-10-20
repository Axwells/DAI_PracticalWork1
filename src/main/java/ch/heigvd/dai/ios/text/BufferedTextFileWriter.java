package ch.heigvd.dai.ios.text;

import ch.heigvd.dai.ios.Writable;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/**
 * A class that writes text files. This implementation writes the file using a buffered writer
 * around a file writer. It manages the writer and the buffered writer properly with a
 * try-with-resources block. It writes sentence by sentence and appends to the file.
 */
public class BufferedTextFileWriter implements Writable {

  @Override
  public void write(String filename, String[] sentences) {
    try (Writer writer = new FileWriter(filename, StandardCharsets.UTF_8, true);
        BufferedWriter bw = new BufferedWriter(writer)) {

      for (String sentence : sentences) {
        bw.write(sentence + ' ');
      }
      bw.flush();

    } catch (IOException e) {
      System.err.println("Error: " + e.getMessage());
    }
  }

  public void reset(String filename) {
    try (Writer writer = new FileWriter(filename, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(writer)) {
      bw.write("");
      bw.flush();
    } catch (IOException e) {
      System.err.println("Error: " + e.getMessage());
    }
  }
}
