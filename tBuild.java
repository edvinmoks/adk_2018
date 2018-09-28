import java.io.*;
import java.lang.*;
import java.util.*;

public class tBuild{

    //Create char array of letters for the hashing.
    static char[] letters = new char[30];
    static HashMap<String,String> hm = new HashMap<String,String>();



    public static void main(String[] args){

      try {
        //Fill char array with letter values.
        int k = 1;
        for(int i = 0; i < 27; i++){
          letters[i] = (char)(95 + (k++));
        }
        letters[0] = (char)(32); //space
        letters[27] = (char)(228); //ä (ä first because unix sorts for ASCII values, where ä comes before å)
        letters[28] = (char)(229); //å
        letters[29] = (char)(246); //ö


      File file = new File("ut.txt");

      BufferedReader reader  = new BufferedReader(
      new InputStreamReader(new FileInputStream(file),"ISO-8859-1"));



      BufferedWriter wordList = new BufferedWriter(new FileWriter(new File("wordList.txt")));
      BufferedWriter positionList = new BufferedWriter(new FileWriter(new File("positionList.txt")));

      int positionIndex = 0;
      int wordCounter = 0;

      String in = reader.readLine(); //Reads first line from ut.txt

      String[] current = in.split("\\s+");

      in = reader.readLine();
      String[] next = in.split("\\s+");

try{
      while(in != null){
        wordList.write(current[0]+","+positionIndex);
        positionList.write(current[1]);

        positionIndex += current[1].length();
        wordCounter = 1;

        while(current[0].equals(next[0])){ //Checks if current word is the same as the next word

          positionList.write(","+next[1]);

          positionIndex += next[1].length() + 1; //Lägger till längden av siffran till postionsListCounter (+1 för komma)
          wordCounter++;

          in = reader.readLine();

          next = in.split("\\s+");
        }

      positionList.write("\n");
      positionList.flush();
      positionIndex++; //For the new line

      wordList.write(","+wordCounter+"\n");
      wordList.flush();

      current = next;
      in = reader.readLine();
      if(in==null) {
        positionList.write(current[1]);
        wordList.write(current[0]+","+positionIndex+","+"1");
        break;
      }
      next = in.split("\\s+");

    } // END WHILE

    reader.close();
    wordList.close();
    positionList.close();

    File file2 = new File("wordList.txt");
    BufferedReader reader2  = new BufferedReader(
    new InputStreamReader(new FileInputStream(file2),"ISO-8859-1"));
    in = reader2.readLine();
    current = in.split(",");
    Integer counter = 0;
    String[] previous = {"z","z","z"}; //Initialize with letters to avoid exception.




    boolean go = true;
    while(go){

      if(hashWord(current[0])!=hashWord(previous[0])){ //kollar om ord är annorlunda från den andra, isåfall lägg in i hashtabell

        hm.put(String.valueOf(hashWord(current[0])),String.valueOf(counter)); //Lägger till unika ord

      }
      counter += in.length()+1;
      previous = current;
      in = reader2.readLine();
      //System.out.println("in: "+ in);
      if(in==null){
        break;
      }

      current = in.split(",");

    }

}
catch(NullPointerException ex){
  System.out.println("Exception!: "+"\n"+"current: "+current[0]+"\n"+"next: "+next[0]);

}
      reader.close();
      wordList.close();
      positionList.close();


      Properties properties = new Properties();
      for (HashMap.Entry<String,String> entry : hm.entrySet()) {

          properties.put(entry.getKey(), entry.getValue());
          }

      properties.store(new FileOutputStream("data.properties"), null);

}
      catch (IOException e){
                System.out.println("there was an error");
  }
}//END MAIN

public static int hashWord(String word) {
  int i = 0;
  int hashValue = 0;
  while(i < word.length() && i < 3) {
    hashValue += Math.pow(30,2-i) * getLetterValue(word.substring(i,i+1));
    i++;
  }
  return hashValue;
}

//Returns the corresponding value of each letter.
public static int getLetterValue(String letter) {
  for(int i = 0; i < letters.length; i++) {
    if(letter.equals(Character.toString(letters[i]))) {
      return i;
    }
  }
  return 0;
}


}
