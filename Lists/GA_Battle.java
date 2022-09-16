public class GA_Battle {
    int soldier_id;
    int horse_id;
    int general;
    GA_Battle next;
    
    GA_Battle(int soldier_id,int horse_id,int general){
        this.soldier_id=soldier_id;
        this.horse_id=horse_id;
        this.general=general;
    }
}
