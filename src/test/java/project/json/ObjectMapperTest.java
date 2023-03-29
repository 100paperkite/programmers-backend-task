package project.json;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import project.json.dto.NestedSample;
import project.json.dto.Sample;

import java.util.List;

public class ObjectMapperTest {
    @Test
    void 객체를_파싱할_수_있다() throws Exception {
        String jsonString =
                "{" +
                "    \"user_id\": 1," +
                "    \"username\": \"test\"," +
                "    \"post_count\": 0" +
                "  }";

        Sample sample = JsonMapper.parse(Sample.class, jsonString);

        Assertions.assertEquals(1, sample.getUserId());
        Assertions.assertEquals("test", sample.getUsername());
        Assertions.assertEquals(0, sample.getPostCount());
    }

    @Test
    void 중첩_객체를_파싱할_수_있다() throws Exception {
        String nestedJson =
                "{ " +
                    "\"user_id\": 100," +
                    " \"inner\": " +
                        "{ \"user_id\": 1, \"username\": \"test\", \"post_count\": 0 }" +
                "}";
        NestedSample nested = JsonMapper.parse(NestedSample.class, nestedJson);

        Assertions.assertEquals(100, nested.getUserId());
        Assertions.assertEquals(1, nested.getInner().getUserId());
        Assertions.assertEquals("test", nested.getInner().getUsername());
        Assertions.assertEquals(0, nested.getInner().getPostCount());

    }

    @Test
    void 리스트_객체를_파싱할_수_있다() throws Exception {
        String sample =
                "{" +
                "    \"user_id\": 1," +
                "    \"username\": \"test\"," +
                "    \"post_count\": 0" +
                "  }";
        String jsonList =  "[ " + sample + "," + sample + "]";

        List<Sample> list = JsonMapper.parseList(Sample.class, jsonList);

        Assertions.assertEquals(2, list.size());

        Assertions.assertEquals(1, list.get(0).getUserId());
        Assertions.assertEquals("test", list.get(0).getUsername());
        Assertions.assertEquals(0, list.get(0).getPostCount());

        Assertions.assertEquals(1, list.get(1).getUserId());
        Assertions.assertEquals("test", list.get(1).getUsername());
        Assertions.assertEquals(0, list.get(1).getPostCount());

    }



}