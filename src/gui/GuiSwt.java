package gui;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import nicht_mehr_in_gebrauch.KonsoleCom;
import nicht_mehr_in_gebrauch.KonsoleDe;
import utilities.ComputeCom;
import utilities.ComputeDe;

public class GuiSwt {
	private static Text text;
//	private static JTextField textField_urlAdress;
	public static String content = "";
//	private static Table table;
	public static int seiten;
//	public static boolean setText = false;
	String columnNames[] = { "Column 1", "Column 2" };
	private static Table wordListTable;
	public static String link;
	public static Thread ko;
	public static String[] good;
	public static boolean inSpeicher = false;
	private static Text text_1;
	private static Text reviewAreaDe;
	public String linkDe = ""; 
	
/**
 * @wbp.parser.entryPoint
 */
public static void gui() {
	
	
	final Display display = new Display ();
	Shell shell = new Shell (display);
	shell.setSize(924, 618);
	shell.setText("Review Analyzer");
	
	
	Button readURLButton = new Button(shell, SWT.PUSH);
	readURLButton.setBounds(819, 11, 95, 19);
	readURLButton.setText("read URL");
	
	text = new Text(shell, SWT.BORDER);
	text.setBounds(10, 10, 734, 20);
	text.setText("190949092X");
	
	Combo combo = new Combo(shell, SWT.NONE);
	combo.setBounds(750, 9, 70, 22);
	combo.setText("Seiten");
	combo.add("1");combo.add("2");combo.add("3");combo.add("4");combo.add("5");combo.add("6");combo.add("7");combo.add("8");combo.add("9");combo.add("10");
	
	combo.addSelectionListener(new SelectionListener() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			seiten = Integer.parseInt((String)combo.getText());
			System.out.println("gewählte Anzahl von Seiten: " + seiten);
			
		}
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
		}
	});
	
	Label label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
	label.setBounds(9, 44, 904, 2);
	
	Label lblReviews = new Label(shell, SWT.NONE);
	lblReviews.setBounds(10, 52, 86, 14);
	lblReviews.setText("ReviewsCom");
	
	
	//TODO: noch die falsche Liste
	String stringGood = "";
	int countGood = 0;
	good = new String[2007];
	BufferedReader brGood;
	try {
		brGood = new BufferedReader(new FileReader("src/WordLists/positive-words.txt"));
		while((stringGood = brGood.readLine()) != null) {
			good[countGood] = stringGood;
			countGood++;
			
		}
	} catch (FileNotFoundException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	
	
	wordListTable = new Table(shell, SWT.BORDER | SWT.VIRTUAL);
	wordListTable.setHeaderVisible(true);
	wordListTable.setBounds(684, 72, 230, 451);
	
	wordListTable.setItemCount(good.length);
//      tableReview.clearAll();
	wordListTable.setLinesVisible(true);
	
	
	TableColumn column = new TableColumn(wordListTable, SWT.LEFT);
    column.setText("Words");
    column.setWidth(250);
    wordListTable.addListener(SWT.SetData, new Listener() {
        public void handleEvent(Event e) {
          TableItem item = (TableItem) e.item;
          int index = wordListTable.indexOf(item);
          item.setText("" + good[index]);
          
        }
      });
    int count = 0;
    while (count++ < 2) {
      int grow = 10;
      String[] newData = new String[good.length + grow];
      System.arraycopy(good, 0, newData, 0, good.length);
      int index = good.length;
      good = newData;
      for (int j = 0; j < grow; j++) {
    	  newData[index++] = good[grow];
      }
    }
 	
	wordListTable.addListener(SWT.Selection, new Listener() {
	      public void handleEvent(Event e) {
	        String string = "";
	        TableItem[] selection = wordListTable.getSelection();
	        for (int i = 0; i < selection.length; i++)
	        string += selection[i] + " ";
	        System.out.println(selection[0]);
	      }
	    });
	
	Label lblNewLabel = new Label(shell, SWT.NONE);
	lblNewLabel.setBounds(684, 52, 117, 14);
	lblNewLabel.setText("Sentiments Table");
	
	Label label_1 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
	label_1.setBounds(10, 532, 904, 2);
	
	
	Button btnNewButton = new Button(shell, SWT.NONE);
	btnNewButton.addSelectionListener(new SelectionListener() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			
			ArrayList<String> words = new ArrayList<String>();
			BreakIterator iterator = BreakIterator.getWordInstance();
			String text = content;
			iterator.setText(text);
			
			int i = 0;
			
			int last = 0;
			while (true) {
				int next = iterator.next();
				if (next == -1)
					break;
				String sub = text.substring(last, next).trim();
				if (sub.length() == 0)
					continue;
//				words.add(sub);
				if(sub.contains("development")){
					System.out.println(text.indexOf("development"));
					i++;
				}
				last = next;
			}
			
			// Liste schreiben
			//
//			for (String word : words) {
//				System.out.println(word);
//				if(word.contains("development")){
//					word.
//					i++;
//				}
//			}
			System.out.println("development "+  i + " mal gefunden");
			
		}

		
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
			
		}
	});
	btnNewButton.setBounds(588, 565, 95, 28);
	btnNewButton.setText("New Button");

	readURLButton.addSelectionListener(new SelectionListener() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			link = text.getText();
			 
			try {
				ComputeDe.compute(link);
			} catch (IOException | InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
//			KonsoleDe konsDe = new KonsoleDe();
//			KonsoleCom konsCom = new KonsoleCom();
//			
//			konsCom.run();
//			konsDe.run();
		}
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
		}
	});

	/*
	 * Nicht dringend
	 */
	
	Button saveButton = new Button(shell, 0);
	saveButton.setText("Save");
	saveButton.setBounds(820, 540, 94, 26);
	saveButton.addSelectionListener(new SelectionListener() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			saveTo(shell);
		}
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
		}
	});
	
	//TODO: funktionsfähig machen
		Button checkBoxSpeicher = new Button(shell, SWT.CHECK);
		checkBoxSpeicher.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		checkBoxSpeicher.setBounds(720, 540, 94, 26);
		checkBoxSpeicher.setText("im Speicher " + "\n" + "behalten");
	
		
		Text reviewAreaCom = new Text(shell, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.MULTI | SWT.WRAP);
		reviewAreaCom.setDoubleClickEnabled(false);
		reviewAreaCom.setBounds(10, 72, 332, 451);
		reviewAreaCom.setEnabled(true);
		
		reviewAreaDe = new Text(shell, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		reviewAreaDe.setEnabled(true);
		reviewAreaDe.setDoubleClickEnabled(false);
		reviewAreaDe.setBounds(346, 72, 332, 451);
		
		Label lblReviewsde = new Label(shell, SWT.NONE);
		lblReviewsde.setText("ReviewsDe");
		lblReviewsde.setBounds(346, 52, 86, 14);
		
	shell.open ();
	
	
	while (!shell.isDisposed ()) {
		
		if(ComputeCom.setText == true){
			reviewAreaCom.setText(content);
		}
		
		if (!display.readAndDispatch ()) 
			display.sleep ();
		
		
	}
	display.dispose ();
	
	try {
		FileOutputStream writer = new FileOutputStream("src/texts/Quelltext.txt");
		writer.close();
		FileOutputStream writer1 = new FileOutputStream("src/texts/reviews.txt");
		writer1.close();
		FileOutputStream writer2 = new FileOutputStream("src/texts/reviewsList.txt");
		writer2.close();
		System.out.println("Textfiles aufgeräumt");
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}	

	
	public static void saveTo(Shell shell) {

		FileDialog dialog = new FileDialog(shell, SWT.SAVE);
		String[] filterNames = new String[] { "Text Files", "All Files (*.*)" };
		String[] filterExtensions = new String[] {
				"*.gif;*.png;*.xpm;*.jpg;*.jpeg;*.tiff", "*" };
		String filterPath = "/";
		String platform = SWT.getPlatform();
		if (platform.equals("win32")) {
			filterNames = new String[] { "Text Files", "All Files (*.*)" };
			filterExtensions = new String[] {
					"*.txt;*.json;", "*.*" };
			filterPath = "c:\\";
		}
		dialog.setFilterNames(filterNames);
		dialog.setFilterExtensions(filterExtensions);
		dialog.setFilterPath(filterPath);
		dialog.setFileName("myfile");
		System.out.println("Save to: " + dialog.open());
	}
}