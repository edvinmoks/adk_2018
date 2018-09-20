import java.io.*;
import java.lang.*;
import java.util.*;

public class tBuild{



    static String[] letters = {" ","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","å","ä","ö"};
    public static void main(String[] args){

      try {







      //BufferedReader buf = new BufferedReader(new FileReader("tempdata.txt"));
      /*
      String testw = "ad";
      String testa = "ade";
      String testa2 = "adekvat";
      String testa3 = "adekvata";
      int testaintw = hashWord(testw);
      int testaint = hashWord(testa);
      int testaint2 = hashWord(testa);
      int testaint3 = hashWord(testa);
      System.out.println(testaintw);
      System.out.println(testaint);
      System.out.println(testaint2);
      System.out.println(testaint3);
*/
      File file = new File("sortedIndexArray.txt");

      BufferedReader reader  = new BufferedReader(
      new InputStreamReader(new FileInputStream(file),"ISO-8859-1"));

      //BufferedReader reader = new BufferedReader(new FileReader("test.txt"));
      // sortedIndexArray
      //BufferedWriter writerWordList = new BufferedWriter(new FileWriter(new File("wordList.txt")));

/*
      RandomAccessFile raf = new RandomAccessFile(new File("sortedIndexArray.txt"), "r");
      raf.seek(11);
      String apa = raf.readLine();
      System.out.println("apa: " + apa);
*/
      //ArrayList<String> wordList = new ArrayList<String>();
      //ArrayList<String> positionList = new ArrayList<String>();
      HashMap<String,String> hm = new HashMap<String,String>();
      Properties properties = new Properties();

      StringBuilder stringBuilder = new StringBuilder();

            //System.out.println("line: " + line);

          /*
      long indexCounter = 0;
      long wordListCount = 0;
      long sameFirstThreeCount = 0;
      long positionListCount = 0;

      String tempLine = "";
      String prevWord = "";
*/
      BufferedWriter wordList = new BufferedWriter(new FileWriter(new File("wordList.txt")));
      BufferedWriter positionList = new BufferedWriter(new FileWriter(new File("positionList.txt")));

      int positionIndex = 0;
      int wordCounter = 0;
      long indexCounter = 0;


      String in = reader.readLine(); //Reads first line from arrayIndex

      String[] current = in.split("\\s+");
      String firstThreeCurrent = current[0].substring(0,Math.min(current[0].length(),3)); //Get first three letters of the first word
      hm.put(String.valueOf(hashWord(firstThreeCurrent)),String.valueOf(indexCounter)); //Add the first word and the correct position to the hashMap
      indexCounter += in.length()+1; //add the length of the first line to the indexCounter
      in = reader.readLine();
      String[] next = in.split("\\s+");
      //System.out.println("current: " + current[0]);
      //System.out.println("next: " + next[0]);
      String firstThreeNext = "";


      //indexCounter += in2.length()+1;
      //System.out.println("indexCounter: "+ indexCounter);
try{
      while(in != null){
        wordList.write(current[0]+","+ positionIndex);
        positionList.write(current[1]);
        positionIndex += current[1].length();
        wordCounter = 1;
        //System.out.println("word: " + word[0]);
        //System.out.println("indexCounter tBuild: "+ indexCounter);
        while(current[0].equals(next[0])){ //Checks if current word is the same as the previous word
          indexCounter += in.length()+1;
          positionList.write(","+next[1]);
          positionIndex += next[1].length() + 1; //Lägger till längden av numret till postionsListCounter (+1 för komma)
          wordCounter++;

          //System.out.println("current: "+ current[0]);
          //System.out.println("next: "+ next[0]);
          //System.out.println("in: "+ in);
          /*

          System.out.println("in length: "+ in.length());

          */
          //  System.out.println("indexCounter: "+ indexCounter);
          //System.out.println("indexCounter tBuild: "+ indexCounter);
          in = reader.readLine();
          //System.out.println("in size: "+ in.length());
          next = in.split("\\s+");
          //System.out.println("next word: "+next[0]+" next word size: "+ next[0].length()+" next number size: "+next[1].length());
          //System.out.println("next size: "+ next[0].length()+next[1].length());

          //System.out.println("indexCounter inside: "+ indexCounter);
          //System.out.println("current: "+ current[0]);
          //System.out.println("next: "+ next[0]);
        }
        //System.out.println("indexCounter: "+ indexCounter);
        firstThreeNext = next[0].substring(0,Math.min(next[0].length(),3)); //Get first three letters of the word
        firstThreeCurrent = current[0].substring(0,Math.min(current[0].length(),3)); //Get first three letters of the word
        //System.out.println("firstThreeNext: "+ firstThreeNext);
        //System.out.println("indexCounter outside: "+ indexCounter);

        if(hashWord(firstThreeNext) != hashWord(firstThreeCurrent)){ //kollar om ord är annorlunda från den andra, isåfall lägg in i hashtabell

          hm.put(String.valueOf(hashWord(firstThreeNext)),String.valueOf(indexCounter)); //Lägger till unika ord

        }

        positionList.write("\n");
        //System.out.println("wordList: "+ wordList);

    //System.out.println("positionList: "+ positionList);

    //System.out.println("indexCounter: "+ indexCounter);
      positionList.flush();
      positionIndex++; //For the new line

      wordList.write(","+wordCounter+"\n");
      wordList.flush();
      indexCounter += in.length()+1;
      current = next;
      in = reader.readLine();
      if(in==null) {
          break;
      }
      next = in.split("\\s+");

    } // END WHILE
}
catch(NullPointerException ex){
  System.out.println("Exception!: "+"\n"+"current: "+current[0]+"\n"+"next: "+next[0]);

  //System.out.println("positionList: "+ positionList);



}
  //System.out.println("indexCounter: "+ indexCounter);

    //System.out.println("wordList: "+wordList);
            //wordList.add(word[0] + " " + wordCount);
            //String tempLine = word+" "+Integer.toString(indexCounter)+"\n";
    //positions.add(builder.toString());
    //System.out.println("first pos: "+positions);

    //System.out.println("second pos: "+positions);
    //wordCount = 0;
    //wordList.add(word[0] + " " + wordCount);

      reader.close();
      wordList.close();
      positionList.close();

      /*
      writerWordList.flush();
      writerWordList.close();
      BufferedWriter positionList = new BufferedWriter(new FileWriter(new File("positionList.txt")));
      builder = new StringBuilder();
      builder.append(",");		// Add a comma to our stringbuilder...
      builder.append("1");	// And the new position it's found at.
      builder.append(",");
      builder.append("2");
      builder.append(",");
      builder.append("3");
      positions.add(builder.toString());
      builder = new StringBuilder();
      builder.append(",");		// Add a comma to our stringbuilder...
      builder.append("4");	// And the new position it's found at.
      builder.append(",");
      builder.append("5");
      builder.append(",");
      builder.append("6");test
      positions.add(builder.toString());
      System.out.println("positions array: "+positions);
      for (String record: positions) {
        positionList.write(record);
        positionList.write("\n");
   }
    positionList.close();
*/

      for (HashMap.Entry<String,String> entry : hm.entrySet()) {

          properties.put(entry.getKey(), entry.getValue());
          }

      properties.store(new FileOutputStream("data.properties"), null);
/*
      indexCounter= 0;
      long st = System.currentTimeMillis();

      String fw = reader.readLine();
      String[] fw1 = fw.split(" ");
      String fw2 = fw1[0];
      hm.put(fw2,indexCounter);
      while((line = reader.readLine()) != null ){
        indexCounter++;
        String[] tempWord = line.split(" ");
        String firstThree = tempWord[0].substring(0,Math.min(tempWord[0].length(),3));
        if(!firstThree.equals(fw2)){

          hm.put(firstThree,indexCounter);
          fw2= firstThree;}



      }
      long et = System.currentTimeMillis();
      System.out.println(et-st);

*/

/*

      //Skapar en array av alla index i filen L;
      ArrayList<String> tokenizer = new ArrayList<String>();
      BufferedReader reader2 = new BufferedReader(new FileReader("ut.txt"));
      //BufferedWriter writer2 = new BufferedWriter(new FileWriter(new File("indexArray.txt")));
      String line2;
      int counter2 = 0;
      String[] tempWord2;
      while((line2 = reader2.readLine()) != null ){
        tempWord2 = line2.split(" ");

        tokenizer.add(tempWord2[1]);

      }
      //System.out.println("tokenizer: "+tokenizer.get(0));



      reader2.close();

*/



      //Läsa position, skapa mening
/*
      RandomAccessFile raf = new RandomAccessFile("/info/adk18/labb1/korpus", "r");
      int tf = Integer.parseInt(tokenizer.get(0));
      raf.seek(tf-15);
      byte[] tp = {raf.readByte()};
      String bs = new String(tp,"ISO-8859-1");
      for(int i=0; i <=30; i++){
        byte[] tpa = {raf.readByte()};
        bs += new String(tpa,"ISO-8859-1");

      };
      System.out.println(bs);
*/

      //Write hashMap to file








    }
      catch (IOException e){
                System.out.println("there was an error");

  }



}

//END MAIN
public static int hashWord(String word) {
  int i = 0;
  int hashValue = 0;
  while(i < word.length() && i < 3) {
    hashValue += Math.pow(30,2-i) * getLetterValue(word.substring(i,i+1));
    i++;
  }
  return hashValue;
}

/**
* Returns the corresponding value to each letter.
*/
public static int getLetterValue(String letter) {
  for(int i = 0; i < letters.length; i++) {
    if(letter.equals(letters[i])) {
      return i;
    }
  }
  return 0;
}


}
