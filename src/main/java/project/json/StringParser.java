package project.json;

import java.text.ParseException;

public class StringParser {
    public static <T> T parse(Class<T> type, String value) throws Exception {
        if (type.isPrimitive()) {
            if (type == int.class) {
                return (T) Integer.valueOf(value);
            }
            else if (type == short.class){
                return (T) Short.valueOf(value);
            }
            else if (type == float.class){
                return (T) Float.valueOf(value);
            }
            else if (type == double.class){
                return (T) Double.valueOf(value);
            }
            else if (type == long.class){
                return (T) Long.valueOf(value);
            }
            else if (type == boolean.class){
                return (T) Boolean.valueOf(value);
            }
            else if (type == byte.class){
                return (T) Byte.valueOf(value);
            }
            else { // char
                if (value.length() != 1) {
                    throw new ParseException(String.format("%s can't be converted to char. length = %d", value, value.length()), 0);
                }
                return (T) value;
            }
        }
        else if (Number.class.isAssignableFrom(type)){
            return (T) type.getMethod("valueOf", String.class).invoke(null, value);
        }
        else if (type.isAssignableFrom(String.class)){
            return (T) value.replaceAll("[\'\"]","");
        }
        else {
            return JsonMapper.parse(type, value);
        }
    }
}
