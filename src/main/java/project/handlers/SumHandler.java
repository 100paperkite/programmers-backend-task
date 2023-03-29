package project.handlers;

import project.dto.Sum;
import project.dto.User;
import project.json.JsonMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SumHandler extends JsonHttpHandler {
    private final String projectDir = System.getProperty("user.dir");

    @Override
    public Sum response() throws IOException {
        String json = Files.readString(Path.of(projectDir,"data/input/user.json"));

        long result = 0;
        List<User> users = JsonMapper.parseList(User.class, json);
        for (User user: users){
            result += user.getPostCount();
        }

        return new Sum(result);
    }
}
