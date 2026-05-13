package kr.or.ddit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class BootDemo6Application {

	public static void main(String[] args) {
		SpringApplication.run(BootDemo6Application.class, args);
		String init = """


								┌─────────────────────────┐
								│                         │
								│       Boot Demo 6       │
								│                         │
								└─────────────────────────┘
				""";
		log.info(init);
	}

}
