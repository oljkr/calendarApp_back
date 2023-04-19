package kr.co.calender.service;

import java.util.List;

import kr.co.calender.domain.Schedule;
import kr.co.calender.domain.Temp;
import kr.co.calender.domain.TempUpdate;

public interface ScheduleService {
	
	public List<Schedule> list() throws Exception;
	
	public List<Schedule> listDay(String date) throws Exception;
	
	public Schedule listScheNo(int scheNo) throws Exception;
	
	public void addSchedule(Temp temp) throws Exception;
	
	public void editSchedule(TempUpdate tempUpdate) throws Exception;
	
	public void deleteSchedule(int scheNo) throws Exception;
}
