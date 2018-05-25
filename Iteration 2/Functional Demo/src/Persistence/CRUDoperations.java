package Persistence;

public abstract class CRUDoperations {

	protected int dbType;
	protected String server, db, usr, pwd;

	public void configDB(int dbType, String server, String db, String usr, String pwd) {
		this.dbType = dbType;
		this.server = server;
		this.db = db;
		this.usr = usr;
		this.pwd = pwd;
	}
}
