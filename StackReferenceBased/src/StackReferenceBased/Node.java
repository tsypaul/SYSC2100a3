package StackReferenceBased;

public class Node {
	private Object item;
	private Node next;
	
	public Node (Object a){
		item = a;
		next = null;
	}
	
	public Node(Object a, Node n){
		item = a;
		next = n;
	}
	
	public Object getItem(){
		return item;
	}
		
	public Node getNext(){
		return next;
	}
	
	public void setItem(Object a){
		item = a;
	}
	
	public void setNext(Node n){
		next = n;
	}
}	
