package FileManager;
import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;

/**
 * Класс для реализации парсинга полей типа данных LocalDateTime
 * @author maria
 */
public class LocalDateTimeAdapter implements JsonDeserializer<LocalDateTime>, JsonSerializer<LocalDateTime> {
    @Override
    public JsonElement serialize(LocalDateTime date, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(date.toString());
    }
    @Override
    public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        return LocalDateTime.parse(json.getAsString());
    }
}