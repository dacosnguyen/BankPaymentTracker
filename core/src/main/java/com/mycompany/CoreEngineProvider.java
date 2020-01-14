package com.mycompany;

import java.util.EnumMap;
import java.util.Map;

public class CoreEngineProvider {

    public enum CORE_ENGINE_NAME {
        PIKACHU,
        PSYDUCK
    }

    private static final Map<CORE_ENGINE_NAME, AbstractCoreEngineFactory> map;
    static {
        map = new EnumMap<>(CORE_ENGINE_NAME.class);
        map.put(CORE_ENGINE_NAME.PIKACHU, new CoreEnginePikachuFactory());
        map.put(CORE_ENGINE_NAME.PSYDUCK, new CoreEnginePsyduckFactory());
    }

    public static AbstractCoreEngineFactory getFactory(CORE_ENGINE_NAME coreEngineName) {
        return map.get(coreEngineName);
    }

}