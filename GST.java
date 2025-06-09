import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class GST {
public static void main(String args[])
{
Gstfind g = new Gstfind();
}
}

class Gstfind extends JFrame {
private Container c;
private JLabel proname, probp, progst, labMsg;
private JTextField txtpn, txtpbp, txtpgst;
private JButton btnFind;

public Gstfind() {
c = getContentPane();
c.setLayout(new FlowLayout());

proname = new JLabel("Enter Product Name");
txtpn = new JTextField(10);

probp = new JLabel("Enter Product Base Price");
txtpbp = new JTextField(10);

progst = new JLabel("Enter GST%");
txtpgst = new JTextField(10);

btnFind = new JButton("Generate Bill");
labMsg = new JLabel("");

Font f = new Font("Arial", Font.BOLD, 30);
proname.setFont(f);
txtpn.setFont(f);

probp.setFont(f);
txtpbp.setFont(f);

progst.setFont(f);
txtpgst.setFont(f);

btnFind.setFont(f);
labMsg.setFont(f);

c.add(proname);
c.add(txtpn);

c.add(probp);
c.add(txtpbp);

c.add(progst);
c.add(txtpgst);

c.add(btnFind);
c.add(labMsg);

btnFind.addActionListener(ae -> {
try {
                // Check if the product name contains only letters (and spaces)
                String o = txtpn.getText();
                if (!o.matches("[a-zA-Z ]+")) {
                    JOptionPane.showMessageDialog(c, "Product Name should only contain letters and spaces.");
                    return; // Stop further execution if the name is invalid
                }

                // Validate Base Price
                double n;
                try {
                    n = Double.parseDouble(txtpbp.getText());
                    if (n <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(c, "Please enter a valid Base Price (positive number).");
                    return;                 }

                // Validate GST%
                double m;
                try {
                    m = Double.parseDouble(txtpgst.getText());
                    if (m <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(c, "Please enter a valid GST% (positive number).");
                    return;                 }
double g = (n*m)/100;

LocalDateTime now = LocalDateTime.now();
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
String formattedDateTime = now.format(formatter);

String billContent = "********* BILL *********\n"+ "Time: " + formattedDateTime + "\n" + "Product Name: " +o+ "\n" + "Base Price: " +m+ "\n" + "Gst: "+g+ "\n" + "****** THANK YOU *******" ;

File folder = new File("C:\\Users\\NIDHI\\Desktop\\JAVA DSA\\intership project\\Bills");

File billFile = new File(folder,o + "_Bill.txt");
try(BufferedWriter writer = new BufferedWriter(new FileWriter(billFile))) {
writer.write(billContent);
}
JOptionPane.showMessageDialog(c,"Thank you");
txtpn.setText("");
txtpbp.setText("");
txtpgst.setText("");
txtpn.requestFocus();

  } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(c, "Please enter valid numbers for Base Price and GST%.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(c, "Error saving the bill: " + e.getMessage());
            }

});


setSize(400,400);
setLocationRelativeTo(null);
setTitle("GST Calculator");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setResizable(false);
setVisible(true);
}
}