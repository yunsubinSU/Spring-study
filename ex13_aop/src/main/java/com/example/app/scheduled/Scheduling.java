package com.example.app.scheduled;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component 
@EnableScheduling //반복처리가 필요할때 쓰는 이노케이션?
public class Scheduling {
	
//	@Scheduled(fixedRate = 1000) //1초마다 반응할 거다.
//	public void t1() {
//		System.out.println("Scheduling's t1() invoke...");
//	}
	
//	@Scheduled(cron = "* * * * * *") //초 분 시 일 월 요일(0이 일요일) */2-> 2초마다 이런식으로 된다. 백업할 때쓰면 좋을거 같다.
//	public void t2() {
//		System.out.println("Scheduling's t2() invoke...");
//	}
}

	