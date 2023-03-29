package project.json.dto;

import project.json.converter.rule.NamingRule;
import project.json.converter.rule.SnakeCase;

public class NestedSample {
    @SnakeCase(from = NamingRule.CAMEL)
    private Integer userId;
    private Sample inner;

    public Integer getUserId() {
        return userId;
    }

    public Sample getInner() {
        return inner;
    }
}