import java.util.*;
import java.io.*;
import java.text.Format;/* フォーマットのため */
import java.text.DateFormat;/* 日付のフォーマット用 */

public class Kadai3 {
    
    public String[] myName = {/* プリントヘッダー*/
        "************************************",
        "作成者：XAICHA JAIXIONG：213379",
        "日付：",
        "入力パラメータ: ",
        "クラス継承",
        "************************************"
    };

    public void myPrint(int x, int y, int z){/* mainの最初に呼ばれる */
        Date now = new Date();/* Dateクラスのオブジェクト生成 */
        Format fmt= /* 日付を日本語ロカールに従ってフォーマット */
            DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.LONG);
        for (int i = 0 ; i < myName.length ; i++){
            System.out.print(myName[i]);
            switch (i) {
                case 2: System.out.print(fmt.format(now)); break;/* 日付 */
                case 3: System.out.print(x + " " + y + " " + z); break;/* x y zのデータ */
            }
            System.out.print('\n');
        }
    }

    public static void main(String[] args){
        int x, y, z;
        ArrayList<Fruit> vl = new ArrayList<Fruit>();/* */
        /* 入力したx, y, zの値をint型に変換する*/
        x = Integer.parseInt(args[0]);
        y = Integer.parseInt(args[1]);
        z = Integer.parseInt(args[2]);

        Kadai3 br = new Kadai3();
        Fruit fruit = null;/* 基底クラスの変数を宣言する */

        if (args.length != 3){
            System.out.println("x y z を代入してください。");
            System.exit(1);
        }
        br.myPrint(x, y, z);
        int n = 0;

        while(n < 3){
            /* 果実の種類ごと線形リストに挿入 */
            if(x >= 1&&y >=1&&z >=1){
                switch (n){/* 基底クラスの変数に、派生クラスのオブジェクトを代入する */
                    case 0: fruit = new Banana(); break;/* バナナ */
                    case 1: fruit = new Orange(); break;/*　みかん */
                    case 2: fruit = new Apple(); break;/* りんご　*/
                }
                vl.add(new Fruit(fruit, Integer.parseInt(args[n])));/* 線形リストへは、基底クラスの変数で挿入 */

            }else{
                System.out.println("x,y,zは１以上に入力してください。");
                System.exit(1);
            }
            n++;
        }
        /* ビタミンCの総量の計算と値段の計算*/
        double vitaminC = 0.0;
        int price = 0;
        for (int i = 0; i < vl.size(); i++){
            Fruit node = (Fruit)vl.get(i);
            Fruit v = node.getFruit();
            int h = node.getHowMany();
            node.print();/* 各派生クラスをプリントする */
            vitaminC += h * v.getVitaminC();/* ビタミンCの総量を計算する */
            price += h * v.getPrice(); /* 値段を計算する */  
        }
        System.out.printf("ビタミンＣ総量 = %.4f (g)\n", vitaminC);
        System.out.printf("値段 = %d (円)\n", price);
        System.out.println("----------------------------------------------");
    }
}/* Kadai3クラスのおわり */