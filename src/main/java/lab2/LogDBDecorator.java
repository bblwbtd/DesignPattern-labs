package lab2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LogDBDecorator extends AbstractDBDecorator {

    private final List<String> logs = new LinkedList<>();

    public LogDBDecorator(DB db) {
        super(db);
    }

    public List<String> getLogs() {
        return logs;
    }

    public void clearLogs() {
        logs.clear();
    }

    private String getCurrentTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public DBTable getTable(String name) {
        return super.getTable(name);
    }

    @Override
    public void createTable(String name, int columns) {
        String log = String.format("%s create table operation, table name: %s",
                getCurrentTime(), name);
        logs.add(log);
        super.createTable(name, columns);
    }

    @Override
    public void insert(String tableName, String[] row) {
        String log = String.format("%s insert operation, table name: %s, row data: %s",
                getCurrentTime(), tableName, Arrays.toString(row));
        logs.add(log);
        super.insert(tableName, row);
    }

    @Override
    public DBTable select(String tabName, int col, String selector) {
        String log = String.format("%s select operation, table name: %s, col: %d, selector: %s",
                getCurrentTime(), tabName, col, selector);
        logs.add(log);
        return super.select(tabName, col, selector);
    }

    @Override
    public DBTable join(String tab1Name, String tab2Name, int tab1Col, int tab2Col) {
        String log = String.format("%s join operation, table1 name: %s, table2 name: %s, tab1 col: %d, tab2 col: %d",
                getCurrentTime(), tab1Name, tab2Name, tab1Col, tab2Col);
        logs.add(log);
        return super.join(tab1Name, tab2Name, tab1Col, tab2Col);
    }
}
