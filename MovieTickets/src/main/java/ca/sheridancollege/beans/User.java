package ca.sheridancollege.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable 
{
	
	private static final long serialVersionUID = -5160823271897802168L;
	
	private long userId;
	private String firstName;
	private String lastName;
	private String userName;
	private String college;
	private int age;
	private String encryptedPassword;

}
