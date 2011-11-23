package lalala;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Posfix {
	public static void main(String[] args) {
		String[] s = { "3", "5", "2", "*", "+", "1", "+" };
		List<String> l = new ArrayList<String>();
		for (String aux : s) {
			l.add(aux);
		}
		System.out.println(new Posfix().posfix(l));
	}

	public double posfix(List<String> s) {
		Deque<Double> stack = new LinkedList<Double>();
		for (String c : s) {
			Operator op = getOperator(c);

			if (op == null) {
				stack.push(Double.valueOf(c));
			} else {
				double a1 = stack.pop();
				double a2 = stack.pop();
				stack.push(op.evaluate(a1, a2));
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
	}

	public class Sum extends Operator {

		@Override
		public double evaluate(double a1, double a2) {
			return a1 + a2;
		}

	}

	public class Sub extends Operator {
		@Override
		public double evaluate(double arg0, double arg1) {
			return arg1 - arg0;
		}
	}

	public class Mult extends Operator {
		@Override
		public double evaluate(double arg0, double arg1) {
			return arg0 * arg1;
		}
	}

	public class Div extends Operator {
		@Override
		public double evaluate(double arg0, double arg1) {
			return arg0 / arg1;
		}
	}
}
