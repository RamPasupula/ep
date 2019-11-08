package com.uob.edag.edagportal.sql;

import java.io.IOException;
import java.io.OutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

import org.springframework.jdbc.core.JdbcTemplate;

public class QueryExecutor {

	public StreamingOutput runQuery(String sql) {
		return new StreamingOutput() {
			@Override
			public void write(final OutputStream os) throws IOException, WebApplicationException {
				JdbcTemplate jdbcTemplate = new JdbcTemplate();
				jdbcTemplate.query(sql, new StreamingJsonResultSetExtractor(os));
			}
		};
	}
	
	/*
	public static void main(String[] args) {
		StreamingOutput so = new QueryExecutor().runQuery("select * from abc");
		
	}
	*/
}
