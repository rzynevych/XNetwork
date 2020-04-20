package com.zman.znetwork.models.messages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.jdbc.core.RowMapper;

public class MessageMapper implements RowMapper<Message> {

    DateTimeFormatter formatter;
    DateTimeFormatter simpleFormatter;
    LocalDateTime now;

    public MessageMapper() {
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        simpleFormatter = DateTimeFormatter.ofPattern("HH:mm");
        now = LocalDateTime.now();
    }

    @Override
    public Message mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new Message(
                rs.getLong("message_id"),
                rs.getInt("parent_id"),
                rs.getInt("receiver"),
                rs.getString("username"),
                rs.getString("text"),
                calculateTime(rs.getString("date"))
        );
    }

    private String calculateTime(String datetime) {

        long diff;
        StringBuilder time = new StringBuilder();

        LocalDateTime date = LocalDateTime.parse(datetime, formatter);
        if ((diff = ChronoUnit.MINUTES.between(date, now)) < 60) {
            time.append(diff).append(" minute");
        } else if ((diff = ChronoUnit.HOURS.between(date, now)) < 24) {
            time.append(diff).append(" hour");
        } else if ((diff = ChronoUnit.DAYS.between(date, now)) < 7) {
            time.append(diff).append(" day");
        } else if ((diff = ChronoUnit.WEEKS.between(date, now)) < 5) {
            time.append(diff).append(" week");
        } else if ((diff = ChronoUnit.MONTHS.between(date, now)) < 12) {
            time.append(diff).append(" month");
        } else {
            diff = ChronoUnit.YEARS.between(date, now);
            time.append(diff).append(" year");
        }
        if (diff % 10 != 1 || diff % 100 == 11)
            time.append("s");
        time.append(" ago");
        return time.toString();
    }
}
