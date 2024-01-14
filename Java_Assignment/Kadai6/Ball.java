/* * * * * * * * * * * * * * * * * * * * * * * * * * * * *Ball.java*
 *     作成者：XIACHA JAIXIONG : 213379 :2021/8/01
 *
 * このクラスはボールを作成するとボールをアニメションするためのクラスである。
 *　関数としては、quit(), keypressed(),init(), paint(), move(), run(),
 *      start(), stop(), resume()などの関数がある。
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.KeyListener;/* キーボードからのイベントのリスナー */
import java.awt.event.KeyEvent;/* キーボードからのイベント */
import java.util.Random;/* 乱数　*/
import java.util.Date;/* 日付　*/

public class Ball extends JFrame implements Runnable, KeyListener {
    Date date = new Date();/* 現在の日付（乱数の初期値用）*/
    Random rand = new Random();/* 乱数クラス */
    int dx = rand.nextInt(81) - 40;  // 円の速度ベクトル
    int dy = rand.nextInt(81) - 40;  // 円の速度ベクトル
    int x = rand.nextInt(400);
    int y = rand.nextInt(400);
    int lastx = x ;
    int lasty = y ;
    int r = rand.nextInt(80);  // 初期位置と円の半径
    int saveX, saveY;             // 現在位置のセーブ用
        
    int saveDx, saveDy;           // 現在の進行方向ベクトルのセーブ用
    final static int sleepTime = 20;           // 休止時間（ミリ秒）
   // オフスクリーン画像用のデータ
    Thread thread = null;/* スレッド用データ */
    Thread saveThread = null;/* セーブスレッド用データ */
    Image offScreenImage = null;/* 前景オフスクリーン用データ */
    Image backGroundImage = null;/* 背景オフスクリーン用データ */
    Graphics backG = null;/* 背景グラフィクス */
    Graphics offG = null;/* 前景グラフィクス */
    Graphics saveOffG = null;/* 前景グラフィクスのセーブ（再開で使用） */
    Graphics2D offG2 = null;/* 前景グラフィクスGraphics2D */
    private int w; // 横幅
    private int h; // 縦幅
    java.awt.Color backColor = new java.awt.Color(1.0f,1.0f,1.0f);/*背景色 */
    float R = rand.nextFloat();/* 赤色 [0.0,1.0] */
    float G = rand.nextFloat();/* 緑色 [0.0,1.0] */
    float B = rand.nextFloat();/* 青色 [0.0,1.0] */
    java.awt.Color ballColor = new java.awt.Color(R,G,B);/*ボールの色；赤色 */
    java.awt.Color ballColor2 = new java.awt.Color(Math.max((int)(1.25*R),255),
        Math.max((int)(1.25*G),255),
        Math.max((int)(1.25*B),255));/*ボールの色2 */
    public Ball(){ super();}
    public Ball(String name){ super(name);}
    
    public void quit(){ System.exit(0); }

    /* 以下の３つの関数はキーボードのイベントを取得するために宣言が必要 */
    public void keyPressed(KeyEvent e){/* キーが押された時のイベント */
      if (e.getKeyCode()==KeyEvent.VK_ESCAPE) quit();/* VK_ESCAPEはキーボードのEscapeボタン */
      else if (e.getKeyCode()==KeyEvent.VK_S) {/* ストップ */
        stop();
      }
      else if (e.getKeyCode()==KeyEvent.VK_R) {/* Rキー：リズーム（再開） */
        resume();
      }
    }

    public void keyReleased(KeyEvent e){}/* キーがリリースされたとき */
    public void keyTyped(KeyEvent e){}/* キーがタイプされたとき */

