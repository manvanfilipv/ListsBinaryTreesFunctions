public class Army_Tree {
    Army root;
    
    Army_Tree(Army root){
        this.root=root;
    }
    
    void add(Army newSoldier){
        if (isEmpty()){
            root=newSoldier;
            return;
        }
        add(root,newSoldier);
    }
    
    private void add(Army node, Army newNode) {
	if (node.id > newNode.id) {
            if (node.lc != null) {
		add(node.lc, newNode);
		} else {
                    node.lc = newNode;
		}
	} else if (node.id < newNode.id) {
            if (node.rc != null) {
                add(node.rc, newNode);
            } else {
                node.rc = newNode;
            }
        } else return;
    }
    
    void printGAsoldiers() {
	System.out.print("GA Soldiers = ");
	if (root != null) print_GASoldiers_inorder(root);
	System.out.println();
    }
    
    private static void print_GASoldiers_inorder(Army node) {
	if (node.lc != null)print_GASoldiers_inorder(node.lc);
	System.out.print("<" + node.id + "> ");
	if (node.rc != null)print_GASoldiers_inorder(node.rc);
    }
    
    void TransitGAsoldiers() {
        HorsePQ h = new HorsePQ();    
	if (root != null) Transit_GASoldiers_inorder(root,h);      
    }
    
    private static void Transit_GASoldiers_inorder(Army node,HorsePQ h) {
	if (node.lc != null)Transit_GASoldiers_inorder(node.lc,h);
        if(h.getPriorityQueue().top.next!=null){
            GA_Tree ba=new GA_Tree();
            ba.add(new GA_Battle(node.id,h.getPriorityQueue().top.id));
            h.getPriorityQueue().pop();
        }
        else{
            GA_Tree ba=new GA_Tree();
            ba.add(new GA_Battle(node.id,-1));
        }
	if (node.rc != null)Transit_GASoldiers_inorder(node.rc,h);
    }

    boolean isEmpty() {
	return (root == null);
    }
}
