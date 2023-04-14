package kr.co.calender.service;

import java.util.List;

import kr.co.calender.domain.Schedule;

public interface ScheduleService {
	
	public List<Schedule> list() throws Exception;
}
