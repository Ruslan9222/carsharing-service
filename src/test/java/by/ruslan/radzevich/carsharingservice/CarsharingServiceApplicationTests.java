package by.ruslan.radzevich.carsharingservice;

import lombok.extern.log4j.Log4j2;
import org.hibernate.id.enhanced.StandardOptimizerDescriptor;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Log4j2
class CarsharingServiceApplicationTests {
	@BeforeAll
	static void setup() {
		log.info("@BeforeAll - executes once before all test methods in this class");
	}
	@DisplayName("Single test successful")
	@Test
	void testSingleSuccessTest() {
		log.info("Success");
	}

	@Test
	@Disabled("Not implemented yet")
	void testShowSomething() {
	}

	@BeforeEach
	void init() {
		log.info("@BeforeEach - executes before each test method in this class");
	}

//	@Test
//	void contextLoads() {
//	}

}
