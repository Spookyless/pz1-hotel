package net.spookyless.commands;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class CommandRunner<T> {
    private final InputStream in;
    private final PrintStream pr;
    private final List<Command<T>> commands;

    private final Scanner scanner;

    public CommandRunner(InputStream in, PrintStream pr, List<Command<T>> commands) {
        this.in = in;
        this.pr = pr;
        this.commands = commands;

        this.scanner = new Scanner(in);
    }

    public void run(T params) {
        pr.println("Enter a command or use '?' for help");

        while (true) {
            pr.print("$ ");

            String line = scanner.nextLine().trim();

            if(line.isEmpty()) {
                continue;
            }

            if(line.equals("?")) {
                printHelp();
                continue;
            }

            Command<T> command = getInvokedCommand(line);

            if(command == null) {
                printUnknownCommand();
                continue;
            }

            try {
                command.execute(in, pr, params);
            } catch (StopIterationException e) {
                return;
            } catch (Exception e) {
                pr.println("[Error] " + e.getMessage());
            }
        }
    }

    private Command<T> getInvokedCommand(String line) {
        for (Command<T> command : commands) {
            if (!Objects.equals(command.name, line)) {
                continue;
            }

            return command;
        }

        return null;
    }

    private void printUnknownCommand() {
        pr.println("Unknown command, please try again (use '?' for help)");
    }

    private void printHelp() {
        for(Command<T> command: commands) {
            pr.println(command.name);
            pr.print(command.description.indent(2));
        }
    }
}
