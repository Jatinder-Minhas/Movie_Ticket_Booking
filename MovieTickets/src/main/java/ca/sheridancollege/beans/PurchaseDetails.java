package ca.sheridancollege.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseDetails implements Serializable{
	

	private static final long serialVersionUID = 6129928580294636727L;
	
	private String userName;
	private int noOfTickets;
	private int singleTicketPrice;
	private int totalPrice;

}
