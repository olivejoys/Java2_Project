package Java2_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GymECommerceGUI extends JFrame {
    private FitnessRecordList recordList;
    private int currentIndex;

    private JTextField searchField;
    private JTextArea displayArea;

    public GymECommerceGUI(FitnessRecordList recordList) {
        this.recordList = recordList;
        this.currentIndex = 0;

        // Initialize components
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        JButton firstButton = new JButton("First");
        JButton previousButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");
        JButton lastButton = new JButton("Last");

        displayArea = new JTextArea(10, 50);
        displayArea.setEditable(false);

        // Setting the layout to our GUI
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Search:"));
        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(addButton);
        topPanel.add(editButton);
        topPanel.add(deleteButton);

        JPanel navigationPanel = new JPanel();
        navigationPanel.add(firstButton);
        navigationPanel.add(previousButton);
        navigationPanel.add(nextButton);
        navigationPanel.add(lastButton);

        // Add components to the frame if you think it's needed
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);
        add(navigationPanel, BorderLayout.SOUTH);

        // Add action listeners
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchRecords();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRecord();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editRecord();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteRecord();
            }
        });

        firstButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateFirst();
            }
        });

        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigatePrevious();
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateNext();
            }
        });

        lastButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateLast();
            }
        });

        // Initialize display
        displayRecords();
    }

    private void displayRecords() {
        if (recordList.getSize() > 0) {
            FitnessRecord currentRecord = recordList.getRecord(currentIndex);
            displayArea.setText(currentRecord.toString());
        } else {
            displayArea.setText("No records found.");
        }
    }

    private void searchRecords() {
        String key = searchField.getText();
        ArrayList<FitnessRecord> searchResult = recordList.searchRecords(key,
                "product_name");

        if (searchResult.isEmpty()) {
            displayArea.setText("No matching records found.");
        } else {
            currentIndex = 0;
            displayRecords();
        }
    }

    private void addRecord() {
        // Implement logic for adding a new record
        //  need to finish the logic
        String product_id = JOptionPane.showInputDialog(this,
                "Enter Product ID:");
        String product_type = JOptionPane.showInputDialog(this,
                "Enter Product Type (Equipment, " +
                        "Supplement, Apparel, or Accessories):");
        String product_name = JOptionPane.showInputDialog(this,
                "Enter Product Name:");
        String brand_name = JOptionPane.showInputDialog(this,
                "Enter Brand Name:");
        double price = 0.0;
        try {
            String priceStr = JOptionPane.showInputDialog(this,
                    "Enter Price:");
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid price format." +
                            " Please enter a valid number.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int product_qty = 0;
        try {
            String qtyStr = JOptionPane.showInputDialog(this,
                    "Enter Product Quantity:");
            product_qty = Integer.parseInt(qtyStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid quantity format." +
                            " Please enter a valid number.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // have to create the string costumer_id, product_location

    }

    private void editRecord() {
        // Implement logic for editing the current record
        // You can use JOptionPane or create a new JFrame for data entry
    }

    private void deleteRecord() {
        int option = JOptionPane.showConfirmDialog(this,
                "Do you really want to delete this record?",
                "Confirmation", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            recordList.deleteRecord(currentIndex);

            if (recordList.getSize() > 0) {
                if (currentIndex >= recordList.getSize()) {
                    currentIndex = recordList.getSize() - 1;
                }
                displayRecords();
            } else {
                displayArea.setText("No records found.");
            }
        }
    }

    private void navigateFirst() {
        currentIndex = 0;
        displayRecords();
    }

    private void navigatePrevious() {
        if (currentIndex > 0) {
            currentIndex--;
            displayRecords();
        }
    }

    private void navigateNext() {
        if (currentIndex < recordList.getSize() - 1) {
            currentIndex++;
            displayRecords();
        }
    }

    private void navigateLast() {
        currentIndex = recordList.getSize() - 1;
        displayRecords();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FitnessRecordList recordList = new FitnessRecordList();
                // Load records from file if needed

                GymECommerceGUI gui = new GymECommerceGUI(recordList);
                gui.setTitle("Gym E-Commerce Platform");
                gui.setSize(600, 400);
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gui.setLocationRelativeTo(null);
                gui.setVisible(true);
            }
        });
    }
}
