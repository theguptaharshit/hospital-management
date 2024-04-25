import java.util.Scanner;
public class Hospital {
    String password;
    int Store[]= {2,3,1,2,3,1,2,3}; //for storing blood units in inventory
    String bloodtype[]= {"O+","O-","A+","A-","B+","B-","AB+","AB-"};
    String[] doctors = new String[50]; //to store doctors
    Hospital() {
        password = "password";
    }

    //Patients linkedlist 
    patients first; //head of patient linkedlist
    public static class patients { //linkedlist class
        int priority;
        String patientName;
        patients next;
        patients(int p, String n) {
            priority = p;
            patientName = n;
            next = null;
        }
    }

    public void addPatient(String patientName, int priority) { //function to add patients in the queue
        patients p = new patients(priority, patientName);
        if(first == null) {
            first = p;
        } else if(first.priority > p.priority) {
            p.next = first;
            first = p;
        }
        else {
            patients temp = first;
            patients temp2 = first;
            while(temp != null && p.priority >= (temp).priority) {
                temp2 = temp;
                temp = temp.next;
            }
            if(temp == null) {
                temp2.next = p;
            } else {
                p.next = temp;
                temp2.next = p;
            }
        }
        patientPrint();
    }

    public void removePatient() { //function to remove patients from the queue as they are treated
        if(first == null) {
            System.out.println("No patients to remove");
        } else if(first.next == null) {
            System.out.println(first.patientName + " treated! ");
            first = null;
        }
        else {
            System.out.println(first.patientName + " treated! ");
            first = first.next;
            patientPrint();
        }
    }

    public void removePatientFromMid() { //function to remove patients from the queue from the anywhere
        System.out.println("Enter patient to remove: ");
        String nm = inputString();
        boolean flag = false;
        if(first == null) {
            System.out.println("No patients to remove");
            flag = true;
        } else if((first.next == null) && (first.patientName.equalsIgnoreCase(nm))) {
            System.out.println(first.patientName + " removed! ");
            first = null;
            flag = true;
        } else {
            patients temp = first;
            if(first.patientName.equalsIgnoreCase(nm)) {
                System.out.println(first.patientName + " removed! ");
                first = first.next;
                flag = false;
            } else {
                while(temp.next != null) {
                    if(temp.next.patientName.equalsIgnoreCase(nm)) {
                        System.out.println(temp.patientName + " removed! ");
                        break;
                    }
                    temp = temp.next;
                }
                temp.next = temp.next.next;
                flag = true;
            }
        }
        if(!flag) {
            System.out.println("Patient "+nm+" not found!");
        }
        patientPrint();
    }

    //main function
    public static void main(String[] args) { //main function - first menu 
        Hospital hosp = new Hospital();
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        int choice;
        while (flag) {
            System.out.println("Choose one: ");
            System.out.println("Please select an option:");
            System.out.println("1. Patient addition");
            System.out.println("2. Patient treated");
            System.out.println("3. Patient wants to remove");
            System.out.println("4. Print Patient queue");
            System.out.println("5. Blood Panel");
            System.out.println("6. Admin Panel");
            System.out.println("0. Exit");
            choice = hosp.input();
            if(choice == 0) {
                flag = false;
                System.out.println("Exited from main menu.");
            } else if(choice == 1) { 
                hosp.patient();
            } else if(choice == 2) { 
                hosp.removePatient();
            } else if(choice == 3) { 
                hosp.removePatientFromMid();
            } else if(choice == 4) { 
                hosp.patientPrint();
            } else if(choice == 5) {
                hosp.blood();
            } else if(choice == 6) {
                hosp.admin();
            } else {
                System.out.println("Wrong choice, try again");
            }
        }
    }

    //patient addition function
    public void patient() { //main patient function
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Patient name: ");
        String name = sc.nextLine();
        boolean flag = true;
        while(flag) {
            System.out.println("Enter disease type: 1 for extreme, 2 for moderate, 3 for general");
            int priority = input();
            if(priority == 1 || priority == 2 || priority == 3) {
                addPatient(name, priority);
                flag = false;
            }
        }       
    }

