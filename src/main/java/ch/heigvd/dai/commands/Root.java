package ch.heigvd.dai.commands;

import ch.heigvd.dai.ios.text.BufferedTextFileReader;
import ch.heigvd.dai.ios.text.BufferedTextFileWriter;
import com.deepl.api.*;
import picocli.CommandLine;

@CommandLine.Command(
    description = "A small CLI to experiment with Java IOs.",
    version = "1.0.0",
    scope = CommandLine.ScopeType.INHERIT,
    mixinStandardHelpOptions = true)
public class Root implements Runnable {
  String API_KEY = "d65ea39b-877f-4397-92f9-fb7082776f8b:fx"; // Should be hidden

  public enum AvailableLanguages {
    FRENCH,
    SPANISH,
    ITALIAN,
    GERMAN,
    CHINESE
  }

  @CommandLine.Option(
      names = {"-i", "--input"},
      description = "The name of the input file.",
      required = true)
  protected String inputFilename;

  @CommandLine.Option(
      names = {"-o", "--output"},
      description = "The name of the output file.",
      required = true)
  protected String outputFilename;

  @CommandLine.Option(
      names = {"-l", "--language"},
      description = "The implementation to use (possible values: ${COMPLETION-CANDIDATES}).",
      required = true)
  protected AvailableLanguages languages;

  @Override
  public void run() {
    Translator translator = new Translator(API_KEY);
    try {
      BufferedTextFileReader reader = new BufferedTextFileReader();
      String[] sentences = reader.read(getInputFilename());
      String[] targetLangs = {"fr", "es", "it", "de", "zh-hans"};

      for (int i = 0; i < sentences.length; i++) {
        TextResult result =
            translator.translateText(sentences[i], "en", targetLangs[languages.ordinal()]);

        if (result != null) {
          sentences[i] = result.getText();
        }
      }

      BufferedTextFileWriter writer = new BufferedTextFileWriter();
      writer.reset(getOutputFilename());
      writer.write(getOutputFilename(), sentences);

    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  public String getInputFilename() {
    return inputFilename;
  }

  public String getOutputFilename() {
    return outputFilename;
  }

  public AvailableLanguages getImplementation() {
    return languages;
  }
}
