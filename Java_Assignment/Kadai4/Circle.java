/* * * * * * * * * * * * * * * * * * * * * * * Circle.java * * * 
 *                 Circleクラス：派生クラス                       *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

import java.io.PrintStream;

public class Circle extends Shape2D{

    private Coord2 v; /* 中心座標 */
    private double r; /* 半径 r */
    private Color c;
    private String name = "円";

    /* 引数があるのコンストラクタ */
    public Circle(Coord2 v, double r, Color c){ 
        this.r = r;/* 半径 r */
        this.v = v;/* 中心座標 */
        this.c = c;
        super.setColor(c);/* 基底クラスの setColor メソッド */
    }
    /* 面積を求める抽象メソッド */
    public double area(){
        double Area;
        Area = r * r * Math.PI; /* 円の面積を求める */

        return Area;
    }
    /* 周囲長を求める抽象メソッド */
    public double perimeter(){
        double P;
        P = 2 * r * Math.PI; /* 円の周囲長を求める */

        return P;
    }
    /* PostScriptでの円の記述 */
    public void psPrint(PrintStream cout){
        printHead(cout, name);
        cout.printf("%.3f %.3f %.3f setrgbcolor\n", c.getR(), c.getG(), c.getB());/* 円の色 */
        cout.println("newpath");
        cout.printf("%.3f %.3f %.3f 0 360 arc\n", v.getX() ,v.getY(), r); /* 円の中心と半径をPostScriptに記述 */
        cout.println("closepath");
        cout.println("stroke");
    }

}