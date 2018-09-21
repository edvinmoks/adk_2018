import java.io.*;
import java.lang.*;
import java.util.*;


public class tKonkordans{


    private static String[] letters = {" ","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","å","ä","ö"};
    public static void main(String[] args){

      try {

        String searchWord = "";
        String hashedSearchWord = "";




        if (args.length == 0) {
    			System.out.println("No input.");
    		} else {
    			//System.out.println(args[0]);
          searchWord = args[0].toLowerCase();
          hashedSearchWord = String.valueOf(hashWord(searchWord));
    		}
        // Läs in hashmapen som genererats i build.java
        Map<String, String> hm = new HashMap<String, String>();
        Properties properties = new Properties();
        properties.load(new FileInputStream("data.properties"));
        for (String key : properties.stringPropertyNames()) {
            hm.put(key, properties.get(key).toString());
          }


        String position = hm.get(hashedSearchWord); //Get the position of the search word in the indexArray.
        //System.out.println("position: " + position);
        Integer indexPosition = Integer.parseInt(position);


        ArrayList<String> ordLista = new ArrayList<String>();

        String tempLine = "";
        String line = "";


        System.out.println("indexPosition: " + indexPosition);
        RandomAccessFile raf = new RandomAccessFile(new File("sortedIndexArray.txt"), "r");
        raf.seek(indexPosition);

        String firstThreeSearchWord = searchWord.substring(0,Math.min(searchWord.length(),3));

        line = raf.readLine();
        String[] word = line.split("\\s+");
        String firstThreeCurrent = word[0].substring(0,Math.min(word[0].length(),3));
        int counter = 0;
        System.out.println("Search word: "+searchWord);
        while(firstThreeSearchWord.equals(firstThreeCurrent)){
          line = raf.readLine();
          word = line.split("\\s+");
          firstThreeCurrent = word[0].substring(0,Math.min(word[0].length(),3));
          counter += line.length()+1;
        }
        System.out.println("Last word: "+word[0]);
        System.out.println("counter: "+counter);

        int start = indexPosition;
        int end = indexPosition + counter;
        boolean found = false;
        int counter2 = 0;
        while (start <= end)
        {
            counter2++;
            int m = start + (end-start)/2;
            raf.seek(m);
            raf.readLine();
            m = (int) raf.getFilePointer();
            line = raf.readLine();
            //m = (int) raf.getFilePointer();
            //line = raf.readLine();
            word = line.split("\\s+");

            System.out.println("word: "+word[0]);
            // Check if x is present at mid
            if (word[0].equals(searchWord)){
                found = true;
                break;
            }
            // If x greater, ignore left half
            if (word[0].compareTo(searchWord) <= 0){
                start = m + 1;
                if (word[0].equals(searchWord)){
                  // Denna check behövs för att annars finns det risk att binärsöken missar att plocka ordet då de är lika.
                  found = true;//Ordet funnet!
                  break;      // GÅ UT UR WHILE SLINGAN!
                }
            }
            // If x is smaller, ignore right half
            else{
                end = m - 1;
            }
        }
        System.out.println("THE WORD: "+word[0]);
        System.out.println("bool: "+found);
        System.out.println("counter2: "+counter2);
        /*
        System.out.println("word: "+word[0]);
        word = raf.readLine().split(" ");
        System.out.println("word: "+word[0]);
        word = raf.readLine().split(" ");
        System.out.println("word: "+word[0]);
        word = raf.readLine().split(" ");
        System.out.println("word: "+word[0]);
        */


        /*

                //Läsa position, skapa mening

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

          }


        catch (IOException | NumberFormatException e){
          System.out.println("The word does not exist in database");
        }



    }    //END main

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
