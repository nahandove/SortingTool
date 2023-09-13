package sorting.sorter;

import sorting.ConsoleHelper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class WordSorter extends Sorter<String> {
    @Override
    public List<String> sort(List<String> items) {
        items.sort(Comparator.comparingInt(String::length));
        return items;
    }

    @Override
    public void printSortedItems(List<String> sortedItems) {
        ConsoleHelper.writeMessage(String.format("Total words: %d.", sortedItems.size()));
        ConsoleHelper.writeMessage(String.format("Sorted data: %s\n", trimData(sortedItems)));
    }

    @Override
    public void writeSortedItems(List<String> sortedItems, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(String.format("Total words: %d.\n", sortedItems.size()));
            fileWriter.write(String.format("Sorted data: %s\n", trimData(sortedItems)));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Something went wrong writing to file");
        }
    }

    private String trimData(List<String> data) {
        String result = String.join(" ", String.valueOf(data));
        return result.substring(1, result.length() - 1).replaceAll(", ", " ");
    }

    @Override
    public <T> void printCount(Map<T, Integer> map) {
        int total = super.getSize(map);
        ConsoleHelper.writeMessage(String.format("Total words: %d.", total));
        super.printCount(map);
    }

    @Override
    public <T> void writeCountToFile(Map<T, Integer> map, String fileName) {
        int total = super.getSize(map);
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(String.format("Total words: %d.", total));
            super.writeCountToFile(map, fileName);
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Something went wrong writing to file");
        }
    }
}