    public void patientPrint() { //function to print the name of patients in the queue
        patients temp = first;
        if(first == null) {
            System.out.println("No patients in queue!");
        }
        System.out.println("Patients in queue are as follows: ");
        while(temp!=null) {
            System.out.print(temp.patientName+" - ");
            temp = temp.next;
        }
        System.out.println();
    }

    public void admin() { //main admin function
        System.out.println("Enter Password");
        String pass = inputString();
        if(!pass.equals(password)) {
            System.out.println("Wrong Password!");
            return;
        }
        int option;
        boolean flag = true;
        while(flag) {
            System.out.println("Choose one: ");
            System.out.println("Please select an option: ");
            System.out.println("1. Doctor's Registration");
            System.out.println("2. Change password");
            System.out.println("3. Inventory");
            System.out.println("0. Exit");
            option = input();
            switch (option) {
                case 1:
                    System.out.println("Doctor's Registration");
                    doctor();
                    break;
                case 2:
                    System.out.println("Enter new Password");
                    password = inputString();
                    System.out.println("Password Changed.");
                    break;
                case 3:
                    System.out.println("Inventory management");
                    inventory();
                    break;
                case 0:
                    System.out.println("Exited from admin panel.");
                    flag = false;
                    break;
                default:
                    System.out.println("Wrong choice!");
            }
        }
    }

    //blood donation part
	public void BloodDonate(String[] bt,int[] Store ) { //main donation function 
        System.out.println("Thank you for donating blood! Enter your blood type:");
		Scanner s=new Scanner(System.in);
		String key=s.nextLine();
		int c=-1;
		for(int i=0;i<bt.length;i++) {
			if(key.equalsIgnoreCase(bt[i])) {
				c=i;
			}
		}
		if(c!=-1) {
		    ++Store[c];
		} else {
			System.out.println("Invalid blood group enterd");
		}
		System.out.println("Bloodtype :"+bt[c]+" blood per unit :"+Store[c]);
	}

	public void checkBloodInventory(String[] bt, int[] store) { //function to check blood inventory
		 System.out.println("The current inventory is as follow");
		 for(int i=0;i<bt.length;i++) {
			 System.out.println("Blood Group :"+bt[i]+"Blood per Unit :"+store[i]);
		 }
		
	}
	public void blood() { //main blood donation function
		//bloods are in units
		boolean flag=true;
		while (flag) {
			System.out.println("enter 0 to check inventory || enter 1 to donate || enter 2 to withdraw ||3 to exit");
			int op=input();
			if (op==0) {
				checkBloodInventory(bloodtype,Store);
			}
			else if(op==1) {
				BloodDonate(bloodtype,Store);
			}
			else if(op==2) {
				Bloodwithdraw(bloodtype,Store);
			}
			else if(op==3) {
				System.out.println("Exited;");
			    flag=false;
			} else {
                System.out.println("Wrong choice");
            }			
		}
	}

	public void Bloodwithdraw(String []bt,int[] store) { //blood withdrawl function
		 System.out.println("Enter the blood type you need:");
			Scanner s=new Scanner(System.in);
			String key=s.nextLine();
			int c=-1;
			for(int i=0;i<bt.length;i++) {
				if(key.equals(bt[i])) {
					c=i;
				}
			}
			if(c==-1) {
				System.out.println("Invalid blood group entered");
			} else if(store[c]<1) {
				System.out.println("Not enough blood available!");
			} else {
				--store[c];
			}
			System.out.println("Bloodtype now :"+bt[c]+" blood per unit :"+store[c]);	
	}

    //doctor management
    public void doctor() { //main doctor function
        doctors[0] = "Dr. Ramesh Agarwal";
        doctors[1] = "Dr. Maya Sharma";
        doctors[2] = "Dr. Pramod Jain";
        int len = 3;
        printDoctors(doctors, len);
        boolean flag = true;
        while(flag) {
            System.out.println("Choose one: ");
            System.out.println("Please select an option:");
            System.out.println("1. Doctor addition");
            System.out.println("2. Printing names of Doctors");
            System.out.println("0. Exit");
            int choice = input();
            if(choice == 0) {
                flag = false;
                System.out.println("Exited from doctor menu.");
            } else if(choice == 1) { 
                if(len == doctors.length) {
                    System.out.println("Maximum strength reached!");
                } else {
                    len++;
                    addDoctor(doctors,len);
                    System.out.println("Doctor added: "+doctors[len-1]);
                }
            } else if(choice == 2) {
                printDoctors(doctors, len);
            } else {
                System.out.println("Wrong choice");
            }
        }
    }

