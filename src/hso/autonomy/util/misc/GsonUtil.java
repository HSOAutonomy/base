/* Copyright 2008 - 2017 Hochschule Offenburg
 * For a list of authors see README.md
 * This software of HSOAutonomy is released under MIT License (see LICENSE).
 */

package hso.autonomy.util.misc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.Optional;

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
					.registerTypeAdapterFactory(OptionalTypeAdapter.FACTORY)
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

	public static class OptionalTypeAdapter<E> extends TypeAdapter<Optional<E>>
	{
		public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
			@Override
			public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type)
			{
				Class<T> rawType = (Class<T>) type.getRawType();
				if (rawType != Optional.class) {
					return null;
				}
				final ParameterizedType parameterizedType = (ParameterizedType) type.getType();
				final Type actualType = parameterizedType.getActualTypeArguments()[0];
				final TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(actualType));
				return new OptionalTypeAdapter(adapter);
			}
		};
		private final TypeAdapter<E> adapter;

		public OptionalTypeAdapter(TypeAdapter<E> adapter)
		{
			this.adapter = adapter;
		}

		@Override
		public void write(JsonWriter out, Optional<E> value) throws IOException
		{
			if (value != null && value.isPresent()) {
				adapter.write(out, value.get());
			} else {
				out.nullValue();
			}
		}

		@Override
		public Optional<E> read(JsonReader in) throws IOException
		{
			final JsonToken peek = in.peek();
			if (peek != JsonToken.NULL) {
				return Optional.ofNullable(adapter.read(in));
			}
			return Optional.empty();
		}
	}
}
