// https://www.w3schools.com/java/java_packages.asp
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

// https://www.baeldung.com/java-binary-tree 
// https://www.geeksforgeeks.org/introduction-to-binary-tree-data-structure-and-algorithm-tutorials/
class node
{
    node left;
    node right;
    String name;
    String number;
    String address;

    // https://www.javatpoint.com/binary-tree-java
    public node(String name, String pnum, String address) {
        left = null;
        right = null;
        this.name = name;
        this.number = pnum;
        this.address = address;
    }
}

// https://www.javatpoint.com/binary-tree-java
class BinaryTree {
    Scanner sc = new Scanner(System.in);
    public static node root;
    String name;
    String pnum;
    String address;
    int ch;

    BinaryTree() {
        root = null;
    }

    //Functions for each pick when between 1-6 the user can select from

    // Used this function to return a number entered by the user and we then check to see down below if this number 
    // already exists in the phonebook
    int flag = 0; 
    String checknumber(String number)
    {
        return number;
    }

    // Create function - Allows us to be able to create/ make a new contact
    public void create()
    {
        // Allows user input into the program
        Scanner sc = new Scanner(System.in);
        int ch, flag = 0, flag1 = 0;
        node ptr, any;
        do {
            // Getting user name, phone number and address
            System.out.println("\nEnter name: ");
            name = sc.next();
            System.out.println("Enter phone number: ");
            pnum = sc.next();
            // Checking if number already exists and if it does we then leave the function and print out our error message 
            // and then go back into our checknumber function to enter in another number that doesnt exist.
            pnum=checknumber(pnum);
            any = root;
            while (any != null) {
                if (any.number.compareTo(pnum) == 0) {
                    flag = 1;
                    break;
                }
                if ((any.number).compareTo(pnum) > 0) {
                    any = any.left;
                } else {
                    any = any.right;
                }
            }
            // Number checker to see if we have any phone numbers that already exist, error message comes up if same
            // number is entered. If its the same number, you will be asked to put in another number when we return 
            // to our function
            if (flag == 1) {
                System.out.println("\nThis number already exists, please enter a new number!");
                pnum=sc.next();
                pnum=checknumber(pnum);
            }
            // Enter user address from the input
            System.out.println("Enter address : ");
            address = sc.next();
            node temp = new node(name, pnum, address);
            if (root == null) {
                root = temp;
            }

            else {
                ptr = root;
                while (flag == 0) {
                    if ((temp.name).compareTo(ptr.name) > 0) {
                        if (ptr.right != null) {
                            ptr = ptr.right;
                        } else {
                            ptr.right = temp;
                            flag = 1;
                        }
                    }
                    if ((temp.name).compareTo(ptr.name) < 0) {
                        if (ptr.left != null) {
                            ptr = ptr.left;
                        } else {
                            ptr.left = temp;
                            flag = 1;
                        }
                    }
                }
            }
            flag = 0;

            System.out.println("");
            System.out.println("Do you want to add another contact? (0/1): ");
			System.out.println("0 = No, 1 = Yes");
			System.out.println("\nPlease enter your pick: ");
            ch = sc.nextInt();
        } while (ch != 0);
    }// create()

    public void insert() {
        create();
    }

    // Search function - we search up a contact via the name one wants to look up
    public void search() {
        int flag = 0;
        node ptr;
        System.out.println("Enter Name");
        String key = sc.next();
        ptr = root;
        // If the name we want to search up exists in the phonebook, we will be able to print it out
        // along with the phone number and address
         while (ptr != null)
         {
            if (ptr.name.compareTo(key) == 0) { // compareTo() method compares the given string with the current string lexicographically.
                flag = 1;
                System.out.println("\n-----------------RECORD FOUND-----------------");
                System.out.println("NAME\t|  CONTACT NUMBER  |\tADDRESS");
                System.out.println("" + ptr.name + "\t    " + ptr.number + "\t      " + "\t\t" + ptr.address);
                break;
            }
            if ((ptr.name).compareTo(key) > 0) {
                ptr = ptr.left;
            } else {
                ptr = ptr.right;
            }
        }
        // If theres no contact to search up, then this message comes up
        if (flag == 0) {
            System.out.println("----------------RECORD NOT FOUND-----------------");
            System.out.println("No contacts are present, try adding a contact");
        }
    }

