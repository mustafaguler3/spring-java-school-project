package com.example.demo.rowmappers;

import com.example.demo.domain.Contact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactRowMapper implements RowMapper<Contact> {
    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact contact = new Contact();
        contact.setId(rs.getInt("Id"));
        contact.setName(rs.getString("Name"));
        contact.setMobileNum(rs.getString("Mobile_number"));
        contact.setEmail(rs.getString("Email"));
        contact.setStatus(rs.getString("Subject"));
        contact.setMessage(rs.getString("Message"));
        contact.setStatus(rs.getString("Status"));
        contact.setCreatedAt(rs.getTimestamp("Created_at").toLocalDateTime());
        contact.setCreatedBy(rs.getString("Created_by"));

        if (rs.getTimestamp("Updated_at") != null){
            contact.setUpdatedAt(rs.getTimestamp("Updated_at").toLocalDateTime());
        }
        contact.setUpdatedBy(rs.getString("Updated_by"));
        return contact;
    }
}




















