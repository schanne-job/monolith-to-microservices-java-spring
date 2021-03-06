package com.example.monolith;

import com.example.monolith.model.Answer;
import com.example.monolith.model.Question;
import com.example.monolith.web.AlphaController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class MonolithApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private AlphaController controller;

	@Autowired
	private TestRestTemplate restTemplate;

	Question question;

	@BeforeEach
	public void setup() {
		question = new Question("my question");
	}

	@Test
	public void alphaReturnsCorrectAnswer() {
		Answer answer = this.restTemplate.postForObject("http://localhost:" + port + "/alpha/answer-question", question, Answer.class);
		assertThat(answer.getAnswer()).isEqualTo("2");
	}

	@Test
	public void alphaReturnsAnswerInXTime() {
		long startTime = System.nanoTime();
		Answer answer = this.restTemplate.postForObject("http://localhost:" + port + "/alpha/answer-question", question, Answer.class);
		long endTime = System.nanoTime();

		assertThat(answer.getAnswer()).isEqualTo("2");
		assertThat((endTime - startTime)/1000000).isLessThan(1000);
	}

}
