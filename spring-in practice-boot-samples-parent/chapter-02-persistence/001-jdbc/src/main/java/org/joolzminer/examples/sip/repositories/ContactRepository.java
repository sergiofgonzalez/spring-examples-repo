package org.joolzminer.examples.sip.repositories;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import org.joolzminer.examples.sip.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepository {
	private static final String CREATE_SQL = 
				"INSERT INTO Contact (last_name, first_name, mi, email) "
			+ 	"VALUES (:lastName, :firstName, :mi, :email)";
	
	private static final String FIND_ALL_SQL =
				"SELECT id, last_name, first_name, mi, email FROM Contact";
	
	private static final String FIND_ALL_BY_EMAIL_LIKE_SQL =
				"SELECT id, last_name, first_name, mi, email FROM Contact "
			+ 	"WHERE email like :email";
	
	private static final String FIND_ONE_SQL =
				"SELECT id, last_name, first_name, mi, email FROM Contact "
			+ 	"  WHERE id = :id";
	
	private static final String UPDATE_SQL =
				"UPDATE Contact "
			+	"   SET last_name  = :lastName, "
			+ 	"       first_name = :firstName, "
			+   "       mi         = :mi, "
			+   "       email      = :email "
			+ 	" WHERE id = :id";
	
	private static final String DELETE_SQL =
				"DELETE FROM Contact "
			+ 	" WHERE id = :id";
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public Contact create(final Contact contact) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(CREATE_SQL, new MapSqlParameterSource() {{ 
			addValue("lastName", contact.getLastName());
			addValue("firstName", contact.getFirstName());
			addValue("mi", contact.getMiddleInitial());
			addValue("email", contact.getEmail());
		}}, keyHolder);
		
		return new Contact( 
				contact.getFirstName(),
				contact.getMiddleInitial(),
				contact.getLastName(), 				 				 
				contact.getEmail()) {{
					setId(keyHolder.getKey().longValue());
				}};		
	}
	
	public List<Contact> getContacts() {
		return jdbcTemplate.query(FIND_ALL_SQL, contactRowMapperLambda);
	}
	
	@SuppressWarnings("serial")
	public List<Contact> getContactsByEmail(String email) {
		
		return jdbcTemplate.query(FIND_ALL_BY_EMAIL_LIKE_SQL, 
				new HashMap<String,String>() {{ put("email", email); }}, 
				contactRowMapperLambda);
	}
	
	@SuppressWarnings("serial")
	public Contact getContact(long id) {
		return jdbcTemplate.queryForObject(FIND_ONE_SQL, 
				new HashMap<String, Long>() {{ put("id", id); }}, 
				contactRowMapperLambda);
	}

	public void updateContact(Contact contact) {
		jdbcTemplate.update(UPDATE_SQL, new MapSqlParameterSource() {{
			addValue("id", contact.getId());
			addValue("lastName", contact.getLastName());
			addValue("firstName", contact.getFirstName());
			addValue("mi", contact.getMiddleInitial());
			addValue("email", contact.getEmail());
		}});
	}
	
	public void delete(long id) {
		jdbcTemplate.update(DELETE_SQL, new MapSqlParameterSource("id", id));
	}
	
	private RowMapper<Contact> contactRowMapperLambda = (ResultSet rs, int rowNum) -> new Contact(rs.getString("first_name"),
																									rs.getString("mi"),																						
																									rs.getString("last_name"),																									
																									rs.getString("email")) {{
																										setId(rs.getLong("id"));
																									}}; 
}
