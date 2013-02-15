package fr.umlv.lastproject.database;

public class GeometryRecord {
	private int id;
	private int type; // 0: point 1:ligne 2:polygone
	private int idMission;

	public GeometryRecord() {
	}

	public GeometryRecord(int type, int idMission) {
		super();
		this.type = type;
		this.idMission = idMission;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getIdMission() {
		return idMission;
	}

	public void setIdMission(int idMission) {
		this.idMission = idMission;
	}

}
