package kr.co.calender.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import kr.co.calender.domain.Member;
import kr.co.calender.domain.MemberAuth;

public class MemberResultSetExtractor implements ResultSetExtractor<Member> {

	@Override
	public Member extractData(ResultSet rs) throws SQLException, DataAccessException {
		Member member = null;
		
		while(rs.next()) {
			if(member == null) {
				member = new Member();
				member.setUserNo(rs.getLong("user_no"));
				member.setUserId(rs.getString("user_id"));
				member.setUserPw(rs.getString("user_pw"));
				member.setUserName(rs.getString("user_name"));
				member.setJob(rs.getString("job"));
				
				member.setRegDate(rs.getTimestamp("reg_date").toLocalDateTime());
				member.setUpdDate(rs.getTimestamp("upd_date").toLocalDateTime());
			}
			
			if(member != null) {
				MemberAuth auth = new MemberAuth();
				auth.setUserNo(rs.getLong("user_no"));
				auth.setAuth(rs.getString("auth"));
				
				member.addAuth(auth);
			}
		}
		
		return member;
	}

}