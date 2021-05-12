package ca.sheridancollege.Database;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.beans.Movies;
import ca.sheridancollege.beans.User;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor

@Repository
public class DataBaseAccess 
{
	@Autowired
	NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	private JdbcTemplate jdbcTemplate = new JdbcTemplate(DatabaseConfig.getDataSource());
	
	
	
	public User findUserAccount(String userName) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM RegisteredUser where userName=:userName";
		parameters.addValue("userName", userName);
		ArrayList<User> users = (ArrayList<User>)jdbc.query(query, parameters,
				new BeanPropertyRowMapper<User>(User.class));
		if (users.size()>0)
			return users.get(0);
		else
			return null;
	}
	
	public int findUserAge(String userName) {
		
		int age = 0;
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT age FROM RegisteredUser where userName=:userName";
		parameters.addValue("userName", userName);
		
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		
		if(rows.size()>0)
			age = ((BigDecimal) rows.get(0).get("age")).intValue();
		
		return age;
	}
	
	public String findCollegeName(String userName) {
		
		String college = "";
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT college FROM RegisteredUser where userName=:userName";
		parameters.addValue("userName", userName);
		
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		
		if(rows.size()>0)
		{
			college = (String)rows.get(0).get("college");
		}	
		
		return college;
	}
	
	public List<String> getRolesById(long userId) {
		ArrayList<String> roles = new ArrayList<String>();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "select user_role.userId, sec_role.roleName "
				+ "FROM user_role, sec_role "
				+ "WHERE user_role.roleId=sec_role.roleId "
				+ "and userId=:userId";
		parameters.addValue("userId", userId);
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		for (Map<String, Object> row : rows) {
			roles.add((String)row.get("roleName"));
		}
		return roles;
	}
	
	public List<Movies> getMovies()
	{
		String query = "select movieId, movieName, imgPath, ticket_count from MOVIETICKETS";
		List<Movies> movieList = new ArrayList<>();
		
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);

        for (Map row : rows) 
        {
            Movies obj = new Movies();
            
            if(((BigDecimal) row.get("ticket_count")).intValue() > 0)
            {
            	obj.setMovieId(((Long) row.get("movieId")));
            	obj.setMovieName((String) row.get("movieName"));
            	obj.setImgPath((String) row.get("imgPath"));
            	movieList.add(obj);
            }

            
        }
		
		return movieList;
		
	}
	
	public List<Movies> getMovieDetails(long movieId)
	{
		String query = "select movieId, movieName, imgPath,ticket_count from MOVIETICKETS"
						+ " where movieId = :movieId";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		List<Movies> movieList = new ArrayList<>();
		parameters.addValue("movieId", movieId);
		
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);

        for (Map row : rows) 
        {
            Movies obj = new Movies();

            obj.setMovieId(((Long) row.get("movieId")));
            obj.setMovieName((String) row.get("movieName"));
            obj.setImgPath((String) row.get("imgPath"));
            obj.setTicket_count(((BigDecimal) row.get("ticket_count")).intValue()); 
            movieList.add(obj);
        }
		
		return movieList;
		
	}
	
	public List<Timestamp> getMovieDatesById(long movieId)
	{
		String query = "select movieDate from MOVIES_DATES"
						+ " where movieId = :movieId";
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		List<Timestamp> movieDates = new ArrayList<>();
		parameters.addValue("movieId", movieId);
		
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);

        for (Map row : rows) 
        {
            movieDates.add((Timestamp)(row.get("movieDate")));
        }
		
		return movieDates;
	}
	

	public void addUser(String firstName, String lastName, String userName, int age, String password) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "insert into RegisteredUser " 
				+ "(firstName, lastName, userName, encryptedPassword, age, ENABLED)" 
				+ " values (:firstName, :lastName, :userName, :encryptedPassword, :age, 1)";
		
		parameters.addValue("firstName", firstName);
		parameters.addValue("lastName", lastName);
		parameters.addValue("userName", userName);
		parameters.addValue("age", age);
		parameters.addValue("encryptedPassword", 
				passwordEncoder.encode(password));	
		jdbc.update(query, parameters);
	
	}
	
	public void addUser(String firstName, String lastName, String userName, String college,int age, String password) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "insert into RegisteredUser " 
				+ "(firstName, lastName, userName, college,encryptedPassword, age, ENABLED)" 
				+ " values (:firstName, :lastName, :userName, :college,:encryptedPassword, :age, 1)";
		
		parameters.addValue("firstName", firstName);
		parameters.addValue("lastName", lastName);
		parameters.addValue("userName", userName);
		parameters.addValue("college", college);
		parameters.addValue("age", age);
		parameters.addValue("encryptedPassword", 
				passwordEncoder.encode(password));	
		jdbc.update(query, parameters);
	
	}
	
	public void addRole(long userId, long roleId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "insert into user_role (userId, roleId)" 
				+ "values (:userId, :roleId);";
		parameters.addValue("userId", userId);
		parameters.addValue("roleId", roleId);
		jdbc.update(query, parameters);	
	}
	
	public void addMovie(String movieName, int ticket_count)
	{
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "insert into MOVIETICKETS"
						+ "movieName, ticket_count"
						+ " values (:movieName, :ticket_count)";
		
		parameters.addValue("movieName", movieName);
		parameters.addValue("ticket_count", ticket_count);
		jdbc.update(query, parameters);
	}
	
	public void updateMovieDetails(long movieId, int ticketSold)
	{
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "update MOVIETICKETS "
				+ " set ticket_count = ticket_count - :ticketSold"
				+ " where movieId = :movieId";
		
		parameters.addValue("ticketSold", ticketSold);
		parameters.addValue("movieId", movieId);
			
		jdbc.update(query, parameters);
		
	}
	
	public ArrayList<String> getUserNames()
	{
		String query = "select userName from RegisteredUser";
		ArrayList<String> userNames = new ArrayList<String>();
		
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);

        for (Map row : rows) 
        {
        	userNames.add((String)(row.get("userName")));
        }
		
		return userNames;
	}
	
}
