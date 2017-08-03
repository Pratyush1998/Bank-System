/*Name: Pratyush Bhandari
 *Date: 5/18/2016
 *Description: This class is meant to openFiles to read, save files to arrays and sort arrays
 *
 *Method List: 
 *void openFile()
 *void bubbleSortName (Customer custArray[], Savings savArray[], Chequing chqArray[])
 *void bubbleSortName(JLabel labelName[], MyButton btn[])
 *void save(Customer cust[], Savings sav[], Chequing chq[])
 */
 
//importing all needed packages 
import java.io.*;
import javax.swing.*;


public class ArrayLibrary
{
	
	//creating all instance variables
	public static Customer customer[];
	public static Savings savings[];
	public static Chequing chequing[];
	public static JLabel lblName[];
	public static MyButton button[];
	public static int size;
	public static FileWriter fw;
	public static BufferedWriter bw;
	
	
	//this method opens up files and saves, creates savings, chequing and customer arrays, and sorts them by name
	public static void openFile() 
	{ 
		try 
		{
			//open file
			FileReader fr = new FileReader("CustomerInfo.txt"); 
			BufferedReader br = new BufferedReader(fr);

			//set size to the value of the first line
			size = Integer.parseInt(br.readLine()); 

			//setting size of the customer, savings and chequing object arrays to the value of the first line
			customer = new Customer[size]; 
			savings = new Savings[size];
			chequing = new Chequing[size];

			//setting custInfoString to size of first line in file
			String custInfoString[] = new String[size]; 
			
			//declaring and initializing savings and chequing balance arrays
			//setting size to the value of the first line in the file
			double[] sBalance = new double[size]; 
			double[] cBalance = new double[size]; 

			//running through length of the file
			for (int i = 0; i < size; i++) 
			{
				custInfoString[i] = br.readLine(); //read line
				sBalance[i] = Double.parseDouble(br.readLine()); //read savings balance
				cBalance[i] = Double.parseDouble(br.readLine()); //read chequing balance
			}

			//running through size of the file
			for (int i = 0; i < size; i++) 
			{ 
				
				//filling all the array values
				String words[] = custInfoString[i].split(","); 

				customer[i] = new Customer(words[0], words[1]); 

				savings[i] = new Savings(customer[i]); 
				chequing[i] = new Chequing(customer[i]);

				savings[i].setBalance(sBalance[i]); 
				chequing[i].setBalance(cBalance[i]);
			}
			
			//calling bubbleSort method to sort the object arrays
			bubbleSortName(customer, savings, chequing); 
			fr.close(); 
		} 
		
		catch (IOException ex) 
		{
			JOptionPane.showMessageDialog(null, "Failed to open file."); 
		}
	}
	
	//this method sorts three arrays; they are sroted alphabetically according to the customer object array
	public static void bubbleSortName (Customer custArray[], Savings savArray[], Chequing chqArray[])
    {
		//loop to control number of passes
		for (int i = 1 ; i < custArray.length - 1 ; i++)
		{
		    // loop to control number of comparisons
		    for (int j = 0 ; j < custArray.length - i - 1 ; j++)
		    {
			
			// compare side-by-side elements and swap them if
			// first element is greater than second element
			//System.out.println(custArray[element]);
			if (custArray [j].getName().compareToIgnoreCase (custArray [j + 1].getName()) > 0)
			{
			    //temporary holding area for swap 
			    Customer custHold; 
			    Savings savHold;
			    Chequing chqHold;
	
			    custHold = custArray [j];
			    custArray [j] = custArray [j + 1];
			    custArray [j + 1] = custHold;
			    
			    savHold = savArray [j];
			    savArray [j] = savArray [j + 1];
			    savArray [j + 1] = savHold;
			    
			    chqHold = chqArray [j];
			    chqArray [j] = chqArray [j + 1];
			    chqArray [j + 1] = chqHold;
			}
		    } 
		} 
    } 
    
    //this method overloads the other bubbleSortName method and is meant to sort labels and buttons
    public static void bubbleSortName(JLabel labelName[], MyButton btn[])
    {
		
		//loop to control the number of passes
    	for (int i = 1 ; i < labelName.length - 1 ; i++)
		{
		    //loop to control the number of comparisons
		    for (int j = 0 ; j < labelName.length - i - 1 ; j++)
		    {
				
				// compare side-by-side elements and swap them if
				// first element is greater than second element
				// System.out.println(custArray[element]);
				
				if (labelName [j].getText().compareToIgnoreCase (labelName [j + 1].getText()) > 0)
				{
				    //temporary holding area for swap
				    JLabel lblNameHold;  
				    MyButton btnHold;
				    
				    lblNameHold = labelName [j];
				    lblName [j] = labelName [j + 1];
				    labelName [j + 1] = lblNameHold;
				    
				    btnHold = btn [j];
				    btn [j] = btn [j + 1];
				    btn [j + 1] = btnHold;
				    
				}
		    } 
		} 	
    }
    
    //this method specifically saves infomration from object arrays into the customer database
    public static void save(Customer cust[], Savings sav[], Chequing chq[]) 
    {
		try 
		{
			//write to specified file
			fw = new FileWriter("CustomerInfo.txt");
			bw = new BufferedWriter(fw);
			
			//print length of the cutomer array
			bw.write (cust.length + "\n");
			
			//run through length of the cutomer array
			for (int i = 0; i < cust.length; i++) 
			{
				//write the name phone cardnumber savings balance and chequing balance
				bw.write(cust[i].getName() + "," + cust[i].getPhone() + "," + sav[i].getCardNumber() + "\n"
						+ sav[i].getBalance() + "\n" + chq[i].getBalance() + "\n");
			}
			//close
			bw.close();
		} 
			
		catch (IOException ex) 
		{
			JOptionPane.showMessageDialog(null, "File Error. Did not Save!");
		}
	}
    
}//class ArrayLibrary


