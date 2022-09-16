/*************************************************************
 * @file   Java.java                                         *
 * @author Nikolaos Batsaras <batsaras@csd.uoc.gr>  	     *
 *                                                    	     *
 * @brief  Source file for the needs of cs-240a project 2018 *
 ************************************************************/
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
        static Army []GA_Army=new Army[2];
        static Satrapy satrapies_list;
        static GA_Battle GA_battle_list;
        static S_Battle S_battle_list;
        static S_Battle S_battle_alter;
        static int insertionSatrapy=0;
        static int insertionSatrapySoldier=0;
	/*
	 * TODO:
	 *
	 * 1) You need to create the classes that correspond to the C structs
	 * 2) You need to create the global variables as in the C header
	 */

	/**
	 * @brief Optional function to initialize data structures that 
	 *        need initialization
	 *
	 * @return true on success
	 *         false on failure
	 */
        
	public static boolean initialize()
	{
            
		return true;
	}

	/**
	 * @brief Register Alexander the Great soldier 
	 *
	 * @param sid     The soldier's id
	 * @param type    The soldier's type
	 * @param general The general the soldier obeys
	 *
	 * @return True on success
	 *         False on failure
	 */
	public static boolean register_GA_soldier (int sid, int type, int general){
            Army newLink=new Army(sid,type,general); 
            Army temp=GA_Army[0];

            if(GA_Army[0]==null || GA_Army[0].id>=newLink.id){
                
                newLink.next=GA_Army[0];
                GA_Army[0]=newLink;
                
            }
            else{     
                while(temp.next!=null && temp.next.id<newLink.id ){
                    temp=temp.next;
                }    
                newLink.next=temp.next;
                temp.next=newLink;
            }

            return true;            
	}

	/**
	 * @brief Register Alexander the Great horse 
	 *
	 * @param hid     The horse's id
	 * @param type    Always the value of 0
	 * @param general The general the soldier obeys
	 *
	 * @return True on success
	 *         False on failure
	 */
	public static boolean register_GA_horse (int hid, int type, int general)
	{
            if(type==0){type=0;}
            Army newLink=new Army(hid,type,general); 
            Army temp=GA_Army[1];

            if(GA_Army[1]==null || GA_Army[1].id>=newLink.id){
                
                newLink.next=GA_Army[1];
                GA_Army[1]=newLink;
                
            }
            else{     
                while(temp.next!=null && temp.next.id<newLink.id ){
                    temp=temp.next;
                }    
                newLink.next=temp.next;
                temp.next=newLink;
            }

            return true;   
	}

	/**
	 * @brief Register Satrapy
	 *
	 * @param sid The Satrapy's id
	 *
	 * @return True on success
	 *         False on failure
	 */
	public static boolean register_satrapy (int sid)
	{
            Satrapy newSatrapy=new Satrapy(sid);
            
            if(satrapies_list==null){
                satrapies_list=newSatrapy;
                newSatrapy.next=newSatrapy;
                newSatrapy.prev=newSatrapy;               
            }
            else{
                satrapies_list.prev.next=newSatrapy;
                newSatrapy.next=satrapies_list;
                newSatrapy.prev=satrapies_list.prev;
                satrapies_list.prev=newSatrapy;
                satrapies_list=newSatrapy;
            }
            
            //int for printing
            insertionSatrapy++;
            
            return true;
	}

	/**
	 * @brief Register Satrapy soldier
	 *
	 * @param sid     The id of the Satrapy the soldier belongs to
	 * @param aid     The soldier's id
	 * @param type    The type of soldier
	 * @param general The general the soldier obeys
	 *
	 * @return True on success
	 *         False on failure
	 */
	public static boolean register_S_soldier (int sid, int aid, int type, int general)
	{   
            //Look for Satrapy based on id
            Satrapy tmp=null;
            Satrapy temp1=satrapies_list;
            while(temp1!=null){
                if(temp1.id==sid){
                    tmp=temp1;
                    break;
                }
                temp1=temp1.next;
            }
            
            if(tmp==null)return false;
            
            Army newSS=new Army(aid,type,general);
            
            //handle the position of sentinel and s_army
            if(tmp.s_army==null)newSS.next=tmp.sentinel;
            else newSS.next=tmp.s_army;
            tmp.s_army=newSS;
            
            //int for printing
            insertionSatrapySoldier++;
            
            return true;

	}

	/**
	 * @brief Prepare Alexander's soldiers for battle
	 * 
	 * @return True on success
	 *         False on failure
	 */
	public static boolean prepare_for_battle_GA ()
	{
            GA_Battle type1=null;
            GA_Battle type2=null;
            GA_Battle type3=null;
            GA_Battle type4=null;           
            
            Army root=GA_Army[0];
            Army rootH=GA_Army[1];
            
            //Search soldiers list and put every kind of soldier to dedicated list(4 lists)
            while(root!=null){
                GA_Battle newSoldier=new GA_Battle(root.id,-1,root.general);
                if (root.type==1) {
                    newSoldier.next=type1;
                    type1=newSoldier;           
                }    
                else if (root.type==2){
                    newSoldier.next=type2;
                    type2=newSoldier;
                }
                else if (root.type==3){
                    newSoldier.next=type3;
                    type3=newSoldier;
                }
                else if (root.type==4){
                    newSoldier.next=type4;
                    type4=newSoldier;
                }
                root=root.next;
            }
            //helpful nodes;
            GA_Battle temp=type1;
            GA_Battle head=type1;
            //Go to end of the list of type=1 
            while(head.next!=null){
                head=head.next;               
            }
            //And add the list of type=2
            head.next=type2;
            head=type2;
            //Reach the end of type=2 list and
            while(head.next!=null){
                head=head.next;
            }
            //add type=3 list
            head.next=type3;
            head=type3;
            //Go to the end of type=3 list and
            while(head.next!=null){
                head=head.next;
            }
            //add list type=4
            head.next=type4;
            //put the whole list into GA_battle_list
            GA_battle_list=temp;
            //put GA_battle_list to head
            head=GA_battle_list;
            //this loop concerns the horses
            //put the id of the horse to element of list and go the next horse and element of head
            while(rootH!=null){
                head.horse_id=rootH.id;
                rootH=rootH.next;
                head=head.next;
            }
            //put the new formatted GA_battle_list to head
            head=GA_battle_list;
            //And print the list of GA battle
            System.out.print("GA battle = ");
            while(head!=null){
                System.out.print("<"+head.soldier_id+","+head.horse_id+","+head.general+">");
                if(head.next!=null)System.out.print(",");
                head=head.next; 
            }
            System.out.println();

            return true;
	}

	/**
	 * @brief Prepare Satrapies for battle
	 *
	 * @param sid The satrapy's id
	 *
	 * @return True on success
	 *         False on failure
	 */
	public static boolean prepare_for_battle_S (int sid)
	{

            Satrapy temp=satrapies_list;
            Satrapy Central=null;
            
            //Look for satrapy with the id=sid
            while(temp!=null){
                if(sid==temp.id){
                   Central=temp;
                   break;
                }
                temp=temp.next;
            }
            
            if(Central==null)throw new RuntimeException("No satrapy found with id "+sid);
            
            
            Satrapy last=Central;
            int times=1;
            int i=0;
            
            
            //insert to S_Battle_list
            if(Central.s_army!=null){
                Army tempS_Army=Central.s_army;
                while(tempS_Army!=Central.sentinel){
                    S_Battle newSoldier=new  S_Battle(tempS_Army.id,Central.id,tempS_Army.type);
                    if(S_battle_list==null)S_battle_list=newSoldier;
                    else S_battle_alter.next=newSoldier;
                    S_battle_alter=newSoldier;
                    tempS_Army=tempS_Army.next;
                }
            }
            
            while(true){
                i++;
                for(int j=0;j<times;j++){
                    if(i%2==0)Central=Central.prev;
                    else Central=Central.next;
                }
                if(Central==last)break;
                last=Central;
                //insert to S_Battle_list
                if(Central.s_army!=null){
                    Army tempS_Army=Central.s_army;
                    while(tempS_Army!=Central.sentinel){
                        S_Battle newSoldier=new  S_Battle(tempS_Army.id,Central.id,tempS_Army.type);
                        if(S_battle_list==null)S_battle_list=newSoldier;
                        else S_battle_alter.next=newSoldier;
                        S_battle_alter=newSoldier;
                        tempS_Army=tempS_Army.next;
                    }
                }
                times++;
            }
            
            S_Battle s=S_battle_list;
            System.out.print("S Battle = ");
            while(s!=null){
                System.out.print("<"+s.soldier_id+","+s.satrapy_id+","+s.type+"> ");
                s=s.next;
            }

            return true;
	}
        

	/**
	 * @brief The death of a soldier
	 * 
	 * @param sid  The soldier's id
	 * @param flag If 0, the soldier belongs to a Satrapy
	 *             If 1, the soldier belongs to Alexander the Great
	 *
	 * @return True on success
	 *         False on failure
	 */
	public static boolean soldiers_death (int sid, int flag)
	{
            
            if(flag==0){
                S_Battle s=S_battle_list;
                S_Battle prev=null;
                while(s!=null){
                    if(s.soldier_id==sid){
                        if(s==S_battle_list)S_battle_list=s.next;
                        else prev.next=s.next;
                    }
                    prev=s;
                    s=s.next;
                }
                
                
            }
            else if(flag==1){
                GA_Battle s=GA_battle_list;
                GA_Battle prev=null;
                while(s!=null){
                    if(s.soldier_id==sid){
                        if(s==GA_battle_list)GA_battle_list=s.next;
                        else prev.next=s.next;
                    }
                    prev=s;
                    s=s.next;
                } 
            }
            
            System.out.print("GA Battle = ");
            GA_Battle temp=GA_battle_list;
            while(temp!=null){
                System.out.print("<"+temp.soldier_id+","+temp.horse_id+","+temp.general+"> ");
                temp=temp.next;
            }
            System.out.println();
            
            System.out.print("Satrapy Battle = ");
            
            S_Battle s=S_battle_list;
            
            while(s!=null){
                System.out.print("<"+s.soldier_id+","+s.satrapy_id+","+s.type+"> ");
                s=s.next;
            }
            
            System.out.println();
           
            return true;
	}


	/**
	 * @brief Victory of Alexander the Great's horsemen
	 *
	 * @return True on success
	 *         False on failure
	 */
	public static boolean GA_horsemen_victory ()
	{
            
            S_Battle s=S_battle_list;
            S_Battle prev=null;
            S_Battle store;
            
            while(s!=null){
                if(s.type==1){
                    store=s.next;
                    //if s exists in the list the delete it or dont
                    if(S_battle_list==s)S_battle_list=s.next;
                    else prev.next=store;
                    s=store;
                }
                else{
                    prev=s;
                    s=s.next;
                }
            
            }
            
            GA_Battle g=GA_battle_list;
            GA_Battle prevG=null;
            GA_Battle storeG; //keep the next 
            
            int num=0;
            
            while(g!=null){
                if(g.horse_id==-1){
                    storeG=g.next;
                    num++;
                    if(num==3){
                        num=0;
                        //every 3rd soldier delete the 3rd soldier
                        if(GA_battle_list==g)GA_battle_list=g.next;
                        else prevG.next=storeG;
                        g=storeG;
                    }
                    else{
                        prevG=g;
                        g=storeG;
                    }
                }
                else{
                    prevG=g;
                    g=g.next;
                }

            }
            
            System.out.print("GA Battle = ");
            
            GA_Battle temp=GA_battle_list;
            
            while(temp!=null){
                System.out.print("<"+temp.soldier_id+","+temp.horse_id+","+temp.general+"> ");
                temp=temp.next;
            }
            System.out.println();
            
            System.out.print("Satrapy Battle = ");
            
            S_Battle temp2=S_battle_list;
            
            while(temp2!=null){
                System.out.print("<"+temp2.soldier_id+","+temp2.satrapy_id+","+temp2.type+"> ");
                temp2=temp2.next;
            }
            System.out.println();
                   
            return true;
	}

	/**
	 * @brief The death of Alexander the Great
	 *
	 * @return True on success
	 *         False on failure
	 */
	public static boolean Alexanders_death ()
	{
            
            GA_Battle list=GA_battle_list;
            GA_Battle Gen0=null;
            GA_Battle Gen1=null;
            GA_Battle Gen2=null;
            GA_Battle Gen3=null;
            GA_Battle Gen4=null;
            //similar to event p
            //the only difference is now sort generals
            while(list != null){
		GA_Battle temp=new GA_Battle(list.soldier_id,list.horse_id,list.general);
                temp.next=null;
		if(list.general == 0){
			temp.next=Gen0;
			Gen0=temp;			
		}
		else if(list.general == 1){
			temp.next=Gen1;
			Gen1=temp;
		}		
		else if(list.general == 2){
			temp.next=Gen2;
			Gen2=temp;
		}
		else if(list.general == 3){
			temp.next=Gen3;
			Gen3=temp;
		}
		else if(list.general == 4){
			temp.next=Gen4;
			Gen4=temp;			
		}
		list=list.next;
            }
            //Start Printing lists 
            //after every print change the list
            list=Gen0;
            System.out.print("General <"+"0"+"> = "); 
            while(list != null){
		System.out.print("<"+list.soldier_id+","+list.horse_id+">");
		if(list.next!=null)System.out.print(",");
		list=list.next;
            }
            System.out.println();
            list=Gen1;
            System.out.print("General <"+"1"+"> = "); 
            while(list != null){
		System.out.print("<"+list.soldier_id+","+list.horse_id+">");
		if(list.next!=null)System.out.print(",");
		list=list.next;
            }
            System.out.println();
            list=Gen2;         
            System.out.print("General <"+"2"+"> = "); 
            while(list != null){
		System.out.print("<"+list.soldier_id+","+list.horse_id+">");
		if(list.next!=null)System.out.print(",");
		list=list.next;
            }
            System.out.println();
            list=Gen3;
            System.out.print("General <"+"4"+"> = "); 
            while(list != null){
		System.out.print("<"+list.soldier_id+","+list.horse_id+">");
		if(list.next!=null)System.out.print(",");
		list=list.next;
            }
            System.out.println();
            list=Gen4;
            System.out.print("General <"+"5"+"> = "); 
            while(list != null){
		System.out.print("<"+list.soldier_id+","+list.horse_id+">");
		if(list.next!=null)System.out.print(",");
		list=list.next;
            }
            System.out.println();
            
            return true;
	}

	/**
	 * @brief Print all soldiers of Alexander the Great
	 *
	 * @return True on success
	 *         False on failure
	 */
	public static boolean print_GA_soldiers ()
	{
            String msg="";
            msg+="GA Soldiers = ";
            Army Link=GA_Army[0];
            while(Link != null){
                msg+=Link.display();
                Link = Link.next;
                if(Link!=null){
                    msg+=",";
                }
            }
            System.out.println(msg);
            msg="";
            msg+="GA Horses = ";
            Link=GA_Army[1];
            while(Link != null){
                msg+=Link.display();
                Link = Link.next;
                if(Link!=null){
                    msg+=",";
                }
            }
            System.out.println(msg);
            return true;
	}

	/**
	 * @brief Print all Satrapy soldiers
	 *
	 * @return True on success
	 *         False on failure
	 */
	public static boolean print_S_soldiers ()
	{
            
            Satrapy temp=satrapies_list;
            
            for(int i=0;i<insertionSatrapy;i++){
                if(temp!=null){
                    System.out.print("Satrapy "+"<"+temp.id+">"+" = ");
                    Army satrapyS=temp.s_army; 
                    for(int j=0;j<insertionSatrapySoldier;j++){
                        if(satrapyS!=null){
                            System.out.print("<"+satrapyS.id+","+satrapyS.type+","+satrapyS.general+">");
                            if(satrapyS.next!=null)System.out.print(",");
                            satrapyS=satrapyS.next;
                        }
                    }
                    System.out.println();

                    temp=temp.next;
               }
            }

            return true;
	}


	/**
     * @param args
     * @throws java.io.FileNotFoundException
	 * @brief The main function
	 *
	 * @param argc Number of arguments
	 * @param argv Argument vector
	 *
	 * @return True on success
	 *         False on failure
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		BufferedReader inputFile;
		String line;
		String [] params;

		/* Check command buff arguments */       
		if (args.length != 1) {
			System.err.println("Usage: <executable-name> <input_file>");
			System.exit(0);
		}

		/* Open input file */        
		inputFile = new BufferedReader(new FileReader(args[0]));

		/* Read input file and handle the events */
		while ((line = inputFile.readLine()) != null) {

			if (line.length() == 0) continue;

			System.out.println(">>> Event: " + line);
			params = line.split(" ");
			char eventID = line.charAt(0);

			switch(eventID) {

				/* Comment */
				case '#':
					break;

					/* Register GA soldier
					 * R <sid> <type> <general> */
				case 'R':
					{
						int sid = Integer.parseInt(params[1]);
						int type = Integer.parseInt(params[2]);
						int general = Integer.parseInt(params[3]);

						if (register_GA_soldier(sid, type, general))
                                                    
							System.out.println("R " + sid + " " + type + " " + general + " succeeded");
						else
							System.err.println("R " + sid + " " + type + " " + general + " failed");

						break;
					}

					/* Register GA horse
					 * H <hid> <type> <general> */
				case 'H':
					{
						int hid = Integer.parseInt(params[1]);
						int type = Integer.parseInt(params[2]);
						int general = Integer.parseInt(params[3]);

						if (register_GA_horse(hid, type, general))
							System.out.println("H " + hid + " " + type + " " + general + " succeeded");
						else
							System.err.println("H " + hid + " " + type + " " + general + " failed");

						break;
					}

					/* Register Satrapy
					 * S <sid> */
				case 'S':
					{   
						int sid = Integer.parseInt(params[1]);

						if (register_satrapy(sid))
							System.out.println("S " + sid + " succeeded");
						else
							System.err.println("S " + sid + " failed");

						break;
					}

					/* Register Satrapy soldier
					 * A <sid> <aid> <type> <general> */
				case 'A':
					{
						int sid = Integer.parseInt(params[1]);
						int aid = Integer.parseInt(params[2]);
						int type = Integer.parseInt(params[3]);
						int general = Integer.parseInt(params[4]);

						if (register_S_soldier(sid, aid, type, general))
							System.out.println("A " + sid + " " + aid + " " + type + " " + general + " succeeded");
						else
							System.err.println("A " + sid + " " + aid + " " + type + " " + general + " failed");

						break;
					}

					/* Prepare Alexander's soldiers for battle
					 * P */
				case 'P':
					{
						if (prepare_for_battle_GA())
							System.out.println("P succeeded");
						else
							System.err.println("P failed");

						break;
					}

					/* Prepare Satrapies for battle
					 * B <sid> */
				case 'B':
					{
						int sid = Integer.parseInt(params[1]);

						if (prepare_for_battle_S(sid))
							System.out.println("B " + sid + " succeeded");
						else
							System.err.println("B " + sid + " failed");

						break;
					}

					/* The death of a soldier
					 * K <sid> <flag> */
				case 'K':
					{
						int sid = Integer.parseInt(params[1]);
						int flag = Integer.parseInt(params[2]);

						if (soldiers_death(sid, flag))
							System.out.println("K " + sid + " " + flag + " succeeded");
						else
							System.err.println("K " + sid + " " + flag + " failed");

						break;
					}

					/* Victory of Alexander the Great's horsemen
					 * V */
				case 'V':
					{                    
						if (GA_horsemen_victory())
							System.out.println("V succeeded");
						else
							System.err.println("V failed");

						break;
					}

					/* The death of Alexander the Great
					 * D */
				case 'D':
					{
						if (Alexanders_death())
							System.out.println("D succeeded");
						else
							System.err.println("D failed");

						break;
					}

					/* Print all soldiers of Alexander the Great
					 * X */
				case 'X':
					{
						if (print_GA_soldiers())
							System.out.println("X succeeded");
						else
							System.err.println("X failed");

						break;
					}

					/* Print all Satrapy soldiers
					 * Y */
				case 'Y':
					{
						if (print_S_soldiers())
							System.out.println("Y succeeded");
						else
							System.err.println("Y failed");

						break;
					}

					/* Empty line */
				case '\n':
					break;

					/* Ignore everything else */
				default:
					System.out.println("Ignoring " + line);
					break;
			}
		}
	}
}
