package lalala;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class InfixToPostfix {
	public static void main(String[] args) {
		String[] s = { "2", "*", "(", "3", "+", "4", ")", "-", "1" };
		List<String> l = new ArrayList<String>();
		for (String aux : s) {
			l.add(aux);
		}
		List<String> aaaaa = new InfixToPostfix().infixToPostfix(l);
		System.out.println(aaaaa);
		List<String> jjjj = new PostfixInfix().postfixToInfix(aaaaa);
		System.out.println(jjjj);
		System.out.println(new Posfix().posfix(new InfixToPostfix()
				.infixToPostfix(l)));
	}

	private List<String> infixToPostfix(List<String> l) {
		List<String> resp = new ArrayList<String>();
		Deque<Operator> stack = new LinkedList<Operator>();
		for (String s : l) {
			Operator op = getOperator(s);
			if (op == null) {
				resp.add(s);
			} else {

				while (!stack.isEmpty() && !stack.peek().toString().equals("(")
						&& stack.peek().precedence() >= op.precedence()) {
					resp.add(stack.pop().toString());
				}

				if (s.equals(")")) {
					if (stack.isEmpty()) {
						throw new RuntimeException();
					} else {
						stack.pop(); // saco el "("
					}
				} else {
					stack.push(op);
				}

			}

		}

		while (!stack.isEmpty()) {
			if (stack.peek().toString().equals("(")) {
				throw new RuntimeException();
			}
			resp.add(stack.pop().toString());
		}

		return resp;
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
		case '(':
			return new Par();
		case ')':
			return new Par2();
		default:
			return null;
		}

	}

	private abstract class Operator {
		public abstract double evaluate(double a1, double a2);

		public abstract int precedence();
	}

	public class Par extends Operator {

		@Override
		public double evaluate(double a1, double a2) {
			return a1 + a2;
		}

		@Override
		public String toString() {
			return "(";
		}

		@Override
		public int precedence() {
			return 10;
		}

	}

	public class Par2 extends Operator {

		@Override
		public double evaluate(double a1, double a2) {
			return a1 + a2;
		}

		@Override
		public String toString() {
			return ")";
		}

		@Override
		public int precedence() {
			return 1;
		}

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
