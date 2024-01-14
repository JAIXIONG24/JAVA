import java.util.Random;/* 乱数　*/
import java.util.Date;/* 日付　*/
import java.io.PrintStream;/* 出力ストリーム */
import java.io.FileNotFoundException;/* ファイルが見つからないエラー */
import java.io.UnsupportedEncodingException;/* PrintStreamの第2引数の符号が見つからないエラー */
import java.util.ArrayList;/* 動的配列リスト（システムクラス）*/
import java.text.Format;/* フォーマットのため */
import java.text.DateFormat;/* 日付のフォーマット用 */

public class Kadai4 {
    final static double XRANGE = 600.0;/* X方向のキャンバスサイズ */
    final static double YRANGE = 800.0;/* Y方向のキャンバスサイズ */
    final static double RADIUS = 200.0;/* 半径の最大値 */
    final static double MIN = 0.0; /* 最小値 */

    public String[] myPsName = {/* PSプリントヘッダー*/
        "%%!PS-Adobe-3.0", 
        "%% 作者: XAICHA JAIXIONG：213379",
        "%% ファイル名: ",
        "%% 日付：",
    };
    public void myPsPrint(PrintStream cout, String name){/* PS出力のヘッダー */
        Date now = new Date();/* Dateクラスのオブジェクト生成 */
        Format fmt= /* 日付を日本語ロカールに従ってフォーマット */
            DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.LONG);
        for (int i = 0 ; i < myPsName.length ; i++){
             cout.print(myPsName[i]);
             switch (i){
                  case 2: cout.print(name); break;/* データファイル名 */
                  case 3: cout.print(fmt.format(now)); break; /* 日付 */
             }
             cout.print('\n');
        }
    }

    public static void main(String[] args){/* ここからmain関数 */
        if (args.length != 2){
            System.err.println("java ShapeInheritance output.ps total_shapes");
            System.out.println("[x1,x2]の間の図形の総数を引数で与えてください");
            System.exit(-1);
        }

        /* Shape2Dクラスを要素として持つ、動的な配列リストクラスを利用 */
        ArrayList<Shape2D> list = new ArrayList<Shape2D>();
        Shape2D shape = null;/* 抽象基底クラスの変数：宣言はOK */
        Coord2 v1, v2, v3, v4, v5, v6;/* 2次元座標 */
        double  Red, Green, Blue; /* r, g, bの色 */
        double radius; /* 半径　*/

        Date date = new Date();/* 現在の日付（乱数の初期値用）*/
        Random rand = new Random(date.getTime());/* 乱数クラス */
        int MinNumberFigure = 10, MaxNumberFigure = 50;
        double Max_Color = 1.0, Min_Color = 0.0;
        int n;

        try {
            n = Integer.valueOf(args[1]).intValue();/* 発生する図形総数:変動可 */
            if (!(MinNumberFigure <= n && n <= MaxNumberFigure)){
			    System.err.println("図形の総数は[10 ~ 50]までの間の数にしてください");
			    System.exit(1);
            }

            for (int j = 0 ; j < n ; j++){ 
                /* 色の設定 */
                Red = (Math.random() * ((Max_Color - Min_Color))) + Min_Color;/* 赤い色の乱数を発生 */
                Green = (Math.random() * ((Max_Color - Min_Color))) + Min_Color;/* 緑色の乱数を発生 */
                Blue = (Math.random() * ((Max_Color - Min_Color))) + Min_Color;/* 青い色の乱数を発生 */

                Color color = new Color(Red, Green, Blue);/* 色:Colorクラスのインスタンス生成*/
                int shapeIndex = rand.nextInt(3);/* returns 0 or 2 */
            
                switch ( shapeIndex ){
                    case 0:/* 三角形を生成：triangle */
                        v1 = new Coord2(
                            (Math.random() * ((XRANGE - MIN) + 1)) + MIN,/* [0.0-XRANGE]の乱数を発生 */
                            (Math.random() * ((YRANGE - MIN) + 1)) + MIN);/* [0.0-YRANGE]の乱数を発生 */
                        
                        v2 = new Coord2(
                            (Math.random() * ((XRANGE - MIN) + 1)) + MIN,/* [0.0-XRANGE]の乱数を発生 */
                            (Math.random() * ((YRANGE - MIN) + 1)) + MIN);/* [0.0-YRANGE]の乱数を発生 */
                        
                        v3 = new Coord2(
                            (Math.random() * ((XRANGE - MIN) + 1)) + MIN,/* [0.0-XRANGE]の乱数を発生 */
                            (Math.random() * ((YRANGE - MIN) + 1)) + MIN);/* [0.0-YRANGE]の乱数を発生 */
                        
                        shape = new Triangle(v1, v2, v3, color);/* 派生クラス（三角形）のインスタンス生成*/ 
                        break;
                    case 1:/* 円を生成：circle */
                        
                        radius = (Math.random() * ((RADIUS - MIN) + 1)) + MIN;/* [0.0-RADIUS]の乱数を発生 */
                        v4 = new Coord2(
                            (Math.random() * ((XRANGE - MIN) + 1)) + MIN,/* [0.0-XRANGE]の乱数を発生 */
                            (Math.random() * ((YRANGE - MIN) + 1)) + MIN);/* [0.0-YRANGE]の乱数を発生 */
                           
                        shape =  new Circle(v4, radius, color);/* 派生クラス（円）のインスタンス生成*/ 
                        break;
                    case 2:/*  長方形を生成：Rectangle */
                        v5 = new Coord2(
                            (Math.random() * ((XRANGE - MIN) + 1)) + MIN,/* [0.0-XRANGE]の乱数を発生 */
                            (Math.random() * ((YRANGE - MIN) + 1)) + MIN);/* [0.0-YRANGE]の乱数を発生 */
                
                        v6 = new Coord2(
                            (Math.random() * ((XRANGE - MIN) + 1)) + MIN,/* [0.0-XRANGE]の乱数を発生 */
                            (Math.random() * ((YRANGE - MIN) + 1)) + MIN);/* [0.0-YRANGE]の乱数を発生 */

                        shape = new Rectangle(v5, v6, color);/* 派生クラス（ 長方形）のインスタンス生成*/
                        break;
                }
                list.add(shape);/* ArrayListにShape2Dクラスのデータを追加 */    
            }

            Kadai4 shapeIn= new Kadai4();/* ShapeInheritanceクラス生成 */
            PrintStream cout = new PrintStream(args[0], "UTF-8");/* プリント出力するファイル名 */
            shapeIn.myPsPrint(cout, args[0]); /* ヘッダー部分のプリント */
            int count = 1;
            double Area = 0, perimeter = 0;
            for (int i = 0 ; i < list.size(); i++ ){/* ArrayListのサイズ回ループ */
                /* i-番目の要素を取り出す */
                cout.println( "%%" + count + "番目の図形" );
                Shape2D shape1 = (Shape2D)list.get(i);
                double A = shape1.area(); /* listの中から面積を取り出す */
                double H = shape1.perimeter(); /* listの中から周囲長を取り出す */

                Area += A;/* 総面積計算する */
                perimeter += H;  /* 総長計算する */
                shape1.psPrint(cout);
                /* 派生クラスに応じたpsPrintメソッド */

                count++;
            }
            /* このあたりで総面積、総長、図形総数などを書き出す */
            cout.printf("%% 総面積 = %.3f\n", Area);
            cout.printf("%% 総長 = %.3f\n", perimeter);
            cout.printf("%% 総面積/総長 = %.3f\n", Area/perimeter);
            cout.printf("%% 図形総数 = %d\n", n);
            cout.println( "showpage" );
        }
        catch (NumberFormatException e){
            System.err.println("引数は[x1-x2]の間の整数にしてください");
            System.exit(1);
        }
        catch (FileNotFoundException e){
            System.err.println("ファイル"+args[1]+"は、見つかりません");
            System.exit(1);
        }
        catch (UnsupportedEncodingException e){
           System.err.println("UTF-8符号はサポートされていません");
           System.exit(1);
        }
        catch (Exception e){
           System.err.println("想定外のエラーです");
           e.printStackTrace();
           System.exit(1);
        }
    }
    
}