import javax.swing.*;
import java.awt.*;

public class FloristShopGUI {

    static int totalOrders = 0; //use static so can directly call in main() no need create a GUI object
    static double totalSales = 0.0;//all class will have the same static variable

    public static void main(String[] args) {
        showMainMenu();
    }

    // ================= MAIN MENU =================
    static void showMainMenu() {
        JFrame frame = new JFrame("🌸 Bloom Bloom Florist Shop 🌸");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 450);
        frame.setLocationRelativeTo(null);//i do this to set the JFrame centre of screen

        JPanel panel = new JPanel(new GridBagLayout()); //GridBagLayout can help allow precise placement of buttons/labels
        panel.setBackground(new Color(255, 240, 245));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); //sets spacing between components

        JLabel title = new JLabel(" 🌹 Bloom Bloom Florist Shop 🌹 ");
        title.setFont(new Font("Apple Color Emoji", Font.BOLD, 24));
        gbc.gridy = 0; //position in the grid
        panel.add(title, gbc);

        JButton btnDisplay = new JButton("Display Flower Packages");
        btnDisplay.setPreferredSize(new Dimension(280, 55));
        btnDisplay.addActionListener(e -> displayPackages()); //calls a method when its clicked
        gbc.gridy = 1;
        panel.add(btnDisplay, gbc);

        JButton btnOrder = new JButton("Calculate Order Charges");
        btnOrder.setPreferredSize(new Dimension(280, 55));
        btnOrder.addActionListener(e -> calculateOrder());
        gbc.gridy = 2;
        panel.add(btnOrder, gbc);

        JButton btnReport = new JButton("Generate Summary Report");
        btnReport.setPreferredSize(new Dimension(280, 55));
        btnReport.addActionListener(e -> generateReport());
        gbc.gridy = 3;
        panel.add(btnReport, gbc);

        JButton btnExit = new JButton("Exit");
        btnExit.setPreferredSize(new Dimension(280, 55));
        btnExit.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame,
                    "Thank you for using Bloom Bloom Florist 🌷");
            System.exit(0);
        });
        gbc.gridy = 4;
        panel.add(btnExit, gbc);

        frame.add(panel);
        frame.setVisible(true);
    }

    // ================= OPTION 1 =================
    static void displayPackages() {
        JTextArea text = new JTextArea(
                "+-----------------------------+------------+\n" +
                        "| Package Type                | Price (RM) |\n" +
                        "+-----------------------------+------------+\n"+
                        "| Flower Stand                |     178.00 |\n" +
                        "| Flower Bouquet              |     228.00 |\n" +
                        "| Graduation Bouquet (Mini)   |      58.00 |\n" +
                        "| Graduation Bouquet (Flower) |     128.00 |\n" +
                        "| Graduation Bouquet (Toy)    |     188.00 |\n" +
                        "| Wedding Bouquet Mini        |      68.00 |\n" +
                        "| Wedding Bouquet             |     168.00 |\n" +
                        "+-----------------------------+------------+\n\n" +
                        "+-----------------------------+-------------+\n" +
                        "|Custom Flower                | Package:    |\n" +
                        "+-----------------------------+-------------+\n" +
                        "|1–10 flowers                 | RM10.00 each|\n" +
                        "+-----------------------------+-------------+\n" +
                        "|11–50 flowers                | RM9.50 each |\n" +
                        "+-----------------------------+-------------+\n" +
                        "|Above 50                     | RM8.00 each |\n" +
                        "+-----------------------------+-------------+\n"
        );

        text.setEditable(false);
        text.setFont(new Font("Monospaced", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(text);
        scrollPane.setPreferredSize(new Dimension(650, 350));

        JOptionPane.showMessageDialog(null, scrollPane,
                "Flower Packages", JOptionPane.INFORMATION_MESSAGE);
    }

    // ================= OPTION 2 =================
    static void calculateOrder() {

        String[] packages = {
                "Flower Bouquet",
                "Flower Stand",
                "Wedding Bouquet",
                "Graduation Bouquet (Mini)",
                "Graduation Bouquet (Flower)",
                "Graduation Bouquet (Toy)",
                "Wedding Bouquet Mini",
                "Wedding Bouquet",
                "Custom Flower Package"
        };

        String selected = (String) JOptionPane.showInputDialog( //1) After user selected flower package
                null,                                           //2) it will store into string store in selected
                "Select a flower package:", //message
                "Package Selection",        //title of dialog
                JOptionPane.PLAIN_MESSAGE,  //type of message
                null,
                packages,                   //list of packages
                packages[0]                 //default selected item
        );

        if (selected == null) return;       //this is for cancel button function

        FlowerPackages flowerPackage;

        // ================= Custom Package =================
        if (selected.equals("Custom Flower Package")) { //3) this will allow system to choose which class
            int flowerCount;
            try {
                flowerCount = Integer.parseInt(
                        JOptionPane.showInputDialog("Enter number of flowers:"));
                if (flowerCount <= 0) throw new NumberFormatException();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Invalid number of flowers!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            flowerPackage = new CustomMadePackages(flowerCount);

            // ================= Ready-made Package =================
        } else {
            int quantity;
            try {
                quantity = Integer.parseInt(
                        JOptionPane.showInputDialog("Enter quantity of packages:"));
                if (quantity <= 0) throw new NumberFormatException();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Invalid quantity!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            flowerPackage = new ReadyMadePackage(selected); //4) this will send selected string to the class
            flowerPackage.setQuantity(quantity);//this stores input of quantity
        }

        double distance;
        try {
            distance = Double.parseDouble(
                    JOptionPane.showInputDialog("Enter delivery distance (km):"));
            if (distance < 0) throw new NumberFormatException();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Invalid distance!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Delivery delivery = new Delivery(distance);
        Order order = new Order(flowerPackage, delivery);
        double total = order.getTotal();//from order file

        totalOrders++;
        totalSales += total;

        // ================= SUMMARY =================
        JTextArea summary = new JTextArea();
        summary.append("Order Summary\n\n");//append is to generate a new line without replacing previous lines
        summary.append("Package: " + flowerPackage.getName() + "\n");

        if (flowerPackage instanceof CustomMadePackages custom) {
            summary.append("Number of Flowers: " + custom.getFlowerCount() + "\n");
        } else {
            summary.append("Quantity of Packages: " + flowerPackage.getQuantity() + "\n");
        }

        summary.append("Package Price: RM " + flowerPackage.getPrice() + "\n");
        summary.append("Delivery Fee: RM " + delivery.calculateFee() + "\n");
        summary.append("\nTOTAL: RM " + total);

        summary.setEditable(false);
        summary.setFont(new Font("Serif", Font.PLAIN, 16));

        JOptionPane.showMessageDialog(null, summary,
                "Order Summary", JOptionPane.INFORMATION_MESSAGE);
    }

    // ================= OPTION 3 =================
    static void generateReport() {
        JTextArea report = new JTextArea(
                "📊 Summary Report\n\n" +
                        "Total Orders: " + totalOrders +
                        "\nTotal Sales: RM " + totalSales
        );

        report.setEditable(false);
        report.setFont(new Font("Serif", Font.PLAIN, 16)); //serif is the name of font in the family

        JOptionPane.showMessageDialog(null, report,
                "Summary Report", JOptionPane.INFORMATION_MESSAGE);
    }
}
