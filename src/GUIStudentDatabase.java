import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GUIStudentDatabase extends JFrame {

    private static HashMap<String, Student> students = new HashMap<>();
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JTextField formula1Field;
    private JTextField formula2Field;
    private TableRowSorter<DefaultTableModel> sorter;

    public GUIStudentDatabase() {
        setTitle("Student Database Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Search bar
        searchField = new JTextField();
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search();
            }
        });
        panel.add(searchField, BorderLayout.NORTH);

        // Table to display student data
        String[] columnNames = {"ID", "Name", "Age", "Gender", "Assignment 1", "Assignment 2", "Final Score"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4 || column == 5;
            }
        };
        JTable table = new JTable(tableModel);
        table.getModel().addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();
            if (column == 4 || column == 5) {
                String id = (String) tableModel.getValueAt(row, 0);
                Student student = students.get(id);
                if (column == 4) {
                    student.setAssignment1((Double) tableModel.getValueAt(row, column));
                } else {
                    student.setAssignment2((Double) tableModel.getValueAt(row, column));
                }
                student.calculateFinalScore();
                tableModel.setValueAt(student.getFinalScore(), row, 6);
            }
        });

        // Add sorting functionality to the table
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = table.columnAtPoint(e.getPoint());
                if (column >= 0) {
                    boolean isAscending = sorter.getSortKeys().isEmpty() ||
                            sorter.getSortKeys().get(0).getSortOrder() == SortOrder.DESCENDING;
                    sorter.setSortKeys(List.of(new RowSorter.SortKey(column, isAscending ? SortOrder.ASCENDING : SortOrder.DESCENDING)));
                    sorter.sort();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel for buttons and formula fields
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2, 1));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Add Student button
        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
        buttonPanel.add(addButton);

        // Remove Student button
        JButton removeButton = new JButton("Remove Student");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });
        buttonPanel.add(removeButton);

        bottomPanel.add(buttonPanel);

        JPanel formulaPanel = new JPanel();
        formulaPanel.setLayout(new FlowLayout());

        JLabel formula1Label = new JLabel("Assignment 1 Weight:");
        formula1Field = new JTextField("0.35", 5);
        JLabel formula2Label = new JLabel("Assignment 2 Weight:");
        formula2Field = new JTextField("0.65", 5);
        JButton updateFormulaButton = new JButton("Update Formula");
        updateFormulaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFormula();
            }
        });

        formulaPanel.add(formula1Label);
        formulaPanel.add(formula1Field);
        formulaPanel.add(formula2Label);
        formulaPanel.add(formula2Field);
        formulaPanel.add(updateFormulaButton);

        bottomPanel.add(formulaPanel);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        add(panel);

        // Pre-populate with sample data
        addSampleData();
    }

    private void addStudent() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField assignment1Field = new JTextField();
        JTextField assignment2Field = new JTextField();

        Object[] fields = {
                "ID:", idField,
                "Name:", nameField,
                "Age:", ageField,
                "Gender (M/F):", genderField,
                "Assignment 1 Score:", assignment1Field,
                "Assignment 2 Score:", assignment2Field
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Add Student", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String id = idField.getText();
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String gender = genderField.getText();
            double assignment1 = Double.parseDouble(assignment1Field.getText());
            double assignment2 = Double.parseDouble(assignment2Field.getText());

            // Exception statement for adding student
            if (students.containsKey(id)) {
                JOptionPane.showMessageDialog(this, "Student with this ID already exists. Please enter a different ID.");
                return;
            }

            Student student = new Student(id, name, age, gender, assignment1, assignment2);
            students.put(id, student);
            addStudentToTable(student);
        }
    }

    private void removeStudent() {
        String id = JOptionPane.showInputDialog(this, "Enter student ID to remove:");
        if (id != null && !id.isEmpty()) {
            if (students.containsKey(id)) {
                students.remove(id);
                removeStudentFromTable(id);
                JOptionPane.showMessageDialog(this, "Student removed successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Student not found.");
            }
        }
    }

    private void addStudentToTable(Student student) {
        Object[] rowData = {
                student.getId(),
                student.getName(),
                student.getAge(),
                student.getGender(),
                student.getAssignment1(),
                student.getAssignment2(),
                student.getFinalScore()
        };
        tableModel.addRow(rowData);
    }

    private void removeStudentFromTable(String id) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).equals(id)) {
                tableModel.removeRow(i);
                break;
            }
        }
    }

    private void search() {
        String searchText = searchField.getText().toLowerCase();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        sorter.setRowFilter(null);

        // Get all students from the HashMap and store them in a list
        List<Student> studentsList = new ArrayList<>();
        for (Student student : this.students.values()) {
            if (student.getName().toLowerCase().contains(searchText) ||
                    String.valueOf(student.getAge()).contains(searchText) ||
                    student.getGender().contains(searchText) ||
                    String.valueOf(student.getAssignment1()).contains(searchText) ||
                    String.valueOf(student.getAssignment2()).contains(searchText)) {
                studentsList.add(student);
            }
        }

        // Update the table model with the filtered students
        tableModel.setRowCount(0);
        for (Student student : studentsList) {
            Object[] rowData = {
                    student.getId(),
                    student.getName(),
                    student.getAge(),
                    student.getGender(),
                    student.getAssignment1(),
                    student.getAssignment2(),
                    student.getFinalScore()
            };
            tableModel.addRow(rowData);
        }
    }

    private void updateFormula() {
        double formula1 = Double.parseDouble(formula1Field.getText());
        double formula2 = Double.parseDouble(formula2Field.getText());

        for (Student student : students.values()) {
            student.setFormula1(formula1);
            student.setFormula2(formula2);
            student.calculateFinalScore();
        }

        updateTableData();
    }

    private void updateTableData() {
        tableModel.setRowCount(0);
        for (Student student : students.values()) {
            addStudentToTable(student);
        }
    }

    private void addSampleData() {
        String[] ids = {"S001", "S002", "S003", "S004", "S005", "S006", "S007", "S008", "S009", "S010",
                "S011", "S012", "S013", "S014", "S015", "S016", "S017", "S018", "S019", "S020"};
        String[] names = {"John", "Emily", "Michael", "Emma", "Daniel", "Olivia", "Matthew", "Sophia", "David", "Ava",
                "James", "Isabella", "Benjamin", "Mia", "William", "Charlotte", "Ethan", "Amelia", "Alexander", "Harper"};
        int[] ages = {20, 19, 21, 18, 22, 20, 19, 21, 18, 20, 19, 21, 18, 22, 20, 19, 21, 18, 20, 19};
        String[] genders = {"M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F", "M", "F"};
        double[] assignment1Scores = {85.5, 92.0, 78.0, 88.5, 90.0, 95.0, 87.5, 91.0, 83.0, 89.5, 92.5, 88.0, 85.0, 93.0, 87.0, 91.5, 84.0, 90.5, 88.5, 92.0};
        double[] assignment2Scores = {90.0, 88.5, 92.5, 87.0, 95.0, 91.0, 89.0, 93.5, 86.0, 92.0, 88.0, 94.0, 90.5, 87.5, 91.5, 89.5, 92.0, 88.0, 93.0, 90.0};

        for (int i = 0; i < 20; i++) {
            Student student = new Student(ids[i], names[i], ages[i], genders[i], assignment1Scores[i], assignment2Scores[i]);
            students.put(ids[i], student);
            addStudentToTable(student);
        }
    }
}