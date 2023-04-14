package kr.co.calender.domain;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Schedule {
	private Long scheNo;
	
//	@NotBlank
//	private String userNo;
	
	@NotBlank
	private String name;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime startDate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime endDate;
	
	private String place;
	
	private String memo;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime regDate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime updDate;
	

}
