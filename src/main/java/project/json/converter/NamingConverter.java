package project.json.converter;

import project.json.converter.rule.NamingRule;
import project.json.exception.NotSupportedNamingConversion;

import java.util.regex.Pattern;

import static project.json.converter.rule.NamingRule.CAMEL;
import static project.json.converter.rule.NamingRule.SNAKE;

public class NamingConverter {
    private static final Pattern snakeToCamelPattern = Pattern.compile("_(\\w)");
    private static final Pattern camelToSnakePattern = Pattern.compile("(.)(\\p{Upper})");

    public static String convert(String target, NamingRule from, NamingRule to){
        if (from == to){
            return target;
        }
        if (from == SNAKE && to == CAMEL){
            return snakeToCamel(target);
        }
        if (from == CAMEL && to == SNAKE){
            return camelToSnake(target);
        }

        throw new NotSupportedNamingConversion();
    }

    private static String camelToSnake(String camelCase){
        String snakeCase = camelToSnakePattern.matcher(camelCase).replaceAll("$1_$2");
        return snakeCase.toLowerCase();
    }

    private static String snakeToCamel(String snakeCase){
        return snakeToCamelPattern.matcher(snakeCase).replaceAll((m) -> m.group(1).toUpperCase());
    }
}
