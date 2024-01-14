/* * * * * * * * * * * * * * * * * * * * * * * * Coord2.java* * * 
 *                   Coord2クラス：2次元座標クラス                  *
 *                                                              *
 *                                                              *
 *       点v1(x1, y1)                                           *
 *            *                                                 * 
 *              *                                               *
 *                *  距離dis(ユークリッド距離)                     *
 *                  *                                           * 
 *                    *                                         *
 *                      *                                       *
 *                        *                                     *  
 *                     点v2(x2, y2)                             *
 *                                                              *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * **/

public class Coord2 {

    private double x, y;
    
    /* Coord2の引数があるのコンストラクタ */
    public Coord2(double x, double y){
        this.x = x;
        this.y = y;
    }
    /* x 座標を返す */
    public double getX(){
        return x;     
    }
    /* y 座標を返す */
    public double getY(){
        return y;
    }
    /* 座標値をセット */
    public void setCoord2(double x, double y){
        this.x = x;
        this.y = y;
    }
    /* ユークリッド距離 */
    public static double distance(Coord2 v1, Coord2 v2){
        double dis;
        dis = Math.sqrt(Math.pow(v1.getX() - v2.getX(), 2) 
             + Math.pow(v1.getY() - v2.getY(), 2));  /* */

        return dis;
    }
}