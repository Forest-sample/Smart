package fr.umlv.lastproject.smart.database;

import java.util.ArrayList;

import android.util.Log;

import fr.umlv.lastproject.smart.layers.Geometry;
import fr.umlv.lastproject.smart.layers.LineGeometry;
import fr.umlv.lastproject.smart.layers.PolygonGeometry;
import fr.umlv.lastproject.smart.layers.Geometry.GeometryType;
import fr.umlv.lastproject.smart.layers.PointGeometry;

public class GeometryRecord {
	private int id;
	private GeometryType type; // 0: point 1:ligne 2:polygone
	private Geometry g;
	private int idMission;
	ArrayList<PointRecord> points  = new ArrayList<PointRecord>();

	public GeometryRecord() {
	}

	public GeometryRecord(Geometry g, int idMission) {
		this.type = g.getType();
		this.g = g;
		this.idMission = idMission;
		createPoints();
	}


	public GeometryRecord(GeometryType type, int idMission) {
		super();
		this.type = type;
		this.idMission = idMission;
		this.g = g;


	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public GeometryType getType() {
		return type;
	}

	public void setType(GeometryType type) {
		this.type = type;
	}

	public int getIdMission() {
		return idMission;
	}

	public void setIdMission(int idMission) {
		this.idMission = idMission;
	}
	
	public ArrayList<PointRecord> getPointsRecord(){
		return points;
	}
	
	public void createPoints(){
		
		switch (type) {
		case POINT:
			PointGeometry p = (PointGeometry) g ;
			points.add(new PointRecord(p) ) ;
			break;

		case LINE :
			LineGeometry l = (LineGeometry) g ;
			for(PointGeometry po : l.getPoints()){
				points.add(new PointRecord(po) ) ;
			}
			break ;
		case POLYGON :
			PolygonGeometry poly = (PolygonGeometry) g ;
			for(PointGeometry po : poly.getPoints()){
				points.add(new PointRecord(po) ) ;
			}
			break ;

		default:
			break;
		}
	}
	

}
