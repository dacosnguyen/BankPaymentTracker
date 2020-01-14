package com.mycompany;

public class CoreEnginePsyduckFactory implements AbstractCoreEngineFactory {
    public AbstractCoreEngine create() {
        return new CoreEnginePsyduck();
    }
}
