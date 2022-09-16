public class MinStack {
    int size;{size=0;}
    Node top;
	
    public void push(int x,int y){
        if(top == null){
            top = new Node(x,y);
        }
	else{
            Node temp = new Node(x,y);
            temp.next = top;
            temp.min = Math.min(top.min, x);
            top = temp;
            size++;
	}
        
    }
	
    public void pop(){
	if(top == null){
            System.out.println("Stack empty!");
            return;
	}
	top = top.next;
    }
	
    public int top(){
        if(top == null){
            System.out.println("Stack empty!");
            return Integer.MAX_VALUE;
	}
        return top.value;
    }
    
    public int min(){
	if(top == null){
            System.out.println("Stack empty!");
            return Integer.MAX_VALUE;
        }
        return top.min;
    }
    boolean isEmpty() {
        return (top == null);
    }


}

class Node {

    int value;
    int min;
    int id;
    Node next;
    
    Node(int x,int y){
        value = x;
        id=y;
        next = null;
        min = x;
    }
}