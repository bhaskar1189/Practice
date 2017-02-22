package com.biosync;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
=======
>>>>>>> 45dbebe93c1f0f9f2c0603433a71c8be42659c02
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;

import com.biosync.config.Config;
import com.biosync.model.AttModel;
import com.biosync.model.RemoteRowModel;
import java.sql.PreparedStatement;

/**
 * Main class for BioSync Utility
 * 
 *
 */

public class Main implements ActionListener {
	static TrayIcon trayIcon;
	static SystemTray tray;
	public static void createAndShowUi(){
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame = new JFrame("BioSync Desktop");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addComponentsToPane(frame.getContentPane());
		frame.pack();
		frame.setVisible(true);
		frame.setSize(810, 575);
		frame.setResizable(false);
		if(SystemTray.isSupported()){ 
			String PathToImage="C:\\Program Files\\Darwinbox\\DarwinboxLogo.jpg";
			tray=SystemTray.getSystemTray();
			Image image=Toolkit.getDefaultToolkit().getImage(PathToImage);
			ActionListener exitListener=new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			};
			PopupMenu popup=new PopupMenu();
<<<<<<< HEAD
			MenuItem defaultItem1=new MenuItem("Exit");
			defaultItem1.addActionListener(exitListener);
			MenuItem defaultItem=new MenuItem("Open");
=======
			MenuItem defaultItem=new MenuItem("Exit");
			defaultItem.addActionListener(exitListener);
			popup.add(defaultItem);
			defaultItem=new MenuItem("Open");
>>>>>>> 45dbebe93c1f0f9f2c0603433a71c8be42659c02
			defaultItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(true);
					frame.setExtendedState(JFrame.NORMAL);
				}
			});
			popup.add(defaultItem);
<<<<<<< HEAD
			popup.add(defaultItem1);
