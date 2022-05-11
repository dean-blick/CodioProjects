// Load required classes
import java.util.Scanner;
import java.io.File;

public class PigLatin{
  
  public static void main(String[] args) throws Exception{
    
    // Scanner variable
    Scanner reader;
    
    // If an argument is present, we are reading from a file
    // specified in args[0]
    if(args.length > 0){
      reader = new Scanner(new File(args[0]));
    
    // If no argument, read from System.in
    }else{
      reader = new Scanner(System.in);
    }

    String finalString = "";
    String finalWord = "";
    String currentWord = "";
    char punctuation = ' ';
    boolean punc = false;
    boolean upperCase = false;
    boolean startsWithVowel = false;
    int cwl = 0;
    String vowels = "aeiouAEIOU";
    String vowelsY = "aeiouyAEIOUY";
    String consonants = "";
    String currentLine = "";
    String[] currentLineArray;
     //Loop moves through each line
     while(true){
         currentLine = reader.nextLine();
         //currentLineArray = new String[currentLine.length()]
         currentLineArray = currentLine.split(" ");
         for(int i = 0; i < currentLineArray.length; i++){
              punc = false;
              upperCase = false;
              startsWithVowel = false;
              currentWord = currentLineArray[i];
              cwl = currentWord.length();
              //Checks word for punctuation
              if((currentWord.charAt(cwl - 1)=='.')||(currentWord.charAt(cwl - 1)=='?')||(currentWord.charAt(cwl - 1)=='!')||(currentWord.charAt(cwl - 1)==',')){
                      punctuation = currentWord.charAt(cwl - 1);
                      currentWord = currentWord.substring(0, cwl - 1);
                      cwl = currentWord.length();
                      punc = true;
              }
              //Checks for capital first letter
              upperCase = Character.isUpperCase(currentWord.charAt(0));
              currentWord = currentWord.toLowerCase();
              //Checks if the first letter is a vowel    
              if(vowels.indexOf(currentWord.charAt(0))>=0){
                      startsWithVowel = true;
                      currentWord = currentWord + "yay";
              }
              //moves consonants to end
              int j = 1;
              while(true){

                  if(startsWithVowel){
                      break;
                  }
                  if(vowelsY.indexOf(currentWord.charAt(j))>=0){
                      consonants = currentWord.substring(0, j);
                      currentWord = currentWord.substring(j, currentWord.length());
                      currentWord = currentWord + consonants + "ay";
                      break;
                  }else{
                      j++;
                      continue;
                  }

              }

              if(upperCase){
              currentWord = currentWord.substring(0, 1).toUpperCase() + currentWord.substring(1);
              }

              if(punc){
                  currentWord += punctuation;
              }
              //Adds the new piglatin word to the output string
              finalString+= currentWord + " ";


           }
           finalString+= "\n";
           
         
         
         if(reader.hasNextLine()){
              continue;
              
          }else{break;}
     }
      System.out.println(finalString);
  }

}