package sorting.sorter;

import sorting.ConsoleHelper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class NumberSorter extends Sorter<Long> {
    @Override
    public List<Long> sort(List<String> items) {
        List<Long> numbers = new ArrayList<>();
        for (String item: items) {
            long number = Long.parseLong(item);
            numbers.add(number);
        }
        Collections.sort(numbers);
        return numbers;
    }

    @Override
    public <Long> Map<Long, Integer> sortByCount(Map<Long, Integer> countMap) {
        List<Map.Entry<Long, Integer>> mapEntries = new ArrayList<>(countMap.entrySet());
        mapEntries.sort((o1, o2) -> {
            if (!Objects.equals(o1.getValue(), o2.getValue())) {
                return o1.getValue() - o2.getValue();
            }
            return (int) ((long) o1.getKey() - (long) o2.getKey());
        });
        Map<Long, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Long, Integer> entry : mapEntries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    @Override
    public <T> Map<T, Integer> findCount(List<T> items) {
        Map<Long, Integer> countMap = new LinkedHashMap<>();
        for (T item: items) {
            long itemKey;
            try {
                itemKey = Long.parseLong((String) item);
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(String.format("\"%s\" is not a long. It will be skipped", item));
                continue;
            }
            if (countMap.containsKey(itemKey)) {
                int count = countMap.get(itemKey);
                countMap.put(itemKey, ++count);
            } else {
                countMap.put(itemKey, 1);
            }
        }
        return (Map<T, Integer>) countMap;
    }

    @Override
    public void printSortedItems(List<Long> sortedItems) {
        ConsoleHelper.writeMessage(String.format("Total numbers: %d.", sortedItems.size()));
        ConsoleHelper.writeMessage(String.format("Sorted data: %s\n", trimData(sortedItems)));
    }

    @Override
    public void writeSortedItems(List<Long> sortedItems, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(String.format("Total numbers: %d.\n", sortedItems.size()));
            fileWriter.write(String.format("Sorted data: %s\n", trimData(sortedItems)));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Something went wrong writing to file");
        }
    }

    private String trimData(List<Long> data) {
        String result = String.join(" ", String.valueOf(data));
        return result.substring(1, result.length() - 1).replaceAll(", ", " ");
    }

    @Override
    public <T> void printCount(Map<T, Integer> map) {
        int total = super.getSize(map);
        ConsoleHelper.writeMessage(String.format("Total numbers: %d.", total));
        super.printCount(map);
    }

    @Override
    public <T> void writeCountToFile(Map<T, Integer> map, String fileName) {
        int total = super.getSize(map);
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(String.format("Total numbers: %d.", total));
            super.writeCountToFile(map, fileName);
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Something went wrong writing to file");
        }
    }
}
