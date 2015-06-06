package gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import utilities.Url;

public class Gui {
	
	private static JTextField textField; // Your class name
	private static JTable table;
	String columnNames[] = { "Column 1", "Column 2" };

	/**
	 * @throws IOException 
	 * @wbp.parser.entryPoint
	 */
	public static void gui() throws IOException {

		String t = Url.review;

		JFrame f = new JFrame("A JFrame");
		f.setSize(800, 600);
		f.setLocation(300, 200);
		f.getContentPane().setLayout(null);

		JButton btnGetTextFrom = new JButton("Read URL");
		btnGetTextFrom.setBounds(651, 6, 132, 29);
		f.getContentPane().add(btnGetTextFrom);

		textField = new JTextField();
		textField.setBounds(16, 4, 596, 30);
		f.getContentPane().add(textField);
		textField.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(6, 33, 777, 12);
		f.getContentPane().add(separator);

		
		String stringGood = "";
		int countGood = 0;
		String[] good = new String[2007];
		BufferedReader brGood = new BufferedReader(new FileReader("/Users/joscha/workspaces/Lucene/Amazon/src/WordLists/positive-words.txt"));
		 while((stringGood = brGood.readLine()) != null) {
			 good[countGood] = stringGood;
			 countGood++;
		 }
		 
		 String stringBad = "";
			int countBad= 0;
			String[] bad = new String[4784];
			BufferedReader brBad = new BufferedReader(new FileReader("/Users/joscha/workspaces/Lucene/Amazon/src/WordLists/negative-words.txt"));
			 while((stringBad = brBad.readLine()) != null) {
				 bad[countBad] = stringBad;
				 countBad++;
			 }
			 
			 //TODO: rowDate Array automatisch befüllen lassen
		Object rowData[][] = { { good[1], bad[1] }, { good[2], bad[2] }, { good[3], bad[3] }, { good[4], bad[4] }, { good[5], bad[5] }, { good[6], bad[6] }, { good[7], bad[7] }, { good[8], bad[8] }, { good[9], bad[9] }, { good[0], bad[0] }, };
		Object columnNames[] = { "Positive", "Negative" };

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(519, 74, 259, 377);
		f.getContentPane().add(scrollPane);

		table = new JTable(rowData, columnNames);
		scrollPane.setViewportView(table);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);

		JLabel lblNewLabel = new JLabel("Reviews");
		lblNewLabel.setBackground(new Color(255, 255, 0));
		lblNewLabel.setBounds(16, 46, 490, 16);
		f.getContentPane().add(lblNewLabel);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(16, 74, 491, 377);
		f.getContentPane().add(scrollPane_1);

		final JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane_1.setViewportView(textArea);
		textArea.setText(t);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();
					System.out.println(table.getValueAt(row, column));
					String searchText = table.getValueAt(row, column).toString();

					javax.swing.text.Document fileContent = textArea.getDocument();
					textArea.getHighlighter().removeAllHighlights();

					try {

						for (int i = 0; i + searchText.length() < fileContent.getLength(); i++) {

							String match = fileContent.getText(i,searchText.length());

							if (searchText.equals(match)) {

								System.out.println("!");
								javax.swing.text.DefaultHighlighter.DefaultHighlightPainter highlightPainter = new javax.swing.text.DefaultHighlighter.DefaultHighlightPainter(Color.CYAN);
								textArea.getHighlighter().addHighlight(i,i + searchText.length(),highlightPainter);
							}
						}
					} catch (Exception ex) {

						ex.printStackTrace();
					}

				}
			}
		});

		JLabel label = new JLabel("Sentiment Table");
		label.setBackground(Color.YELLOW);
		label.setBounds(518, 46, 259, 16);
		f.getContentPane().add(label);
		f.setVisible(true);

	}
}
