import lab2.DB;
import lab2.LogDBDecorator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogDBDecoratorTest {

    private final LogDBDecorator decorator = new LogDBDecorator(new DB());
    private final String tableName = "testTable";
    private final String tableName1 = "testTable1";

    LogDBDecoratorTest() {
        decorator.createTable(tableName, 2);
        decorator.createTable(tableName1, 2);
    }

    private boolean checkLogs(String keyword) {
        List<String> logs = decorator.getLogs();
        return (logs.get(logs.size() - 1).contains(keyword));
    }

    @Test
    void testCreateTable() {
        decorator.createTable("test", 2);
        assertTrue(checkLogs("create table"));
    }

    @Test
    void testInsertRow() {
        decorator.insert(tableName, new String[]{"123", "456"});
        assertTrue(checkLogs("insert"));
    }

    @Test
    void testSelect() {
        decorator.select(tableName, 0, "");
        assertTrue(checkLogs("select"));
    }

    @Test
    void testJoin() {
        decorator.join(tableName, tableName1, 0, 0);
        assertTrue(checkLogs("join"));
    }
}
