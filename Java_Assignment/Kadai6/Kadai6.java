/* * * * * * * * * * * * * * * * * * * * * * * * * * * * *Ball.java*
 *     作成者：XIACHA JAIXIONG : 213379 :2021/8/01
 *
 * このクラスmainクラスである。このmainクラスはBallのクラスを呼び出す
 *　　　キーボードから入力したボールの数を与えるクラスである。
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.KeyListener;/* キーボードからのイベントのリスナー */
import java.awt.event.KeyEvent;/* キーボードからのイベント */
import java.text.Format;/* フォーマットのため */
import java.text.DateFormat;/* 日付のフォーマット用 */
import java.util.Date;/* 日付　*/

public class Kadai6 {

    public static void main(String[] args) {
        if (args.length != 1){
            System.err.println("java ShapeInheritance total_of_balls");
            System.out.println("[10,30]の間の図形の総数を引数で与えてください");
            System.exit(-1);
        }

        int numBalls = Integer.valueOf(args[0]).intValue();/* 発生するボールの総数:変動可 */

        if (!(10 <= numBalls && numBalls <= 30)){
			    System.err.println("ボールは[10 ~ 30]までの間の数にしてください");
			    System.exit(1);
        }

        Date now = new Date();/* Dateクラスのオブジェクト生成 */
        Format fmt = /* 日付を日本語ロカールに従ってフォーマット */
            DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.LONG); 
        
        Ball bcf = 
        new Ball("課題6:ボール数＝"+numBalls+" XAICHA JAIXIONG：213379 日付 :"+ fmt.format(now));
        bcf.setSize(600, 600);
        bcf.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        bcf.setVisible(true);
        bcf.start();
        bcf.init();
        
        Ball[] balls = null;
        Ball[] saveBalls = null;

        balls = new Ball[numBalls];
        saveBalls = new Ball[numBalls];
        for( int i = 0; i < numBalls; i++){
            balls[i] = new Ball();
            // saveBalls[i] = new Ball();
            // balls[i].init();
            // saveBalls[i].resume();    
        }
        

        
  }
}