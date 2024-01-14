/* * * * * * * * * * * * * * * * * * * * * * * Rectangle.java* * 
 *                  長方形クラス：派生クラス                       *
 *                                                             *
 *                この長方形は以下のような点から作った               *
 *                                                             *
 *                v1 **************** v3(Xv2, Yv1)             *
 *                   *              *                          *
 *                   *              *                          *
 *     V4(Xv1, Yv2)  **************** v2                       *
 *                                                             *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

import java.io.PrintStream;

public class Rectangle extends Shape2D{
    private Coord2 v1, v2;
    private Color c;
    private String name = "長方形";

    /* 引数があるのコンストラクタ */
    public Rectangle(Coord2 v1, Coord2 v2, Color c){
        this.c = c;
        this.v1 = v1;
        this.v2 = v2;
        super.setColor(c);/* 基底クラスの setColor メソッド */
    }
    /* 面積を求める抽象メソッド */
    public double area(){
        double Area, A1, A2;
        A1 = Math.sqrt(Math.pow(v1.getX() - v2.getX(), 2) 
             + Math.pow(v1.getY() - v1.getY(), 2));  /* 横の辺の長さを求める */
        A2 = Math.sqrt(Math.pow(v1.getX() - v1.getX(), 2) 
             + Math.pow(v1.getY() - v2.getY(), 2));  /* 縦の辺の長さを求める */
        
        Area = A1 * A2; /* 面積　*/

        return Area;
    }
    /* 周囲長を求める抽象メソッド */
    public double perimeter(){
        double t, t1, t2;
        t1 = Math.sqrt(Math.pow(v1.getX() - v2.getX(), 2) 
             + Math.pow(v1.getY() - v1.getY(), 2));  /* 横の辺の長さを求める */
        t2 = Math.sqrt(Math.pow(v1.getX() - v1.getX(), 2) 
             + Math.pow(v1.getY() - v2.getY(), 2));  /* 縦の辺の長さを求める */
            
        t = 2*t1 + 2*t2; /* 周囲長 */

        return t;
    }
    /* PostScriptでの長方形の記述 */
    public void psPrint(PrintStream cout){
        printHead(cout, name);
        cout.printf("%.3f %.3f %.3f setrgbcolor\n", c.getR(), c.getG(), c.getB());/* 図形の色 */
        cout.println("newpath");
        cout.printf("%.3f %.3f moveto\n", v1.getX(), v1.getY()); /* v1 の座標をPostScriptでの長方形の記述　*/
        cout.printf("%.3f %.3f lineto\n", v2.getX(), v1.getY()); /* v3 の座標をPostScriptでの長方形の記述　*/
        cout.printf("%.3f %.3f lineto\n", v2.getX(), v2.getY()); /* v2 の座標をPostScriptでの長方形の記述　*/
        cout.printf("%.3f %.3f lineto\n", v1.getX(), v2.getY()); /* v4 の座標をPostScriptでの長方形の記述　*/
        cout.println("closepath");
        cout.println("stroke");
    }

}/* プログラムを終わり */