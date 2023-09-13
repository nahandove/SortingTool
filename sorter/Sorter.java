package sorting.sorter;

import sorting.ConsoleHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public abstract class Sorter<T> {
    public List<String> getAllEntries() {
        List<String> items = new ArrayList<>();

        while (ConsoleHelper.hasNext()) {
            String input = ConsoleHelper.readString();
            List<String> inputs = Arrays.asList(input.split("\\s+"));
            items.addAll(inputs);
        }
        return items;
    }

    public List<String> getEntriesFromFile(String fileName) {
        List<String> items = new ArrayList<>();
        try(BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            while (fileReader.ready()) {
                String input = fileReader.readLine();
                List<String> inputs = Arrays.asList(input.split("\\s+"));
                items.addAll(inputs);
            }
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Something went wrong reading from file");
        }
        return items;
    }

    public abstract List<T> sort(List<String> items);

    public abstract void printSortedItems(List<T> sortedItems);

    public abstract void writeSortedItems(List<T> sortedItems, String fileName);

    public <T> Map<T, Integer> sortByCount(Map<T, Integer> countMap) {
        List<Map.Entry<T, Integer>> mapEntries = new ArrayList<>(countMap.entrySet());
        mapEntries.sort((o1, o2) -> {
            if (!Objects.equals(o1.getValue(), o2.getValue())) {
                return o1.getValue() - o2.getValue();
            }
            return CharSequence.compare((CharSequence) o1.getKey(), (CharSequence) o2.getKey());
        });
        Map<T, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<T, Integer> entry : mapEntries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    public <T> Map<T, Integer> findCount(List<T> items) {
        Map<T, Integer> countMap = new LinkedHashMap<>();
        for (T item: items) {
            if (countMap.containsKey(item)) {
                int count = countMap.get(item);
                countMap.put(item, ++count);
            } else {
                countMap.put(item, 1);
            }
        }
        return countMap;
    }

    public <T> void printCount(Map<T, Integer> map) {
        int total = getSize(map);
        for (Map.Entry<T, Integer> pair : map.entrySet()) {
            double percentValue = pair.getValue() * 1.0 / total * 100;
            ConsoleHelper.writeMessage(String.format("%s: %d time(s), %d%%",
                    pair.getKey(), pair.getValue(), (int) Math.round(percentValue)));
        }
    }

    public <T> void writeCountToFile(Map<T, Integer> map, String fileName) {
        int total = getSize(map);
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (Map.Entry<T, Integer> pair : map.entrySet()) {
                double percentValue = pair.getValue() * 1.0 / total * 100;
                fileWriter.write(String.format("%s: %d time(s), %d%%",
                        pair.getKey(), pair.getValue(), (int) Math.round(percentValue)));
            }
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Something went wrong writing to file");
        }
    }

    protected <T> int getSize(Map<T, Integer> map) {
        int total = 0;
        for (Integer count: map.values()) {
            total += count;
        }
        return total;
    }
}
