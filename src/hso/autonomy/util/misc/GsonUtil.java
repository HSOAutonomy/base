/* Copyright 2008 - 2017 Hochschule Offenburg
 * For a list of authors see README.md
 * This software of HSOAutonomy is released under MIT License (see LICENSE).
 */

package hso.autonomy.util.misc;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

public class GsonUtil
{
	private static Gson gson =
			new GsonBuilder()
					.registerTypeAdapter(Double.class,
							(JsonSerializer<Double>) (src, typeOfSrc, context) -> {
								if (src.isNaN() || src.isInfinite()) {
									return new JsonPrimitive(src.toString());
								}
								return new JsonPrimitive(
										new BigDecimal(src).setScale(3, RoundingMode.HALF_UP).doubleValue());
							})
					.registerTypeAdapter(Float.class,
							(JsonSerializer<Float>) (src, typeOfSrc, context) -> {
								if (src.isNaN() || src.isInfinite()) {
									return new JsonPrimitive(src.toString());
								}
								return new JsonPrimitive(
										new BigDecimal(src).setScale(3, RoundingMode.HALF_UP).floatValue());
							})
					.registerTypeAdapter(Duration.class,
							(JsonSerializer<Duration>) (src, typeOfSrc,
									context) -> new JsonPrimitive(DurationUtil.format(src)))
					.registerTypeAdapter(Duration.class,
							(JsonDeserializer<Duration>) (src, typeOfSrc,
									context) -> DurationUtil.parse(src.getAsString()))
					.setPrettyPrinting()
					.serializeSpecialFloatingPointValues()
					.create();

	/**
	 * JSON-Serialization with pretty-printing, float / double rounding to 3
	 * decimals and support for special floating point values (NaN, infinity...).
	 */
	public static String toJson(Object src)
	{
		return gson.toJson(src);
	}

	public static <T> T fromJson(String src, Type typeOfT)
	{
		return gson.fromJson(src, typeOfT);
	}
}
