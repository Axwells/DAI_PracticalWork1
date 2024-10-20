package ch.heigvd.dai.commands;

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
  }

  @CommandLine.Parameters(index = "0", description = "The name of the file.")
  protected String filename;

  @CommandLine.Option(
      names = {"-l", "--language"},
      description = "The implementation to use (possible values: ${COMPLETION-CANDIDATES}).",
      required = true)
  protected AvailableLanguages languages;

  @Override
  public void run() {
    switch (languages) {
      case FRENCH:
        Translator translator = new Translator(API_KEY);
        try {
          TextResult result = translator.translateText("Hello, world!", null, "fr");
          System.out.println(result.getText()); // "Bonjour, le monde !"
        } catch (Exception e) {

        }

        break;
      case SPANISH:
        System.out.println("You selected SPANISH.");
        break;
      case ITALIAN:
        System.out.println("You selected ITALIAN.");
        break;
      case GERMAN:
        System.out.println("You selected GERMAN.");
        break;
      default:
        System.out.println("Unknown language selected.");
        break;
    }
  }

  public String getFilename() {
    return filename;
  }

  public AvailableLanguages getImplementation() {
    return languages;
  }
}
