package org.joolzminer.examples.sip.repository;

import org.joolzminer.examples.sip.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositoryImpl implements AccountRepositoryCustom {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountRepositoryImpl.class);
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private static final String UPDATE_PASSWORD_SQL = 
			"update account set password = :password where username = :username";

	private static final String FIND_PASSWORD_SQL = "select password from account where username = :username";
	
	@Override
	public Account save(Account account, String password) {
		accountRepository.save(account);
		
		LOGGER.debug("Hashing password for user {}", account.getUsername());

		String encodedPassword = passwordEncoder.encode(password);
		LOGGER.debug("Password hashed for user {}: '{}'", account.getUsername(), encodedPassword);
		
		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("password", encodedPassword)
			.addValue("username", account.getUsername());
		jdbcTemplate.update(UPDATE_PASSWORD_SQL, params);
		
		return account;
	}

	@Override
	public String findPasswordByUsername(String username) {
		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("username", username);
		return jdbcTemplate.queryForObject(FIND_PASSWORD_SQL, params, String.class);		
	}
	
	
}
