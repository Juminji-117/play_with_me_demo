package com.idea5.playwithme;

import com.idea5.playwithme.event.domain.Event;
import com.idea5.playwithme.event.domain.repository.EventRepository;
import com.idea5.playwithme.event.domain.service.EventService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PlaywithmeApplicationTests {
	@Autowired
	private EventService eventService;
	private List<Event> events = new ArrayList<>();

	@Test
	void getEvent_Test() {
		eventService.create(1L,1,"홍길동","서울",LocalDateTime.of(2022,8,15,00,00,00));

		String category = "baseball";
		String stringDate="2022-08-15";

		Integer categoryId = 0;
		switch (category) {
			case "baseball":
				categoryId = 1;
				break;
			case "soccer":
				categoryId = 2;
				break;
			case "basketball":
				categoryId = 3;
				break;
			case "musical":
				categoryId = 4;
				break;
			case "concert":
				categoryId = 5;
				break;
		}

		//String -> LocalDate로 파싱
		LocalDate localDateType = LocalDate.parse(stringDate, DateTimeFormatter.ISO_DATE);
		//LocalDate -> LocalDateTime으로 파싱
		LocalDateTime localDateTimeType = localDateType.atStartOfDay();

		events = eventService.findByCategoryIdAndDate(categoryId, localDateTimeType);
		assertThat(events.size()).isEqualTo(1);
	}
}
