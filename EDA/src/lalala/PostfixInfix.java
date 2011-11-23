package lalala;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PostfixInfix {

	public List<String> postfixToInfix(List<String> s) {
		List<String> resp = new ArrayList<String>();
		Deque<List<String>> stack = new LinkedList<List<String>>();

		for(String c: s){
			Operator op = getOperator(c);
			if(op == null){
				List<String> l = new ArrayList<String>();
				l.add(c);
				stack.push(l);
			} else{
				List<String> string = new ArrayList<String>();
				List<String> aux = stack.pop();
				string.add("(");
				string.addAll(stack.pop());
				string.add(op.toString());
				string.addAll(aux);
				string.add(")");
				stack.push(string);
			}
		}
		return stack.pop();
	}

	public Operator getOperator(String s) {
		switch (s.charAt(0)) {
		case '+':
			return new Sum();
		case '-':
			return new Sub();
		case '*':
			return new Mult();
		case '/':
			return new Div();
		default:
			return null;
		}

	}

	private abstract class Operator {
		public abstract double evaluate(double a1, double a2);

		public abstract int precedence();
	}

	public class Sum extends Operator {

		@Override
		public double evaluate(double a1, double a2) {
			return a1 + a2;
		}

		@Override
		public String toString() {
			return "+";
		}

		@Override
		public int precedence() {
			return 5;
		}

	}

	public class Sub extends Operator {
		@Override
		public double evaluate(double arg0, double arg1) {
			return arg1 - arg0;
		}

		@Override
		public String toString() {
			return "-";
		}

		@Override
		public int precedence() {
			return 5;
		}
	}

	public class Mult extends Operator {
		@Override
		public double evaluate(double arg0, double arg1) {
			return arg0 * arg1;
		}

		@Override
		public String toString() {
			return "*";
		}

		@Override
		public int precedence() {
			return 7;
		}
	}

	public class Div extends Operator {
		@Override
		public double evaluate(double arg0, double arg1) {
			return arg0 / arg1;
		}

		@Override
		public String toString() {
			return "/";
		}

		@Override
		public int precedence() {
			return 7;
		}
	}

}