    public void init(){
      Dimension d = getSize();// ウィンドウサイズ獲得
      w = d.width;//横幅セット
      h = d.height;//縦幅セット
      // オフスクリーン（前面）画像用のデータ領域
      offScreenImage = createImage(w,h);//前面オフスクリーン領域生成
      offG = saveOffG = offScreenImage.getGraphics();//前面オフスクリーン描画用オブジェクト獲得
      offG2 = (Graphics2D) offG;
      // オフスクリーン（背景）画像用のデータ領域
      backGroundImage = createImage(w,h);//後面オフスクリーン領域生成
      backG = backGroundImage.getGraphics();//後面オフスクリーン描画用オブジェクト獲得
      backG.setColor(backColor);//背景は白色
      backG.fillRect(0,0,w,h);//背景を白で塗りつぶし
      addKeyListener(this);/* キーボードのリスナーを設定 */
    }

    public void paint(Graphics g) {
        /* キャンバスが準備できていない場合は何もしない */
        if (offG == null || backGroundImage == null) return;
        // まず背景オフスクリーン画像を描画
        offG.drawImage(backGroundImage,0,0,this);
        java.awt.Color centerColor = ballColor;
        java.awt.Color color2 = ballColor2;
        Point2D center = new Point2D.Double(x, y);/* ボールの中央位置 */
        float radius = r;/* 半径 */
        float[] dist = {0.15f, 0.8f};/* 0.15(color2), 0.8(centerColor) [0.15-0.8]を補間*/
        Color[] colors = {color2, centerColor};/* 補間のための2色 */
        RadialGradientPaint rgp = new RadialGradientPaint(center, radius, dist, colors);
        offG2.setPaint(rgp);/* ボールの色塗り設定 (Graphics2Dクラス(offG2)が必要）*/
        offG.fillOval(x-r, y-r, r*2, r*2);/* ボールの描画（前景）*/
        g.drawImage(offScreenImage,0,0,this);
            
    }
    public void animate() {/* ボールの位置変更 */
      if (thread != null){
        // ウィンドウの端で速度ベクトルを反転 
        java.awt.Rectangle bounds = getBounds();
        if ((x - r + dx < 0) || (x + r + dx > bounds.width)) dx = -dx; //画面の左右の壁にぶつかったら x 方向を変更
        if ((y - r + dy < 0) || (y + r + dy > bounds.height)) dy = -dy; //画面の上下の壁にぶつかったら y 方向を変更

        x = lastx + dx;//現在の x 位置
        y = lasty + dy;//現在の y 位置

        lastx = x;
        lasty = y;

        repaint();// paint()メソッドを呼び出す（repaint()で間接的に）
      }
    }

    /**
     * This method is from the Runnable interface.  It is the body of the 
     * thread that performs the animation.  The thread itself is created 
     * and started in the start() method.
     **/
    public void run() {
        while (true){
            animate();//描画位置更新
            try {
                Thread.sleep(sleepTime);// 待つ
            }
            catch (InterruptedException e){}//割り込みなし
        }
    }

    /** Start animating when the browser starts the applet */
    public void start() {
        if (thread == null){
            thread = saveThread = new Thread(this);//スレッド生成
            thread.start();//スレッド開始
        }
    }

    /** Stop animating when the browser stops the applet */
    /*
    public void saveBalls() {
    */
    public void stop() {
        if (thread != null){
	        saveX = x; saveY = y;/* 現在位置をセーブ */
	        saveDx = dx; saveDy = dy;/* 現在の方向をセーブ */
          offG = null;/* thread.stop()は非推奨なのでpaintを空回りさせ動きをストップさせる */
          thread = null;
        }
    }
    // public void saveBalls(){
    public void resume(){/* スレッド再開 */
	    if (offG == null && thread == null){
		    offG = saveOffG;/* セーブしていたオフスクリーングラフィックスを戻す*/
		    thread = saveThread;/* セーブしていたスレッドを戻す */
		    x = saveX; y = saveY;/* セーブしていた位置を戻す */
		    dx = saveDx; dy = saveDy;/* セーブしていたベクトルを戻す */
	    }
    }
}