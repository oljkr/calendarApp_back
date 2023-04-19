package kr.co.calender.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


import org.springframework.stereotype.Service;

import kr.co.calender.controller.ScheduleController;
import kr.co.calender.dao.ScheduleDao;
import kr.co.calender.domain.Schedule;
import kr.co.calender.domain.Temp;
import kr.co.calender.domain.TempUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ScheduleServiceImpl implements ScheduleService {
	private final ScheduleDao dao;
	
	@Override
	public List<Schedule> list() throws Exception {
		return dao.select();
	}
	
	@Override
	public List<Schedule> listDay(String date) throws Exception {
		System.out.println(date);
		String reDate=date.substring(0, 4)+"-"+date.substring(4,6)+"-"+date.substring(6);
		System.out.println(reDate);
		return dao.selectDay(date);
	}
	
	@Override
	public Schedule listScheNo(int scheNo) throws Exception {
		System.out.println(scheNo);
		return dao.selectSchedule(scheNo);
	}
	
	@Override
	public void addSchedule(Temp temp) throws Exception {
		log.info(temp.toString());
//		String reDate=date.substring(0, 4)+"-"+date.substring(4,6)+"-"+date.substring(6);
//		System.out.println(reDate);
//		 startDate: 2023-04-15 00:00:00.000Z
		String name=temp.getName(); 
		LocalDateTime startDate;
		LocalDateTime endDate;
		log.info("a");
		if(temp.getStartTime().length()==1) {
			String tmp = temp.getStartDate().substring(0, 10)+" 0"+temp.getStartTime()+":00:00";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			startDate = LocalDateTime.parse(tmp, formatter);
		}else {
			String tmp=temp.getStartDate().substring(0, 10)+" "+temp.getStartTime()+":00:00";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			startDate = LocalDateTime.parse(tmp, formatter);
		}
		
		if(temp.getEndTime().length()==1) {
			String tmp = temp.getStartDate().substring(0, 10)+" 0"+temp.getEndTime()+":00:00";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			endDate = LocalDateTime.parse(tmp, formatter);
		}else {
			String tmp=temp.getStartDate().substring(0, 10)+" "+temp.getEndTime()+":00:00";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			endDate = LocalDateTime.parse(tmp, formatter);
		}	
		
//		String str = "2021-11-05 13:47:13.248";
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
//		LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
//		System.out.println(dateTime);

		dao.insertSchedule(name, startDate, endDate);
	}
	
	@Override
	public void editSchedule(TempUpdate tempUpdate) throws Exception {
		log.info(tempUpdate.toString());
//		String reDate=date.substring(0, 4)+"-"+date.substring(4,6)+"-"+date.substring(6);
//		System.out.println(reDate);
//		 startDate: 2023-04-15 00:00:00.000Z
		String name=tempUpdate.getName(); 
		LocalDateTime startDate;
		LocalDateTime endDate;
		log.info("a");
		if(tempUpdate.getStartTime().length()==1) {
			String tmp = tempUpdate.getStartDate().substring(0, 10)+" 0"+tempUpdate.getStartTime()+":00:00";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			startDate = LocalDateTime.parse(tmp, formatter);
		}else {
			String tmp=tempUpdate.getStartDate().substring(0, 10)+" "+tempUpdate.getStartTime()+":00:00";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			startDate = LocalDateTime.parse(tmp, formatter);
		}
		
		if(tempUpdate.getEndTime().length()==1) {
			String tmp = tempUpdate.getStartDate().substring(0, 10)+" 0"+tempUpdate.getEndTime()+":00:00";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			endDate = LocalDateTime.parse(tmp, formatter);
		}else {
			String tmp=tempUpdate.getStartDate().substring(0, 10)+" "+tempUpdate.getEndTime()+":00:00";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			endDate = LocalDateTime.parse(tmp, formatter);
		}	
		
//		String str = "2021-11-05 13:47:13.248";
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
//		LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
//		System.out.println(dateTime);

		dao.updateSchedule(tempUpdate.getScheNo() ,name, startDate, endDate);
	}
	
	@Override
	public void deleteSchedule(int scheNo) throws Exception {
		dao.deleteSchedule(scheNo);
	}
	
	
}