    // Delete function - aim was to get the contact name to be deleted - had issues with this
    // https://www.geeksforgeeks.org/deletion-binary-tree/
    // https://www.geeksforgeeks.org/binary-search-tree-set-2-delete/
    public void delete() {
        int flag = 0;
        node ptr, parent = null;
        System.out.println("Enter the contact name you want to delete.");
        Scanner scan = new Scanner(System.in);
        String key = scan.next();
        ptr = root;
        while (ptr != null) {
            if ((ptr.name).compareTo(key) == 0)
            {
                flag = 1;
                break;
            }
            if ((ptr.name).compareTo(key) > 0)
            {
                parent = ptr;
                ptr = ptr.left;
            }
            else
            {
                parent = ptr;
                ptr = ptr.right;
            }
        }
        // https://stackoverflow.com/questions/64894223/how-to-add-delete-method-in-binary-search-tree-java
        // https://www.javatpoint.com/deletion-in-binary-search-tree
        System.out.println(ptr.name);
        if (flag == 1) {
            if (ptr.left != null && ptr.right == null) {
                if (parent.left == ptr) {
                    parent.left = ptr.left;
                } else {
                    parent.right = ptr.left;
                }
            }
            // https://www.digitalocean.com/community/tutorials/binary-search-tree-bst-search-insert-remove
            if (ptr.left == null && ptr.right != null) {
                if (parent.left == ptr) {
                    parent.left = ptr.right;
                } else {
                    parent.right = ptr.right;
                }
            }
            if (ptr.left != null && ptr.right != null) {
                node p;
                p = ptr.left;
                System.out.println("ptr= " + ptr.name);
                while (p.right != null) {
                    parent = p;
                    p = p.right;
                }
                ptr.name = p.name;
                ptr.number = p.number;
                if (p.left != null) {
                    parent.right = p.left;
                } else {
                    ptr.left = null;
                }
            }
            // https://www.youtube.com/watch?v=JsgqnTLjfps
            if (ptr.left == null && ptr.right == null) {
                if (parent.left == ptr) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
        }
    }

    // Inorder traversal of binary search tree will produce the output in acending order.
    public void inorder(node root)

    {
          if (root != null) {
            inorder(root.left);
            System.out.println("" + root.name + "\t    " + root.number + "\t      " + root.address);
            inorder(root.right);
        }
    }

    public void display() {
        inorder(root);
    }
}

public class oo {
    public static void main(String[] args) throws FileNotFoundException {
        BinaryTree b = new BinaryTree();
        Scanner sc = new Scanner(System.in);
        int ch, ch1;

        // Prints out our introduction for our built-in Java shell program
		System.out.print("****************************************************************");
		System.out.print("\n\n\n*********COLIN AND GIDEON'S SHELL - 20403046 & 20364806*********");
		System.out.print("\n\n\t---------USE AT YOUR OWN RISK----------");
		System.out.print("\n\n------Unsure of what commands to use? Type '5'------");
		System.out.print("\n\n\n****************************************************************\n");
		/*https://stackoverflow.com/questions/3342651/i-get-exception-when-using-thread-sleepx-or-wait got help with the sleep function*/
		try {
			Thread.sleep(1000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		System.out.print("Welcome User to Colin & Gideon's custom Java command shell program!\n");
		System.out.print("----------------------------------------------------------------\n");
		try {
			Thread.sleep(800);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		System.out.println("\n\tLet's begin, here are your options down below!\n");
	    do { // Everytime we continue in using the program our menu will be printed to show menu options
			System.out.print("\n-----------------------------MENU-------------------------------");
            System.out.print("\n 1. Add Contact \n 2. Delete Contact \n 3. Search Contact \n 4. Show All Contacts \n 5. Show 'README' Instructions \n 6. Quit Program\n");
            System.out.println("\nEnter your pick: ");
            ch = sc.nextInt();
            switch (ch) { // Depending on which option is picked it will trigger the selected function
            case 1:b.create();
                   break;
            case 2:b.delete();
                   break;
            case 3: b.search();
                   break;
            case 4:System.out.println("NAME\t|  CONTACT NUMBER  |\tADDRESS");
                   b.display();
                   break;
            // https://www.youtube.com/watch?v=lHFlAYaNfdo&list=PL59LTecnGM1Mg6I4i_KbS0w5bPcDjl7oz&index=8 used this vid to help me
            // find how to open a file in java had to use FileNotFoundException incase no file exists or is available
            case 5:File file = new File("readmej.md");
                   Scanner scan = new Scanner(file);
                   while(scan.hasNextLine()) {
                        System.out.println(scan.nextLine());
                   }
                   break;
            case 6:System.out.println("\nYou are now about to 'EXIT' the phonebook!...");
                   break;
            }
            System.out.println("");
            System.out.println("Do you want to continue using the phonebook?(0/1)");
			System.out.println("0 = No, 1 = Yes");
			System.out.println("\nPlease enter your pick: ");
            ch1 = sc.nextInt();
        } while (ch1==1);
    }
}
