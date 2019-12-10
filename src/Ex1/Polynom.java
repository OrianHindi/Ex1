package Ex1;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

import javax.management.RuntimeErrorException;
import javax.swing.plaf.synth.SynthToolTipUI;


/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{

	/**
	 * Zero (empty polynom)
	 */
	public Polynom() {
		this.arr=new LinkedList<>();

	}
	/**
	 * init a Polynom from a String such as:
	 * Good Examples: {x+x^3-5,7x^2+9x+x^3,0,-5,-7x}.
	 * Bad Examples : { Xr^3,X^2+1,+7x+5--x^2,++x,(x^2+3),(4+5)(x^2)}
	 * @param s: is a string represents a Polynom
	 */

	public Polynom(String s) {
		if(s.charAt(0)== '+') s=s.substring(1);
		s=s.replaceAll(" ", "");
		int index=0;
		boolean flag= true;
		for (int i = 0; i < s.length(); i++) {
			flag=true;
			if(s.charAt(i)=='-' && i!=0) {
				Monom temp = new Monom(s.substring(index,i ));
				flag=checkSame(this.arr, temp);
				if(flag) {
					this.arr.add(new Monom(temp));
				}
				index=i;
			}
			else if(s.charAt(i)== '+') {
				Monom temp = new Monom(s.substring(index, i));
				flag=checkSame(this.arr, temp);
				if(flag) {
					this.arr.add(new Monom(temp));
				}

				index=i+1;
			}
			else if(i==s.length()-1) {
				Monom temp = new Monom(s.substring(index));   
				flag=checkSame(this.arr, temp);
				if(flag) {
					this.arr.add(new Monom(temp));
				}
			}
		}
		if(this.arr.size()>1) this.checkZeros();
		this.sortPolynom();

	}
	/**
	 * this function represent a simple function of type y=f(x).
	 * where both y and x are real number.
	 * @param x
	 * @return the value of the function(=y) at point x.
	 */
	@Override
	public double f(double x) {
		double sum =0;
		Iterator<Monom> it = arr.iterator();
		while(it.hasNext()) {
			Monom temp= it.next();
			sum +=temp.f(x);

		}
		return sum;
	}
	/**
	 * this function add Polynom_able p1 to this Polynom.
	 * @param p1 : its a Polynom_able
	 * the function is void,after the function this.Polynom= it self+ p1.
	 */
	@Override
	public void add(Polynom_able p1) {
		boolean flag = true;
		Iterator<Monom> it = p1.iteretor();
		while(it.hasNext()) {
			Monom temp = it.next();
			flag=checkSame(this.arr, temp);
			if(flag) {
				this.arr.add(temp);
			}

		}
		this.sortPolynom();
	}
	/**
	 * add m1 to this Polynom.
	 * @param m1: repesent a monom.
	 * after the function this.Polynom= itself+ m1.
	 */
	@Override
	public void add(Monom m1) {
		boolean flag= true;
		flag=checkSame(this.arr, m1);
		if(flag) {
			this.arr.add(m1);
		}
		this.sortPolynom();
	}
	/** substract p1 from this Polynom.
	 * @param p1: is a Polynom able that repesent a Polynom.
	 * after the function this.Polynom = itself - p1.
	 */
	@Override
	public void substract(Polynom_able p1) {
		Monom helper = new Monom(-1.0,0);
		Polynom_able s = (Polynom_able) p1.copy();
		Iterator<Monom> it = s.iteretor();
		while(it.hasNext()) {
			Monom temp = it.next();
			temp.multipy(helper);
		}
		this.add(s);
		if(this.arr.size()==0) {
			this.arr.add(new Monom("0"));
		}
	}
	/**
	 * Multiply this Polynom by p1.
	 * @param p1 : is a Polynom able that repesent a polynom.
	 * after the function this.Polynom = itself * p1.
	 */
	@Override
	public void multiply(Polynom_able p1) {
		Polynom ans = new Polynom();
		Iterator<Monom> it = p1.iteretor();
		while(it.hasNext()) {
			Polynom_able copy = this.copy();
			Monom temp = it.next();
			copy.multiply(temp);
			ans.add(copy);
		}
		this.arr=ans.arr;
	}
	/**
	 * test if this Polynom is logically equals to p1.
	 * @param p1 : is a Polynom able that repesent Polynom.
	 * @return true if the Polynoms are equals,else return true;
	 */

	public boolean equals(Object p1) {
		Iterator<Monom> it= this.iteretor();
		Iterator<Monom> it1 = ((Polynom)p1).iteretor();
		while(it.hasNext() && it1.hasNext()) {
			Monom temp = it.next();
			Monom temp1= it1.next();
			if(!temp.equals(temp1)) return false;
		}
		if(it.hasNext() && !it1.hasNext()) return false;
		if(!it.hasNext() && it1.hasNext()) return false;

		return true;
	}
	/**
	 * Test if this is the Zero Polynom.
	 * @return true if all the this.Polynom = 0 ,else flase.
	 */
	@Override
	public boolean isZero() {
		Iterator<Monom> Iter=this.iteretor();
		while(Iter.hasNext()) {
			if(Iter.next().get_coefficient()!=0)
				return false;
		}
		return true;
	}		
	/**
	 * Compute a value x' (x0<=x'<=x1) for with |f(x')| < eps
	 * assuming (f(x0)*f(x1)<=0, else should throws runtimeException 
	 * computes f(x') such that:
	 * 	(i) x0<=x'<=x1 && 
	 * 	(ii) |f(x')|<eps
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps>0 (positive) representing the epsilon range the solution should be within.
	 * @return an approximated value (root) for this (cont.) function 
	 */
	@Override
	public double root (double x0, double x1, double eps) {  //fix.
		if(this.isZero()) return 0;
		double mid = 0; 
		if(x0>x1) 
			return 0 ;
		if ((f(x0)) * (f(x1)) > 0) 
			throw new RuntimeException("ERR: The function does not cut the x axis");
		if(f(x0)==0) return x0;
		if(f(x1)==0) return x1;
		while ((x1-x0)>= eps) {  
			mid = (x0+x1)/2;   
			if (f(mid) == 0.0) 
				break;
			else if ((f(mid)*f(x0)) < 0) 
				x1 = mid; 
			else
				x0 = mid; 
		} 

		return mid;
	}		
	/** 
	 * Create a deep copy of this.Polynom.
	 * @return Polynom able.
	 */
	@Override
	public Polynom_able copy() {
		Polynom_able s = new Polynom();
		Iterator<Monom> it = this.arr.iterator();
		while(it.hasNext()) {
			Monom temp = it.next();
			Monom tmp = new Monom(temp.get_coefficient(),temp.get_power());
			s.add(tmp);
		}

		return s;
	}
	/** 
	 * Compute a new Polynom which is the derivative of this polynom.
	 * @return Polynom able that repesent the derivative.
	 */
	@Override
	public Polynom_able derivative() {
		Polynom_able s = new Polynom();
		String p = this.toString();
		if(!p.contains("x")) {
			s.add(new Monom(0,0));
			return s;
		}
		Iterator<Monom> it = this.arr.iterator();
		while(it.hasNext()) {
			Monom temp = it.next();
			temp=temp.derivative();
			if(temp.get_coefficient()!=0) {
				s.add(temp);
			}
		}
		return s;
	}
	/**
	 * Compute a Riman's integral from x0 to x1 in eps steps. 
	 * @param x0 starting pooint
	 * @param x1 end point
	 * @param eps positive step value
	 * @return the approximated area above X-axis below this function bounded in the range of [x0,x1]
	 */
	@Override
	public double area(double x0, double x1, double eps) {
		if(this.isZero()) return 0;
		
		double sum=0;
		for (double i =  x0; i <= x1; i+=eps) {
			if(f(i)>=0) {
			sum+= (this.f(i) * eps);
			}
		}
		return sum;
	}
	/**
	 * @return an Iterator (of Monoms) over this Polynom.
	 */
	@Override
	public Iterator<Monom> iteretor() {
		Iterator<Monom> it = this.arr.iterator();
		return it ;
	}
	/**
	 * Multiply this Polynom by Monom m1.
	 * @param m1 : that repesent a Monom.
	 * after this function this.Polynom= it self*m1.
	 */
	@Override
	public void multiply(Monom m1) {    //fix if multiply by 0.
		Iterator<Monom> it = this.arr.iterator();
		while(it.hasNext()) {
			Monom temp = it.next();
			temp.multipy(m1);
		}
			this.checkZeros();

	}
	/**
	 * sort the Polynom from highest degree to lowest degree.
	 */
	public void sortPolynom() {
		Comparator<Monom> m =new Monom_Comperator();
		this.arr.sort(m);
	}
	/**
	 * check if there is zero's at the Polynom that are not necessary.
	 */
	public void checkZeros() {
		Iterator<Monom> it = this.iteretor();
		while(it.hasNext()) {
			Monom tmp = it.next();
			if(tmp.get_coefficient()==0 && this.arr.size()>1) {
				this.arr.remove(tmp);
			}
		}
		if(this.arr.size()==1 && this.arr.getFirst().get_coefficient()==0) {
			this.add(new Monom ("0"));
			this.arr.remove(0);
		}

	}
/**
 * This function print the Polynom
 * @return String s : that repesent the Polynom.
 */
	@Override
	public String toString() {
		Iterator<Monom> it = this.iteretor();
		String s = "";
		int index =0;
		while(it.hasNext()) {
			Monom temp = it.next();
			if(temp.get_coefficient()>=0 && index!=0 ) {
				s+="+"+ temp.toString();
			}
			else s+=temp.toString();
			index++;
		}
		return s;
	}

	@Override
	public function initFromString(String s) {
		function ans = new Polynom(s);
		return ans;
		}





	/**
	 * the function is check if there are any same power.if there is add it to the monom.
	 * @param list :repesent the Polynom.
	 * @param s : repesent the monom we want to add.
	 * @return false if the monom have been added here,flase if not.
	 */
	public boolean checkSame(LinkedList<Monom> list, Monom s) {
		Iterator<Monom> it =  list.iterator();
		while(it.hasNext()) {
			Monom tmp=it.next();
			if(s.get_power()==tmp.get_power()) {
				tmp.add(s);
				if(tmp.get_coefficient()== 0) {
					this.arr.remove(tmp);
				}
				return false;
			}

		}
		return true;

	}

	public LinkedList<Monom> arr = new LinkedList<Monom>();
	//	
	public static void main(String[] args) {
		Polynom s1 = new Polynom("x+x+1");
		Polynom s = new Polynom("2x+1");

		System.out.println(s1.equals(s));
//		double a = 0;
//		a = s.area(-1.7, 0, 0.00001);
//		System.out.println(a);
//		System.out.println(s.toString());
//		String p = "div(x3)";
//		Polynom r1= new Polynom("x^2+2x");
//		Polynom_able s2=r1.derivative();
//		System.out.println(s2);
//		int j =3;
//		p=p.substring(j+1,p.length()-1);
//		System.out.println(p);
//		String e = "";
//		for (int i = 0; i < p.length(); i++) {
//			if (p.charAt(i) == '(') {
//				e = p.substring(0, i );
//			}
//			System.out.println(e);
//
//		}

	}
}
