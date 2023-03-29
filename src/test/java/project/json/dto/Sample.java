package project.json.dto;

import project.json.converter.rule.NamingRule;
import project.json.converter.rule.SnakeCase;

public class Sample {
    @SnakeCase(from = NamingRule.CAMEL)
    private long userId;
    private String username;
    @SnakeCase(from = NamingRule.CAMEL)
    private int postCount;

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public int getPostCount() {
        return postCount;
    }
}