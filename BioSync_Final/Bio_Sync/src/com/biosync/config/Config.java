package com.biosync.config;


import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.HashMap;

import java.util.TimeZone;

import com.biosync.Main;
/**
 *  This class holds current configuration
 * @author krish
 *
 */
public class Config {


	public static final String KEY_BIO_TABLE_NAME = "bio_data";
	public static final String KEY_REMOTE_TABLE_NAME = "emp_att";
	public static final String KEY_MAPPING_TABLE_NAME = "employee";

	public static final String KEY_DB_TYPE = "db_type";
	public static final String KEY_LOCAL_CSTRING = "local_cstring";
	public static final String KEY_REMOTE_CSTRING = "remote_cstring";
	public static final String KEY_INTERVAL = "interval";
	public static final String KEY_SCHEDULE1 = "schedule1";
	public static final String KEY_SCHEDULE2 = "schedule2";
	public static final String KEY_SCHEDULE3 = "schedule3";
	public static final String KEY_SCHEDULE4 = "schedule4";
	public static final String KEY_SCHEDULE5 = "schedule5";
	public static final String KEY_SCHEDULE6 = "schedule6";
	public static final String KEY_SCHEDULE7 = "schedule7";
	public static final String KEY_SCHEDULE8 = "schedule8";

	private HashMap<String, String> cProperties = new HashMap<String, String>();

	private Config() {

	}

	private static Config _instance = null;

	public static Config getInstance() {
		if (_instance == null)
			_instance = new Config();
		return _instance;
	}

	public void addPropertey(String key, String value) {
		cProperties.put(key, value);
	}

	public String getProperty(String key) {
		return cProperties.get(key);
	}

	public boolean isInterval() {
		String interval = getProperty(KEY_INTERVAL);
		return (interval == null || interval.equals(Main.SELECT_INTERVAL)) ? false
				: true;
	}

	public int getIntervalTime() {
		return Integer.parseInt(getProperty(KEY_INTERVAL));
	}

	public String getBioTableName() {
		return KEY_BIO_TABLE_NAME;
	}

	public String getMappingTableName() {
		return KEY_MAPPING_TABLE_NAME;
	}

	public String getRemoteTableName() {
		return KEY_REMOTE_TABLE_NAME;
	}

	public Date[] getScheduleDate() {
		int count = getScheduleCount();
		Date[] dates = new Date[count];
		String schedule1 = getProperty(KEY_SCHEDULE1);
		String schedule2 = getProperty(KEY_SCHEDULE2);
		String schedule3 = getProperty(KEY_SCHEDULE3);
		String schedule4 = getProperty(KEY_SCHEDULE4);
		String schedule5 = getProperty(KEY_SCHEDULE5);
		String schedule6 = getProperty(KEY_SCHEDULE6);
		String schedule7 = getProperty(KEY_SCHEDULE7);
		String schedule8 = getProperty(KEY_SCHEDULE8);

		// boolean bi= (interval == null ||
		// interval.equals(Main.SELECT_INTERVAL) ) ? false :true;
		boolean bs1 = (schedule1 == null || schedule1
				.equals(Main.SELECT_SCHEDULE)) ? false : true;
		boolean bs2 = (schedule2 == null || schedule2
				.equals(Main.SELECT_SCHEDULE)) ? false : true;
		boolean bs3 = (schedule4 == null || schedule3
				.equals(Main.SELECT_SCHEDULE)) ? false : true;
		boolean bs4 = (schedule4 == null || schedule4
				.equals(Main.SELECT_SCHEDULE)) ? false : true;
		boolean bs5 = (schedule5 == null || schedule5
				.equals(Main.SELECT_SCHEDULE)) ? false : true;
		boolean bs6 = (schedule6 == null || schedule6
				.equals(Main.SELECT_SCHEDULE)) ? false : true;
		boolean bs7 = (schedule7 == null || schedule7
				.equals(Main.SELECT_SCHEDULE)) ? false : true;
		boolean bs8 = (schedule8 == null || schedule8
				.equals(Main.SELECT_SCHEDULE)) ? false : true;

		int i = 0;
		if (bs1) {
			dates[i] = getDateFromTime(schedule1);
			i++;
		}
		if (bs2) {
			dates[i] = getDateFromTime(schedule2);
			i++;
		}
		if (bs3) {
			dates[i] = getDateFromTime(schedule3);
			i++;
		}
		if (bs4) {
			dates[i] = getDateFromTime(schedule4);
			i++;
		}
		if (bs5) {
			dates[i] = getDateFromTime(schedule5);
			i++;
		}
		if (bs6) {
			dates[i] = getDateFromTime(schedule6);
			i++;
		}
		if (bs7) {
			dates[i] = getDateFromTime(schedule7);
			i++;
		}
		if (bs8) {
			dates[i] = getDateFromTime(schedule8);
			i++;
		}
		return dates;
	}

	private Date getDateFromTime(String time) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		sf.setTimeZone(TimeZone.getDefault());
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
		sf1.setTimeZone(TimeZone.getDefault());
		String f = sf1.format(new Date());
		f = f + " " + time;

