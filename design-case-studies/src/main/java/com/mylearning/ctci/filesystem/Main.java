package com.mylearning.ctci.filesystem;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){

            CommandDefinition command = getCommand(scanner.nextLine());
            switch (command.command){
                case CD:
                    fileSystem.changeDirectory(command.parameter);
                    break;

                case LS:
                    List<Entry> content = fileSystem.listContents();
                    for (Entry entry : content){
                        System.out.println(entry);
                    }
                    break;

                case MKDIR:
                    fileSystem.makeDirectory(command.parameter);
                    break;

                case QUIT:
                    break;

                case PWD:
                    fileSystem.getCurrentDirectory();
                    break;

                case TOUCH:
                    fileSystem.createFile(command.parameter);
                    break;

            }
        }
    }

    static CommandDefinition getCommand(String command){
        String[] tokens = command.split(" ");
        CommandDefinition commandDefinition = new CommandDefinition(Command.valueOf(tokens[0]), tokens[1]);
        return commandDefinition;
    }
}
