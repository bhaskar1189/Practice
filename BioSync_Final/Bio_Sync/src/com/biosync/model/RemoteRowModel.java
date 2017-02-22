package com.biosync.model;

import java.sql.Timestamp;
/**
 * Model represent a row on remote database settings
 * @author krish
 *
 */
public class RemoteRowModel {

	public String query;
	public String machineId;
	public int lastRecordId;
	public Timestamp datetime;
	public int tenentId;
	

}
