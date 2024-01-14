/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 *   作成者：XAICHA JAIXIONG  学籍番号：213379
 *   作成日: 2021年07月024
 *このクラスはRegressionの継承のクラスである。このクラスは, xmean, ymean, a, b, R2、predictedを
 *計算するクラスである
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

public class FoodRegression extends Regression {
    
    /* 低数があるのコンストラクタ */
    public FoodRegression(double variables, double values){
        super(variables, values);   
         
    }
    /* 説明変数の平均値と目的変数の平均値の計算する関数*/
    public void compMean(){
        xmean = variables/samples;
        ymean = values/samples;

    }
    
    /* 単回帰を計算しpredicted, a, b, R2を セットする関数 */
    public void doRegression(){
        double Sxx = 0.0, Sxy = 0.0;
        Food food = new Food();
        
        Sxx += (food.getfat()- this.xmean)*(food.getfat() - this.xmean);
        // Sxx += (food.getProtein()- xmean)*(food.getProtein() - xmean);

        Sxy += (food.getfat()- this.xmean)*(food.getfat() - this.ymean);
        // Sxy += (food.getProtein()- xmean)*(food.getProtein() - ymean);

        
        a = Sxx/Sxy;
        b = ymean - a*xmean;

        // System.out.println(a +  ": " + b);   
    }

    
    

}