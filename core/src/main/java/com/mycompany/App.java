package com.mycompany;

import com.mycompany.engine.AbstractCoreEngine;
import com.mycompany.service.CoreEngineProvider;

public class App {

    public static void main(String[] args) {
        AbstractCoreEngine engine = CoreEngineProvider.getFactory(CoreEngineProvider.CORE_ENGINE_NAME.PIKACHU).create();
        engine.start();
    }

}
