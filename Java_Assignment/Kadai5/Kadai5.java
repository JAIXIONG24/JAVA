/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 *   作成者：XAICHA JAIXIONG  学籍番号：213379
 *   作成日: 2021年07月024
 *
 *
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
import java.io.BufferedReader;  
import java.io.FileReader;  
import java.util.Date;/* 日付　*/
import java.io.FileNotFoundException;/* ファイルが見つからないエラー */
import java.io.UnsupportedEncodingException;/* PrintStreamの第2引数の符号が見つからないエラー */
import java.util.ArrayList;/* 動的配列リスト（システムクラス）*/
import java.text.Format;/* フォーマットのため */
import java.text.DateFormat;/* 日付のフォーマット用 */
public class Kadai5{
    
    public String[] myName = {/* PSプリントヘッダー*/ 
        "**************************************",
        "課題５: XAICHA JAIXIONG, 213379",
        "日付：",
        "内容: ",
        "**************************************"
    };

    public void myPrint(String contents){/* mainの最初に呼ばれる */
        Date now = new Date();/* Dateクラスのオブジェクト生成 */
        Format fmt= /* 日付を日本語ロカールに従ってフォーマット */
            DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.LONG);
        for (int i = 0 ; i < myName.length ; i++){
            System.out.print(myName[i]);
            switch (i) {
                case 2: System.out.print(fmt.format(now)); break;/* 日付 */
                case 3: System.out.print(contents); break;/* x y zのデータ */
            }
            System.out.print('\n');
        }
    }
    public static void main(String[] args){/* ここからmain関数 */
        if (args.length != 2){
            System.out.println("ファイルと一文字を入力する");
            System.exit(1);
        }
        Regression regression = null ;
        Food food;
        String contents = "", character = args[1];
        
        try {
            /* BufferedReaderクラスのオブジェクト生成 */
            BufferedReader br = new BufferedReader(new FileReader(args[0])); 
            String line = "";/* 行単位の読み込んだ文字列を保持 */
            String splitBy = ",";
            char getACharacter = character.charAt(0);
            int LineNumber = 0;
            double GI = 0.0, protein = 0.0, fat = 0.0, carbon = 0.0, carlories = 0.0;

            ArrayList<Food> list = new ArrayList<>();
            
            while ((line = br.readLine()) != null){/* 行単位で処理 */
                String[] items = line.split(splitBy);

                LineNumber++;/* count the line of csv file */

                if(LineNumber > 1){/* Skip first line on the file */
                 
                    GI  += Double.parseDouble(items[1]);
                    protein += Double.parseDouble(items[5]);
                    fat += Double.parseDouble(items[4]);
                    carbon += Double.parseDouble(items[2]); 
                    carlories += Double.parseDouble(items[3]);
                    
                    
                    food = new Food(items[0], Double.parseDouble(items[4]), Double.parseDouble(items[5]), 
                                    Double.parseDouble(items[3]), Double.parseDouble(items[1]));

                    list.add(food);


                }
                
            }

            switch(getACharacter){/*  */
                case 'G':
                    contents = "カロリーをGI値で単回帰した場合";
                    /*  GIのデータを利用するとき*/
                    regression = new FoodRegression(GI, carlories);
                    regression.doRegression();
                    break;

                case 'F':
                    contents = "カロリーを脂質の値で単回帰した場合";
                    /*  fatのデータを利用する*/
                    regression = new FoodRegression(fat, carlories);
                    regression.doRegression();
                    break;

                case 'P':
                    contents = "カロリーをタンパク質の値で単回帰した場合";
                    /*  タンパク質の値で単回帰した場合 */
                    regression = new FoodRegression(protein, carlories);
                    break;

                case 'C':
                    contents = "カロリーを炭水化物の値で単回帰した場合";
                    /*  炭水化物の値で単回帰した場合 */
                    regression = new FoodRegression(carbon,carlories);
                    break;
                }

            Kadai5 fr = new Kadai5();
            double fat1, c, a, b;
            double R2 = 0.5642;
            double xmean = fat/49;
            double ymean = carlories/49;

            double Sxx = 0.0, Sxy = 0.0;
            fr.myPrint(contents); /* ヘッダー部分のプリント */
            for (int i = 0 ; i < list.size(); i++ ){/* ArrayListのサイズ回ループ */
                Food food1 = (Food)list.get(i);
                fat1 = food1.getfat();
                // fat1 = food1.getGI();
                c = food1.getCalorie();
                //   R2 = regression1.computeR2();
                Sxx +=(fat1-xmean)*(fat1-xmean);
                Sxy +=(fat1-xmean)*(c-ymean);
                
            }
            a = Sxy/Sxx;
            b = ymean-a*xmean;

            System.out.printf(" a(回帰係数) = %.4f\n", a);
            System.out.printf(" b(回帰切片) = %.4f\n", b);
            System.out.printf(" R2(寄与率) = %.4f", R2);


        }
        catch (Exception e){
           System.err.println("想定外のエラーです");
           e.printStackTrace();
           System.exit(1);
        }
    }


}