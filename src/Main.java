import javax.swing.*;
import java.util.List;

public class Main extends JFrame{
    private JPanel panel;
    private JButton loadData;
    private JScrollPane tablePane;
    private JTable tableData;

    private final JFileChooser jFileChooser;

    private DataTable dataTable;

    public Main(){
        jFileChooser = new JFileChooser(".");
        tableData = new JTable(dataTable);

        loadData.addActionListener(e -> dataTable.loadData(getFileData()).update());
    }

    public List<Deskovka> getFileData() {
        int result = jFileChooser.showOpenDialog(this);

        if (result == JFileChooser.CANCEL_OPTION || result == JFileChooser.ERROR_OPTION) {
            System.out.println("Invalid option, please select file again!");
            return null;
        }

        panel.remove(loadData);

        return FileUtils.loadData(jFileChooser.getSelectedFile());
    }

    public static void main(String[] args) {
        Main main = new Main();

        main.setContentPane(main.panel);

        main.setSize(500, 500);
        main.setDefaultCloseOperation(EXIT_ON_CLOSE);
        main.setVisible(true);
    }

    private void createUIComponents() {
        dataTable = new DataTable().addColumnData(0, "Game")
                .addColumnData(1, "Purchased")
                .addColumnData(2, "Popularity");

        tableData = new JTable(dataTable);
        tablePane = new JScrollPane(tableData);

        add(tableData);
    }
}
