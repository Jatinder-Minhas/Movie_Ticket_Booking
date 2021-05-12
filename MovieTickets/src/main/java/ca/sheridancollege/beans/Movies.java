package ca.sheridancollege.beans;

import java.io.Serializable;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import ca.sheridancollege.Database.DataBaseAccess;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movies implements Serializable  
{
	
	private static final long serialVersionUID = -862422050804094340L;

	
	private DataBaseAccess da;
	
	private long movieId;
	private String movieName;
	private String imgPath;
	private int ticket_count;
	private int ticket_sold;
	private double price;
	
	
	public double getPrice(DataBaseAccess da, String userName)
	{
		int age = da.findUserAge(userName); 
		price = 15;
		String collegeName = "";
		
		
		if(da.findCollegeName(userName) != null)
			collegeName = da.findCollegeName(userName);
		
		
		if(age <= 12 || age >= 65)
			price = 5;
		else if(collegeName.equals("SHERIDAN"))
			price = 10;
		else
			price = 15;
		
		price = price - ((price/100) * 20);
		
		return price;
	}
	
}
