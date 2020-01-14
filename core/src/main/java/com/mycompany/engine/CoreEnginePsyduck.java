package com.mycompany.engine;

/**
 * A dummy engine just to demonstrate we can inject another engine.
 * For example for bimodal IT.
 */
public class CoreEnginePsyduck implements AbstractCoreEngine {
    public void start() {
        throw new RuntimeException("Psyduck Core Engine is not implemented yet");
    }
}
