package project.json.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static project.json.converter.rule.NamingRule.CAMEL;
import static project.json.converter.rule.NamingRule.SNAKE;

class NamingConverterTest {

    @Test
    public void snake_case_를_CamelCase_로_변환한다() throws Exception {
        //given
        String snake_case = "user_id_test";

        //when
        String result = NamingConverter.convert(snake_case, SNAKE, CAMEL);

        //then
        Assertions.assertEquals(result, "userIdTest");
    }

    @Test
    public void CamelCase_를_snake_case_로_변환한다() throws Exception {
        //given
        String camelCase = "userIdTest";

        //when
        String result = NamingConverter.convert(camelCase, CAMEL, SNAKE);

        //then
        Assertions.assertEquals(result, "user_id_test");
    }
}