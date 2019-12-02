package Ex1;
import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	public static String badInput;
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	public double get_coefficient() {
		return this._coefficient;
	}
	public int get_power() {
		return this._power;
	}
	
	/**
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	}
	public boolean isZero() {return this.get_coefficient() == 0;}
	// ****** add your code below *********
	/**
	 * This constructor gets String and turns it into monom.
	 * Good Examples: { x,x^3,-x,-x^2,2x^3,0}
	 * Bad Examples: {Xr^2,+x,--4x,-7x^sf1,12f,X^x}.
	 * @param s : is a string that repesent a monom.
	 */
	public Monom(String s) {
		s=s.toLowerCase();
		badInput=s;
		try {
		double mekadem=0 ;
		int hezka=0 ;
		if(!s.contains("x")) {          // check if the string is 0 or a real number.
			if(s.equals("0") || s.equals("0.0")) {				  
				this._coefficient=0;							 
				this._power=0;
				return;
			}
			else {
				mekadem=Double.parseDouble(s);
				this._coefficient=mekadem;
				this._power=0;
				return;
			}
		}
		for (int i = 0; i < s.length(); i++) {
			if(i==0 & s.charAt(i) == 'x') {    //if string look like x , x^..
				mekadem = 1;
				if(s.length()>1) {
					s=s.substring(2);
					hezka= Integer.parseInt(s);
				}
				else {
					hezka =1;
				}
				break;
			}
			if(s.charAt(0)=='-'&& s.charAt(1) == 'x' ) {    //if the string look like -x,-x^..
				mekadem= -1;
				if (s.length()==2) {
					hezka= 1;
				}
				if(s.length()>2) {
					s=s.substring(3);
					hezka = Integer.parseInt(s);
				}

				break;
			}
			if (s.charAt(i) =='x') {     //if string look like  +/-..x^.... , +/-...x
				mekadem = Double.parseDouble(s.substring(0,i));
				if(i == s.length()-1) {
					hezka =1;
					break;				
				}
				else {
					hezka=Integer.parseInt(s.substring(i+2));
					break;
				}
			}


		}
		this._coefficient=mekadem;    
		this._power=hezka;
		}
		catch(Exception e) {
			System.out.println("Wrong input! The bad string is: " + badInput);
		}
		}
		
		
	/**
	 * if the power of monoms are same add the coefficeint else return.
	 * @param m: monom that we want to add.
	 */
		public void add(Monom m) {      
			if(this.get_power()!=m.get_power()) {
				return;
			}
			else {
				this.set_coefficient(this.get_coefficient()+m.get_coefficient());
			}
		}
		/**
		 * This function get Monom and multiply the coefficient and add powers.
		 * @param d
		 */
		public void multipy(Monom d) {
			this.set_coefficient(this.get_coefficient()*d.get_coefficient());    
			this.set_power(this.get_power()+d.get_power());  
		}
		/**
		 * the function print the Monom.
		 * @return String.
		 */
		public String toString() {
			if (this.get_coefficient()== -1 && this.get_power()== 1) return "-x";
			else if(this.get_coefficient() ==1 && this.get_power()== 1) return "x";
			else if(this.get_power() ==1 ) return "" + this.get_coefficient() + "x";
			else if(this.get_power()==0) return "" + this.get_coefficient();
			else if(this.get_coefficient()==0 && this.get_power() ==1 ) return "0x";
			else if(this.get_coefficient()==0 && this.get_power() ==0 ) return "0";
			else if (this.get_coefficient() ==0 ) return "0x^" + this.get_power();
			else return "" + this.get_coefficient() + "x^" +this.get_power();
		}

	@Override
	public function initFromString(String s) {
			function ans = new Monom(this.toString());
	return ans;
	}

	@Override
	public function copy() {
			function ans = new Monom(this.get_coefficient(),this.get_power());
		return ans;
	}

	/**
		 * check if the coefficient equals and if powers is equals.
		 * @param d
		 * @return true if equals else flase.
		 */
		public boolean equals(Monom d) {  
			if(this.get_coefficient()== 0 &&  d.get_coefficient() ==0 ) return true;
			if(Math.abs(this.get_coefficient()-d.get_coefficient())<=EPSILON && this.get_power() ==d.get_power()) return true;
			else return false;
		}
		// you may (always) add other methods.

		//****** Private Methods and Data *******


		private void set_coefficient(double a){
			this._coefficient = a;
		}
		private void set_power(int p) {
			if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
			this._power = p;
		}
		private static Monom getNewZeroMonom() {return new Monom(ZERO);}
		private double _coefficient;
		private int _power;

		public static void main(String[] args) {
//			String[] secondcheck;
//			String[] check = s.split("x");
//			System.out.println(check.length);
//			System.out.println(check[0]);
//			System.out.println(check[1]);
//			if(check[1].contains("\\^")) {
//				secondcheck=check[1].split("\\^");
//				System.out.println(secondcheck.length);
//				System.out.println(secondcheck[0]);
//			}
			//		System.out.println(x);
			String p = "4px^90";
			Monom a = new Monom(p);
			Monom b = new Monom("0");
			Monom c = new Monom("-9x^17");
			
			System.out.println("a=" + a.toString());
			System.out.println("b=" + b.toString());
			System.out.println("c=" + c.toString());
			c.multipy(a);
			System.out.println( " c*a =  " + c.toString());
			b.multipy(a);
			System.out.println("b*a =   " + b.toString());
			
			
		}

	}


