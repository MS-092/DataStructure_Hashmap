import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.Map;

public class StudentTableModel extends AbstractTableModel {
    private Map<String, Student> students = new HashMap<>();

    public int getColumnCount() {
        return 5; // id, name, age, assignment1, assignment2
    }

    public int getRowCount() {
        return students.size();
    }

    public Object getValueAt(int row, int column) {
        String studentId = (String) students.keySet().toArray()[row];
        Student student = students.get(studentId);
        switch (column) {
            case 0:
                return student.getId();
            case 1:
                return student.getName();
            case 2:
                return student.getAge();
            case 3:
                return student.getAssignment1();
            case 4:
                return student.getAssignment2();
            default:
                return null;
        }
    }

    public void addStudent(Student student) {
        students.put(student.getId(), student);
        fireTableRowsInserted(getRowCount() - 1, getRowCount());
    }

    public void removeStudent(String studentId) {
        students.remove(studentId);
        fireTableRowsDeleted(getRowCount() - 1, getRowCount());
    }
}