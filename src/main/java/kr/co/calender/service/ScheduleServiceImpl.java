package kr.co.calender.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.calender.dao.ScheduleDao;
import kr.co.calender.domain.Schedule;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ScheduleServiceImpl implements ScheduleService {
	private final ScheduleDao dao;
	
	@Override
	public List<Schedule> list() throws Exception {
		return dao.select();
	}
}
