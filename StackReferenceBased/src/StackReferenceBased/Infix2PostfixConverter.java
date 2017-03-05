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
    	int num1,num2,result=0;
    	StackReferenceBased s = new StackReferenceBased();
    	String[] num = postfix.split("");
    	for(int i = 0; i<num.length; i++){
    		String s1 = num[i];
    		if(isOperator(s1.charAt(0))){
    			s.push(Integer.parseInt(s1));
    		}else{
    			String s2 = num[i];
    			num1 = (int) s.pop();
    			num2 = (int) s.pop();
    			if(s2.equals("+")){
    				result = num1+num2;
    			}else if(s2.equals("-")){
    				result = num1-num2;
    			}else if(s2.equals("*")){
    				result = num1*num2;
    			}else if(s2.equals("/")){
    				result = num1/num2;
    			}
    			s.push((Object)result);
    		}
    	}
    	result = (int) s.pop();
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
