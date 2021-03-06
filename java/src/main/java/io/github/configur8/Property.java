package io.github.configur8;

/**
 * A typed property which can be serialized and deserialized to a string value
 */
public class Property<T> {

    public final String name;
    public final Deserializer<T> deserialize;
    public final Serializer<T> serialize;
    public final ExposeMode exposeMode;

    private Property(String name, Deserializer<T> deserialize, Serializer<T> serialize, ExposeMode exposeMode) {
        this.name = name;
        this.deserialize = deserialize;
        this.serialize = serialize;
        this.exposeMode = exposeMode;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Create a Property
     * @param name of the property
     * @param deserialize function
     * @param serialize function
     * @param <T> type
     * @return The property
     */
    public static <T> Property<T> of(String name, Deserializer<T> deserialize, Serializer<T> serialize) {
        return of(name, deserialize, serialize, ExposeMode.Public);
    }

    /**
     * Create a Property
     * @param name of the property
     * @param deserialize function
     * @param <T> type
     * @return The property
     */
    public static <T> Property<T> of(String name, Deserializer<T> deserialize) {
        return of(name, deserialize, Object::toString, ExposeMode.Public);
    }

    /**
     * Create a Property
     * @param name of the property
     * @param deserialize function
     * @param exposeMode mode which determines if the value should be publicly accessible or concealed
     * @param <T> type
     * @return The property
     */
    public static <T> Property<T> of(String name, Deserializer<T> deserialize, ExposeMode exposeMode) {
        return of(name, deserialize, Object::toString, exposeMode);
    }


    /**
     * Create a Property
     * @param name of the property
     * @param deserialize function
     * @param serialize function
     * @param exposeMode mode which determines if the value should be publicly accessible or concealed
     * @param <T> type
     * @return The property
     */
    public static <T> Property<T> of(String name, Deserializer<T> deserialize, Serializer<T> serialize, ExposeMode exposeMode) {
        return new Property<>(name, deserialize, serialize, exposeMode);
    }

    public static Property<String> string(String name) {
        return string(name, ExposeMode.Public);
    }

    public static Property<String> string(String name, ExposeMode exposeMode) {
        return new Property<>(name, String::new, s -> s, exposeMode);
    }

    public static Property<Integer> integer(String name) {
        return integer(name, ExposeMode.Public);
    }

    public static Property<Integer> integer(String name, ExposeMode exposeMode) {
        return new Property<>(name, Integer::parseInt, Object::toString, exposeMode);
    }

    public static Property<Long> aLong(String name) {
        return aLong(name, ExposeMode.Public);
    }

    public static Property<Long> aLong(String name, ExposeMode exposeMode) {
        return new Property<>(name, Long::parseLong, Object::toString, exposeMode);
    }

    public static Property<Boolean> bool(String name) {
        return bool(name, ExposeMode.Public);
    }

    public static Property<Boolean> bool(String name, ExposeMode exposeMode) {
        return new Property<>(name, Boolean::parseBoolean, Object::toString, exposeMode);
    }

    public static Property<Character> character(String name) {
        return character(name, ExposeMode.Public);
    }

    public static Property<Character> character(String name, ExposeMode exposeMode) {
        return new Property<>(name, (in) -> in.charAt(0), Object::toString, exposeMode);
    }
}
