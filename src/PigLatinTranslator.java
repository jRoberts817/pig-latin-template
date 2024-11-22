import java.lang.*;

public class PigLatinTranslator
{
  public static Book translate(Book input)
  {
    Book translatedBook = new Book();
    translatedBook.setTitle(input.getTitle() + " Translated to Pig Latin");
    for(int i = 0; i < input.getLineCount(); i++){
      String line = input.getLine(i);
      translatedBook.appendLine(translate(line));
    }


    return translatedBook;
  }

  public static String translate(String input)
  {
    // System.out.println("Translate String: '" + input + "'");

    // Replace this code to translate a string input.
    // The input to this function could be any English string. 
    // A sentence, paragraph, or a single word. 
    // It should call translateWord at least once.

    String result = translateWord(input);

    return result;
  }

  private static String translateWord(String input)
  {
    if(input.contains(" ") || input.contains("-") || input.contains("_")){
      int i = 0;
      String temp = input;
      String result = "";
      String word = "Unknown";
      if(input.contains(" ") && containsCons(input)){
        for(int l = 0; l < charCount(input, " "); l++){
          word = temp.substring(i, temp.indexOf(" "));
          temp = temp.substring(temp.indexOf(" ") + 1);
          result += translateWord(word) + " ";
          
        }
        result += translateWord(temp);
        return result;
      }else if(input.contains("-") && containsCons(input)){

        for(int l = 0; l < charCount(input, "-"); l++){
          word = temp.substring(i, temp.indexOf("-"));
          temp = temp.substring(temp.indexOf("-") + 1);
          result += translateWord(word) + "-";
          
        }
        result += translateWord(temp);
        return result;

      }else if(input.contains("_") && containsCons(input)){

        for(int l = 0; l < charCount(input, "_"); l++){
          word = temp.substring(i, temp.indexOf("_"));
          temp = temp.substring(temp.indexOf("_") + 1);
          result += translateWord(word) + "_";
          
        }
        result += translateWord(temp);
        return result;

      }else{
        return input;
      }
    }else if (input.length()>0){
      if(containsCaps(input)){
        int vowel = firstVowel(input);
        String temp = input.substring(vowel, input.length());
        String first = temp.substring(0, 1);
        String result = first.toUpperCase() + temp.substring(1) + input.substring(0, vowel).toLowerCase() + "ay";
        if(result.contains(".")){
         result = result.replace(".", "");
         result += ".";
        }
        return result;
      }else{
        int vowel = firstVowel(input);
        String temp = input.substring(vowel, input.length());
        String result = temp + input.substring(0, vowel).toLowerCase() + "ay";
       return result;
      }
    }else{
      return input;
    }
  }

  // Add additonal private methods here.
  // For example, I had one like this:
  // private static String capitalizeFirstLetter(String input)

  private static int firstVowel(String input){
    int vowel = -1;
    int index = -1;
    String vowels = "aeiouyAEIOUY";
    while((vowel<0) && (index < input.length()-1 )){
      index++;
      vowel = vowels.indexOf(input.substring(index, index+1));
    }
    return index;
  }

  private static boolean containsCons(String input){
    String consonants = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    int i = 0;
    while(i < input.length()){
      if(consonants.contains(input.substring(i, i+1))){
        return true;
      }else{
        i++;
      }
    }
    return false;
  }
  private static int charCount(String input, String c){
    int index = 0;
    int count = 0;
    while(index<input.length()){
      if (input.substring(index, index+1).equals(c)){
        count++;
      }
      index++;
    }
    return count;
  }
  private static boolean containsCaps(String input){
    String caps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    int i = 0;
    while(i < input.length()){
      if(caps.contains(input.substring(i, i+1))){
        return true;
      }else{
        i++;
      }
    }
    return false;
  }
}