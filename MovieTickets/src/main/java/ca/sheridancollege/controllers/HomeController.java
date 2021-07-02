package ca.sheridancollege.controllers;



import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.Database.DataBaseAccess;
import ca.sheridancollege.beans.Movies;
import ca.sheridancollege.beans.WriteInFile;

@Controller
public class HomeController {
	
	@Autowired
	@Lazy
	private DataBaseAccess da;
	
	@GetMapping("/")
	public String goHome(Model model)
	{
		model.addAttribute("movies", da.getMovies());
		
		return "index.html";
	}
	
	@GetMapping("/user")
	public String goHomeUser(Model model)
	{
		model.addAttribute("movies", da.getMovies());
		return "/user/index";
	}
	
	@GetMapping("/details")
	public String goCheckDetails(Model model, @RequestParam String movieId)
	{
		model.addAttribute("movies", da.getMovieDetails(Integer.parseInt(movieId)));
		model.addAttribute("dates", da.getMovieDatesById(Integer.parseInt(movieId)));
		model.addAttribute("price", 15);
		
		
		return "MovieDetails";
	}
	
	@PostMapping("/details")
	public String goCheckDetailsUser(Model model, HttpSession session, @RequestParam String movieId)
	{	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String userName = "";
		
		if (!(auth instanceof AnonymousAuthenticationToken)) 
		{
		    userName = auth.getName();
		    session.setAttribute("userName", userName);
		}
		
		Movies movie = new Movies();
		
		model.addAttribute("movies", da.getMovieDetails(Integer.parseInt(movieId)));
		model.addAttribute("dates", da.getMovieDatesById(Integer.parseInt(movieId)));
		model.addAttribute("price", movie.getPrice(da, userName));
		
		return "/User/MovieDetails";
	}
	
	@PostMapping("/buy")
	public String buyUser(Model model, HttpSession session, @RequestParam int ticket_count, @RequestParam String dates,
			@RequestParam double price, @RequestParam long movieId,
			@RequestParam String movieName)
	{
		WriteInFile write = new WriteInFile();
		
		write.writeForUser(movieName, price, ticket_count, dates);
		da.updateMovieDetails(movieId, ticket_count);	
		
		model.addAttribute("movieName", movieName);
		model.addAttribute("ticket_count", ticket_count);
		model.addAttribute("price", price);
		model.addAttribute("totalPrice", (price * (double)ticket_count));
		model.addAttribute("date", dates);
		
		return "/user/BuyDetails";
	}
	
	@GetMapping("/buy")
	public String buy(Model model, @RequestParam int ticket_count, @RequestParam String dates,
			@RequestParam double price, @RequestParam long movieId,
			@RequestParam String movieName)
	{
		WriteInFile write = new WriteInFile();
		
		write.writeForNonUser(movieName, price, ticket_count, dates);
		model.addAttribute("movieName", movieName);
		model.addAttribute("ticket_count", ticket_count);
		model.addAttribute("price", price);
		model.addAttribute("totalPrice", (price * (double)ticket_count));
		model.addAttribute("date", dates);
		
		da.updateMovieDetails(movieId, ticket_count);				
		
		return "BuyDetails";
	}
	
	
	@GetMapping("/login")
	public String goLoginPage()
	{
		return "login.html";
	}
	
	@PostMapping("/login")
	public String goLogin()
	{
		return "login.html";
	}
	
	@GetMapping("/access-denied")
	public String goAccessDenied()
	{
		return "/error/access-denied.html";
	}
	
	@GetMapping("/register")
	public String goRegistration()
	{
		return "register";
	}
	
	@PostMapping("/register")
	public String processRegistration(Model model, @RequestParam String firstName, 
			@RequestParam String lastName, @RequestParam String userName, 
			@RequestParam String password, @RequestParam int age, @RequestParam String college)
	{
		ArrayList<String> userNames = da.getUserNames();
		boolean exist = false;
		
		for(String n: userNames)
		{
			if(userName.equals(n))
			{
				exist = true;
			}
		}
		
		if(exist == false)
		{
			if(college.equals("Please select..") || college == null)
				da.addUser(firstName, lastName, userName, age, password);
			else
				da.addUser(firstName, lastName, userName, college,age, password);
				
			long userId=da.findUserAccount(userName).getUserId();
			da.addRole(userId, 2);
			return "redirect:/login";
		}
		else
		{
			model.addAttribute("exist", exist);
			return "register";
		}
		
		
		
	}
}
