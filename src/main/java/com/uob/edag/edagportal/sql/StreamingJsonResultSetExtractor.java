package com.uob.edag.edagportal.sql;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Streams a ResultSet as JSON.
 */
public class StreamingJsonResultSetExtractor implements ResultSetExtractor<Void> {

  private final OutputStream os;

  /**
   * @param os the OutputStream to stream the JSON to
   */
  public StreamingJsonResultSetExtractor(final OutputStream os) {
    this.os = os;
  }

  @Override
  public Void extractData(final ResultSet rs) {
    final ObjectMapper objectMapper = new ObjectMapper();
		try (JsonGenerator jg = objectMapper.getFactory().createGenerator(os, JsonEncoding.UTF8)) {
      writeResultSetToJson(rs, jg);
      jg.flush();
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (SQLException se) {
    	throw new RuntimeException(se);
    }
		
    return null;
  }

  private static void writeResultSetToJson(final ResultSet rs, final JsonGenerator jg)
      throws SQLException, IOException {
    final ResultSetMetaData rsmd = rs.getMetaData();
    final int columnCount = rsmd.getColumnCount();
    jg.writeStartArray();
    while (rs.next()) {
      jg.writeStartObject();
      for (int i = 1; i <= columnCount; i++) {
        jg.writeObjectField(rsmd.getColumnName(i), rs.getObject(i));
      }
      jg.writeEndObject();
    }
    jg.writeEndArray();
  }
}