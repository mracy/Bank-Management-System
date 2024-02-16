package bank.management.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SignupTwo extends JFrame implements ActionListener {

    JTextField pan, nationalid;
    JButton next;
    JRadioButton eano, eayes, scitznno, scitznyes;

    ButtonGroup seniorCitizenGroup, existingAccountGroup;
    JComboBox<String> religion, category, occupation, education, income;
    String formno;

    SignupTwo(String formno) {

        this.formno = formno;

        setLayout(null);
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");

        JLabel additionalDetails = new JLabel("Page 2: Additional Details");
        additionalDetails.setFont(new Font("Raleway", Font.BOLD, 20));
        additionalDetails.setBounds(290, 80, 400, 30);
        add(additionalDetails);

        JLabel religionLabel = new JLabel("Religion: ");
        religionLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        religionLabel.setBounds(100, 140, 100, 30);
        add(religionLabel);

        String[] valReligion = {"Hindu", "Muslim", "Christian", "Buddhist", "Others"};
        religion = new JComboBox<>(valReligion);
        religion.setBounds(300, 140, 400, 30);
        religion.setBackground(Color.WHITE);
        add(religion);

        JLabel categoryLabel = new JLabel("Category: ");
        categoryLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        categoryLabel.setBounds(100, 190, 200, 30);
        add(categoryLabel);

        String[] valCategory = {"Brahmin", "Chhetri", "Dalit", "Janajati", "Madhesi", "Newar", "Others"};
        category = new JComboBox<>(valCategory);
        category.setBounds(300, 190, 400, 30);
        category.setBackground(Color.WHITE);
        add(category);

        JLabel incomeLabel = new JLabel("Income: ");
        incomeLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        incomeLabel.setBounds(100, 240, 200, 30);
        add(incomeLabel);

        String[] incomeCategory = {"Null", "<150000", "<200000", "<500000", "<1000000", "<2000000", "Upto 20000000"};
        income = new JComboBox<>(incomeCategory);
        income.setBounds(300, 240, 400, 30);
        income.setBackground(Color.WHITE);
        add(income);

        JLabel educationLabel = new JLabel("Educational");
        educationLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        educationLabel.setBounds(100, 290, 200, 30);
        add(educationLabel);

        JLabel qualificationLabel = new JLabel("Qualification: ");
        qualificationLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        qualificationLabel.setBounds(100, 315, 200, 30);
        add(qualificationLabel);

        String educationValues[] = {"Non-Graduation", "Graduation", "Post-Graduation", "Doctorate", "Others"};
        education = new JComboBox<>(educationValues);
        education.setBounds(300, 315, 400, 30);
        education.setBackground(Color.WHITE);
        add(education);

        JLabel occupationLabel = new JLabel("Occupation: ");
        occupationLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        occupationLabel.setBounds(100, 390, 200, 30);
        add(occupationLabel);

        String[] occupationValues = {"Salaried", "Self-employed", "Businessman", "Students", "Retired", "Others"};
        occupation = new JComboBox<>(occupationValues);
        occupation.setBounds(300, 390, 400, 30);
        occupation.setBackground(Color.WHITE);
        add(occupation);

        JLabel panLabel = new JLabel("PAN Number: ");
        panLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        panLabel.setBounds(100, 440, 200, 30);
        add(panLabel);

        pan = new JTextField();
        pan.setFont(new Font("Raleway", Font.BOLD, 14));
        pan.setBounds(300, 440, 400, 30);
        add(pan);

        JLabel nidLabel = new JLabel("NID Number: ");
        nidLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        nidLabel.setBounds(100, 490, 200, 30);
        add(nidLabel);

        nationalid = new JTextField();
        nationalid.setFont(new Font("Raleway", Font.BOLD, 14));
        nationalid.setBounds(300, 490, 400, 30);
        add(nationalid);

        JLabel seniorCitizenLabel = new JLabel("Senior Citizen: ");
        seniorCitizenLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        seniorCitizenLabel.setBounds(100, 540, 200, 30);
        add(seniorCitizenLabel);

        scitznyes = new JRadioButton("Yes");
        scitznyes.setBounds(300, 540, 100, 30);
        scitznyes.setBackground(Color.WHITE);
        add(scitznyes);

        scitznno = new JRadioButton("No");
        scitznno.setBounds(450, 540, 100, 30);
        scitznno.setBackground(Color.WHITE);
        add(scitznno);

        seniorCitizenGroup = new ButtonGroup();
        seniorCitizenGroup.add(scitznyes);
        seniorCitizenGroup.add(scitznno);

        JLabel existingAccountLabel = new JLabel("Existing Account: ");
        existingAccountLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        existingAccountLabel.setBounds(100, 590, 200, 30);
        add(existingAccountLabel);

        eayes = new JRadioButton("Yes");
        eayes.setBounds(300, 590, 100, 30);
        eayes.setBackground(Color.WHITE);
        add(eayes);

        eano = new JRadioButton("No");
        eano.setBounds(450, 590, 100, 30);
        eano.setBackground(Color.WHITE);
        add(eano);

        existingAccountGroup = new ButtonGroup();
        existingAccountGroup.add(eayes);
        existingAccountGroup.add(eano);

        next = new JButton("Next");
        next.setBackground(Color.WHITE);
        next.setForeground(Color.BLACK);
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBounds(620, 660, 80, 30);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.WHITE);
        setSize(850, 800);
        setLocation(350, 10);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String sreligion = (String) religion.getSelectedItem();
        String scategory = (String) category.getSelectedItem();
        String sincome = (String) income.getSelectedItem();
        String seducation = (String) education.getSelectedItem();
        String soccupation = (String) occupation.getSelectedItem();

        String seniorcitizen = null;
        if (scitznyes.isSelected()) {
            seniorcitizen = "Yes";
        } else if (scitznno.isSelected()) {
            seniorcitizen = "No";
        }

        String existingAccountLabel = null;
        if (eayes.isSelected()) {
            existingAccountLabel = "Yes";
        } else if (eano.isSelected()) {
            existingAccountLabel = "No";
        }

        String panText = pan.getText();
        String nidText = nationalid.getText();

        // Check if any of the fields are empty
        if (sreligion.isEmpty() || scategory.isEmpty() || sincome.isEmpty() || seducation.isEmpty() || soccupation.isEmpty()
                || seniorcitizen == null || existingAccountLabel == null || panText.isEmpty() || nidText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all the fields", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Stop further execution
        }

        // Rest of your code...
        // Note: You need to define the missing variables like name, fname, dob, gender, email, marital, address, city, pincode, state, etc.
        try {
            Conn c = new Conn();
            String query = "INSERT INTO signuptwo VALUES('" + formno + "', '" + sreligion + "', '" + scategory + "', '" + sincome
                    + "', '" + seducation + "', '" + soccupation + "', '" + panText + "', '" + nidText + "', '" + seniorcitizen
                    + "', '" + existingAccountLabel + "')";
            c.s.executeUpdate(query);

            // SignupThree Object
            setVisible(false);
            new SignupThree(formno).setVisible(true);

            //SignupThree 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String arg[]) {
        new SignupTwo("");
    }
}
