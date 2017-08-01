package dcmsearch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.dcm4che3.io.DicomInputStream;

public class AppletLauncher extends JFrame{

	private static final long serialVersionUID = -1067714612341517487L;
	private static ResourceBundle messages;
	private Locale currentLocale;
	
	private JButton btnChooseDirectory;
	private JPanel panel;
	private JPanel panelStatus;
	private JPanel panelListObservation;
	private JLabel lblStatus;
	
	
	
	public static void main(String[] args) {
		AppletLauncher applet = new AppletLauncher();
		applet.setVisible(true);
	}
	
	public AppletLauncher()  {
		
		super();
		initLocale();
		initGUI();
		initEvents();
		
	}
	
	private void initLocale(){
		currentLocale = new Locale(new String("ua"),new String("UA"));  
        messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);	
	}
	
	private void initGUI() {
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		
		panelListObservation = new JPanel();
		panelListObservation.setLayout(new BorderLayout());
		
		panelStatus = new JPanel();
		panelStatus.setLayout(new BoxLayout(panelStatus, BoxLayout.Y_AXIS));
		panelStatus.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		
		btnChooseDirectory = new JButton(messages.getString("btnChooseDirectoryTitle"));
		btnChooseDirectory.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblStatus = new JLabel(messages.getString("statusWaitToChooseDirectory"));
		lblStatus.setBounds(10,10,100, 20);
		lblStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.setBounds(100, 100, 500, 500);
		this.add(panel);
		this.setVisible(true);
		
		
		panelStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelStatus.add(lblStatus);
		panelStatus.setPreferredSize(getPreferredSize());
	
		
		panel.add(Box.createVerticalStrut(20));
		panel.add(panelStatus);
		panel.add(Box.createVerticalStrut(20));

		panel.add(btnChooseDirectory);
	
		panel.add(panelListObservation);
		
		
		
	}

	private void initEvents() {
		
		btnChooseDirectory.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  btnChooseDirectory();
		  }
		});
			
	}
	
	private void btnChooseDirectory(){
		
		String directory = showDialog();
		System.out.println(directory);
	}
	private String showDialog(){
		
		JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new java.io.File("."));
	    chooser.setDialogTitle(messages.getString("dialogTitle"));
	    chooser.setLocale(new Locale("ru", "RU"));
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);

	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	     
	      System.out.println("Its ok, directory are selected ");
	      System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
	      
	    } else {
	      System.out.println("No Selection ");
	    }
	    return chooser.getSelectedFile().toString();
	}
	
	public void checkDCM(File file){

		try {
			DicomInputStream inputStream = new DicomInputStream(file);
			System.out.println(inputStream);
		} catch (IOException e) {
			
			System.out.println("Ôאיכ םו ÷ DICOM פאיכמל");
			e.printStackTrace();
		}
		
	}
	
	

}