=======
>>>>>>> 45dbebe93c1f0f9f2c0603433a71c8be42659c02
			trayIcon=new TrayIcon(image, "BioSyncApp", popup);
			trayIcon.setImageAutoSize(true);
		}else{
		//System.out.println("system tray not supported");
		}
		frame.addWindowStateListener(new WindowStateListener() {
 			public void windowStateChanged(WindowEvent e) {
				if(e.getNewState()==frame.ICONIFIED){
					try {
						tray.add(trayIcon);
						frame. setVisible(false);
						//System.out.println("added to SystemTray");
					} catch (AWTException ex) {
						//System.out.println("unable to add to tray");
					}
				}
				if(e.getNewState()==7){
					try{
						tray.add(trayIcon);
						frame.setVisible(false);
						//System.out.println("added to SystemTray");
					}catch(AWTException ex){
					//System.out.println("unable to add to system tray");
					}
				}
				if(e.getNewState()==frame.MAXIMIZED_BOTH){
					tray.remove(trayIcon);
					frame.setVisible(true);
					//System.out.println("Tray icon removed");
				}
				if(e.getNewState()==frame.NORMAL){
					tray.remove(trayIcon);
					frame.setVisible(true);
					//System.out.println("Tray icon removed");
				}
			}
		});
		int fileStatus=0;
		DataInputStream in = null;
		try {
			String path = System.getProperty("java.io.tmpdir") + "config";
			File f = new File(path);
			if (f.exists()){
				in = new DataInputStream(new FileInputStream(f));
				String interval = in.readUTF();
				if(!interval.equals("Select Interval")){
					runAutomatically();
					fileStatus=1;
				}
			}

		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				saveConfiguration();
				super.windowClosing(e);
			}
		});
	}
	public static final int MAX_SCHEDULE_COUNT = 8;
	public static final String HELP_TEXT = "Please check if atleast one scheduler or interval is provided.\n In case local database is MS Access local connection string formate should be dbpath=<msacess database path>.\n"
			+ "If local database is MS SQL server then local connection String must be <Host;databaseName=dbname;user=abc;password=pass>.\nIf local database is MySql then location stirng must be <Host/dbname?user=user&password=pass>.\n";
			

	public static final String MYSQL_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	public static final String MSSQL_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String SELECT_INTERVAL = "Select Interval";
	public static final String SELECT_SCHEDULE = "Select Schedule";
	public static final String[] timeIntervals = new String[] {
			SELECT_INTERVAL, "5", "10", "15", "20", "25", "30", "35", "40",
			"45", "50", "55", "60" };
	public static final String[] selectSchedule = new String[] {
			SELECT_SCHEDULE, "00:00", "1:00", "2:00", "3:00", "4:00", "5:00",
			"6:00", "7:00", "8:00", "9:00", "10:00", "11:00", "12:00", "13:00",
			"14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00",
			"21:00", "22:00", "23:00" };

	static JFrame frame;
	static JComboBox<String> intervals = null;
	static JComboBox<String> schedule1, schedule2, schedule3, schedule4,
			schedule5, schedule6, schedule7, schedule8;
	public static Timer timer[] = new Timer[1];

	public static Main _instance;

	public static Main getInstance() {
		if (_instance == null)
			_instance = new Main();

		return _instance;
	}

	private static String getMachineId() {
		try {
			String firstInterface = null;
			Map<String, String> addressByNetwork = new HashMap<>();
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface
					.getNetworkInterfaces();

			while (networkInterfaces.hasMoreElements()) {
				NetworkInterface network = networkInterfaces.nextElement();

				byte[] bmac = network.getHardwareAddress();
				if (bmac != null) {
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < bmac.length; i++) {
						sb.append(String.format("%02X%s", bmac[i],
								(i < bmac.length - 1) ? "-" : ""));
					}

					if (sb.toString().isEmpty() == false) {
						addressByNetwork.put(network.getName(), sb.toString());
						
					}

					if (sb.toString().isEmpty() == false
							&& firstInterface == null) {
						firstInterface = network.getName();
					}
				}
			}

			if (firstInterface != null) {
				return addressByNetwork.get(firstInterface);
			}

		} catch (Exception e) {
			e.printStackTrace(System.err);
		}

		return "";
	}

	public static void runAutomatically(){
		if (isSyncOn == false) {
			updateConfigData();
			// start Sync
			if (Config.getInstance().isConfigOK()) {
				try {
					Image img = ImageIO.read(new Object().getClass()
							.getResourceAsStream("/stop_btn.png"));
					start.setIcon(new ImageIcon(img));
				} catch (IOException ee) {
					ee.printStackTrace();
				}
				start.setBorder(null);
				isSyncOn = true;
				saveConfiguration();
				scheduler.startScheduler();
				disableAll();
			} else {
				JOptionPane.showMessageDialog(frame,"Invalid Config. Please check help for right configuration.");
			}
		} else {
			scheduler.stopScheduler();
		}
		frame.setState(frame.ICONIFIED);
	}

	public static void main(String[] kmj) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowUi();
			}
		});
	}

	/**
	 * Load existing configuration if any
	 */
	private static void loadConfiguration() {
		DataInputStream in = null;
		try {

			String path = System.getProperty("java.io.tmpdir") + "config";

			File f = new File(path);
			if (!f.exists())
				return;
			in = new DataInputStream(new FileInputStream(f));
			String interval = in.readUTF();
			String schedules = in.readUTF();
			int dbType = in.readInt();
			String lDbString = in.readUTF();
			String rDbString = in.readUTF();
			localDBLocationCString.setText(lDbString);
			remoteDBLocationCString.setText(rDbString);
			updateDbType(dbType);
			updateSchedule(schedules);
			updateInterval(interval);

		} catch (Exception e) {
			e.printStackTrace(System.err);

		} finally {
			try {

				if (in != null)
					in.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	private static void updateInterval(String interval) {

		if (!interval.equals(SELECT_INTERVAL))
			intervals.setSelectedItem("" + interval);

	}

	private static void updateSchedule(String schedule) {
		Object boxes[] = new Object[8];
		boxes[0] = schedule1;
		boxes[1] = schedule2;
		boxes[2] = schedule3;
		boxes[3] = schedule4;
		boxes[4] = schedule5;
		boxes[5] = schedule6;
		boxes[6] = schedule7;
		boxes[7] = schedule8;
		String[] sdls = schedule.split(";");
		for (int i = 0; i < sdls.length; i++) {
			if (!sdls.equals(SELECT_SCHEDULE))
				((JComboBox<String>) boxes[i]).setSelectedItem(sdls[i]);
		}

	}

	private static void updateDbType(int type) {
		if (type == 0)
			accessDbRadio.setSelected(true);
		else if (type == 1)
			selectDbRadio.setSelected(true);
		else
			mySqlDbRadio.setSelected(true);

	}

	/**
	 * This saves existing configuration
	 */
	private static void saveConfiguration() {

		DataOutputStream out = null;
		try {

			String path = System.getProperty("java.io.tmpdir") + "config";

			File f = new File(path);
			if (f.exists()) {
				f.delete();

			}
			f.createNewFile();
			out = new DataOutputStream(new FileOutputStream(f));

			out.writeUTF(intervals.getSelectedItem().toString());
			out.writeUTF(getSchedularsData());
			out.writeInt(getLocalDbType());
			out.writeUTF(localDBLocationCString.getText());
			out.writeUTF(remoteDBLocationCString.getText());
			out.flush();

		} catch (Exception e) {
			e.printStackTrace(System.err);

		} finally {
			try {

				if (out != null)
					out.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	private static int getLocalDbType() {
		int rtn = 0;
		if (accessDbRadio.isSelected())
			return 0;
		else if (selectDbRadio.isSelected())
			return 1;
		else
			return 2;

		// return rtn;
	}

	/**
	 * Schedular data is stored as comma separated string in configuration file
	 * 
	 * @return Comma separated schedules value
	 */
	private static String getSchedularsData() {
		String rtn = "";
		rtn += schedule1.getSelectedItem().toString() + ";"
				+ schedule2.getSelectedItem().toString() + ";"
				+ schedule3.getSelectedItem().toString() + ";"
				+ schedule4.getSelectedItem().toString() + ";"
				+ schedule5.getSelectedItem().toString() + ";"
				+ schedule6.getSelectedItem().toString() + ";"
				+ schedule7.getSelectedItem().toString() + ";"
				+ schedule8.getSelectedItem().toString() + ";";

		return rtn;

	}

	static JButton startStopButton;
	static Scheduler scheduler = new Scheduler();
	static JTextField fileTextField;

	/**
	 * Utility UI creation
	 */

	static String queryText;
	static int tenantIdNum;
	static String errorMsg;

	/**
	 * Present UI to get query and tenant id from user
	 * 
	 * @return true if query and tenant id is filed else false
	 */
	public static boolean getQuery() {
		String[] msg = new String[] { "select id,employee_id,machine_id,status,timestamp from bio_data where id > ?" };
		JTextArea query = new JTextArea(4, 30);
		query.setText(msg[0]);
		JTextField tanentId = new JTextField();

		final JComponent[] inputs = new JComponent[] {
				new JLabel("Enter Query"), query, new JLabel("Tenant Id"),
				tanentId };
		int result = JOptionPane.showConfirmDialog(frame, inputs,
				"My custom dialog", JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {

			String qt = query.getText().trim();
			String tid = tanentId.getText().trim();
			if (qt.equals("") || tid.equals("")) {
				errorMsg = "Query and tenant id must not be blank";
				return false;

			} else {
				try {
					tenantIdNum = Integer.parseInt(tid);
				} catch (Exception e) {
					errorMsg = "Tenant id must be a number";
					return false;
				}
				queryText = qt;
				return true;
			}
		}
		errorMsg = "You need to enter query and tenant id to move further";
		return false;
	}

	/**
	 * Addes all the UI components to base layout i.e content pane
	 * 
	 * @param contentPane
	 */
	public static void addComponentsToPane(Container contentPane) {

		contentPane.setLayout(new BorderLayout(5, 5));
		JPanel mainPanel = new JPanel(new BorderLayout(5, 5));

		addToolbar(contentPane);
		addSchedulerPanel(mainPanel);
		addDBConfig(mainPanel);
		addConsole(mainPanel);
		contentPane.add(mainPanel, BorderLayout.CENTER);
		loadConfiguration();

	}

	static boolean isSyncOn = false;

	/**
	 * Before stating sync , Config is update with latest values.
	 */
	private static void updateConfigData() {
		if (accessDbRadio.isSelected())
			Config.getInstance().addPropertey(Config.KEY_DB_TYPE, "0");
		else if (selectDbRadio.isSelected())
			Config.getInstance().addPropertey(Config.KEY_DB_TYPE, "1");
		else
			Config.getInstance().addPropertey(Config.KEY_DB_TYPE, "2");

		Config.getInstance().addPropertey(Config.KEY_LOCAL_CSTRING,
				localDBLocationCString.getText());
		Config.getInstance().addPropertey(Config.KEY_REMOTE_CSTRING,
				remoteDBLocationCString.getText());
		Config.getInstance().addPropertey(Config.KEY_INTERVAL,
				intervals.getSelectedItem().toString());
		Config.getInstance().addPropertey(Config.KEY_SCHEDULE1,
				schedule1.getSelectedItem().toString());
		Config.getInstance().addPropertey(Config.KEY_SCHEDULE2,
				schedule2.getSelectedItem().toString());
		Config.getInstance().addPropertey(Config.KEY_SCHEDULE3,
				schedule3.getSelectedItem().toString());
		Config.getInstance().addPropertey(Config.KEY_SCHEDULE4,
				schedule4.getSelectedItem().toString());
		Config.getInstance().addPropertey(Config.KEY_SCHEDULE5,
				schedule5.getSelectedItem().toString());
		Config.getInstance().addPropertey(Config.KEY_SCHEDULE6,
				schedule6.getSelectedItem().toString());
		Config.getInstance().addPropertey(Config.KEY_SCHEDULE7,
				schedule7.getSelectedItem().toString());
		Config.getInstance().addPropertey(Config.KEY_SCHEDULE8,
				schedule8.getSelectedItem().toString());

	}

	static final JButton start = new JButton("");

	private static void addToolbar(Container contentPane) {
		JToolBar bar = new JToolBar(JToolBar.HORIZONTAL);

		start.setPreferredSize(new Dimension(30, 35));
		try {
			Image img = ImageIO.read(new Object().getClass()
					.getResourceAsStream("/start_btn.png"));
			start.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
		start.setToolTipText("Start Sync");
		start.setBorder(null);
		// start/stop button action
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isSyncOn == false) {
					updateConfigData();
					// start Sync
					if (Config.getInstance().isConfigOK()) {
						try {
							Image img = ImageIO.read(new Object().getClass()
									.getResourceAsStream("/stop_btn.png"));
							start.setIcon(new ImageIcon(img));
						} catch (IOException ee) {
							ee.printStackTrace();
						}
						start.setBorder(null);
						isSyncOn = true;
						saveConfiguration();
						scheduler.startScheduler();
						disableAll();
					} else {
						JOptionPane.showMessageDialog(frame,"Invalid Config. Please check help for right configuration.");
					}
				} else {
					scheduler.stopScheduler();
				}

			}
		});
		bar.setFloatable(false);
		bar.add(start);
		bar.addSeparator();
		// save Button
		final JButton save = new JButton("");
		save.setPreferredSize(new Dimension(30, 35));
		try {
			Image img = ImageIO.read(new Object().getClass()
					.getResourceAsStream("/save_btn.png"));
			save.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		save.setToolTipText("Save Configuration");
		save.setBorder(null);
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveConfiguration();

			}
		});
		bar.add(save);
		bar.addSeparator();
		// Help button
		final JButton help = new JButton("");
		help.setPreferredSize(new Dimension(30, 35));
		try {
			Image img = ImageIO.read(new Object().getClass()
					.getResourceAsStream("/help_btn.png"));
			help.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		help.setToolTipText("Help- How To Use");
		help.setBorder(null);
		help.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Image img = null;
				try {
					img = ImageIO.read(new Object().getClass()
							.getResourceAsStream("/help_btn.png"));

				} catch (Exception ee) {
					ee.printStackTrace(System.err);
				}
				JOptionPane.showMessageDialog(frame, HELP_TEXT, "HELP",
						JOptionPane.QUESTION_MESSAGE, new ImageIcon(img));

			}
		});
		bar.add(help);
		contentPane.add(bar, BorderLayout.PAGE_START);
	}

	/**
	 * Enable all ui components , used when schedulers are stopped
	 */
	private static void enableAll() {
		intervals.setEnabled(true);
		schedule1.setEnabled(true);
		schedule2.setEnabled(true);
		schedule3.setEnabled(true);
		schedule4.setEnabled(true);
		schedule5.setEnabled(true);
		schedule6.setEnabled(true);
		schedule7.setEnabled(true);
		schedule8.setEnabled(true);
		localDBLocationCString.setEnabled(true);
		remoteDBLocationCString.setEnabled(true);
		accessDbRadio.setEnabled(true);
		selectDbRadio.setEnabled(true);
		mySqlDbRadio.setEnabled(true);
	}

	/**
	 * Disable all UI components , used when schedulers are started
	 */
	private static void disableAll() {
		intervals.setEnabled(false);
		schedule1.setEnabled(false);
		schedule2.setEnabled(false);
		schedule3.setEnabled(false);
		schedule4.setEnabled(false);
		schedule5.setEnabled(false);
		schedule6.setEnabled(false);
		schedule7.setEnabled(false);
		schedule8.setEnabled(false);
		localDBLocationCString.setEnabled(false);
		remoteDBLocationCString.setEnabled(false);
		selectDbRadio.setEnabled(false);
		accessDbRadio.setEnabled(false);
		mySqlDbRadio.setEnabled(false);

	}

	private static void addSchedulerPanel(Container contentPane) {
		JPanel p = new JPanel();
		TitledBorder tittle = new TitledBorder("Scheduler Configuration");
		p.setBorder(tittle);
		p.setAlignmentY(Component.TOP_ALIGNMENT);
		contentPane.add(p, BorderLayout.NORTH);

		JPanel locationTextPanel = new JPanel();

		intervals = new JComboBox<String>(timeIntervals);
		intervals.addActionListener(listener);
		// intervals.
		locationTextPanel.add(intervals);
		JLabel oRText = new JLabel("OR");
		locationTextPanel.add(oRText);
		JPanel pp = new JPanel(new BorderLayout(2, 2));
		JPanel pp1 = new JPanel();
		JPanel pp2 = new JPanel();
		schedule1 = new JComboBox<String>(selectSchedule);
		schedule1.addActionListener(Main.getInstance());
		pp1.add(schedule1);
		schedule2 = new JComboBox<String>(selectSchedule);
		schedule2.addActionListener(Main.getInstance());
		pp1.add(schedule2);
		schedule3 = new JComboBox<String>(selectSchedule);
		schedule3.addActionListener(Main.getInstance());
		pp1.add(schedule3);
		schedule4 = new JComboBox<String>(selectSchedule);
		schedule4.addActionListener(Main.getInstance());
		pp1.add(schedule4);
		pp.add(pp1, BorderLayout.NORTH);

		schedule5 = new JComboBox<String>(selectSchedule);
		schedule5.addActionListener(Main.getInstance());
		pp2.add(schedule5);
		schedule6 = new JComboBox<String>(selectSchedule);
		schedule6.addActionListener(Main.getInstance());
		pp2.add(schedule6);
		schedule7 = new JComboBox<String>(selectSchedule);
		schedule7.addActionListener(Main.getInstance());
		pp2.add(schedule7);
		schedule8 = new JComboBox<String>(selectSchedule);
		schedule8.addActionListener(Main.getInstance());
		pp2.add(schedule8);
		pp.add(pp2, BorderLayout.SOUTH);

		locationTextPanel.add(pp);

		p.add(locationTextPanel);

	}

	/**
	 * Interval listener
	 */
	static ActionListener listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			int selectIndex = intervals.getSelectedIndex();

			if (selectIndex != 0) {

				schedule1.setSelectedIndex(0);
				schedule2.setSelectedIndex(0);
				schedule3.setSelectedIndex(0);
				schedule4.setSelectedIndex(0);
				schedule5.setSelectedIndex(0);
				schedule6.setSelectedIndex(0);
				schedule7.setSelectedIndex(0);
				schedule8.setSelectedIndex(0);

			}
			// intervals.addActionListener(this);
		}
	};
	static JRadioButton accessDbRadio = null;
	static JRadioButton selectDbRadio = null;
	static JRadioButton mySqlDbRadio = null;
	static JTextField localDBLocationCString = null;
	static JTextField remoteDBLocationCString = null;

	/**
	 * DB Config UI panel
	 * 
	 * @param contentPane
	 *            Base container
	 */
	private static void addDBConfig(Container contentPane) {

		JPanel mainDb = new JPanel(new BorderLayout(5, 5));

		TitledBorder tittle = new TitledBorder("DB Config");
		mainDb.setBorder(tittle);
		mainDb.setAlignmentY(Component.TOP_ALIGNMENT);

		JPanel selectDb = new JPanel();
		selectDb.setAlignmentY(Component.LEFT_ALIGNMENT);
		selectDb.setAlignmentX(Component.LEFT_ALIGNMENT);
		JLabel dbTypeLabel = new JLabel("Local DB Type: ");
		dbTypeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		selectDb.add(dbTypeLabel);
		accessDbRadio = new JRadioButton("MS Access");
		selectDbRadio = new JRadioButton("MS SQL");
		mySqlDbRadio = new JRadioButton("MYSQL");
		accessDbRadio.setSelected(true);
		ButtonGroup bG = new ButtonGroup();
		bG.add(accessDbRadio);
		bG.add(selectDbRadio);
		bG.add(mySqlDbRadio);
		selectDb.add(accessDbRadio);
		selectDb.add(selectDbRadio);
		selectDb.add(mySqlDbRadio);
		mainDb.add(selectDb, BorderLayout.NORTH);

		JPanel p = new JPanel(new BorderLayout(5, 5));

		JPanel p2 = new JPanel();
		p2.setAlignmentX(Component.LEFT_ALIGNMENT);
		JLabel localDbLabel = new JLabel("Local DB CString:         ");
		localDbLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		p2.add(localDbLabel);
		localDBLocationCString = new JTextField("");
		localDBLocationCString.setBorder(javax.swing.BorderFactory
				.createLineBorder(Color.BLACK, 1));
		localDBLocationCString.setPreferredSize(new Dimension(150, 30));

		localDBLocationCString.setMargin(new Insets(5, 22, 5, 22));
		localDBLocationCString.setColumns(38);
		localDBLocationCString.setAlignmentX(Component.LEFT_ALIGNMENT);
		p2.add(localDBLocationCString);

		p.add(p2, BorderLayout.WEST);
		JPanel p3 = new JPanel();
		p3.setAlignmentX(Component.LEFT_ALIGNMENT);
		JLabel remoteDbLabel = new JLabel("Remote DB CString:      ");
		remoteDbLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		p3.add(remoteDbLabel);
		remoteDBLocationCString = new JTextField("");
		remoteDBLocationCString.setBorder(javax.swing.BorderFactory
				.createLineBorder(Color.BLACK, 1));
		remoteDBLocationCString.setPreferredSize(new Dimension(150, 30));
		remoteDBLocationCString.setMargin(new Insets(5, 22, 5, 22));
		remoteDBLocationCString.setColumns(38);
		remoteDBLocationCString.setAlignmentX(Component.LEFT_ALIGNMENT);

		p3.add(remoteDBLocationCString);
		JLabel tLabel = new JLabel("");
		tLabel.setPreferredSize(new Dimension(170, 30));
		p3.add(tLabel);
		p.add(p3, BorderLayout.SOUTH);
		mainDb.add(p, BorderLayout.SOUTH);
		contentPane.add(mainDb, BorderLayout.LINE_START);
	}

	/**
	 * Console panel
	 * 
	 * @param contentPane
	 *            base container
	 */
	private static void addConsole(Container contentPane) {
		JPanel p = new JPanel();
		TitledBorder tittle = new TitledBorder("Console");
		p.setBorder(tittle);
		p.setAlignmentY(Component.TOP_ALIGNMENT);

		final JTextComponent textComponent = new JTextPane();
		JPopupMenu menu = new JPopupMenu();
		JMenuItem clear = new JMenuItem("Clear");
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textComponent.setText("");

			}
		});
		menu.add(clear);
		textComponent.setComponentPopupMenu(menu);
		textComponent.setEditable(false);
		JScrollPane scroll = new JScrollPane(textComponent);
		scroll.setPreferredSize(new Dimension(780, 200));
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		MessageConsole console = new MessageConsole(textComponent);
		console.setMessageLines(500);
		console.redirectOut(Color.BLACK, null);
		console.redirectErr(Color.RED, null);

		p.add(scroll);
		contentPane.add(p, BorderLayout.SOUTH);
	}

	/**
	 * Redirects All logging to console panel
	 * 
	 * @param text
	 */
	public static void log(String text) {
		System.out.println(new Date() + " : " + text);
	}

	/**
	 * This class is responsible for managing all schedulers
	 * 
	 * @author krish
	 *
	 */
	static class Scheduler {
		/**
		 * Start Scheduler
		 */
		public void startScheduler() {
			Main.timer[0] = new Timer();
			if (Config.getInstance().isInterval()) {
				int scheduleInterval = Config.getInstance().getIntervalTime();
				Main.timer[0].schedule(new SyncTimer(), 0,
						scheduleInterval * 60 * 1000);
			} else {
				Date[] d = Config.getInstance().getScheduleDate();
				Main.timer = new Timer[d.length];
				Main.log("Sync time is " + d.toString());
				for (int i = 0; i < d.length; i++) {
					Main.timer[i] = new Timer();
					long delay = getDelay(d[i]);
					Main.log("Scheduler will start after " + delay
							+ " milliseconds");
					Main.timer[i].schedule(new SyncTimer(), delay,
							24 * 60 * 60 * 1000);
				}
			}
		}

		private long getDelay(Date d) {
			long time = d.getTime();
			long currentTime = System.currentTimeMillis();
			long diff = currentTime - time;
			if (diff < 0)
				return Math.abs(diff);
			else {
				return (24 * 60 * 60 * 1000) - diff;
			}

		}

		/**
		 * Stops scheduler
		 */
		public void stopScheduler() {
			try {
				Image img = ImageIO.read(new Object().getClass()
						.getResourceAsStream("/start_btn.png"));
				start.setIcon(new ImageIcon(img));
			} catch (IOException ee) {
				ee.printStackTrace();
			}
			start.setBorder(null);
			isSyncOn = false;

			enableAll();

			if (Config.getInstance().isInterval())
				Main.timer[0].cancel();
			else {
				int count = Config.getInstance().getScheduleCount();
				for (int i = 0; i < count; i++)
					Main.timer[i].cancel();
			}
		}

	}

	/**
	 * Define task to be performed by Schedulers
	 * 
	 * @author krish
	 *
	 */
	static class SyncTimer extends TimerTask {
		public void run() {
			Main.log("Sync is starting now ");
			// Do the actual syncing job here
			// get non updated data
			boolean isError = false;
			ArrayList<AttModel> attData = null;
			try {
				// Retrieves data from remote
				RemoteRowModel row = getRemoteRowModel(getMachineId());
				if (row == null) {
					// If not found initiate data , asks for query and tenant id
					// , then make a record on server
					row = updateQueryAndGetRemoteModel();
					if (row == null) {
						// If error connecting remote DB , stops all scheduler
						// and returns
						scheduler.stopScheduler();
						return;
					}

				}
				// get Records to update
				attData = getNonUpdateData(row);
				Main.log("Total Records to update : " + attData.size());
			} catch (Exception e) {
				Main.log("Error while fetching data from bio database");
				isError = true;
			}

			// Finally update that data to server
			if (!isError) {
				try {
					if (attData != null && attData.size() > 0) {
						updateOnREmote(attData);
						//storeLastRecordId(lastId);
					}
				} catch (Exception e) {
					Main.log("Error while updating on remote database");
				}

			}
		}

		/**
		 * Creates settings record on remote server after getting query and
		 * tenant id input
		 * 
		 * @return Object with initial values
		 */
		private RemoteRowModel updateQueryAndGetRemoteModel() {
			boolean query = Main.getQuery();
			if (!query) {
				Main.log(errorMsg);
				return null;
			}
			String machineId = getMachineId();
			int last_primary_key = -1;

			Connection con = null;
			// PreparedStatement pstmt = null;
			PreparedStatement st = null;
			RemoteRowModel rtn = null;
			try {
				Class.forName(MYSQL_DRIVER_CLASS);
				con = DriverManager.getConnection(Config.getInstance()
						.getRemoteDbCString());

				st = con.prepareStatement("insert into settings (sql_query,machine_id,last_primary_key_value,tenant_id) values (?,?,?,?)");
				st.setString(1, queryText);
				st.setString(2, machineId);
				st.setInt(3, last_primary_key);
				st.setInt(4, tenantIdNum);
				st.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace(System.err);
				return null;

			} finally {
				try {

					if (st != null)
						st.close();
					if (con != null)
						con.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			RemoteRowModel model = new RemoteRowModel();
			model.lastRecordId = last_primary_key;
			model.machineId = machineId;
			model.query = queryText;
			return model;
		}

		/**
		 * Try to get settings for machine id from remote database
		 * 
		 * @param machineId
		 *            against which record to be searched
		 * @return Object with current settings on server
		 */
		private RemoteRowModel getRemoteRowModel(String machineId) {

			Connection con = null;
			// PreparedStatement pstmt = null;
			ResultSet rs = null;
			RemoteRowModel rtn = null;
			try {
				Class.forName(MYSQL_DRIVER_CLASS);
				con = DriverManager.getConnection(Config.getInstance()
						.getRemoteDbCString());
				// con.setAutoCommit(false);
				Statement st = con.createStatement();
				rs = st.executeQuery("select * from settings where machine_id='"
						+ machineId + "'");

				while (rs.next()) {
					rtn = new RemoteRowModel();
					rtn.datetime = rs.getTimestamp("last_run_date");
					rtn.lastRecordId = rs.getInt("last_primary_key_value");
					rtn.machineId = rs.getString("machine_id");
					rtn.query = rs.getString("sql_query");
					rtn.tenentId = rs.getInt("tenant_id");
				}

			} catch (Exception e) {
				e.printStackTrace(System.err);
				// throw new Exception(" Error : " + e.getMessage());

			} finally {
				try {

					if (rs != null)
						rs.close();
					if (con != null)
						con.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			return rtn;
		}

		/**
		 * Update attendance data on remote
		 * 
		 * @param attData
		 *            data model
		 * @throws Exception
		 *             if not able to connect to remote database
		 */
		private void updateOnREmote(ArrayList<AttModel> attData)
				throws Exception {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				Class.forName(MYSQL_DRIVER_CLASS);
				con = DriverManager.getConnection(Config.getInstance()
						.getRemoteDbCString());
				con.setAutoCommit(false);
				Main.log("Starting Insertion of records into the remote Database."); //added.. changed
				pstmt = con
						.prepareStatement("insert into "
								+ Config.getInstance().getRemoteTableName()
								+ " (machine_id,employee_id,status,timestamp,transaction_id) values(?,?,?,?,?)"); //changed
				int count=0; //added.. changed
				for (AttModel attd : attData) { //added.. changed
					if(count%100!=0 || count ==0) { //added.. changed
						pstmt.setString(1, attd.bioId); //added.. changed
						pstmt.setString(2, attd.empId); //added.. changed
						pstmt.setLong(3, attd.status); //added.. changed
						pstmt.setTimestamp(4, attd.timeStamp); //added.. changed
						pstmt.setLong(5, attd.id); //added.. changed
						pstmt.addBatch(); //added.. changed
						

					} //added.. changed
					else { //added.. changed
						pstmt.setString(1, attd.bioId); //added.. changed
						pstmt.setString(2, attd.empId); //added.. changed
						pstmt.setLong(3, attd.status); //added.. changed
						pstmt.setTimestamp(4, attd.timeStamp); //added.. changed
						pstmt.setLong(5, attd.id); //added.. changed
						pstmt.addBatch(); //added.. changed
						
						pstmt.executeBatch(); //added.. changed
						Main.log("Executed Batch upto record no:"+count); //added.. changed
						con.commit(); //added.. changed
						Main.log("Commited upto record no:"+count); //added.. changed
						storeLastRecordId(attd.id); //added.. changed					
					} //added.. changed
					count++; //added.. changed
				}
				pstmt.executeBatch(); //added.. changed
				Main.log("Executed Batch upto record no:"+count); //added.. changed
				con.commit(); //added.. changed
				Main.log("Commited upto record no:"+count); //added.. changed
				
				/*
				int count=0;
				for (AttModel attd : attData) {
					pstmt.setString(1, attd.bioId);
					pstmt.setString(2, attd.empId);
					pstmt.setLong(3, attd.status);
					pstmt.setTimestamp(4, attd.timeStamp);
					pstmt.addBatch();
					count++;
					Main.log("inserting "+count); //added.. changed
				}
				pstmt.executeBatch();
				Main.log("Executed Batch"); //added.. changed
				con.commit();
				Main.log("Commited"); //added.. changed
				Main.log("Done Updating on the remote data base"); //added.. changed
				*/
				storeLastRecordId(lastId);
				Main.log("Done Updating on the remote data base"); //added.. changed
			} catch (Exception e) {
				e.printStackTrace(System.err);
				throw new Exception(" Error : " + e.getMessage());

			} finally {
				try {

					if (pstmt != null)
						pstmt.close();
					if (con != null)
						con.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}

		long lastId = -1;

		/**
		 * Get local data eligible for udpate on remote database server
		 * 
		 * @param row
		 *            Current setting data on remote database to get last id
		 * @return List of data to be updated on remote database
		 * @throws Exception
		 */
		private ArrayList<AttModel> getNonUpdateData(RemoteRowModel row)
				throws Exception {
			lastId = -1;
			ArrayList<AttModel> attData = new ArrayList<AttModel>();
			// long id = getLastRecordId();
			long id = row.lastRecordId;
			Main.log(" Last record id update was " + id);
			Connection con = null;
			ResultSet rs = null;

			try {

				if (Config.getInstance().isMSAccessData()) {
					HashMap<String, String> msData = Config.getInstance()
							.getAccessConfig();
					con = DriverManager.getConnection("jdbc:ucanaccess://"
							+ msData.get("dbpath"));
				} else {
					if (Config.getInstance().isMSSQLData()) //changed
						Class.forName(MSSQL_DRIVER_CLASS);
					else
						Class.forName(MYSQL_DRIVER_CLASS);
					con = DriverManager.getConnection(Config.getInstance()
							.getLocalDbCString());

				}
				String mId = getMachineId();
				PreparedStatement stmt = con.prepareStatement(row.query);
				stmt.setInt(1, row.lastRecordId);
				rs = stmt.executeQuery();
				while (rs.next()) {
					long tmpId = rs.getLong("id");
					if (tmpId > lastId)
						lastId = tmpId;
					String empId = rs.getString("employee_id");
					//Main.log("Updating for " + empId); //added.. changed
					String bioId = null;
					try {
						bioId = rs.getString("machine_id");
					} catch (Exception e) {
						bioId = mId;
					}

					if (bioId == null) {
						Main.log("Bio Id not found for employee id " + empId
								+ " Can not sync this recored.");
						continue;
					}
					long status = rs.getLong("status");
					Timestamp timeStamp = rs.getTimestamp("timestamp");
					attData.add(new AttModel(bioId, empId, status, timeStamp,tmpId));
				}

			} catch (Exception e) {
				e.printStackTrace(System.err);
				throw new Exception(" Error: " + e.getMessage());
				// e.printStackTrace(System.err);
			} finally {
				try {

					if (rs != null)
						rs.close();
					if (con != null)
						con.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			return attData;
		}

		/**
		 * Updates last recored id on remote settings
		 * 
		 * @param id
		 *            last record id to be updated
		 */

		private void storeLastRecordId(long id) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				Class.forName(MYSQL_DRIVER_CLASS);
				con = DriverManager.getConnection(Config.getInstance()
						.getRemoteDbCString());
				pstmt = con
						.prepareStatement("update settings set last_primary_key_value ="
								+ id
								+ " where machine_id='"
								+ getMachineId()
								+ "'");

				pstmt.execute();

			} catch (Exception e) {
				e.printStackTrace(System.err);

			} finally {
				try {

					if (pstmt != null)
						pstmt.close();
					if (con != null)
						con.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (schedule1.getSelectedIndex() != 0
				|| schedule2.getSelectedIndex() != 0
				|| schedule3.getSelectedIndex() != 0
				|| schedule4.getSelectedIndex() != 0
				|| schedule5.getSelectedIndex() != 0
				|| schedule6.getSelectedIndex() != 0
				|| schedule7.getSelectedIndex() != 0
				|| schedule8.getSelectedIndex() != 0) {

			intervals.setSelectedIndex(0);

		}

	}

<<<<<<< HEAD
}
=======
}
>>>>>>> 45dbebe93c1f0f9f2c0603433a71c8be42659c02
