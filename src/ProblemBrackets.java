import java.util.Scanner;

public class ProblemBrackets {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter regular expression:");
        String expression = myObj.nextLine();
        BracketsChecker bracketsChecker = new BracketsChecker(expression);
        System.out.println("Expression "+expression+" is correct: "+bracketsChecker.isCorrect());
    }
}

class BracketsChecker {

    class Stack {

        private String[] stack;
        private int top = 0;

        public Stack(int size) {
            stack = new String[size];
            top = 0;
        }

        public void pop() {
            String[] oldStack = stack;
            stack = new String[stack.length];
            for (int i = 0; i < top - 1; i++) {
                stack[i] = oldStack[i];
            }
            top--;
        }

        public boolean isFull() {
            return (top == (stack.length - 1))&&(top>0);
        }

        public void insert(String c) {
            if (this.isFull()) {
                System.out.println("Can't insert - stack is full!");
            } else {
                stack[top] = c;
                top++;
            }
        }

        public String top() {
            return stack[top-1];
        }

        public boolean isEmpty() {
            return top == 0;
        }

        public int size() {
            return top;
        }
    }

    String expression;

    public BracketsChecker(String expression) {
        this.expression = expression;
        System.out.println(this.expression);
    }

    public boolean isOpeningBracket(String s){ return s.equals("(")|| s.equals("["); }
    public boolean isClosingBracket(String s){ return s.equals(")")|| s.equals("]"); }


    public boolean isCorrect() {

        Stack stack = new Stack(expression.length());

        for (int i = 0; i < expression.length(); i++) {
            String b = expression.substring(i, i + 1);

            if (isOpeningBracket(b)) {
                stack.insert(b);
            } else {
                if(!isClosingBracket(b)){continue;}
                if (stack.isEmpty()) {
                    return false;
                }
                if (b.equals("]")) {
                    if (stack.top().equals("[")) {
                        stack.pop();
                    } else {
                        return false;
                    }
                } else {
                    if (stack.top().equals("(")) {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }


}



