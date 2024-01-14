/* * * * * * * * * * * * * * * Banana.java* * *
 *       バナナクラス（派生クラス）                *
 * * * * * * * * * * * * * * * ** * * * * * * */

public class Banana extends Fruit {

	final static double vitaminC = 160.0; /* キャベツのビタミンC含有量:1kgあたり */
	final static int price = 300; /* キャベツの1kgあたりの値段 */

	/* コンストラクタ　*/
	public Banana(){
		super(Banana.vitaminC, Banana.price); /* 基底クラスのコンストラクタの呼び出し */
	}
	/* print関数 */
	public void print(){
		System.out.print("<バナナ> " );
	}
} /* Bananaクラスのおわり */