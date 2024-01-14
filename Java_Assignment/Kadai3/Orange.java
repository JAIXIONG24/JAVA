/* * * * * * * * * * * * * * * Orange.java* * *
 *       みかんクラス（派生クラス）                *
 * * * * * * * * * * * * * * * ** * * * * * * */

public class Orange extends Fruit {

	final static double vitaminC = 320.0; /* キャベツのビタミンC含有量:1kgあたり */
	final static int price = 400; /* キャベツの1kgあたりの値段 */

	/* コンストラクタ　*/
	public Orange(){
		super(Orange.vitaminC, Orange.price); /* 基底クラスのコンストラクタの呼び出し */
	}
	/* print関数 */
	public void print(){
		System.out.print("<みかん> " );
	}
} /* Orangeクラスのおわり */