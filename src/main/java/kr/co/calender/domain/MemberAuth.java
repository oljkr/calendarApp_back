package kr.co.calender.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberAuth {

	private Long userNo;
	private String auth;

}