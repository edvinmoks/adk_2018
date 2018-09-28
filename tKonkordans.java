import java.io.*;
import java.lang.*;
import java.util.*;


public class tKonkordans{

    static char[] letters = new char[30];

    public static void main(String[] args){
        try{
        //Fill char array with letter values.
        int k = 1;
        for(int i = 0; i < 27; i++){
          letters[i] = (char)(95 + (k++));
        }
        letters[0] = (char)(32); //space
        letters[27] = (char)(228); //ä (ä first because unix sorts for ASCII values, where ä comes before å)
        letters[28] = (char)(229); //å
        letters[29] = (char)(246); //ö

        String searchWord = "";
        String hashedSearchWord = "";

        if (args.length == 0) {
    			System.out.println("No input.");
    		} else {
    			//System.out.println(args[0]);
          searchWord = args[0].toLowerCase();
          hashedSearchWord = String.valueOf(hashWord(searchWord));
          //System.out.println("hashedSearchWord: "+hashedSearchWord);
    		}
        // Läs in hashmapen som genererats i build.java
        Map<String, String> hm = new HashMap<String, String>();
        Properties properties = new Properties();
        properties.load(new FileInputStream("data.properties"));
        for (String key : properties.stringPropertyNames()) {
            hm.put(key, properties.get(key).toString());
          }

        String position = hm.get(hashedSearchWord); //Get the position of the search word in the indexArray.

        Integer indexPosition = Integer.parseInt(position);
        System.out.println("indexPosition: "+indexPosition);
        RandomAccessFile rafWord = new RandomAccessFile(new File("wordList.txt"), "r");
        rafWord.seek(indexPosition);
        String foo = rafWord.readLine();
        String[] word = foo.split(",");

        String firstThreeSearchWord = searchWord.substring(0,Math.min(searchWord.length(),3)); //get first three letters from search word
        String firstThreeCurrent = word[0].substring(0,Math.min(word[0].length(),3)); //get first three letters from first three letters of the first ocurrence of the searchword.

        boolean found = false;
        String line = "";

        while(firstThreeSearchWord.equals(firstThreeCurrent)){

          if(word[0].equals(searchWord)){
            found = true;
            break;
          }
          line = rafWord.readLine();
          word = line.split(",");
          firstThreeCurrent = word[0].substring(0,Math.min(word[0].length(),3));
        }

        if(!found){
          System.out.println("The word does not exist in the database.");
          System.exit(0);
        }

        System.out.println("There is "+ word[2] +" occurences of the word "+word[0]);
        boolean printAll = true;
        int wordCount = Integer.parseInt(word[2]);

        if(wordCount > 25){
          printAll = false;
          System.out.println("Since there is more than 25 occurences of the word, do you want to print all the sentences where it occurs? [y/n]");
          Scanner scanner = new Scanner(System.in);
          String answer = scanner.nextLine();
          if(answer.equals("y")){
            printAll = true;
          }
        }

        RandomAccessFile rafPos = new RandomAccessFile(new File("positionList.txt"), "r");
        int pos = Integer.parseInt(word[1]);
        rafPos.seek(pos);
        String tempLine = rafPos.readLine();
        String[] positions = tempLine.split(",");

        int wordLength = word[0].length();
        int end = 60+wordLength;
        String bas = "";
        String bs = "";
        RandomAccessFile rafKorp = new RandomAccessFile("korpus.txt", "r");


        if(printAll){
        for(int i=0; i < positions.length; i++){
          pos = Integer.parseInt(positions[i]);
          pos = pos - 30;

          if((pos)<0){
            while((pos)<0){
              pos++;
            }
          }
          if((pos+end)>rafKorp.length()){
            while((pos+end)>rafKorp.length()){
              end--;
            }
          }
          rafKorp.seek(pos);

            for(int j=0; j < end; j++){
              byte[] tpa = {rafKorp.readByte()};
              bs = new String(tpa,"ISO-8859-1");
              bs = bs.replaceAll("\n", " ");
              bas += bs;
            }
          System.out.println(bas);
          bas = "";
          end = 60+wordLength;
        }
        }

}
    catch (IOException e){
          System.out.println("There was an error loading files");
        }



    }    //END main
  //Creates a unique hash value for the first three letters of every word.
  public static int hashWord(String word) {
    int i = 0;
    int hashValue = 0;
    while(i < word.length() && i < 3) {
      hashValue += Math.pow(30,2-i) * getLetterValue(word.substring(i,i+1));
      i++;
    }
    return hashValue;
  }

  //Returns the corresponding value of each letters for the char array.
  public static int getLetterValue(String letter) {
    for(int i = 0; i < letters.length; i++) {
      if(letter.equals(Character.toString(letters[i]))) {
        return i;
      }
    }
    return 0;
  }

}
