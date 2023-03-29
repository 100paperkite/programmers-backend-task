package project.json;

import org.json.JSONArray;
import org.json.JSONObject;
import project.json.converter.NamingConverter;
import project.json.converter.rule.CamelCase;
import project.json.converter.rule.CustomCase;
import project.json.converter.rule.SnakeCase;
import project.json.exception.ObjectMappingException;

import java.lang.reflect.Field;
import java.util.*;

import static project.json.converter.rule.NamingRule.CAMEL;
import static project.json.converter.rule.NamingRule.SNAKE;

public class JsonMapper {
    public static <T> T parse(Class<T> clazz, String source) throws ObjectMappingException {
        T result = null;
        try {
            result = clazz.getDeclaredConstructor().newInstance();

            Map<String, String> json2Field = new HashMap<>();
            for (Field field: clazz.getDeclaredFields()){
                String jsonFieldName = convertFieldNameByNamingRule(field);
                json2Field.put(jsonFieldName, field.getName());
            }

            var json = new JSONObject(source);
            for (Iterator<String> it = json.keys(); it.hasNext(); ) {
                String key = it.next();
                String value = json.get(key).toString();

                Field classField = clazz.getDeclaredField(json2Field.get(key));
                classField.setAccessible(true);

                // if object
                if (value.startsWith("{") && value.endsWith("}")) {
                    classField.set(result, parse(classField.getType(), value));
                }
                // if value
                else {
                    classField.set(result, StringParser.parse(classField.getType(), value));
                }
            }

        } catch (Exception e) {
            throw new ObjectMappingException(e);
        }
        return result;
    }

    public static <T> List<T> parseList(Class<T> clazz, String source) throws ObjectMappingException {
        var jsonArray = new JSONArray(source);
        List<T> result = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++){
            result.add(parse(clazz, jsonArray.get(i).toString()));
        }
        return result;
    }


    public static <T> String toJson(T object) {
        return new JSONObject(object).toString();
    }
;
    /**
     * 필드에 선언된 Naming에 따라 필드명을 변환한다.
     */
    private static String convertFieldNameByNamingRule(Field field) {
        if (field.isAnnotationPresent(CustomCase.class)){
            return field.getAnnotation(CustomCase.class).value();
        }
        else if (field.isAnnotationPresent(SnakeCase.class)){
            return NamingConverter.convert(field.getName(), field.getAnnotation(SnakeCase.class).from(), SNAKE);
        }
        else if (field.isAnnotationPresent(CamelCase.class)){
            return NamingConverter.convert(field.getName(), field.getAnnotation(CamelCase.class).from(), CAMEL);
        }
        else {
            return field.getName();
        }
    }
}
