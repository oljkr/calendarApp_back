package kr.co.calender.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kr.co.calender.domain.Schedule;

public class ScheduleRowMapper implements RowMapper<Schedule>{

	@Override
	public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
		Schedule schedule = new Schedule();
		
		schedule.setScheNo(rs.getLong("sche_no"));
		schedule.setName(rs.getString("name"));
		schedule.setStartDate(rs.getTimestamp("start_date").toLocalDateTime());
		schedule.setEndDate(rs.getTimestamp("end_date").toLocalDateTime());

		return schedule;
	}

}
