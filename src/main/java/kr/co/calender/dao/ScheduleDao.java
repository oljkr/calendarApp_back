package kr.co.calender.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.co.calender.domain.Schedule;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ScheduleDao {
	private final JdbcTemplate jdbcTemplate;
	private StringBuilder sql=null;
	
	public List<Schedule> select() throws Exception {
		List<Schedule> results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT sche_no, name, start_date, end_date ");
			sql.append(" FROM schedule ");
			
			RowMapper<Schedule> rowMapper=new RowMapper<Schedule>() {
				@Override
				public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
					Schedule schedule = new Schedule();
					
					schedule.setScheNo(rs.getLong("sche_no"));
					schedule.setName(rs.getString("name"));
					schedule.setStartDate(rs.getTimestamp("start_date").toLocalDateTime());
					schedule.setEndDate(rs.getTimestamp("end_date").toLocalDateTime());
			
					return schedule;
				}//mapRow() end
			};//rowMapper end
			
			results = jdbcTemplate.query(sql.toString(), rowMapper);
		}catch (Exception e) {
			System.out.println("일정 목록 자료읽기 실패:" +e);
		}//end
		
		return results;
	}
	
	public List<Schedule> selectDay(String date) throws Exception {
		List<Schedule> results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT sche_no, name, start_date, end_date ");
			sql.append(" FROM schedule ");
			sql.append(" where DATE(start_date)='"+date+"' ");
			
			results = jdbcTemplate.query(sql.toString(), new ScheduleRowMapper());
		}catch (Exception e) {
			System.out.println("날짜 선택 일정 목록 자료읽기 실패:" +e);
		}//end
		
		return results;
	}
	
	public Schedule selectSchedule(int scheNo) throws Exception {
		Schedule results = null;
		try {
			sql=new StringBuilder();
			sql.append(" SELECT sche_no, name, start_date, end_date ");
			sql.append(" FROM schedule ");
			sql.append(" where sche_no='"+scheNo+"' ");
			
			results = jdbcTemplate.queryForObject(sql.toString(), new ScheduleRowMapper());
		}catch (Exception e) {
			System.out.println("선택 일정 세부 자료읽기 실패:" +e);
			return null;
		}//end
		
		return results;
	}
	
	public int insertSchedule(String name, LocalDateTime startDate, LocalDateTime endDate) {
		int cnt=0;
		try {
			sql=new StringBuilder();
			sql.append(" INSERT INTO schedule(name, start_date, end_date, reg_date) ");
			sql.append(" VALUES (?, ?, ?, now()) ");
			cnt=jdbcTemplate.update(sql.toString(), name, startDate, endDate);
		}catch (Exception e) {
			System.out.println("일정등록실패:" + e);
		}//end
		return cnt;
	}//insertSchedule() end
	
	public int updateSchedule(int scheNo, String name, LocalDateTime startDate, LocalDateTime endDate) {
		int cnt=0;
		try {
			sql=new StringBuilder();
			sql.append(" UPDATE schedule ");
			sql.append(" SET name = ?, start_date = ?, end_date = ? ");
			sql.append(" WHERE sche_no = ? ");
			cnt=jdbcTemplate.update(sql.toString(), name, startDate, endDate, scheNo);
		}catch (Exception e) {
			System.out.println("일정수정실패:" + e);
		}//end
		return cnt;
	}//updateSchedule() end
	
	public int deleteSchedule(int scheNo) {
		int cnt=0;
		try {
			sql=new StringBuilder();
			sql.append(" DELETE FROM schedule ");
			sql.append(" WHERE sche_no= ? ");
			cnt=jdbcTemplate.update(sql.toString(), scheNo);
		}catch (Exception e) {
			System.out.println("일정삭제실패:" + e);
		}//end
		return cnt;
	}//deleteSchedule() end

}
