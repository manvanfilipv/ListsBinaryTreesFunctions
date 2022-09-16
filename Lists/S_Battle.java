public class S_Battle {
    int soldier_id;   
    int satrapy_id;
    int type;
    S_Battle(int soldier_id,int satrapy_id,int type){
        this.soldier_id=soldier_id;
        this.satrapy_id=satrapy_id;
        this.type=type;
    }
    S_Battle next;
}
