package sorting.sorter;

import sorting.ConsoleHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class LineSorter extends Sorter<String> {
    @Override
    public List<String> getAllEntries() {
        List<String> items = new ArrayList<>();
        while (ConsoleHelper.hasNext()) {
            String input = ConsoleHelper.readString();
            items.add(input);
        }
        return items;
    }

    @Override
    public List<String> getEntriesFromFile(String fileName) {
        List<String> items = new ArrayList<>();
        try(BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            while (fileReader.ready()) {
                String input = fileReader.readLine();
                items.add(input);
            }
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Something went wrong reading from file");
        }
        return items;
    }

    @Override
    public List<String> sort(List<String> items) {
        items.sort(Comparator.comparingInt(String::length));
        return items;
    }

    @Override
    public void printSortedItems(List<String> sortedItems) {
        ConsoleHelper.writeMessage(String.format("Total lines: %d.\nSorted data:", sortedItems.size()));
        for (String line: sortedItems) {
            ConsoleHelper.writeMessage(line);
        }
    }

    @Override
    public void writeSortedItems(List<String> sortedItems, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(String.format("Total lines: %d.\nSorted data:\n", sortedItems.size()));
            for (String line: sortedItems) {
                fileWriter.write(line);
            }
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Something went wrong writing to file");
        }
    }

    @Override
    public <T> void printCount(Map<T, Integer> map) {
        int total = super.getSize(map);
        ConsoleHelper.writeMessage(String.format("Total lines: %d.", total));
        super.printCount(map);
    }

    @Override
    public <T> void writeCountToFile(Map<T, Integer> map, String fileName) {
        int total = super.getSize(map);
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(String.format("Total lines: %d.", total));
            super.writeCountToFile(map, fileName);
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Something went wrong writing to file");
        }
    }
}