    public void addDoctor(String[] doc,int len) { //function to add doctors in the array
        System.out.println("Enter the name of the Doctor");
        doc[len-1] = inputString();
    }

    public void printDoctors(String[] doc, int len) { //function to print names of doctor
        System.out.println("Currently our Doctors are: ");
        for (int i = 0; i < len; i++) {
            System.out.println(doc[i]);
        }
    }  
   
   //inventory management 
   public void inventory() { //main inventory function
        Item newItem1 = new Item("Bed", 10);
        head = newItem1;
        Item newItem2 = new Item("Syringe", 5);
        newItem1.next = newItem2;
        Item newItem3 = new Item("Glucose", 6);
        newItem2.next = newItem3;
        Item newItem4 = new Item("Surgical gloves", 8);
        newItem3.next = newItem4;
        int choice;
        boolean flag = true;
        while(flag) {
            System.out.println("\n1. Add new Inventory\n2. Add Inventory quantity\n3. Remove Inventory\n4. View Inventory\n0. Exit");
            System.out.println("Enter your choice: ");
            choice = input();
            if(choice == 0) {
                flag = false;
                System.out.println("Exited from main menu.");
            } else if(choice == 1) { 
                addNewInventory();
            } else if(choice == 2) {
                addInventory();
            } else if(choice == 3) {
                removeInventory();
            } else if(choice == 4) {
                viewInventory();
            } else {
                System.out.println("Wrong choice, try again");
            }
        }
    }
    Item head;
    static class Item { //inventory item linkedlist
        String name;
        int quantity;
        Item next;

        Item(String name, int quantity) {
            this.name = name;
            this.quantity = quantity;
            next = null;
        }
    }

    public void viewInventory() { //function to print inventory
        Item temp = head;
        while(temp != null) {
            System.out.println(temp.name+" "+temp.quantity);
            temp = temp.next;
        }
    }

    public void addNewInventory() { //function to add new type of inventory
        System.out.println("Enter the item to add: ");
        String name = inputString();
        System.out.println("Enter the quantity: ");
        int quantity = input();
        Item newItem = new Item(name, quantity);
        if(head == null) {
            head = newItem;
        } else {
            Item temp = head;
            while(temp.next != null) {
                temp = temp.next;
            }
            temp.next = newItem;
        }
        System.out.println("Inventory added successfully!");
    }

    public void addInventory() { //function to add quantity of an item in an inventory
        System.out.println("Enter the item in which to add: ");
        String nm = inputString();
        System.out.println("Enter the quantity to be added: ");
        int qty = input();
        Item temp = head;
        boolean flag = false;
        while(temp != null) {
            if((temp.name).equalsIgnoreCase(nm)) {
                temp.quantity += qty;
                flag = true;
                System.out.println(temp.name+" is now "+ temp.quantity);
                break;
            }
            temp = temp.next;
        }
        if(!flag) {
            System.out.println("Inventory not found!");
        }
    }

    public void removeInventory() { //function to remove quantity of an item
        System.out.println("Enter the item in which to remove: ");
        String name = inputString();
        System.out.println("Enter the quantity to be removed: ");
        int quantity = input();
        Item temp = head;
        boolean flag = false;
        while(temp != null) {
            if((temp.name).equalsIgnoreCase(name)) {
                if(quantity <= temp.quantity) {
                    temp.quantity -= quantity;
                    System.out.println(temp.name +" is now "+temp.quantity);
                } else {
                    System.out.println("Insufficient inventory!");
                }                
                flag = true;
            }
            temp = temp.next;
        }
        if(!flag) {
            System.out.println("Inventory not found!");
        }
    }

    //function to input integers
    public int input() { 
		Scanner s = new Scanner(System.in);
		int in=s.nextInt();
		return in;
	}

    //function to input String
    public String inputString() {
        Scanner c = new Scanner(System.in);
        String text = c.nextLine();
        return text;
    }
}