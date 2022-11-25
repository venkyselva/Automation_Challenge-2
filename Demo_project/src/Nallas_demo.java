import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import io.netty.util.internal.ThreadLocalRandom;

public class Nallas_demo {

	public static String Set1 = "Yet to start";
	public static String Set2 = "Yet to start";
	public static JLabel Set_list1;
	public static JLabel Set_list2;
	public static JLabel Status;
	public static String user_status ="Yet to start";
	public static  String  Verification="Yet to start";
	public static  int Guess_count=0;
	public static JTextField userEnt;
	public static int random_numner=0;
	public static int totla;
	public static String user_guess;
	public static String guess_satus = "Failed";
	public static Integer [] int_array;
	public static JButton number;
	public static JButton reset;
	public static String finalvalue="empty";
	public static void main(String[] args) 
	{
					
     try
     {
	     //---Javaframe and java Panel intialization----
		JFrame frame = new JFrame();
		frame.setTitle("-----Welcome to NALLAS-----");
		
		JPanel pane1 = new JPanel();
		
		//---to identify system resolution----
		int[] scrn_resln = system_resolution();
		 int width = scrn_resln[0];
		 int height = scrn_resln[1];
		 frame.setSize(width/2,height/2);
		//---Element initialization----
         number = new JButton("Validate");
         reset = new JButton("Reset");
        JLabel list_label = new JLabel("Enter number with comma Seperation(Ex:1,3,4,...)");
        userEnt = new JTextField("", 10);
        Set_list1 = new JLabel("<html><br>"+"Possible Array Set **-- "+Set1+" --**</html>");
        //Set_list2 = new JLabel("<html><br>"+"SET2**-- " +Set2+" --**</html>");
        Status =  new JLabel("<html><br>"+"Status:(" +Verification+")</html>");
        //---added Element to jpanel----
        frame.add(pane1);
        pane1.add(list_label);
        pane1.add(userEnt);
        pane1.add(number);
        pane1.add(reset);
        pane1.add(Set_list1);
       // pane1.add(Set_list2);
        pane1.add(Status);
        
        frame.setVisible(true);
        reset.setEnabled(false);
        //----random number generation---
         number.addActionListener(new ActionListener(){  
			  public void actionPerformed(ActionEvent e){
				  reset.setEnabled(true);
				  userEnt.setEditable(false);
				  String get_value = userEnt.getText();
				  
        		String value =  check_array(get_value);	  
        		System.out.println("final decision"+value);
			      number.setEnabled(false);
			      if(Verification.equalsIgnoreCase("Split not possible"))
			       {
			      Status.setText("<html><br>"+"Status:(" +Verification+")</html>");
			      Set_list1.setText("<html><br>"+"Possible Array Set **-- "+finalvalue+" --**</html>");
			      Status.setBackground(Color.orange);
			      Status.setOpaque(true);
			      }
			      
			      else
			      {
			    	 finalvalue= finalvalue.replace("Yet to start", "");
			    	  Status.setText("<html><br>"+"Status:(" +Verification+")</html>");
			    	  
			    	  finalvalue =finalvalue.replace("empty", "");
			    	  String[] valstr = finalvalue.split("~");
			    	  String finl_str="";
			    	  for(String ary : valstr)
			    	  {
			    		  finl_str += "<p>"+ary;
			    	  }
			    	  if(!Verification.contains("Invalid"))
			    	  {
			    	  Set_list1.setText("<html><br>"+"Possible Array Set **-- "+finl_str+" --**</html>");
				      Status.setBackground(Color.green);
				      Status.setOpaque(true);
			    	  }
			      } 
			     			      
			  }  
		});  
         
         reset.addActionListener(new ActionListener(){  
			  public void actionPerformed(ActionEvent e){
				  number.setEnabled(true);
				  userEnt.setEditable(true);
				  userEnt.setText("");
				  reset.setEnabled(false);
				  Verification = "Reset Completed"; 
				  Status.setText("<html><br>"+"Status:(" +Verification+")</html>");
				  Status.setBackground(Color.yellow);
				  Status.setOpaque(true);
				  finalvalue ="Yet to start";
				  Set_list1.setText("<html><br>"+"Possible Array Set **-- "+finalvalue+" --**</html>");
				  
			  }});
         
     }
     catch(Exception e)
     {
    	 System.out.println("test");
    	 Verification = "Invalid input";
    	 Status.setText("<html><br>"+"Status:(" +Verification+")</html>");
    	 Status.setBackground(Color.red);
	      Status.setOpaque(true);
    	 finalvalue = "Not Applicable";
    	 Set_list1.setText("<html><br>"+"Possible Array Set **-- "+finalvalue+" --**</html>");
    	 
    	 
     }
     
	}
	
	public static String check_array(String get_value)
	{
		String status ="";
		try
		{
		String[] val = get_value.split(",");
		int arylen = val.length;
		int sum = 0;
			List<Integer> lst = new ArrayList();
			for(int i=0; i<arylen; i++) 
			{
		    	 int vall  = Integer.parseInt(val[i]);
		    	   lst.add(vall);
		    	  sum +=vall;
		    }
		System.out.println("sum"+sum);
		if (sum % 2 == 0) 
		{
			totla = sum/2;
			System.out.println("success");
			Vector<Integer> A
			= new Vector<>(lst);

			Combination(A, totla);
			System.out.println(finalvalue);
			if(finalvalue.equalsIgnoreCase("Empty"))
			{
				Verification = "Split not possible";
			}
			else
			{
             Verification = "Split Completed";
		    //the division results in an integer.
			}
		} 
		
		else
		{
			System.out.println("invalid");
			status = "false";
			Verification = "Split Not Possible";
		    //the division results in a fraction.
		}
		
		
		}
		catch(Exception e)
		{
			System.out.println("test");
	    	 Verification = "Invalid input";
	    	 Status.setText("<html><br>"+"Status:(" +Verification+")</html>");
	    	 Status.setBackground(Color.red);
		      Status.setOpaque(true);

	    	 finalvalue = "Not Applicable";
	    	 Set_list1.setText("<html><br>"+"Possible Array Set **-- "+finalvalue+" --**</html>");
		}
		return status; 
	}
	
	public static void Combination(Vector<Integer> A, int K)
	{
	Vector<Integer> local = new Vector<Integer>();
	find_array(0, 0, K, local, A);
	}
 
     
 static void find_array(int l, int sum, int K,Vector<Integer> local,Vector<Integer> A)
{
// If a unique combination is found
if (sum == K) 
{
		System.out.println("test2");
	finalvalue+= "{ "+local+" }~";
	
return;
}

// For all other combinations
for (int i = l; i < A.size(); i++) 
{
// Check if the sum exceeds K
if (sum + A.get(i) > K)
{	
continue;
}

// Check if it is repeated or not
if (i > l && A.get(i) == A.get(i - 1) )
{	
continue;
}
// Take the element into the combination
local.add(A.get(i));

// Recursive call
find_array(i + 1, sum + A.get(i), K,
         local, A);

// Remove element from the combination
local.remove(local.size() - 1);
}
}

// Function to find all combination
// of the given elements

public static int[] system_resolution()
{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		
		int height = (int)screenSize.getHeight();
		int[] val = {width,height};
		return val;
		
}
	
	
	

}
