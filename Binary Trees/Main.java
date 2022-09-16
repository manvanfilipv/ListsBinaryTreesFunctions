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
        static String message="";
        static String hash="";
        static int max_horses;
        static int max_soldiers_g;
        static int max_soldiers_id_g;
        
        static int [] primes_g=new int[] { 5,7,  11,  13,  17,  19,  23,  29,  31,  37,
        41,  43,  47,  53,  59,  61,  67,  71,  73,  79,
        83,  89,  97, 101, 103, 107, 109, 113, 127, 131,
        137, 139, 149, 151, 157, 163, 167, 173, 179, 181,
        191, 193, 197, 199, 211, 223, 227, 229, 233, 239,
        241, 251, 257, 263, 269, 271, 277, 281, 283, 293,
        307, 311, 313, 317, 331, 337, 347, 349, 353, 359,
        367, 373, 379, 383, 389, 397, 401, 409, 419, 421,
        431, 433, 439, 443, 449, 457, 461, 463, 467, 479,
        487, 491, 499, 503, 509, 521, 523, 541, 547, 557,
        563, 569, 571, 577, 587, 593, 599, 601, 607, 613,
        617, 619, 631, 641, 643, 647, 653, 659, 661, 673,
        677, 683, 691, 701, 709, 719, 727, 733, 739, 743,
        751, 757, 761, 769, 773, 787, 797, 809, 811, 821,
        823, 827, 829, 839, 853, 857, 859, 863, 877, 881,
        883, 887, 907, 911, 919, 929, 937, 941, 947, 953,
        947, 953, 967, 971, 977, 983, 991, 997, 1009, 1013, 1019, 1021, 1031, 1033, 1039, 1049, 1051, 1061, 1063, 1069,
        1087, 1091, 1093, 1097, 1103, 1109, 1117, 1123, 1129, 1151, 1153, 1163, 1171, 1181, 1187, 1193, 1201, 1213, 1217, 1223,
        1229, 1231, 1237, 1249, 1259, 1277, 1279, 1283, 1289, 1291, 1297, 1301, 1303, 1307, 1319, 1321, 1327, 1361, 1367, 1373,
        1381, 1399, 1409, 1423, 1427, 1429, 1433, 1439, 1447, 1451, 1453, 1459, 1471, 1481, 1483, 1487, 1489, 1493, 1499, 1511,
        1523, 1531, 1543, 1549, 1553, 1559, 1567, 1571, 1579, 1583, 1597, 1601, 1607, 1609, 1613, 1619, 1621, 1627, 1637, 1657,
        1663, 1667, 1669, 1693, 1697, 1699, 1709, 1721, 1723, 1733, 1741, 1747, 1753, 1759, 1777, 1783, 1787, 1789, 1801, 1811,
        1823, 1831, 1847, 1861, 1867, 1871, 1873, 1877, 1879, 1889, 1901, 1907, 1913, 1931, 1933, 1949, 1951, 1973, 1979, 1987,
        1993, 1997, 1999, 2003, 2011, 2017, 2027, 2029, 2039, 2053, 2063, 2069, 2081, 2083, 2087, 2089, 2099, 2111, 2113, 2129,
        2131, 2137, 2141, 2143, 2153, 2161, 2179, 2203, 2207, 2213, 2221, 2237, 2239, 2243, 2251, 2267, 2269, 2273, 2281, 2287,
        2293, 2297, 2309, 2311, 2333, 2339, 2341, 2347, 2351, 2357, 2371, 2377, 2381, 2383, 2389, 2393, 2399, 2411, 2417, 2423,
        2437, 2441, 2447, 2459, 2467, 2473, 2477, 2503};
        
        static Army_Tree a =new Army_Tree(null);
        static HorsePQ h = new HorsePQ();
        static GA_Tree e = new GA_Tree();
        static HashTable hs = new HashTable();
        


	/**
     * @param a
     * @param b
     * @param c
	 * @brief Optional function to initialize data structures that 
	 *        need initialization
	 *
	 * @return true on success
	 *         false on failure
	 */
	public static boolean initialize(int a,int b,int c)
	{
            max_horses=a;
            max_soldiers_g=b;
            max_soldiers_id_g=c;
            return true;
	}

	/**
	 * @brief Register Alexander the Great soldier 
	 *
	 * @param sid     The soldiers id
	 * @return True on success
	 *         False on failure
	 */
	public static boolean register_GA_soldier (int sid)
	{
            a.add(new Army(sid));
            return true;
	}

	/**
     * @param hid
	 * @brief Register Alexander the Great horse
	 * @param age     The horse's age
	 *
	 * @return True on success
	 *         False on failure
	 */
	public static boolean register_GA_horse (int hid, int age)
	{
            h.horse(hid, age);

            return true;
	}

	/**
	 * @brief Register Satrapy soldier
	 * @param aid     The soldiers id
	 *
	 * @return True on success
	 *         False on failure
	 */
	public static boolean register_AR_soldier (int aid)
	{   
            hs.assign(aid,primes_g);
            return true;
	}

	/**
	 * @brief Prepare Alexanders soldiers for battle
	 * 
	 * @return True on success
	 *         False on failure
	 */
	public static boolean prepare_for_battle_GA ()
	{
            h.addToStack();
            h.setPriorityQueue();
            a.TransitGAsoldiers();
            e.printGAbattle();
            message=e.printGAbattleOLD();
            return true;
	}

	/**
	 * @brief The ambush against Alexander the Great
	 *
	 * @param X  Kill 1-every-X soldiers
	 * @return True on success
	 *         False on failure
	 */
	public static boolean ambush_GA (int X)
	{
            e.delete(X);
            e.printGAbattle();
            return true;
	}

	/**
	 * @brief Victory of Alexander the Great's army
	 *
	 * @return True on success
	 *         False on failure
	 */
	public static boolean GA_victory ()
	{
            hash=hs.printOLD();
            e.Transit_GA_battle(hs);
            hs.print();
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
            e.printGenerals("General 0 = ",500);
            e.split(500);
            e.printGenerals("General 1 = ",1000);
            e.split(1000);
            e.printGenerals("General 2 = ",1500);
            e.split(1500);
            e.printGenerals("General 3 = ",2000);
            e.split(2000);
            e.printGenerals("General 4 = ",2500);
            e.split(2500);
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
            a.printGAsoldiers();
            return true;
	}

	/**
	 * @brief Print all horses of Alexander the Great
	 *
	 * @return True on success
	 *         False on failure
	 */
	public static boolean print_GA_horses ()
	{
            h.printHorses();
            return true;
	}

	/**
	 * @brief Print Alexander the Great army
	 *
	 * @return True on success
	 *         False on failure
	 */
	public static boolean print_GA_army ()
	{
            System.out.println(message);
            return true;
	}

	/**
	 * @brief Print all soldiers of Alexander the Great
	 *
	 * @return True on success
	 *         False on failure
	 */
	public static boolean print_AR_army ()
	{
            System.out.println(hash);
            return true;
	}


	/**
     * @param args
     * @throws java.io.FileNotFoundException
	 * @brief The main function
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

		int max_horses = Integer.parseInt(inputFile.readLine());
                
		System.out.println("Max horses: " + max_horses);

		int max_soldiers_g = Integer.parseInt(inputFile.readLine());
                
		System.out.println("Max enemy soldiers: " + max_soldiers_g);

		int max_soldiers_id_g = Integer.parseInt(inputFile.readLine());
                
		System.out.println("Max enemy soldiers' ID: " + max_soldiers_id_g);
                
                initialize(max_horses,max_soldiers_g,max_soldiers_id_g);
                
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

						if (register_GA_soldier(sid))
							System.out.println("R " + sid + " succeeded");
						else
							System.err.println("R " + sid + " failed");

						break;
					}

					/* Register GA horse
					 * H <hid> <type> <general> */
				case 'H':
					{
						int hid = Integer.parseInt(params[1]);
						int age = Integer.parseInt(params[2]);

						if (register_GA_horse(hid, age))
							System.out.println("H " + hid + " " + age + " succeeded");
						else
							System.err.println("H " + hid + " " + age + " failed");

						break;
					}
					/* Register Satrapy soldier
					 * A <sid> <aid> <type> <general> */
				case 'A':
					{
						int aid = Integer.parseInt(params[1]);

						if (register_AR_soldier(aid))
							System.out.println("A " + " " + aid + " succeeded");
						else
							System.err.println("A " + " " + aid + " failed");

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

					/* Ambush against Alexander the Great
					 * T <X> */
				case 'T':
					{
						int X = Integer.parseInt(params[1]);

						if (ambush_GA(X))
							System.out.println("T " + X + " succeeded");
						else
							System.err.println("T " + X + " failed");

						break;
					}

					/* Victory of Alexander the Great's army
					 * K */
				case 'K':
					{                    
						if (GA_victory())
							System.out.println("K succeeded");
						else
							System.err.println("K failed");

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
						if (print_GA_horses())
							System.out.println("Y succeeded");
						else
							System.err.println("Y failed");

						break;
					}
					/* Print of Alexander the Great army
				 * Z */
				case 'Z':
					{
						if (print_GA_army())
							System.out.println("Z succeeded");
						else
							System.err.println("Z failed");

						break;
					}
					/* Print of Alexander the Great army
				 * W */
				case 'W':
					{
						if (print_AR_army())
							System.out.println("W succeeded");
						else
							System.err.println("W failed");

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
