package com.biosync.model;

import java.sql.Timestamp;
/**
 * Model represent row of emp_att table on remote database
 * @author krish
 *
 */
public class AttModel {
	public String bioId;
	public String empId;
	public long status;
	public Timestamp timeStamp; 
	public long id;

	public AttModel(String bioId,String empId,long status,Timestamp timeStamp,long id ) {
		this.bioId=bioId;
		this.empId=empId;
		this.status=status;
		this.timeStamp=timeStamp;
		this.id=id;
	}

}
