package kr.co.calender.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.calender.domain.Schedule;
import kr.co.calender.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
	private final ScheduleService service;
	
	@GetMapping
	public ResponseEntity<List<Schedule>> list() throws Exception {
		log.info("list");
	
		return new ResponseEntity<>(service.list(), HttpStatus.OK);
	}

}