		try {
			return sf.parse(f);

		} catch (Exception e) {

		}
		return null;
	}

	public int getScheduleCount() {
		int count = 0;
		String schedule1 = getProperty(KEY_SCHEDULE1);
		String schedule2 = getProperty(KEY_SCHEDULE2);
		String schedule3 = getProperty(KEY_SCHEDULE3);
		String schedule4 = getProperty(KEY_SCHEDULE4);
		String schedule5 = getProperty(KEY_SCHEDULE5);
		String schedule6 = getProperty(KEY_SCHEDULE6);
		String schedule7 = getProperty(KEY_SCHEDULE7);
		String schedule8 = getProperty(KEY_SCHEDULE8);

		// boolean bi= (interval == null ||
		// interval.equals(Main.SELECT_INTERVAL) ) ? false :true;
		boolean bs1 = (schedule1 == null || schedule1
				.equals(Main.SELECT_SCHEDULE)) ? false : true;
		boolean bs2 = (schedule2 == null || schedule2
				.equals(Main.SELECT_SCHEDULE)) ? false : true;
		boolean bs3 = (schedule4 == null || schedule3
				.equals(Main.SELECT_SCHEDULE)) ? false : true;
		boolean bs4 = (schedule4 == null || schedule4
				.equals(Main.SELECT_SCHEDULE)) ? false : true;
		boolean bs5 = (schedule5 == null || schedule5
				.equals(Main.SELECT_SCHEDULE)) ? false : true;
		boolean bs6 = (schedule6 == null || schedule6
				.equals(Main.SELECT_SCHEDULE)) ? false : true;
		boolean bs7 = (schedule7 == null || schedule7
				.equals(Main.SELECT_SCHEDULE)) ? false : true;
		boolean bs8 = (schedule8 == null || schedule8
				.equals(Main.SELECT_SCHEDULE)) ? false : true;

		if (bs1)
			count = count + 1;
		if (bs2)
			count = count + 1;
		if (bs3)
			count = count + 1;
		if (bs4)
			count = count + 1;
		if (bs5)
			count = count + 1;
		if (bs6)
			count = count + 1;
		if (bs7)
			count = count + 1;
		if (bs8)
			count = count + 1;

		return count;
	}

	public String getLocalDbCString() {
		if (this.isMSSQLData())
			return "jdbc:sqlserver://" + getProperty(KEY_LOCAL_CSTRING);
		else if (this.isMYSQLData())
			return "jdbc:mysql://" + getProperty(KEY_LOCAL_CSTRING);
		else
			return getProperty(KEY_LOCAL_CSTRING);
	}

	public String getRemoteDbCString() {
		return "jdbc:mysql://" + getProperty(KEY_REMOTE_CSTRING);
	}

	public HashMap<String, String> getAccessConfig() {
		HashMap<String, String> accessData = new HashMap<String, String>();
		String localDbCString = getProperty(KEY_LOCAL_CSTRING);
		String[] vals = localDbCString.split(";");
		for (String val : vals) {
			String[] keyVals = val.split("=");
			try {
				accessData.put(keyVals[0], keyVals[1]);
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

		return accessData;
	}

	public boolean isMSSQLData() {
		String dbType = getProperty(KEY_DB_TYPE);
		
		return dbType.equals("1");
	}

	public boolean isMYSQLData() {
		String dbType = getProperty(KEY_DB_TYPE);
		return dbType.equals("2");
	}

	public boolean isMSAccessData() {
		String dbType = getProperty(KEY_DB_TYPE);
		if(dbType == null)
			return false;
		return dbType.equals("0");
	}

	private boolean isValidAccessData() {
		HashMap<String, String> data = getAccessConfig();
		if (data.get("dbpath") == null)
			return false;
		else
			return true;
	}

	private boolean isValidIntervals() {
		String interval = getProperty(KEY_INTERVAL);
		String schedule1 = getProperty(KEY_SCHEDULE1);
		String schedule2 = getProperty(KEY_SCHEDULE2);
		String schedule3 = getProperty(KEY_SCHEDULE3);
		String schedule4 = getProperty(KEY_SCHEDULE4);
		String schedule5 = getProperty(KEY_SCHEDULE5);
		String schedule6 = getProperty(KEY_SCHEDULE6);
		String schedule7 = getProperty(KEY_SCHEDULE7);
		String schedule8 = getProperty(KEY_SCHEDULE8);

		boolean bi = (interval == null || interval.equals(Main.SELECT_INTERVAL)) ? false
				: true;
		boolean bs1 = (schedule1 == null || schedule1
				.equals(Main.SELECT_SCHEDULE)) ? false : true;
		boolean bs2 = (schedule2 == null || schedule2
				.equals(Main.SELECT_SCHEDULE)) ? false : true;
		boolean bs3 = (schedule4 == null || schedule3
				.equals(Main.SELECT_SCHEDULE)) ? false : true;
		boolean bs4 = (schedule4 == null || schedule4
				.equals(Main.SELECT_SCHEDULE)) ? false : true;
		boolean bs5 = (schedule5 == null || schedule5
				.equals(Main.SELECT_SCHEDULE)) ? false : true;
		boolean bs6 = (schedule6 == null || schedule6
				.equals(Main.SELECT_SCHEDULE)) ? false : true;
		boolean bs7 = (schedule7 == null || schedule7
				.equals(Main.SELECT_SCHEDULE)) ? false : true;
		boolean bs8 = (schedule8 == null || schedule8
				.equals(Main.SELECT_SCHEDULE)) ? false : true;
		return !(!bi && !bs1 && !bs2 && !bs3 && !bs4 && !bs5 && !bs6 && !bs7 && !bs8);
	}

	public boolean isConfigOK() {

		String dbType = getProperty(KEY_DB_TYPE);
		// String localDbCString=getProperty(KEY_LOCAL_CSTRING);
		String remoteDbCString = getProperty(KEY_REMOTE_CSTRING);

		if (dbType.equals("0"))// ms access{
		{
			if (!isValidAccessData())
				return false;
		} else if (remoteDbCString == null || remoteDbCString.equals(""))
			return false;

		if (!isValidIntervals())
			return false;

		return true;

	}

	public static boolean isValidPropertry(String value) {
		return value != null && value != "";
	}

	public static boolean isValidInterval(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isValidSchedular(String value) {
		SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
		try {
			sf.parse(value);
			return true;
		} catch (Exception e) {

		}

		return false;

	}
}
