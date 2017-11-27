package cyb.spring.boot.demo.common.json;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Unix时间戳反序列化提供程序
 * 
 * @author Administrator
 *
 */
public class UnixTimestampDeserializer extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		Long seconds = Long.valueOf(parser.getText());
		Long milliseconds = TimeUnit.SECONDS.toMillis(seconds);
		return new Date(milliseconds);
	}

}
