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

        //indexPosition = (indexPosition)/2; //Adjust value for position
        //System.out.println("indexPosition: " + indexPosition);
        ArrayList<String> ordLista = new ArrayList<String>();

        String tempLine = "";
        String line2 = "";
/*
        BufferedReader reader2 = new BufferedReader(new FileReader("wordList.txt"));
        //line2 = reader2.readLine(); //remove first bad line
        while((line2 = reader2.readLine()) != null ){
          //tempLine = line2.split(" ");
          ordLista.add(line2);
        }apa.length()
        System.out.println(ordLista);
        //System.out.println(ordLista.get(indexPosition));
        reader2.close();

        //Läs in indexArray skapad av tokenizer.c
        BufferedReader reader = new BufferedReader(new FileReader("sortedIndexArray.txt"));
        //Läs in wordList = ord - antal ord


        String line = "";
        for (int i = 0; i < indexPosition+1; i++){

          line = reader.readLine();
          //System.out.println("i: "+i);
          //System.out.println("line: "+line);

        }

        //String[] theLine = line.split(" ");
        //String korpusIndex = theLine[1];
        //System.out.println("line: "+line);
        reader.close();
*/      String apa = "";
        System.out.println("indexPosition: " + indexPosition);
        RandomAccessFile raf = new RandomAccessFile(new File("sortedIndexArray.txt"), "r");
        raf.seek(indexPosition);
        apa = raf.readLine();
/*
        while(apa.length() < 1){
          System.out.println("loop!");
          indexPosition++;
          raf.seek(indexPosition);
          apa = raf.readLine();
        }
*/
        System.out.println("apa: "+apa);
        //System.out.println("apa length2: "+apa.length());
        //System.out.println("apa: "+apa);


/*
        BufferedReader reader2 = new BufferedReader(new FileReader("wordList.txt"));
        line = reader2.readLine();
        System.out.println("wordList: " + line);
        line = reader2.readLine();
        System.out.println("wordList: " + line);
        line = reader2.readLine();
        System.out.println("wordList: " + line);
        line = reader2.readLine();
        System.out.println("wordList: " + line);
*/








/*
        //Läsa position, skapa mening
        RandomAccessFile raf = new RandomAcscessFile("test.txt", "r");
        raf.seek(0);
        byte[] bytes = new byte[1];
        raf.read(bytes);
        raf.close();
        System.out.println(new String(bytes));

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



/*
         Map.Entry<String,String> entry = hm.entrySet().iterator().next();
         String key = entry.getKey();
         String value = entry.getValue();
         System.out.println(key);
         System.out.println(value);
         */
         //String searchWordHash = String.valueOf(hashWord(searchWord));
         //System.out.println(searchWordHash);


        //BufferedReader buf = new BufferedReader(new FileReader("tempdata.txt"));
        /*
        BufferedReader reader = new BufferedReader(new FileReader("ut.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("hashtable.txt")));

        StringBuilder builder = new StringBuilder();

        String line = reader.readLine();
        String[] firstLine = line.split(" ");
        String word = firstLine[0];
        int indexCounter = 0;

        String tempLine = word+" "+Integer.toString(indexCounter)+"\n";
        System.out.println(tempLine);
        writer.write(word+" "+Integer.toString(indexCounter));


                reader.close();
                writer.flush();
                writer.close();

*/


        //HashMap<String,Integer> hm = new HashMap<String,Integer>();
/*
        while(line != null){
          indexCounter++;
          String[] tempWord = line.split(" ");
          String firstThree = tempWord[0].substring(0,Math.min(tempWord[0].length(),3));
          if(!firstThree.equals(word)){ //kollar om ord är annorlunda från den andra, isåfall lägg in i hashtabell
            //x=hashfunktion(firstthree).
            tempLine = firstThree+" "+Integer.toString(indexCounter)+"\n"; //format för ABC - INDEX, newline
            hm.put(firstThree,indexCounter);
            //.seek(x)
            writer.write(tempLine); // skriver till filen
            word = firstThree;
          }
          line = reader.readLine();

        }
        */
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
        String line2;
        int counter2 = 0;
        String[] tempWord2;
        while((line2 = reader2.readLine()) != null ){
          tempWord2 = line2.split(" ");
          tokenizer.add(tempWord2[1]);

        }
        System.out.println("tokenizer: "+tokenizer.get(0));
        reader2.close();
*/







          }


        catch (IOException | NumberFormatException e){
          System.out.println("The word does not exist in database");
        }



    }

    //END main

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
/*
    public int binarySearch(String arr[], String x)
    {
        int l = 0, r = arr.length - 1;
        while (l <= r)
        {
            int m = l + (r-l)/2;

            // Check if x is present at mid
            if (arr[m] == x)
                return m;

            // If x greater, ignore left half
            if (arr[m] < x)
                l = m + 1;

            // If x is smaller, ignore right half
            else
                r = m - 1;
        }

        // if we reach here, then element was
        // not present
        return -1;
    }

*/
}

/*
public class HashTable{

  public static void HashFile(){


    BufferedReader buf = new BufferedReader(new FileReader("tempdata.txt"));
    String line = buf.readLine();
    BufferedWriter writer = new BufferedWriter(new FileWriter("hashtable.txt"));
    String[] firstLine = line.split(" ");

    int indexCounter = 1;
    writer.write(firstLine[0]+" "+Integer.toString(indexCounter));
    while(buf.readline != null){


    }



      }
*/
