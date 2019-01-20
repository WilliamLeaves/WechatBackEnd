package com.WechatBackEnd.Controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.WechatBackEnd.Model.Schedule;
import com.WechatBackEnd.Service.ScheduleService;

@RestController
public class ScheduleController {
	@Autowired
	private ScheduleService scheduleService;

	@RequestMapping(value = "/getSchedule", method = RequestMethod.GET)
	public Map<String, Object> getSchedule(String sid) {
		return this.scheduleService.getSchedule(sid);
	}

	@RequestMapping(value = "/schedule/addSchedule", method = RequestMethod.POST)
	public String addSchedule(HttpServletRequest req) {
		Schedule schedule = new Schedule();
		schedule.sid=0;
		schedule.uid=Integer.parseInt(req.getParameter("uid").toString());
		//todo
		
		if(this.scheduleService.addSchedule(schedule)) {
			return "发布出游成功";
		}else {
			return "发布出游失败";
		}
	}
}
