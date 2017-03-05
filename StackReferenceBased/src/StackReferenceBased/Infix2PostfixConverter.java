package StackReferenceBased;

import java.util.Scanner;

public class Infix2PostfixConverter {
	
	private static boolean isOperator(char c){
        return (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')');
    }

    private static boolean isLowerPrecedence(char op1, char op2){
        switch (op1){
            case '+':
            case '-':
                return !(op2 == '+' || op2 == '-');
            case '*':
            case '/':
                return op2 == '(';
            case '(':
                return true;
            default:
                return false;
        }
    }

	
	public static String convertInfixToPostfix(String inFix){
		StringBuffer postFix = new StringBuffer(inFix.length());
		//String inFix = o.toString();
		StackReferenceBased s = new StackReferenceBased();
		Character c;
		for(int i=0; i < inFix.length(); i++){
			c = inFix.charAt(i);
			if(!isOperator(c)){
				postFix.append(c);
			}else{
				if(c == ')'){
					while(!s.isEmpty() && s.peek().toString().charAt(0) != '('){
						postFix.append(c);
					}
					if(!s.isEmpty()){
						s.pop();
					}
				}else{
					if(!s.isEmpty() && !isLowerPrecedence(c, s.peek().toString().charAt(0))){
						s.push(c);
					}else{
						while(!s.isEmpty() && isLowerPrecedence(c, s.peek().toString().charAt(0))){
							Character a = s.pop().toString().charAt(0);
							if(c != '('){
								postFix.append(a);
							}else{
								c = a;
							}
						}
						s.push(c);
					}
				}
			}
		}
		while(!s.isEmpty()){
			postFix.append(s.pop());
		}
		return postFix.toString();
	}
	
	public static void main(String[] arg){
		System.out.println("Please enter a infix: ");
		Scanner S = new Scanner(System.in);
		String infix = S.nextLine();
		S.close();
//		Object[] in = new Object [infix.length()];
//		for (int i = 0; i < infix.length(); i++){
//			in[i] = infix.charAt(i);
//		}
		System.out.println("infix: " + infix);
		System.out.println("postfix: " + convertInfixToPostfix(infix));
		
	}
}
