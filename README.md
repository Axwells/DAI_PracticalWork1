# Practical work 1

## Initial idea
Create a CLI that translates an English text file to different languages.

Languages to translate to:

- French
- Spanish
- Italian
- German
- Simplified Chinese if time allows it (encode is different)

Library to use: https://github.com/DeepLcom/deepl-java

Supported languages: https://developers.deepl.com/docs/resources/supported-languages

## Usage
To use our CLI you must use the following command :
````
java -jar target/packagedApplication.jar -l languageToTranslateTo -i inputFile.txt -o outputFile.txt
````

## Conclusion

To make a good use of the DeepL library, we decided to create a String array,
containing all the sentences that needed to be translated.
Once we have this array we translate every sentence using the DeepL library.

We also learned that simplified chinese actually doesn't need a different encoding, so it was pretty easy to implement in
our project.

The API key is written in clear in our project, it's something that should not be done. Since we didn't learn how to do
it, we decided to wait the right course to implement it.