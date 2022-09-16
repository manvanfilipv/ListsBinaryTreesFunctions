public class HashTable {
    
    static String message;static {message="";}
    
    private class List {
        
        private class List_node {
            int eid;
            List_node next;

            List_node(int eid) {
		this.eid = eid;
            }
	}
        
        List_node start;
        
        void add(int eid) {
            List_node n = new List_node(eid);
            n.next = start;
            start = n;
	}

	void printList() {
            List_node ln = start;
            while (ln != null) {
		System.out.print("<" + ln.eid + "> ");
		ln = ln.next;
            }
	}
        
        void printListOLD() {
            List_node ln = start;
            while (ln != null) {
		message+=("<" + ln.eid + "> ");
		ln = ln.next;
            }
            message+="\n";
	}
        
        void delete(List a,int aid){
            List_node temp=a.start;
            //a.start=a.start.next;
            //a.start.next=temp;
            a.start = temp.next; // Changed head  
        }     
    }
        
    static List[] a = new List[2503];{
        for (int i = 0; i < a.length; i++) {
            a[i] = new List();
        }
    }
    
    void delete(int aid){
        for (int i = 0; i < a.length; i++) {
            if (a[i].start == null)continue;
            if(i==aid){
                for(int j=0;j<3;j++){
                    a[aid].delete(a[aid],aid);
                }
            }
        }   
    }  

    void assign(int aid,int []HashArray) {
        int hash=(((40*aid+60)%(2503)%10));
        a[hash].add(aid);
    }

    void print() {
        for (int i = 0; i < a.length; i++) {
            if (a[i].start == null)continue;
            System.out.print("Index <" + i + ">: ");
            a[i].printList();
            System.out.println();
        }
    }
    
    String printOLD(){
        for (int i = 0; i < a.length; i++) {
            if (a[i].start == null)continue;
            message+=("Index <" + i + ">: ");
            a[i].printListOLD();
        }  
        return message;
    }
    
}
