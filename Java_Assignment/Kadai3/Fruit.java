/* * * * * * * * * * * * * * * Fruit.java * * *
 *       果実クラス（基底クラス）                 *
 * * * * * * * * * * * * * * * ** * * * * * * */
public class Fruit {

	private double vitaminC; /* ビタミンＣ含有量：100gあたり */
	private int price; /* 野菜の小売価格: 1kgあたり */
    private int howMany; /* 何個（何kg）買ったか */
    private Fruit fruit; /* 果実クラス*/

	/* コンストラクタ　*/
	public Fruit(double vitaminC, int price){
		this.vitaminC = vitaminC; /* ビタミンCの代入 */
		this.price = price; /* 値段の代入 */
	}
   
    /* 果実を設定 */
    public Fruit(Fruit fruit, int howMany){
        this.fruit = fruit;
        this.howMany = howMany;
    }
     /* print関数 */
	public void print(){
        fruit.print();
		System.out.println(howMany);
	}
	/* ビタミンCを返す関数 */
	public double getVitaminC(){ 
        return vitaminC; 
    }
	/* 価格を返す関数 */
	public int getPrice(){ 
        return price; 
    }
    /* 買った個数を返す */
    public int getHowMany(){ 
        return howMany; 
    }
    /* 果実を設定 */
    public Fruit getFruit(){ 
        return fruit;
    }
    /* 果実の個数をカウントアップ */
    public void add(){
        this.howMany++;
    }
} /* Fruitクラスのおわり */