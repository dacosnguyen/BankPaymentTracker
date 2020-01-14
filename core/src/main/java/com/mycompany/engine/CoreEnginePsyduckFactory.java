package com.mycompany.engine;

public class CoreEnginePsyduckFactory implements AbstractCoreEngineFactory {
    public AbstractCoreEngine create() {
        return new CoreEnginePsyduck();
    }
}
