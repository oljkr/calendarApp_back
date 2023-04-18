package kr.co.calender.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.calender.domain.Schedule;
import kr.co.calender.domain.Temp;
import kr.co.calender.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/schedule")
@CrossOrigin("*")
public class ScheduleController {
	private final ScheduleService service;
	
	@GetMapping
	public ResponseEntity<List<Schedule>> list() throws Exception {
		log.info("list");
	
		return new ResponseEntity<>(service.list(), HttpStatus.OK);
	}
	
	@GetMapping(params= {"date"})
	public ResponseEntity<List<Schedule>> listDay(@RequestParam("date") String date) throws Exception {
		log.info("listDay");
	
		return new ResponseEntity<>(service.listDay(date), HttpStatus.OK);
	}
	
	
	@PostMapping
	public ResponseEntity register(@RequestBody Temp temp) throws Exception {
		log.info("postSchedule");
		service.addSchedule(temp);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@DeleteMapping(params= {"scheNo"})
	public ResponseEntity delete(@RequestParam("scheNo") int scheNo) throws Exception {
		log.info("deleteSchedule");
		service.deleteSchedule(scheNo);
		return new ResponseEntity(HttpStatus.OK);
	}

}
