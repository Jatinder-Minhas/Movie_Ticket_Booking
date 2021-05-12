package ca.sheridancollege.beans;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.InputMismatchException;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class WriteInFile implements Serializable
{
	
	private static final long serialVersionUID = -2038802190830587762L;
	
	private File file;

	/**
	 * This method print the receipt for user
	 * @param movieName movieName to print
	 * @param price price of 1 ticket
	 * @param ticket_count Total number of ticket
	 * @param date movie date
	 * @throws InputMismatchException
	 */
	public void writeForUser(String movieName,double price, int ticket_count, String date) throws InputMismatchException
    {
		
		file =  new File("c:\\receipt\\receipt.txt");
		
		if (! file.exists())
		{
			file.getParentFile().mkdirs();
	    }

        try
        {
            PrintWriter output =  new PrintWriter(new BufferedWriter(new FileWriter(file)));
            
            output.write("");
            output.flush();

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    		
    		String userName = "";
    		
    		if (!(auth instanceof AnonymousAuthenticationToken)) 
    		{
    		    userName = auth.getName();
    		}
    		
    		output.println("Name: " + userName);
    		output.println("Movie Name: " + movieName);
    		output.println("Number of Tickets: " + ticket_count);
    		output.println("Date and Time: " + date);
    		output.println("Price of 1 Ticket: " + "$" + price);
    		output.println("Total Price: " + "$" + (price * ticket_count));
    		
    		output.close();
            
        }
        catch(IOException e)
        {
            System.out.println("File doesn't exist");
        }
    }
	
	/**
	 * This method print the receipt for non user
	 * @param movieName movieName to print
	 * @param price price of 1 ticket
	 * @param ticket_count Total number of ticket
	 * @param date movie date
	 * @throws InputMismatchException
	 */
	public void writeForNonUser(String movieName,double price, int ticket_count, String date) throws InputMismatchException
    {
		
		file =  new File("c:\\receipt\\receipt.txt");
		
		if (! file.exists())
		{
			file.getParentFile().mkdirs();
	    }

        try
        {
            PrintWriter output =  new PrintWriter(new BufferedWriter(new FileWriter(file)));
            
            output.write("");
            output.flush();
            
    		output.println("Movie Name: " + movieName);
    		output.println("Number of Tickets: " + ticket_count);
    		output.println("Date and Time: " + date);
    		output.println("Price of 1 Ticket: " + "$" + price);
    		output.println("Total Price: " + "$" + (price * ticket_count));
    		
    		output.close();
            
        }
        catch(IOException e)
        {
            System.out.println("File doesn't exist");
        }
    }

}
