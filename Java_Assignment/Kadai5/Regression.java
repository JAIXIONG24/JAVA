/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 *   作成者：XAICHA JAIXIONG  学籍番号：213379
 *   作成日: 2021年07月024
 * このクラスは抽象回帰クラスである。 xmean, ymean, a, b, R2などを計算する
 *関数を設定のクラスである。
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
 
public abstract class Regression {
    protected double a; /*係数 */
    protected double b; /* 係数*/
    protected double R2; /* 寄与率 */
    protected double xmean ; /* 説明変数の平均値(計算用) */
    protected double ymean; /* 目的変数の平均値(計算用) */
    protected int samples = 0; /* データのサンプル数 */
    /* 自由変数 */
    protected double variables;/* 説明変数(計算対象説明変数のみ) */
    protected double values;/* 目的変数(本課題ではカロリー) */
    protected int predicted;/* 目的変数の予測値(サンプル数個)*/

    /*  引数があるのコンストラクタ */
    public Regression(double variables, double values){
        this.variables = variables;
        this.values = values; 
        samples = 49;
        a = 0.0;
        b = 0.0;
        R2 = 0.0;
        xmean = 0.0;
        ymean = 0.0;
    }
    public abstract void compMean(); /* 単回帰を計算し xmean, ymeanを計算する*/
    public abstract void doRegression();/* 単回帰を計算しpredicted, a, b, R2を セットする */

    /* */
    public  double computeR2(){
        return R2;
     }
    /* */
    public double getA(){
        return a;

    }
    /* */
    public double getB(){
        return b;
    }
    

}