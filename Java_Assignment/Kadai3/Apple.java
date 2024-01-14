/* * * * * * * * * * * * * * * Apple.java * * *
 *       りんごクラス（派生クラス）               *
 * * * * * * * * * * * * * * * ** * * * * * * */

public class Apple extends Fruit {

	final static double vitaminC = 40.0; /* キャベツのビタミンC含有量:1kgあたり */
	final static int price = 500; /* キャベツの1kgあたりの値段 */

	/* コンストラクタ　*/
	public Apple(){
		super(Apple.vitaminC, Apple.price); /* 基底クラスのコンストラクタの呼び出し */
	}
	/* print関数 */
	public void print(){
		System.out.print("<りんご> " );
	}
} /* Appleクラスのおわり */