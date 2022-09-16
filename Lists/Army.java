public class Army {
    
    public int id;
    public int type;
    public int general;

    public Army next;
    
    Army(int id ,int type,int general){
        this.id=id;
        this.type=type;
        this.general=general;
    }

    public String display(){
        return "<"+id+","+type+","+general+">";
    }
   
}




