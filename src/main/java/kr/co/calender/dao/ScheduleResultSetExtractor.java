package kr.co.calender.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import kr.co.calender.domain.Schedule;


public class ScheduleResultSetExtractor implements ResultSetExtractor<Schedule>{
	
	@Override
	public Schedule extractData(ResultSet rs) throws SQLException, DataAccessException {
		Schedule schedule = null;
		
		while(rs.next()) {
			if(schedule == null) {
				schedule = new Schedule();
				schedule.setScheNo(rs.getLong("sche_no"));
				schedule.setName(rs.getString("name"));
				schedule.setStartDate(rs.getTimestamp("start_date").toLocalDateTime());
				schedule.setEndDate(rs.getTimestamp("end_date").toLocalDateTime());
				schedule.setPlace(rs.getString("place"));
				schedule.setMemo(rs.getString("memo"));
				
				schedule.setRegDate(rs.getTimestamp("reg_date").toLocalDateTime());
				schedule.setUpdDate(rs.getTimestamp("upd_date").toLocalDateTime());
			}
		}
		
		return schedule;
	}

}
