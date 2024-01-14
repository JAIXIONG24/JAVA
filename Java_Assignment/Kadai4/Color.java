/* * * * * * * * * * * * * * * * * * * * * * * * Color.java* * * 
 *                        Colorクラス                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

public class Color {

    private double r, g, b;//r(赤), g (緑), b(青), 0.0<=r,g,b <=1.0

    /* Colorの引数があるのコンストラクタ */
    public Color(double r, double g, double b){
        this.r = r;
        this.g = g;
        this.b = b;
    }
    /* 赤色を返す関数 */
    public double getR(){
       return r;
    }
    /* 緑色を返す関数 */
    public double getG(){
        return g;
    }
    /* 青色を返す関数 */
    public double getB(){
        return b;
    }

}