/* * * * * * * * * * * * * * * * * * * * * * * * *
 *      MxN 行列を表す Matrixクラスを作成            *
 *              Matrix.java                      *
 * * * * * * * * * * * * * * * * * * * * * * * * */

import java.util.*;
import java.io.*;
import java.text.Format;/* フォーマットのため */
import java.text.DateFormat;/* 日付のフォーマット用 */

public class Matrix {
    private double[][] m;/* 行列m */
    private int row, col;/* 行列のrowは行とcolは列 */
    
    public Matrix(){ /*　無引数のコンストラクタ　*/
        row = 0;
        col = 0;
    }

    public Matrix(int M, int N){/* 行と列を引数で与える2引数のコンストラクタ */
        this.row = M;
        this.col = N;
        m = new double[M][N]; /* 行列mのサイズMxNを コンストラクタに作成して置く*/
    }
    
    public String[] myName = {/* プリントヘッダー*/
        "*****************************************",
        "作成者：XAICHA JAIXIONG：213379",
        "日付：",
        "入力ファイル名 1：",
        "入力ファイル名 2：",
        "ファイルから行列を読み込み行列クラスの積計算",
      "*******************************************"
    };

    public void myPrint(String fileNameA, String fileNameB){/* mainの最初に呼ばれる */
        Date now = new Date();/* Dateクラスのオブジェクト生成 */
        Format fmt= /* 日付を日本語ロカールに従ってフォーマット */
            DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.LONG);
        for (int i = 0 ; i < myName.length ; i++){
            System.out.print(myName[i]);
            switch (i) {
                case 2: System.out.print(fmt.format(now)); break;/* 日付 */
                case 3: System.out.print(fileNameA); break;/* データファイル名1 */
                case 4: System.out.print(fileNameB); break;/* データファイル名2 */
            }
            System.out.print('\n');
        }
    }

    public int getRow(){ /* この関数は行サイズを求める関数 */

        return row;
    }

    public int getColumn(){ /* この関数は列サイズを求める関数 */
        return col;

    }
    
    public Matrix read(String filename){/* この関数はtxtファイルを読み込む関数 */
        int a, c =0;
        double b;
        Matrix X = new Matrix(3, 4);
        try{
            FileReader fr = new FileReader(filename);/* FileReaderクラスのオブジェクト生成 */
            Scanner br = new Scanner(fr);
            int i = 0, j=0, n;
            while (br.hasNextLine()){/* 行単位で処理 */
                if (br.hasNextInt()){/* ファイルのデータがint型の時*/
                    a = br.nextInt();
                    c++;
                    if(c > 0){
                        row = a;  //行になる 
                    } else {
                        col = a;//列になる
                    }
                        
                } else if (br.hasNextDouble()){/* ファイルのデータがdouble型のとき*/
                    b = br.nextDouble();
                    j++;
                    if(j-1 < 4 ){
                        X.m[i][j-1] = b;/* ファイルのデータを行列 X に書き込む */

                    } else {
                        j = 1;
                        i++;
                        X.m[i][j-1] = b;/* ファイルのデータを行列 X に書き込む */
                    }

                } else {/* ファイルのデータが実数と整数以外のときプログラムを終了する */
                        System.out.println("フォーマットエラー");/* */
                        System.exit(1);

                }
    
            }
            br.close(); /* ファイルを閉じる */
            col = 3;
            System.out.println("サイズ: (" + row + ", " + col + ")");
            for (i = 0; i < 3; i++){
                System.out.print("| ");
                for (j = 0; j < 4; j++){
                    System.out.printf("%1.6f", X.m[i][j]); /* 行列を値をプリントする */
                    System.out.print(" ");
                }
                System.out.print("|\n");
            }

        }catch(Exception e){
            e.printStackTrace();/* エラー処理 :トレース*/
        }

        return X ;

    }

    public Matrix multiply( Matrix B){/*積行列の関数multiply() C = A・B*/
        Matrix A = this;
        Matrix C = new Matrix(A.row, B.col);/*行列Cを作成する */
        /*
        System.out.println("サイズ: (" + A.row + ", " + B.col + ")");
        if((A.row >= 3&&A.row<=10) && (B.col >= 3&&B.col<=10)){// 3<=M, K, N<= 10の条件をif文にする
            if(A.row != B.col ){ //M,N,Kの値のどれか一つは他と異なる値とすること
                if(A.col == B.row){//(ここでは K としている)が一致する。
                    for (int i = 0; i < C.row; i++){
                        for (int j = 0; j < C.col; j++){
                            for (int k = 0; k < A.col; k++){
                                C.m[i][j] += (A.m[i][k] * B.m[k][j]);
                            }
                        }
                    }
                }else{//Kの値が一致しない場合はプログラムを終了する
                    System.out.println("Kの値が一致しない");
                    System.exit(1);
                }
            }
        }
        */
        return C;
    }

    public void print(){ /* 配列 MxN 行列の中身の要素をプリントする関数 */
    /*
        for (int i = 0; i < A.row; i++){
            for (int j = 0; j < B.col; j++){
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
        */

    }
  
    public static void main(String[] args){
       
        if (args.length != 2){
            System.out.println("行列データファイルは 2 つ与えてください");
            System.exit(1);
        }

        Matrix x = new Matrix();/*クラスのオブジェクトのインスタンスを作る */

        x.myPrint(args[0], args[1]);/* ヘッダーをプリント */

        System.out.print("入力行列 A, ");
        Matrix A = x.read(args[0]); /* 第一引数 */

        System.out.print("入力行列 B, ");
        Matrix B = x.read(args[1]); /* 第二引数 */

        System.out.print("出力の積行列 C=A x B, ");
        Matrix C = A.multiply(B);/* 出力の積行列 */
        // C.print();       
        
    }
}