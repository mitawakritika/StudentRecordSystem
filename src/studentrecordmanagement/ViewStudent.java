/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentrecordmanagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewStudent extends JFrame {

	private JPanel contentPane;
        private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewStudent frame = new ViewStudent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ViewStudent() {
                setTitle("View Student Records");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
                
                contentPane.setBackground(Color.GRAY);
		
//		JDesktopPane desktopPane = new JDesktopPane();
//		desktopPane.setBackground(Color.GRAY);
//		GroupLayout gl_contentPane = new GroupLayout(contentPane);
//		gl_contentPane.setHorizontalGroup(
//			gl_contentPane.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_contentPane.createSequentialGroup()
//					.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 753, GroupLayout.PREFERRED_SIZE)
//					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//		);
//		gl_contentPane.setVerticalGroup(
//			gl_contentPane.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_contentPane.createSequentialGroup()
//					.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
//					.addContainerGap(423, Short.MAX_VALUE))
//		);
		
		JLabel lblHeading = new JLabel("Student Records", SwingConstants.CENTER);
		lblHeading.setForeground(Color.BLACK);
		lblHeading.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblHeading.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
                contentPane.add(lblHeading, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("Go Back");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menu = new Menu();
				menu.show();
				dispose();
			}
		});
                
                table = new JTable();
                JScrollPane scrollPane = new JScrollPane(table);
                contentPane.add(scrollPane, BorderLayout.CENTER);

                displayStudentData();
                
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(200, 96, 113, 32);
		contentPane.add(btnNewButton);
		//contentPane.setLayout(gl_contentPane);
	}
        
        private void displayStudentData() {
        String[] columnNames = {"Name", "Entry", "Email", "Contact", "Home"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table.setModel(model);

        try {
            ResultSet rs = StudentDataHelper.getAllStudents();
            if(rs==null)
            {
		JOptionPane.showMessageDialog(null, "No Record Found :)");
		dispose();
		Menu menu = new Menu();
		menu.show();
	    }
            while (rs != null && rs.next()) {
                String name = rs.getString("name");
                String entry = rs.getString("entrynumber");
                String email = rs.getString("email");
                String contact = rs.getString("contactnumber");
                String home = rs.getString("homecity");
                model.addRow(new Object[]{name, entry, email, contact, home});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
