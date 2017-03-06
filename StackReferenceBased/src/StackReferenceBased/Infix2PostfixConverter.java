package StackReferenceBased;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Infix2PostfixConverter {
	
	public static boolean isOperator(char c){
        return (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')');
    }

    public static boolean getPrecedence(char op1, char op2){
    	if(op1 == '+' || op1 == '-'){
    		return !(op2 == '+' || op2 == '-');
    	}else if(op1 == '*' || op1 == '/'){
    		return op2 == '(';
    	}else if(op1 == '('){
    		return true;
    	}
    	return false;
    }
    
    public static String infixToPostfix(String infix){
    	StackReferenceBased s = new StackReferenceBased();
    	StringTokenizer split = new StringTokenizer(infix, "+-*/()", true);
    	StringBuffer postfix = new StringBuffer(infix.length());
    	char c;
    	while(split.hasMoreTokens()){
    		String x = split.nextToken();
    		c = x.charAt(0);
    		if(x.length() == 1 && isOperator(c)){
    			while(!s.isEmpty() && !getPrecedence(s.peek().toString().charAt(0),c)){
    				postfix.append(s.pop().toString());
    			}
    			if(c == ')'){
    				String a = s.pop().toString();
    				while(a.charAt(0) != '('){
    					postfix.append(a);
    					a = s.pop().toString();
    				}
    			}else{
    				s.push(x);
    			}
    		}else{
    			postfix.append(x);
    		}
    	}
    	while(!s.isEmpty()){
    		postfix.append(s.pop().toString());
    	}
    	return postfix.toString();
    }
    
    public static int getResult(String postfix){
    	int result = 0,num1,num2;
    	StackReferenceBased s = new StackReferenceBased();
    	char c;
    	for (int i = 0; i < postfix.length(); i++){
    		c = postfix.charAt(i);
    		if(c == ' '){
    			
    		}else if(c>'0' && c<'9'){
    			s.push(c);
    		}else if(!s.isEmpty()){
    			num1 = Integer.parseInt(String.valueOf(s.pop()));
    			num2 = Integer.parseInt(String.valueOf(s.pop()));
    			switch(c){
    			case '+':
    				s.push(num1+num2);
    				break;
    			case '-':
    				s.push(num1-num2);
    				break;
    			case '*':
    				s.push(num1*num2);
    				break;
    			case '/':
    				s.push(num1/num2);
    				break;
    			}
    		}
    	}
    	result = Integer.parseInt("" + s.pop());
    	return result;
    }
	
	public static void main(String[] arg){
		System.out.println("Please enter a infix: ");
		Scanner S = new Scanner(System.in);
		String infix = S.nextLine();
		S.close();
		String postfix = infixToPostfix(infix);
		System.out.println("infix: " + infix);
		System.out.println("postfix: " + postfix);
		System.out.println("result: " + getResult(postfix));
		
	}
}
