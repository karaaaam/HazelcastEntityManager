package fr.karam.data.utils;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Consumer;

public final class GsonUtils {

    private static Gson cachedInstance;
    private static final Map<Type, Object> SERIALIZERS = new HashMap<>();
    private static final Collection<ExclusionStrategy> EXCLUSION_STRATEGIES = new HashSet<>();

    private GsonUtils() {
    }

    public static void registerSerializer(Type type, Object serializer){
        if(!(serializer instanceof JsonDeserializer<?> || serializer instanceof JsonSerializer<?>)){
            throw new IllegalStateException("Invalid serializer");
        }

        SERIALIZERS.put(type, serializer);
        cachedInstance = null;
    }

    public static void registerExclusionStrategy(ExclusionStrategy exclusionStrategy){
        EXCLUSION_STRATEGIES.add(exclusionStrategy);
        cachedInstance = null;
    }

    public static void unregisterType(Type type){
        SERIALIZERS.remove(type);
        cachedInstance = null;
    }

    public static void unregisterExclusionStrategy(ExclusionStrategy exclusionStrategy){
        EXCLUSION_STRATEGIES.remove(exclusionStrategy);
        cachedInstance = null;
    }

    public static Gson get(){
        if(cachedInstance == null) cachedInstance = get(GsonOption.DEFAULT);
        return cachedInstance;
    }

    public static Gson get(GsonOption option){

        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.serializeNulls();
        gsonBuilder.disableHtmlEscaping();
        gsonBuilder.enableComplexMapKeySerialization();

        if(option.pretty) gsonBuilder.setPrettyPrinting();
        if(option.customSettings != null) option.customSettings.accept(gsonBuilder);

        Collection<ExclusionStrategy> exclusionStrategies = new HashSet<>(EXCLUSION_STRATEGIES);
        exclusionStrategies.removeAll(option.removeStrategies);
        exclusionStrategies.addAll(option.strategies);

        gsonBuilder.setExclusionStrategies(new GlobalExclusionStrategy(exclusionStrategies));

        Collection<Map<Type, Object>> serializers = Arrays.asList(SERIALIZERS, option.serializers);
        for (Map<Type, Object> serializer : serializers) {

            for (Map.Entry<Type, Object> adapter : serializer.entrySet()) {

                if(option.removeTypes.contains(adapter.getKey())) continue;
                if(option.serializers.containsKey(adapter.getKey())) continue;

                gsonBuilder.registerTypeAdapter(adapter.getKey(), adapter.getValue());
                if(adapter.getKey() instanceof Class<?> clazz){
                    gsonBuilder.registerTypeHierarchyAdapter(clazz, adapter.getValue());
                }

            }

        }

        return gsonBuilder.create();
    }

    public static final class GsonOption {

        public static final GsonOption DEFAULT = new GsonOption();

        private boolean pretty;
        private final Collection<Type> removeTypes = new HashSet<>(1);
        private final Collection<ExclusionStrategy> removeStrategies = new HashSet<>(1);

        private Consumer<GsonBuilder> customSettings;
        private final Map<Type, Object> serializers = new HashMap<>(1);
        private final Collection<ExclusionStrategy> strategies = new HashSet<>(1);

        public GsonOption() {
        }

        public GsonOption setPretty(boolean pretty) {
            this.pretty = pretty;
            return this;
        }

        public GsonOption removeTypes(Type... types){
            this.removeTypes.addAll(Arrays.asList(types));
            return this;
        }

        public GsonOption removeType(Type type){
            this.removeTypes.add(type);
            return this;
        }

        public GsonOption removeStrategies(ExclusionStrategy... strategies){
            this.removeStrategies.addAll(Arrays.asList(strategies));
            return this;
        }

        public GsonOption removeStrategy(ExclusionStrategy strategy){
            this.removeStrategies.add(strategy);
            return this;
        }

        public GsonOption customSettings(Consumer<GsonBuilder> customSettings){
            this.customSettings = customSettings;
            return this;
        }

        public GsonOption registerSerializer(Type type, Object serializer){
            if(!(serializer instanceof JsonDeserializer<?> || serializer instanceof JsonSerializer<?>)) throw new IllegalStateException("Invalid serializer");
            this.serializers.put(type, serializer);
            return this;
        }

        public GsonOption registerExclusionStrategy(ExclusionStrategy exclusionStrategy){
            this.strategies.add(exclusionStrategy);
            return this;
        }

    }

    private record GlobalExclusionStrategy(Collection<ExclusionStrategy> exclusionStrategies) implements ExclusionStrategy {

        @Override
            public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                for (ExclusionStrategy exclusionStrategy : exclusionStrategies)
                    if (exclusionStrategy.shouldSkipField(fieldAttributes))
                        return true;
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> aClass) {
                for (ExclusionStrategy exclusionStrategy : exclusionStrategies)
                    if (exclusionStrategy.shouldSkipClass(aClass))
                        return true;
                return false;
            }

        }

}