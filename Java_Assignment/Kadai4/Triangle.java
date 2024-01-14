/* * * * * * * * * * * * * * * * * * * * * * * * Triangle.java * 
 *                                                             *
 *                三角形クラス：派生クラス                         *
 *                                                             *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

import java.io.PrintStream;

public class Triangle extends Shape2D{

    private Coord2 v1, v2, v3;/* 三頂点座標 */
    private Color c;
    private String name = "三角形";
    
    /* 引数があるのコンストラクタ */
    public Triangle(Coord2 v1, Coord2 v2, Coord2 v3, Color c){
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.c = c;
        super.setColor(c);/* 基底クラスの setColor メソッド */
    } 
    /* 頂点 v1 を返す */ 
    public Coord2 getV1(){
        return v1;
    } 
    /* 頂点 v2 を返す */ 
    public Coord2 getV2(){
        return v2;
    } 
    /* 頂点 v3 を返す */ 
    public Coord2 getV3(){
        return v3;
    } 
    /* 三角形の面積を返す関数 */
    public double area(){
        double Area, A1, A2, A3;
        A1 = v1.getX()*v2.getY() - v2.getX()*v1.getY();/* 点v1, v2のx1・y2 - x2・y1を計算 */
        A2 = v2.getX()*v3.getY() - v3.getX()*v2.getY();/* 点v2, v3のx2・y3 - x3・y2を計算 */
        A3 = v3.getX()*v1.getY() - v1.getX()*v3.getY();/* 点v3, v1のx3・y1 - x1・y3を計算 */

        Area = Math.abs((A1 + A2 + A3))/2; /* */

        return Area;
    }
    /* 三角形の周囲長を返す関数 */
    public double perimeter(){
        double t1, t2, t3, t;
        t1 = Coord2.distance(v1, v2); /* 点v1からv2のユークリッド距離 */
        t2 = Coord2.distance(v2, v3); /* 点v2からv3のユークリッド距離 */
        t3 = Coord2.distance(v3, v1); /* 点v3からv1のユークリッド距離 */

        t = t1 + t2 + t3; /* 3辺を足し算する */

        return t;
    }
    /* PostScript で出力 */
    public void psPrint(PrintStream cout){
        printHead(cout, name);/* */
        cout.printf("%.3f %.3f %.3f setrgbcolor\n", c.getR(), c.getG(), c.getB());/* 図形の色 */
        cout.println("newpath");
        cout.printf("%.3f %.3f moveto\n", v1.getX(), v1.getY());/* v1 の座標をPostScriptでの三角形の記述　*/
        cout.printf("%.3f %.3f lineto\n", v2.getX(), v2.getY());/* v1 の座標をPostScriptでの三角形の記述　*/
        cout.printf("%.3f %.3f lineto\n", v3.getX(), v3.getY());/* v1 の座標をPostScriptでの三角形の記述　*/
        cout.println("closepath");
        cout.println("stroke");


    }
}