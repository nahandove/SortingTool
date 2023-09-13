package sorting;

import sorting.sorter.LineSorter;
import sorting.sorter.NumberSorter;
import sorting.sorter.Sorter;
import sorting.sorter.WordSorter;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(final String[] args) {
        Sorter sorter = null;

        if (args.length == 0) {
            sorter = new WordSorter();
            sorter.printSortedItems(sorter.sort(sorter.getAllEntries()));
        } else {
            List<String> arguments = Arrays.asList(args);
            if (arguments.contains("-dataType") && (!arguments.contains("long") &&
                    !arguments.contains("line") && !arguments.contains("word"))) {
                ConsoleHelper.writeMessage("No data type defined!");
                return;
            }
            if (arguments.contains("-sortingType") && (!arguments.contains("natural") &&
                    !arguments.contains("byCount"))) {
                ConsoleHelper.writeMessage("No sorting type defined!");
                return;
            }

            checkValidArguments(args);

            for (int i = 0; i < args.length - 1; i++) {
                if ("-dataType".equals(args[i]) && "long".equals(args[i + 1])) {
                    sorter = new NumberSorter();
                    break;
                } else if ("-dataType".equals(args[i]) && "line".equals(args[i + 1])) {
                    sorter = new LineSorter();
                    break;
                } else {
                    sorter = new WordSorter();
                }
            }
            outputData(arguments, sorter);
        }
    }

    private static void checkValidArguments(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if ("-inputFile".equals(args[i]) || "-outputFile".equals(args[i])) {
                i += 2;
                continue;
            }

            if (!"-dataType".equals(args[i]) && !"-sortingType".equals(args[i]) &&
                    !"natural".equals(args[i]) && !"byCount".equals(args[i]) && !"long".equals(args[i]) &&
                    !"line".equals(args[i]) && !"word".equals(args[i])) {
                ConsoleHelper.writeMessage(String.format("\"%s\" is not a valid parameter. It will be skipped.", args[i]));
            }
        }
    }

    private static void outputData(List<String> arguments, Sorter sorter) {
        if (!arguments.contains("-inputFile") && !arguments.contains("-outputFile")) {
            readAndWriteOnConsole(arguments, sorter);
        } else if (!arguments.contains("-outputFile")) {
            outputFileDataToConsole(arguments, sorter);
        } else if (!arguments.contains("-inputFile")) {
            writeConsoleDataToFile(arguments, sorter);
        } else {
            readAndWriteWithFiles(arguments, sorter);
        }
    }

    private static void readAndWriteOnConsole(List<String> arguments, Sorter sorter) {
        if (isSortByCount(arguments)) {
            sorter.printCount(sorter.sortByCount(sorter.findCount(sorter.getAllEntries())));
        } else {
            sorter.printSortedItems(sorter.sort(sorter.getAllEntries()));
        }
    }

    private static void outputFileDataToConsole(List<String> arguments, Sorter sorter) {
        int inputIndex = 0;
        for (int i = 0; i < arguments.size(); i++) {
            if ("-inputFile".equals(arguments.get(i))) {
                inputIndex = i + 1;
            }
        }
        if (isSortByCount(arguments)) {
            sorter.printCount(sorter.sortByCount(sorter.findCount(
                    sorter.getEntriesFromFile(arguments.get(inputIndex)))));
        } else {
            sorter.printSortedItems(sorter.sort(sorter.getEntriesFromFile(arguments.get(inputIndex))));
        }
    }

    private static void writeConsoleDataToFile(List<String> arguments, Sorter sorter) {
        int outputIndex = 0;
        for (int i = 0; i < arguments.size(); i++) {
            if ("-outputFile".equals(arguments.get(i))) {
                outputIndex = i + 1;
            }
        }
        if (isSortByCount(arguments)) {
            sorter.writeCountToFile(sorter.sortByCount(sorter.findCount(
                    sorter.getAllEntries())), arguments.get(outputIndex));
        } else {
            sorter.writeSortedItems(sorter.sort(sorter.getAllEntries()), arguments.get(outputIndex));
        }
    }

    private static void readAndWriteWithFiles(List<String> arguments, Sorter sorter) {
        int inputIndex = 0;
        int outputIndex = 0;
        for (int i = 0; i < arguments.size(); i++) {
            if ("-inputFile".equals(arguments.get(i))) {
                inputIndex = i + 1;
            }
            if ("-outputFile".equals(arguments.get(i))) {
                outputIndex = i + 1;
            }
        }
        if (isSortByCount(arguments)) {
            sorter.writeCountToFile(sorter.sortByCount(sorter.findCount(
                    sorter.getEntriesFromFile(arguments.get(inputIndex)))), arguments.get(outputIndex));
        } else {
            sorter.writeSortedItems(sorter.sort(sorter.getEntriesFromFile(arguments.get(inputIndex))),
                    arguments.get(outputIndex));
        }
    }

    private static boolean isSortByCount(List<String> arguments) {
        return arguments.contains("-sortingType") && arguments.contains("byCount");
    }
}


