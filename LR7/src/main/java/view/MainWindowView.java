package view;

import org.romadis.controllers.StudentCreateController;
import org.romadis.controllers.StudentUpdateController;
import org.romadis.controllers.Student_list_controller;
import org.romadis.dto.StudentFilter;
import org.romadis.pattern.student.Data_list_student_short;
import org.romadis.student.Student;
import org.romadis.student.Student_short;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainWindowView implements ViewInterface {

    private static final int PAGE_SIZE = 20;
    private static int currentPage = 1;

    private Student_list_controller controller;

    public void setController(Student_list_controller controller) {
        this.controller = controller;
    }

    private Data_list_student_short dataList;

    public void setDataList(Data_list_student_short dataList) {
        this.dataList = dataList;
    }

    private DefaultTableModel tableModel;

    private final JTextField nameField = new JTextField();

    private final JComboBox<String> gitComboBox = new JComboBox<>(new String[] { "No matter", "Yes", "No" });
    private final JTextField gitField = new JTextField();

    private final JTextField emailField = new JTextField();
    private final JComboBox<String> emailComboBox = new JComboBox<>(new String[] { "No matter", "Yes", "No" });

    private final JTextField phoneField = new JTextField();
    private final JComboBox<String> phoneComboBox = new JComboBox<>(new String[] { "No matter", "Yes", "No" });

    private final JTextField telegramField = new JTextField();
    private final JComboBox<String> telegramComboBox = new JComboBox<>(new String[] { "No matter", "Yes", "No" });

    private final JLabel pageInfoLabel = new JLabel("Page: 1 / ?");
    private final JButton prevPageButton = new JButton("Prev");
    private final JButton nextPageButton = new JButton("Next");

    private final JButton refreshButton = new JButton("Refresh");
    private final JButton addButton = new JButton("Add");
    private final JButton editButton = new JButton("Edit");
    private final JButton deleteButton = new JButton("Delete");

    public MainWindowView() {}

    public void create(Student_list_controller controller) {
        setController(controller);
        controller.firstInitDataList();
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Student Management");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.add("Adding", createStudentTab());

            frame.add(tabbedPane);
            frame.setVisible(true);
            update();
        });
    }

    private JPanel createStudentTab() {
        JPanel panel = new JPanel(new BorderLayout());
//        addFilters(panel);

        String[] columnNames = dataList.getEntityFields().toArray(new String[0]);
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel buttonPanel = new JPanel();

        editButton.setEnabled(false);
        deleteButton.setEnabled(false);

        table.getSelectionModel().addListSelectionListener(e -> {
            int selectedRowCount = table.getSelectedRowCount();
            editButton.setEnabled(selectedRowCount == 1); 
            deleteButton.setEnabled(selectedRowCount > 0); 
        });

        addButton.addActionListener(e -> {
            StudentCreateController studentCreateController = new StudentCreateController(this.controller);
            StudentFormModal modal = new StudentFormModal();
            modal.controller = studentCreateController;
            modal.create(null, "Creating");
        });

        editButton.addActionListener(e -> {
            StudentUpdateController studentUpdateController = new StudentUpdateController(this.controller);
            StudentFormModal modal = new StudentFormModal();
            modal.controller = studentUpdateController;
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                Student student = studentUpdateController.getStudentById(id);
                if (student == null) {
                    showError("Adding!");
                }
                modal.create(student, "Cant add");
            }
        });

        deleteButton.addActionListener(e -> {
            int[] selectedRows = table.getSelectedRows();
            if (selectedRows.length > 0) {
                int confirm = JOptionPane.showConfirmDialog(
                        panel,
                        "Are you shure?",
                        "deleted",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    boolean success = true;

                    for (int i = selectedRows.length - 1; i >= 0; i--) {
                        int id = (int) tableModel.getValueAt(selectedRows[i], 0);
                        if (!controller.deleteStudent(id)) {
                            success = false;
                        }
                    }

                    if (success) {
                        JOptionPane.showMessageDialog(panel, "Deleted!");
                    } else {
                        JOptionPane.showMessageDialog(panel, "Cant delete.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    controller.refresh_data();
                }
            }
        });

        nextPageButton.addActionListener(e -> {
            currentPage++;
            controller.refresh_data(PAGE_SIZE, currentPage, getCurrentFilter());
        });

        prevPageButton.addActionListener(e -> {
            if (currentPage > 1) {
                currentPage--;
                controller.refresh_data(PAGE_SIZE, currentPage, getCurrentFilter());
            }
        });

        refreshButton.addActionListener(e -> controller.refresh_data(PAGE_SIZE, currentPage, null));

        buttonPanel.add(pageInfoLabel); 
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(prevPageButton);
        buttonPanel.add(nextPageButton);
        buttonPanel.add(refreshButton);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    @Override
    public void update() {
        set_table_params();
        set_table_data();
    }

    private void set_table_params() {
        List<String> newColumnNames = dataList.getEntityFields();
        tableModel.setColumnIdentifiers(newColumnNames.toArray());

        int lastPage = dataList.getPagination().getTotalPages();

        if (lastPage < currentPage) {
            currentPage = lastPage;
            controller.refresh_data(PAGE_SIZE, currentPage, getCurrentFilter());
            return;
        }

        updatePageControls(lastPage);
    }

    private void updatePageControls(int lastPage) {

        pageInfoLabel.setText("Page: " + currentPage + " / " + lastPage);

        prevPageButton.setEnabled(currentPage > 1);
        nextPageButton.setEnabled(currentPage < lastPage);
    }

    private StudentFilter getCurrentFilter() {
        return null;
    }

    private void set_table_data() {
        tableModel.setRowCount(0); 

        List<Student_short> students = dataList.toList();

        for (Student_short student : students) {
            tableModel.addRow(new Object[] {
                    student.getId(),
                    student.getLastNameWithInitials(),
                    student.getGitInfo(),
                    student.getContactInfo(),
            });
        }
    }

    public void showError(String message) {
        JDialog dialog = new JDialog((Frame) null, "Error", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(7, 2));
        JOptionPane.showMessageDialog(dialog, "Error: " + message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}