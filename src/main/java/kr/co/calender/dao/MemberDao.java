package kr.co.calender.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import kr.co.calender.domain.Member;
import kr.co.calender.domain.MemberAuth;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class MemberDao {
	private final JdbcTemplate jdbcTemplate;
	
	// 등록 처리
	public void insert(Member member) throws Exception {
		String sql = "INSERT INTO member (user_id, user_pw, user_name, job, coin, reg_date, upd_date) VALUES(?, ?, ?, ?, 0, now(), now())";
	
		KeyHolder keyHolder = new GeneratedKeyHolder();
	
		jdbcTemplate.update(
		new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, member.getUserId());
				ps.setString(2, member.getUserPw());
				ps.setString(3, member.getUserName());
				ps.setString(4, member.getJob());
				return ps;
			}
		}, keyHolder);
	
		member.setUserNo(keyHolder.getKey().longValue());
	}
	
	// 권한 생성
	public void insertAuth(MemberAuth memberAuth) throws Exception {
		String sql = "INSERT INTO member_auth (user_no, auth, reg_date, upd_date) VALUES(?, ?, now(), now())";
		jdbcTemplate.update(sql, memberAuth.getUserNo(), memberAuth.getAuth());
	}
	
	// 상세 조회
	public Member selectOne(Long userNo) throws Exception {
		String sql = "SELECT mem.user_no, mem.user_id, user_pw, user_name, job, coin, mem.reg_date, mem.upd_date, auth";
		sql += " FROM member mem LEFT OUTER JOIN member_auth auth ON mem.user_no = auth.user_no";
		sql += " WHERE mem.user_no = ?";
	
		MemberResultSetExtractor extractor = new MemberResultSetExtractor();
		Member result = jdbcTemplate.query(sql, extractor , userNo);
	
		return result;
	}
	
	// 수정 처리
	public void update(Member member) throws Exception {
		String sql = "UPDATE member SET user_name = ?, job = ? WHERE user_no = ?";
		jdbcTemplate.update(sql, member.getUserName(), member.getJob(), member.getUserNo());
	}

	// 삭제 처리
	public void delete(Long userNo) throws Exception {
		String sql = "DELETE FROM member WHERE user_no = ?";
		jdbcTemplate.update(sql, userNo);
	}
	
	// 목록 조회
	public List<Member> select() throws Exception {
		List<Member> results = jdbcTemplate.query(
			"SELECT user_no, user_id, user_pw, user_name, (SELECT code_name FROM code_detail WHERE group_code = 'A01' AND code_value = job) AS job, coin, reg_date FROM member ORDER BY reg_date DESC",
			new RowMapper<Member>() {
				@Override
				public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
					Member member = new Member();
				
					member.setUserNo(rs.getLong("user_no"));
					member.setUserId(rs.getString("user_id"));
					member.setUserPw(rs.getString("user_pw"));
					member.setUserName(rs.getString("user_name"));
					member.setJob(rs.getString("job"));
				
					Timestamp timestamp = rs.getTimestamp("reg_date");
					member.setRegDate(timestamp.toLocalDateTime());
				
					return member;
				}
			});
	
		return results;
	}
	
	// 권한 삭제
	public void deleteAuth(Long userNo) throws Exception {
		String sql = "DELETE FROM member_auth WHERE user_no = ?";
		jdbcTemplate.update(sql, userNo);
	}

}
