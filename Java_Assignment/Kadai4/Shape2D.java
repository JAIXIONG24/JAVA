/* * * * * * * * * * * * * * * * * * * * * * * * Shape2D.java* * * 
 *                   Shape2Dクラス：抽象クラス                      *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
 
import java.io.PrintStream;

public abstract class Shape2D {

    private Color color; /* Color クラスは後述 */
    private String name;

    abstract double area(); /* 面積を計算する抽象関数 */
    abstract double perimeter(); /* 周囲長を計算する抽象関数 */
    abstract void psPrint(PrintStream cout);/* プリント関数 */
    
    /* 戻り値として Color 変数を返す関数 */
    public Color getColor(){ 
        return color;
    }
    /*Color 変数に値をセットする関数 */
    public void setColor(Color c){ 
        this.color = c;
    }
    /* 形状の名前を返す：抽象関数ではない */
    public String getName( String name){ 
        return name; 
    }
    /* PostScriptヘッダーのプリント */
    void printHead(PrintStream cout, String name){
        cout.printf( "%% %s 面積 = %5.3f\n", getName(name), area());
        cout.printf( "%% %s 周囲長 = %5.3f\n", getName(name), perimeter());
     }
}