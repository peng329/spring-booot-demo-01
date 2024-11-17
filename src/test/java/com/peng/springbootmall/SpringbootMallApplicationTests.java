package com.peng.springbootmall;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//以下測試註解加參數，使之啟動完整的 Web 環境，使單元測試完不會自動關閉
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SpringbootMallApplicationTests {

	@Test
	void contextLoads() {
	}

}
