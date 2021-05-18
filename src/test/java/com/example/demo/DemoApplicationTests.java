package com.example.demo;

import com.example.demo.controllers.FirstController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	private FirstController controller;
	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
