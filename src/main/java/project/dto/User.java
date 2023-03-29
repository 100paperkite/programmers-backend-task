package project.dto;

import project.json.converter.rule.CustomCase;
import project.json.converter.rule.NamingRule;
import project.json.converter.rule.SnakeCase;

public class User {
    @SnakeCase(from = NamingRule.SNAKE)
    private Long user_id;
    @SnakeCase(from = NamingRule.CAMEL)
    private String username;
    @CustomCase(value = "post_count")
    private int postCount;

    public Long getUserId() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public int getPostCount() {
        return postCount;
    }
}
