package kr.or.ddit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class BootDemo4Application {

	public static void main(String[] args) {
		SpringApplication.run(BootDemo4Application.class, args);
		String init = """


								┌─────────────────────────┐
								│                         │
								│       Boot Demo 4       │
								│                         │
								└─────────────────────────┘
				""";
		log.info(init);
	}

}
