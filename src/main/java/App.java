import com.lab1.command.Command;
import com.lab1.command.CommandInvoker;
import com.lab1.model.HTML;
import com.lab1.util.CommandParser;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        HTML html = new HTML();
        CommandInvoker commandInvoker = new CommandInvoker();
        while (true) {
            System.out.println("Enter command: ");
            Scanner input=new Scanner(System.in);
            String commandStr=input.nextLine();
            if (commandStr.equals("exit")) {
                break;
            }
            try {
                Command command = CommandParser.parse(commandStr, commandInvoker, html);
                commandInvoker.storeAndExecute(command);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }
}

