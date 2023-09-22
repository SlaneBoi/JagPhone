import java.util.*;
public class UAP {
	
// Joseph William Indarto
// 2501964011
	Scanner scan;
	Random rand;
	
	int menuSelect;
	int phonePrice;
	String phoneName;
	String phoneType;
	String phoneID;
	String phoneDelete;

	ArrayList<String> phoneTypeList;
	ArrayList<String> phoneNameList;
	ArrayList<String> phoneIDList;
	ArrayList<Integer> phonePriceList;
	
	final String intErrorMessage = "Input can only be numerics!";
	
	public void initialization() {
		scan = new Scanner(System.in);
		rand = new Random();
		
		menuSelect = 0;
		phonePrice = 0;
		phoneName = null;
		phoneType = null;
		phoneID = null;
		phoneDelete = null;
		
		phoneTypeList = new ArrayList<>();
		phoneNameList = new ArrayList<>();
		phoneIDList = new ArrayList<>();
		phonePriceList = new ArrayList<>();
	}

	public void mainMenu()  {
		
		System.out.println("============================");
		System.out.println("         JaGPhone     	    ");
		System.out.println("============================");
		System.out.println("1. Add New Handphone     	");
		System.out.println("2. View All Handphone   	");
		System.out.println("3. Delete Handphone  		");
		System.out.println("4. Exit						");
		System.out.println("============================");

		do{
			try{
				System.out.printf("Choose >> ");
				menuSelect = scan.nextInt(); scan.nextLine();
			}catch(Exception e){
				menuSelect = -1;
				System.out.println(intErrorMessage);
			}finally{
				if(menuSelect == -1){
					scan.nextLine();
				}
			}
			}while(!(menuSelect >= 1 && menuSelect <=5));

			switch (menuSelect) {
			case 1: 
			cls();
			addPhone();
			System.out.println("Press Enter to return to Main Menu...");
			scan.nextLine();
			mainMenu();
			break;

			case 2: 
			cls();
			viewPhone();
			System.out.println("Press Enter to Return to Main Menu...");
			scan.nextLine();
			mainMenu();
			break;

			case 3: 
			cls();
			deletePhone();
			System.out.println("Press Enter to return to Main Menu...");
			scan.nextLine();
			mainMenu();
			break;

			case 4: 
			System.exit(0);
			break;
			}

	}
	
	public void addPhone() {
		do{
			System.out.printf("Input Handphone Name [3-10 characters & Alphanumeric] : ");
			phoneName = scan.nextLine();
			}while(!(phoneName.length() >= 3 && phoneName.length() <= 10) && phoneName.matches("[A-Za-z0-9]+"));
		do{
			System.out.printf("Input Phone Type [Apple | Samsung | Xiaomi] [Case-Sensitive] : ");
			phoneType = scan.nextLine();
			}while(!(phoneType.equals("Apple") || phoneType.equals("Samsung") || phoneType.equals("Xiaomi")));
		do{
			try{
			System.out.printf("Input Handphone Price [1,000,000 - 30,000,000]: ");
			phonePrice = scan.nextInt(); scan.nextLine();
			}catch(Exception e){
				phonePrice = -1;
				System.out.println(intErrorMessage);
			}finally{
				if(phonePrice == -1){
					scan.nextLine();
				}
			}
			}while(!(phonePrice >= 1000000 && phonePrice <= 30000000));
		
		phoneID = "H" + "P" + "" + rand.nextInt(9) + ""+rand.nextInt(9) + ""+rand.nextInt(9);
		ArrayList(phoneName, phoneType, phoneID, phonePrice);
	}

	public void viewPhone() {
		if(phoneNameList.isEmpty()){
			System.out.println("There are No Handphone!");
		}else{
			sortingAscendingPrice();
			System.out.println("===============================================");
			System.out.println("ID - Handphone Name - Type - Price");
			System.out.println("===============================================");
			for(int i = 0 ; i < phoneNameList.size() ; i++){
			System.out.println(i+1 + ". " + phoneIDList.get(i) + " - " + phoneNameList.get(i) + " - " + phoneTypeList.get(i) + " - Rp." + phonePriceList.get(i));
			}
			System.out.println("===============================================");
		}
	}

	public void deletePhone() {
		if(phoneNameList.isEmpty()){
			System.out.println("There are No Handphone!");
		} else{
			viewPhone();
			System.out.print("Input Handphone ID to Delete:");
	        phoneDelete = scan.nextLine();

	        if(phoneIDList.contains(phoneDelete)) {

	            int index = phoneIDList.indexOf(phoneDelete);
	            phoneIDList.remove(index);
	            phoneNameList.remove(index);
	            phoneTypeList.remove(index);
	            phonePriceList.remove(index);

	            System.out.println("Handphone ID deleted");

	        } else if (!phoneIDList.contains(phoneDelete)) {
	            System.out.println("Handphone ID not found!");
	        }
	    }
	}

	
	public void cls(){
		for(int i = 0 ; i < 30 ; i++){
			System.out.println("\n");
		}
	}
	
	public void ArrayList(String phoneType, String phoneName, String phoneID, int phonePrice){
		phoneNameList.add(phoneName);
		phoneTypeList.add(phoneType);
		phoneIDList.add(phoneID);
		phonePriceList.add(phonePrice);
		
		System.out.println("Handphone successfully added!");
	}
	
	public void sortingAscendingPrice(){
		for(int i = 0 ; i < phonePriceList.size() ; i++){
			for(int j = i+1 ; j < phonePriceList.size() ; j++){
				if(phonePriceList.get(i).compareTo(phonePriceList.get(j)) > 0){
					String tempID = phoneIDList.get(j);
					phoneIDList.set(j, phoneIDList.get(i));
					phoneIDList.set(i, tempID);
					
					String tempName = phoneNameList.get(j);
					phoneNameList.set(j, phoneNameList.get(i));
					phoneNameList.set(i, tempName);
					
					String tempType = phoneTypeList.get(j);
					phoneTypeList.set(j, phoneTypeList.get(i));
					phoneTypeList.set(i, tempType);
					
					int tempPrice = phonePriceList.get(j);
					phonePriceList.set(j, phonePriceList.get(i));
					phonePriceList.set(i, tempPrice);
				}
			}
		}
	}
	
	public boolean checkPhoneID(String phoneDelete){
		boolean isFound = false;
		
		for(int i = 0 ; i < phoneIDList.size() ; i++){
			if(phoneDelete.equals(phoneIDList.get(i))){
				isFound = true;
				break;
			}
		}
		return isFound;
	}
	
	public UAP() {
		initialization();
		mainMenu();
	}

	public static void main(String[] args) {
		new UAP();
	}

}
