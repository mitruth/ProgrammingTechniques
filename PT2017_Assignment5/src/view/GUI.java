package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.DataManipulation;
import model.MonitoredData;

public class GUI {

	private JFrame frame;

	private JPanel panel;

	private JTextField result;

	private JButton btnDistinctDaysCount, btnActivityOccurance, btnActivityCount, btnActivityDuration,
			btnActivityFilter;

	private DataManipulation dM;
	private List<MonitoredData> data;

	public GUI() {

		frame = new JFrame("Activity Processing");
		frame.setBounds(100, 100, 900, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setLayout(null);

		dM = new DataManipulation();
		data = new ArrayList<MonitoredData>();

		result = new JTextField();
		result.setBounds(15, 400, 870, 350);
		result.setEditable(false);
		panel.add(result);

		btnDistinctDaysCount = new JButton("Count Distinct Days");
		btnDistinctDaysCount.setBounds(15, 15, 250, 50);
		btnDistinctDaysCount.setForeground(Color.BLACK);

		btnDistinctDaysCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					data = dM.readInput();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				int daysSize = dM.countDistinctDays(data);
				result.setText(String.valueOf(daysSize));
			}
		});

		panel.add(btnDistinctDaysCount);

		btnActivityOccurance = new JButton("Show Activity Occurances");
		btnActivityOccurance.setBounds(475, 15, 250, 50);
		btnActivityOccurance.setForeground(Color.CYAN);

		btnActivityOccurance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					data = dM.readInput();
					dM.ActivityOccurances(data);
					String file = new File("DailyActivities.txt").getAbsolutePath();
					BufferedReader buffRead = new BufferedReader(new FileReader(file));
					result.read(buffRead, null);
					buffRead.close();
					result.requestFocusInWindow();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		panel.add(btnActivityOccurance);

		btnActivityCount = new JButton("Activity Count of each day");
		btnActivityCount.setBounds(15, 150, 250, 50);
		btnActivityCount.setForeground(Color.DARK_GRAY);

		btnActivityCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		panel.add(btnActivityCount);

		btnActivityDuration = new JButton("Total activity duration");
		btnActivityDuration.setBounds(475, 150, 250, 50);
		btnActivityDuration.setForeground(Color.MAGENTA);

		btnActivityDuration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					data = dM.readInput();
					dM.activityTotalDuration(data);
					String file = new File("totalDuration.txt").getAbsolutePath();
					BufferedReader buffRead = new BufferedReader(new FileReader(file));
					result.read(buffRead, null);
					buffRead.close();
					result.requestFocusInWindow();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		panel.add(btnActivityDuration);

		btnActivityFilter = new JButton("Filter activities");
		btnActivityFilter.setBounds(290, 285, 250, 50);
		btnActivityFilter.setForeground(Color.BLUE);

		btnActivityFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					data = dM.readInput();
					dM.activityFilter(data);
					String file = new File("filter.txt").getAbsolutePath();
					BufferedReader buffRead = new BufferedReader(new FileReader(file));
					result.read(buffRead, null);
					buffRead.close();
					result.requestFocusInWindow();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		panel.add(btnActivityFilter);

		frame.add(panel);
		frame.setVisible(true);
	}
}
