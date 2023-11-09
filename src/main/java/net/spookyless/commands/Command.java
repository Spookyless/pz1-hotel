package net.spookyless.commands;

import java.io.InputStream;
import java.io.PrintStream;

public abstract class Command<T> {
    public final String name;
    public final String description;

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    abstract public void execute(InputStream in, PrintStream pr, T params) throws Exception;
}
