package ru.martynov.music_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MusicServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicServiceApplication.class, args);
	}

}
