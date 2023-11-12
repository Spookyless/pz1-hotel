package net.spookyless.fs;

abstract public class Persist<T> {
    protected final String filepath;

    public Persist(String filepath) {
        this.filepath = filepath;
    }

    public abstract void save(T object) throws Exception;
    public abstract T load() throws Exception;
}
