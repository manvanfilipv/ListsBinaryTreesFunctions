public class GA_Tree{
    int size;
    static String a;static {a="";}
    static GA_Battle Ga_tree;
    {size=0;}  

    void add(GA_Battle newSoldier){
        if (isEmpty()){
            Ga_tree=newSoldier;
            return;
        }
        add(Ga_tree,newSoldier);
    }
    
    private void add(GA_Battle node,GA_Battle newNode){
        if (node.soldier_id > newNode.soldier_id) {
            if (node.lc != null) {
		add(node.lc, newNode);
		} else {
                    node.lc = newNode;
		}
	} else if (node.soldier_id < newNode.soldier_id) {
            if (node.rc != null) {
                add(node.rc, newNode);
            } else {
                node.rc = newNode;
            }
        } else return;
    }
    
    void printGAbattle() {
	System.out.print("GA Soldiers = ");
	if (Ga_tree != null) print_GAbattle_inorder(Ga_tree);
	System.out.println();
    }
    
    private static void print_GAbattle_inorder(GA_Battle node) {
	if (node.lc != null)print_GAbattle_inorder(node.lc);
	System.out.print("<" + node.soldier_id + ","+node.horse_id+"> ");
	if (node.rc != null)print_GAbattle_inorder(node.rc);
    }
    
    String printGAbattleOLD() {
	System.out.print("GA Soldiers = ");
	if (Ga_tree != null) print_GAbattle_inorderOLD(Ga_tree);
        return a;
    }
    
    private void print_GAbattle_inorderOLD(GA_Battle node) {
	if (node.lc != null)print_GAbattle_inorderOLD(node.lc);
	a+=("<" + node.soldier_id + ","+node.horse_id+"> ");
	if (node.rc != null)print_GAbattle_inorderOLD(node.rc);
    }
    
    boolean isEmpty() {
	return (Ga_tree == null);
    }
    
    void delete(int X){
        if(Ga_tree!=null)ambush(Ga_tree,X,0);
    }
    
    private void ambush(GA_Battle node,int X,int size){
        if (node.lc != null)ambush(node.lc,X,size);
	size++;
        if(size==X){
            size=0;
            DeleteNode(Ga_tree,node.soldier_id);  
        }
	if (node.rc != null)ambush(node.rc,X,size);
    }
    
    private GA_Battle DeleteNode(GA_Battle root,int key){
        if(root ==null)return root;
        if (key < root.soldier_id) root.lc = DeleteNode(root.lc, key); 
        else if (key > root.soldier_id) root.rc = DeleteNode(root.rc, key);
        else{
            if(root.lc==null)return root.rc;
            else if(root.rc==null)return root.lc;
            root.soldier_id=minValue(root.rc);
            root.rc=DeleteNode(root.rc,root.soldier_id);    
        }
        return root;
    }
    
    private int minValue(GA_Battle root){ 
        int minv = root.soldier_id; 
        while (root.lc != null) 
        { 
            minv = root.lc.soldier_id; 
            root = root.lc; 
        } 
        return minv; 
    } 
    
    GA_Battle split(int id) {
	if (Ga_tree == null)
            return null;
	return split(Ga_tree,id);
    }

    private GA_Battle split(GA_Battle node, int aid) {
	GA_Battle r = Ga_tree;
	if (aid <node.soldier_id) {
            GA_Battle a = node.lc;
            if (a != null) {
		GA_Battle b = split(a, aid);
                if (Ga_tree != r) {
                    node.lc = Ga_tree;
                    Ga_tree = r;
                }
                return b;
            } 
            else {
                return null;
            }
	}
        else if (node.soldier_id < aid) {
            GA_Battle a = node.rc;
            if (a != null) {
		Ga_tree = a;
                GA_Battle b = split(a, aid);
		node.rc = b;
		return node;
            } 
            else{
		Ga_tree = null;
		return node;
            }
        }
            Ga_tree = node.rc;
            node.rc = null;
            return node;
    }
    
    void printGenerals(String a,int x) {
	System.out.print(a);
	if (Ga_tree != null) print_Generals_inorder(Ga_tree,x);
	System.out.println();
    }
    
    private static void print_Generals_inorder(GA_Battle node,int x) {
	if (node.lc != null)print_Generals_inorder(node.lc,x);
	if(node.soldier_id<=x)System.out.print("<" + node.soldier_id + ","+node.horse_id+"> ");
	if (node.rc != null)print_Generals_inorder(node.rc,x);
    }
    
    void Transit_GA_battle(HashTable hsr) {
	if (Ga_tree != null) Transit_GA_battle_inorder(Ga_tree,hsr);
    }
    
    private void Transit_GA_battle_inorder(GA_Battle node,HashTable hs) {
	if (node.lc != null)Transit_GA_battle_inorder(node.lc,hs);
        hs.delete(node.soldier_id);
	if (node.rc != null)Transit_GA_battle_inorder(node.rc,hs);
    }

}
