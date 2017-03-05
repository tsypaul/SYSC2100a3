package StackReferenceBased;

public class StackReferenceBased {
	
	private Node top;
	private int items;
	
	public StackReferenceBased(){
		createStack();
	}
	
	private void createStack(){
		top = null;
		items = 0;
	}
	
	public void popAll(){
		top = null;
		items = 0;
	}
	
	public boolean isEmpty(){
		return (top == null);
	}
	
	public void push(Character o){
		top = new Node(o, top);
		items++;
	}
	
	public Character pop(){
		Character o = top.getItem();
		top = top.getNext();
		items--;
		return o;
	}
	
	public Character peek(){
		return top.getItem();
	}
}
