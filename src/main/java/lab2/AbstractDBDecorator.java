package lab2;

public class AbstractDBDecorator extends DB {
    private final DB db;

    public AbstractDBDecorator(DB db) {
        this.db = db;
    }

    @Override
    public DBTable getTable(String name) {
        return super.getTable(name);
    }

    @Override
    public void createTable(String name, int columns) {
        super.createTable(name, columns);
    }

    @Override
    public void insert(String tableName, String[] row) {
        super.insert(tableName, row);
    }

    @Override
    public DBTable select(String tabName, int col, String selector) {
        return super.select(tabName, col, selector);
    }

    @Override
    public DBTable join(String tab1Name, String tab2Name, int tab1Col, int tab2Col) {
        return super.join(tab1Name, tab2Name, tab1Col, tab2Col);
    }
}
