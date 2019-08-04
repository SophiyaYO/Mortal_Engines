package common;

import common.interfaces.CommandInterpreter;
import common.interfaces.InputReader;
import common.interfaces.OutputWriter;

public class MainController {
    private InputReader reader;
    private OutputWriter writer;
    private CommandInterpreter commandInterpreter;
    private boolean isRunning;

    public MainController(InputReader reader, OutputWriter writer,
                          CommandInterpreter commandInterpreter) {
        this.reader = reader;
        this.writer = writer;
        this.commandInterpreter = commandInterpreter;
        isRunning = false;
    }

    public void run() {
        isRunning = true;
        StringBuilder aggregator = new StringBuilder();

        while (this.isRunning) {
            String[] args = this.reader.readLine().split("\\s+");
            String command = args[0];

            String output = this.commandInterpreter.interpret(command, args);

            if (output.equals("Over")) {
                this.writer.writeLine(aggregator.toString().trim());
                this.isRunning = false;
            } else {
                aggregator
                        .append(output)
                        .append(System.lineSeparator());
            }


        }
    }
}
