/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 *   作成者：XAICHA JAIXIONG  学籍番号：213379
 *   作成日: 2021年07月024
 *このクラスは、foodのファイルのデータを処理するクラスである。
 *
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

public class Food {
    private String name;/* 食べ物の名前 */
    private double fat;/* 脂質(含有量) */
    private double protein; /*  タンパク質(含有量) */
    private double calorie;/*  カロリー */
    private double gi;

    /*  引数がないのコンストラクタ */
    public Food(){
    
    }
    /*  引数があるのコンストラクタ　*/
    public Food(String name, double fat, double protein, double calorie, double gi){
        this.name = name;
        this.fat = fat;
        this.protein = protein;
        this.calorie = calorie;
        this.gi = gi;
    }
    /* getname関数*/
    public String getName(){
        return name;
    }
    /*  getfat関数 */
    public double getfat(){
        return fat;
    }
    /*  getProtein関数 */
    public double getProtein(){
        return protein;
    }
    /*  getCalorie関数 */
    public double getCalorie(){
        return calorie;
    }
    public double getGI(){
        return gi;
    }
}