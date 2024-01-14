/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * Kadai1.java * * *
 *     ファイル内の英単語の出現数をカウントし、ファイルを最後(EOF)まで                   *
 *     読み終わったら、出現した総単語数と、すべての英単語とそ の出現頻度                  *
 *     を出力するプログラム                                                        *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

import java.util.*;
import java.io.*;
import java.text.Format;/* フォーマットのため */ 
import java.text.DateFormat;/* 日付のフォーマット用 */

public class Kadai1 {

    static public int count = 0;
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    public String[] myName = {/* プリントヘッダー*/
            "**************************************", 
            "作成者:XAICHA JAIXIONG:213379", 
            "日付:",
            "入力ファイル名:",
            "**************************************"
    };

    public void myPrint(String name){/* mainの最初に呼ばれる */ 
        Date now = new Date();/* Dateクラスのオブジェクト生成 */ 
        Format fmt= /* 日付を日本語ロカールに従ってフォーマット */
            DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.LONG); 
        for (int i = 0; i < myName.length; i++){
            System.out.print(myName[i]);

            switch (i) {
                case 2: System.out.print(fmt.format(now)); break;/* 日付 */
                case 3: System.out.print(name); break;/* データファイル名 */ 
            }
            System.out.print("\n");
        }
    }

    /* addWord(): This function to devide the words
     * line [String]: This type to get the data from input.txt be one line
     * ArrayList: to get the words */
    public void addWords(String line){
        ArrayList<String> list=new ArrayList<String>();
        String token = "";
        char c;
        int count = 0;

        for (int n = 0; n < line.length(); n++){
            c = line.charAt(n);
            if (('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z')){ /* get the char that only charecter */
                token = token + c;  /* make word when c is charecter */       
            } else {
                if(token !=""){ /* input the words to the list when token not empty */
                    list.add(token);
                    token = "";
                }    
            }    
        }
        if(token != ""){ /* to get the last data of the token to list */
            list.add(token);
        }
        this.MatchedWords(list); 
    }
    /* MatchedWords(): This function to count the occurences of the words
     * list [ArrayList]: Is the list that contain words in
     */
    public static void MatchedWords(ArrayList<String> list) {
        /* hashmap to store the frequency of element */
        Map<String, Integer> map = new HashMap<String, Integer>();
        int count_words = 0;

        for (String i : list) {
            Integer j = map.get(i);
            map.put(i, (j == null) ? 1 : j + 1);
        }
  
        /* displaying the occurrence of elements in the arraylist */
        System.out.println(String.format("\t%-20s%-20s", "The Word", "Occurences"));
        System.out.println("--------------------------------------");
        for (Map.Entry<String, Integer> matchedWord : map.entrySet()) {
            Integer occurrence = matchedWord.getValue();
            System.out.println(String.format("\t%-20s%-20s", matchedWord.getKey(), occurrence.toString()));
            count_words++;
        }
        System.out.printf("\n\t総単語数は%dです\n", count_words);
        System.out.println("--------------------------------------");
    }

    public static void main(String[] args) throws IOException{ 
        ArrayList<String> list = new ArrayList<String>();
        char c;  
        String fileName = args[0];/* 第一引数 */ 

        if (args.length != 1){
            System.out.println("java(u) FileReadline [ファイル名]");
            System.exit(0);
        }

        Kadai1 fr = new Kadai1();/* 行単位の読み込み */ 

        try {
            fr.myPrint(args[0]);/* ヘッダーをプリント */
            /* BufferedReaderクラスのオブジェクト生成 */
            BufferedReader br = new BufferedReader(new FileReader(args[0])); 
            String line, lowercase, one_line = "";/* 行単位の読み込んだ文字列を保持 */

            while ((line = br.readLine()) != null){/* 行単位で処理 */
                lowercase = line.toLowerCase(); /* to change the uppercase letter to be Lowercase */
                one_line = one_line + " " + lowercase;
                
            }
            fr.addWords(one_line);

        }
        catch (Exception e){
            e.printStackTrace();/* エラー処理 :トレース*/
        }
        
    } 
}