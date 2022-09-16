public class HorsePQ {
    Horse horsePQ,rightmost;
    static MinStack horseAge = new MinStack();
    static int size=0;
    

    void horse(int id, int age) {
        if (horsePQ == null) {
            horsePQ = rightmost = new Horse(id, age);
            return;
        }
        Horse node = searchHorse(horsePQ, id);
        if (node != null) {
            if (node.age < age)node.age = age;
        } else {
            node = new Horse(id, age);
            Horse h = findIncNode(horsePQ, size++);
            node.p = h;
            if (h.lc == null)h.lc = node;
            else h.rc = node;
            rightmost = node;
        }
    }

    private Horse searchHorse(Horse node, int id) {
        if (node.id == id)
            return node;
        Horse h;
        if (node.lc != null) {
            h = searchHorse(node.lc, id);
            if (h != null)return h;
        } else if (node.rc != null) {
            h = searchHorse(node.rc, id);
            if (h != null)return h;
        }
        return null;
    }
    
    private Horse findIncNode(Horse node, int nodeNo) {
	int parent = (nodeNo - 1) / 2;
	String isChild = ((nodeNo - 1) % 2 == 0) ? "lc" : "rc";
	if (nodeNo == 0) {
            return node;
	}
	Horse v = findIncNode(node, parent);
	if (v.rc == null) {
            return v;
	}
	if ("lc".equals(isChild)) {
            return v.lc;
	}
	return v.rc;
    }
    
    void printHorses() {
        Queue <Horse> q = new Queue <>();
	Horse hor;
	int level = 0;
	q.enqueue(horsePQ);
	while (!q.isEmpty()) {
            q.enqueue(new Horse(-1, -1));
            System.out.print("Level " + level + ": ");
            hor = q.dequeue();
            while (hor.id != -1) {
		System.out.print("<" + hor.id +","+ hor.age+">");
                q.enqueue(hor.lc);
		q.enqueue(hor.rc);
		hor = q.dequeue();
            }
            System.out.println();
            level++;
	}
    }

    
    void addToStack() {
	if (horsePQ != null) addToStack_inorder(horsePQ);
    }
    
    private void addToStack_inorder(Horse node) {
	if (node.lc != null)addToStack_inorder(node.lc);
        horseAge.push(node.age,node.id);   
	if (node.rc != null)addToStack_inorder(node.rc);
    }
    
    void setPriorityQueue(){
        int []horseArrID=new int[horseAge.size];
        int []horseArrAge=new int[horseAge.size];
        for(int i=0;i<horseArrAge.length;i++){
            horseArrAge[i]=horseAge.top.value;
            horseArrID[i]=horseAge.top.id;
            horseAge.pop();
        }
        int temp;
        int temp1;
        for (int i = 1; i < horseArrAge.length; i++) {
            for (int j = i; j > 0; j--) {
                if (horseArrAge[j] < horseArrAge [j - 1]) {
                    temp = horseArrAge[j];
                    horseArrAge[j] = horseArrAge[j - 1];
                    horseArrAge[ j- 1] = temp;
                    temp1 = horseArrID[j];
                    horseArrID[j] = horseArrID[j - 1];
                    horseArrID[ j- 1] = temp1;
                }
            }
        }
        for(int i=horseAge.size-1;i>=0;i--){
            horseAge.push(horseArrAge[i],horseArrID[i]);
        }
    }
    
    MinStack getPriorityQueue(){
        return horseAge;
    }
    
}

