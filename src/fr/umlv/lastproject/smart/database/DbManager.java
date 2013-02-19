package fr.umlv.lastproject.smart.database;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import fr.umlv.lastproject.smart.form.BooleanField;
import fr.umlv.lastproject.smart.form.Field;
import fr.umlv.lastproject.smart.form.Form;
import fr.umlv.lastproject.smart.form.HeightField;
import fr.umlv.lastproject.smart.form.ListField;
import fr.umlv.lastproject.smart.form.Mission;
import fr.umlv.lastproject.smart.form.NumericField;
import fr.umlv.lastproject.smart.form.PictureField;
import fr.umlv.lastproject.smart.form.TextField;
import fr.umlv.lastproject.smart.layers.Geometry.GeometryType;

/**
 * Class to manage the database and the static tables (missions, geometries,
 * points)
 * 
 * @author Maelle Cabot
 * 
 */

public class DbManager {

	public static final String DB_NAME = "smart.db";
	public static final String DB_PATH = Environment
			.getExternalStorageDirectory() + "/SMART/DB/";

	public static final String TABLE_MISSIONS = "missions";
	private static final String MISSIONS_COL_ID = "id";
	private static final int MISSIONS_NUM_COL_ID = 0;
	private static final String MISSIONS_COL_TITLE = "title";
	private static final int MISSIONS_NUM_COL_TITLE = 1;
	private static final String MISSIONS_COL_STATUS = "status";
	private static final int MISSIONS_NUM_COL_STATUS = 2;
	private static final String MISSIONS_COL_DATE = "date";
	private static final int MISSIONS_NUM_COL_DATE = 3;

	public static final String TABLE_GEOMETRIES = "geometries";
	private static final String GEOMETRIES_COL_ID = "id";
	private static final int GEOMETRIES_NUM_COL_ID = 0;
	private static final String GEOMETRIES_COL_TYPE = "type";
	private static final int GEOMETRIES_NUM_COL_TYPE = 1;
	private static final String GEOMETRIES_COL_ID_MISSION = "idMission";
	private static final int GEOMETRIES_NUM_COL_ID_MISSION = 2;

	public static final String TABLE_POINTS = "points";
	private static final String POINTS_COL_ID = "id";
	private static final int POINTS_NUM_COL_ID = 0;
	private static final String POINTS_COL_X = "x";
	private static final int POINTS_NUM_COL_X = 1;
	private static final String POINTS_COL_Y = "y";
	private static final int POINTS_NUM_COL_Y = 2;
	private static final String POINTS_COL_Z = "z";
	private static final int POINTS_NUM_COL_Z = 3;
	private static final String POINTS_COL_ID_GEOMETRY = "idGeometry";
	private static final int POINTS_NUM_COL_ID_GEOMETRY = 4;

	private static final int TEXT_FIELD = 0;
	private static final int NUMERIC_FIELD = 1;
	private static final int BOOLEAN_FIELD = 2;
	private static final int LIST_FIELD = 3;
	private static final int PICTURE_FIELD = 4;
	private static final int HEIGHT_FIELD = 5;

	private DbHelper mDbHelper;
	private SQLiteDatabase mDb;

	private static String CREATE_TABLE_MISSIONS = "CREATE TABLE IF NOT EXISTS missions ( "
			+ "id INTEGER PRIMARY KEY,"
			+ "title TEXT UNIQUE,"
			+ "status INTEGER NOT NULL," + "date TEXT NOT NULL );";

	private static String CREATE_TABLE_GEOMETRIES = "CREATE TABLE IF NOT EXISTS geometries ( "
			+ "id INTEGER PRIMARY KEY,"
			+ "type INTEGER NOT NULL,"
			+ "idMission INTEGER NOT NULL,"
			+ "FOREIGN KEY (idMission) REFERENCES "
			+ TABLE_MISSIONS
			+ "("
			+ MISSIONS_COL_ID + "));";

	private static String CREATE_TABLE_POINTS = "CREATE TABLE IF NOT EXISTS points ( "
			+ "id INTEGER PRIMARY KEY,"
			+ "x REAL NOT NULL,"
			+ "y REAL NOT NULL,"
			+ "z REAL,"
			+ "idGeometry INTEGER NOT NULL,"
			+ "FOREIGN KEY (idGeometry) REFERENCES "
			+ TABLE_GEOMETRIES
			+ "("
			+ GEOMETRIES_COL_ID + "));";

	/**
	 * Create the database and the statics tables
	 * 
	 * @author Maelle Cabot
	 * 
	 */
	private final class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			super(context, DB_NAME, null, 1);
			File folder = new File(Environment.getExternalStorageDirectory()
					+ "/SMART/");
			if (!folder.exists()) {
				folder.mkdir();

			}
			File ssfolder = new File(Environment.getExternalStorageDirectory()
					+ "/SMART/DB/");
			if (!ssfolder.exists()) {
				ssfolder.mkdir();
			}
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}

		/**
		 * Open the database and create it if it's not exist
		 * 
		 * @return an access to database (read and write mode)
		 * @throws SQLException
		 */
		public SQLiteDatabase openDataBase() throws SQLException {
			SQLiteDatabase dbRetour = null;

			try {
				dbRetour = SQLiteDatabase.openOrCreateDatabase(DB_PATH
						+ DB_NAME, null);
				dbRetour.execSQL(CREATE_TABLE_MISSIONS);
				dbRetour.execSQL(CREATE_TABLE_GEOMETRIES);
				dbRetour.execSQL(CREATE_TABLE_POINTS);

			} catch (Exception e) {
			}

			return dbRetour;

		}

	}

	/**
	 * Open the database and instanciate the point of entry
	 * 
	 * @param context
	 *            of application
	 */
	public void open(Context context) {
		mDbHelper = new DbHelper(context);
		mDb = mDbHelper.openDataBase();

	}

	/**
	 * Close the database
	 */
	public void close() {
		mDb.close();
	}

	/**
	 * Create a table for the form if it's not exists
	 * 
	 * @param form
	 *            to create
	 * @return 0 if the creation ok, -1 if it's not
	 */
	public int createTableForm(Form f) {
		SQLiteDatabase db = null;
		String sql = "CREATE TABLE IF NOT EXISTS " + f.getName()
				+ "( id INTEGER PRIMARY KEY, " + "date TEXT NOT NULL, ";
		List<Field> listFields = f.getFieldsList();
		for (Field field : listFields) {
			int typeField = field.getType();

			switch (typeField) {
			case TEXT_FIELD:
				TextField tf = (TextField) field;
				sql += tf.getLabel() + " TEXT, ";
				break;
			case NUMERIC_FIELD:
				NumericField nf = (NumericField) field;
				sql += nf.getLabel() + " REAL CHECK (" + nf.getLabel() + " > "
						+ nf.getMin() + " AND " + nf.getLabel() + " < "
						+ nf.getMax() + " ),";
				break;
			case BOOLEAN_FIELD:
				BooleanField bf = (BooleanField) field;
				sql += bf.getLabel() + " INTEGER,";
				break;
			case LIST_FIELD:
				ListField lf = (ListField) field;
				sql += lf.getLabel() + " TEXT,";
				break;
			case PICTURE_FIELD:
				PictureField pf = (PictureField) field;
				sql += pf.getLabel() + " TEXT,";
				break;
			case HEIGHT_FIELD:
				HeightField hf = (HeightField) field;
				sql += hf.getLabel() + " TEXT,";
				break;
			default:
				break;
			}
		}

		sql += "idGeometry INTEGER NOT NULL,"
				+ "FOREIGN KEY (idGeometry) REFERENCES " + TABLE_GEOMETRIES
				+ "(" + GEOMETRIES_COL_ID + "));";

		try {
			db = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null,
					SQLiteDatabase.OPEN_READWRITE);
			db.execSQL(sql);

		} catch (SQLiteException e) {
			Log.d("TEST", "Erreur lors de l'ouverture de la base");
			return -1;
		} catch (SQLException e) {
			Log.d("TEST", "Erreur SQL");
			return -1;
		}

		return 0;
	}

	/**
	 * Insert a new row in the table form corresponding
	 * 
	 * @param formRecord
	 *            to insert
	 * @return 0 if the insertion ok, -1 if it's not
	 */
	public int insertFormRecord(FormRecord formRecord, int idGeometry) {

		ContentValues values = new ContentValues();

		List<FieldRecord> fields = new ArrayList<FieldRecord>();

		fields = formRecord.getFields();
		Log.d("TEST", "f " + fields.toString() + " " + formRecord.getName());
		for (FieldRecord field : fields) {

			int typeField = field.getField().getType();

			switch (typeField) {
			case TEXT_FIELD:
				TextFieldRecord tf = (TextFieldRecord) field;
				Log.d("TEST", tf.getField().getLabel() + " " + tf.getValue());
				values.put(tf.getField().getLabel(), tf.getValue());
				break;
			case NUMERIC_FIELD:
				NumericFieldRecord nf = (NumericFieldRecord) field;
				Log.d("TEST", nf.getField().getLabel() + " " + nf.getValue());

				values.put(nf.getField().getLabel(), nf.getValue());
				break;
			case BOOLEAN_FIELD:
				BooleanFieldRecord bf = (BooleanFieldRecord) field;
				Log.d("TEST", bf.getField().getLabel() + " " + bf.isValue());

				values.put(bf.getField().getLabel(), bf.isValue());
				break;
			case LIST_FIELD:
				ListFieldRecord lf = (ListFieldRecord) field;
				values.put(lf.getField().getLabel(), lf.getValue());
				break;
			case PICTURE_FIELD:
				PictureFieldRecord pf = (PictureFieldRecord) field;
				values.put(pf.getField().getLabel(), pf.getValue());
				break;
			case HEIGHT_FIELD:
				HeightFieldRecord hf = (HeightFieldRecord) field;
				Log.d("TEST", hf.getField().getLabel() + " " + hf.getValue());

				values.put(hf.getField().getLabel(), hf.getValue());
				break;
			default:
				break;
			}
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd/MM/yyyy HH:mm:ss", Locale.FRENCH);
		values.put("date", dateFormat.format(new Date()));
		values.put("idGeometry", idGeometry);

		try {
			mDb.insertOrThrow(formRecord.getName(), null, values);
			return 0;

		} catch (SQLException e) {
			return -1;
		}
	}

	/**
	 * Insert a new mission in the table "missions"
	 * 
	 * @param mission
	 *            to insert
	 * @return 0 if the insertion ok, -1 if it's not
	 */
	public int insertMission(MissionRecord mission) {

		ContentValues values = new ContentValues();

		createTableForm(mission.getForm());
		
		values.put(MISSIONS_COL_TITLE, mission.getTitle());
		if (mission.isStatus()) {
			values.put(MISSIONS_COL_STATUS, 1);
		} else {
			values.put(MISSIONS_COL_STATUS, 0);
		}
		values.put(MISSIONS_COL_DATE, mission.getDate());
		try {
			mDb.insertOrThrow(TABLE_MISSIONS, null, values);
			int id = getLastIndex(TABLE_MISSIONS);
			mission.setId(id);
			return 0;

		} catch (SQLException e) {
			int id = getMission(Mission.getInstance().getTitle());
			mission.setId(id);
			return -1;
		}
	}

	/**
	 * Request the table "missions" with a criterion of name
	 * 
	 * @param name
	 * @return the id of the mission
	 */
	public int getMission(String name) {

		Cursor c = mDb.rawQuery("SELECT " + MISSIONS_COL_ID + " FROM "
				+ TABLE_MISSIONS + " WHERE " + MISSIONS_COL_TITLE + " = '"
				+ name + "'", null);

		c.moveToNext();
		int id = c.getInt(MISSIONS_NUM_COL_ID);
		c.close();
		return id;
	}

	/**
	 * Request the table "missions" to get all rows
	 * 
	 * @return a list of Mission
	 */
	public List<MissionRecord> getAllMissions() {

		Cursor c = mDb.query(TABLE_MISSIONS, new String[] { MISSIONS_COL_ID,
				MISSIONS_COL_TITLE, MISSIONS_COL_STATUS, MISSIONS_COL_DATE },
				null, null, null, null, null);

		LinkedList<MissionRecord> missions = new LinkedList<MissionRecord>();
		while (c.moveToNext()) {
			missions.add(cursorToMission(c));
		}
		c.close();
		return missions;
	}

	/**
	 * Convert a cursor to a Mission object
	 * 
	 * @param c
	 *            the cursor
	 * @return a Mission object
	 */
	private MissionRecord cursorToMission(Cursor c) {
		if (c.getCount() == 0) {
			return null;
		}
		MissionRecord mission = new MissionRecord();

		mission.setId(c.getInt(MISSIONS_NUM_COL_ID));
		mission.setTitle(c.getString(MISSIONS_NUM_COL_TITLE));
		if (c.getInt(MISSIONS_NUM_COL_STATUS) == 0) {
			mission.setStatus(false);
		} else {
			mission.setStatus(true);
		}
		mission.setDate(c.getString(MISSIONS_NUM_COL_DATE));
		return mission;
	}

	/**
	 * Insert a new geometry in the table "geometries"
	 * 
	 * @param geometry
	 *            to insert
	 * @return 0 if the insertion ok, -1 if it's not
	 */
	public int insertGeometry(GeometryRecord geometry) {

		ContentValues values = new ContentValues();

		int type;
		switch (geometry.getType()) {
		case POINT:
			type = 0;
			break;
		case LINE:
			type = 1;
			break;
		case POLYGON:
			type = 2;
			break;
		default:
			type = 0;
		}

		values.put(GEOMETRIES_COL_TYPE, type);
		values.put(GEOMETRIES_COL_ID_MISSION, geometry.getIdMission());

		try {
			mDb.insertOrThrow(TABLE_GEOMETRIES, null, values);
			int id = getLastIndex(TABLE_GEOMETRIES);
			for (PointRecord pr : geometry.getPointsRecord()) {
				pr.setIdGeometry(id);
				insertPoint(pr);
			}
			return id;

		} catch (SQLException e) {
			Log.d("TEST", "doublon");
			return -1;
		}
	}

	/**
	 * Request the table "geometries" to get all rows
	 * 
	 * @return a list of Geometry
	 */
	public List<GeometryRecord> getAllGeometries() {

		Cursor c = mDb.query(TABLE_GEOMETRIES, new String[] {
				GEOMETRIES_COL_ID, GEOMETRIES_COL_TYPE,
				GEOMETRIES_COL_ID_MISSION }, null, null, null, null, null);

		LinkedList<GeometryRecord> geometries = new LinkedList<GeometryRecord>();
		while (c.moveToNext()) {
			geometries.add(cursorToGeometry(c));
		}
		c.close();
		return geometries;
	}

	/**
	 * Convert a cursor to a Geometry object
	 * 
	 * @param c
	 *            the cursor
	 * @return a Geometry object
	 */
	private GeometryRecord cursorToGeometry(Cursor c) {
		if (c.getCount() == 0) {
			return null;
		}
		GeometryRecord geometry = new GeometryRecord();

		GeometryType type;
		switch (c.getInt(GEOMETRIES_NUM_COL_TYPE)) {
		case 0:
			type = GeometryType.POINT;
			break;
		case 1:
			type = GeometryType.LINE;
			break;
		case 2:
			type = GeometryType.POLYGON;
			break;

		default:
			type = GeometryType.POINT;
		}

		geometry.setId(c.getInt(GEOMETRIES_NUM_COL_ID));
		geometry.setType(type);
		geometry.setIdMission(c.getInt(GEOMETRIES_NUM_COL_ID_MISSION));

		return geometry;
	}

	/**
	 * Insert a new point in the table "points"
	 * 
	 * @param point
	 *            to insert
	 * @return 0 if the insertion ok, -1 if it's not
	 */
	public int insertPoint(PointRecord point) {

		ContentValues values = new ContentValues();

		values.put(POINTS_COL_X, point.getX());
		values.put(POINTS_COL_Y, point.getY());
		values.put(POINTS_COL_Z, point.getZ());
		values.put(POINTS_COL_ID_GEOMETRY, point.getIdGeometry());

		try {
			mDb.insertOrThrow(TABLE_POINTS, null, values);
			return 0;

		} catch (SQLException e) {
			Log.d("TEST", "doublon");
			return -1;
		}
	}

	/**
	 * Request the table "points" to get all rows
	 * 
	 * @return a list of Geometry
	 */
	public List<PointRecord> getAllPoints() {

		Cursor c = mDb.query(TABLE_POINTS, new String[] { POINTS_COL_ID,
				POINTS_COL_X, POINTS_COL_Y, POINTS_COL_Z,
				POINTS_COL_ID_GEOMETRY }, null, null, null, null, null);

		LinkedList<PointRecord> points = new LinkedList<PointRecord>();
		while (c.moveToNext()) {
			points.add(cursorToPoint(c));
		}
		c.close();
		return points;
	}

	/**
	 * Request the table "points" in terms of the mission id
	 * 
	 * @param idMission
	 * @return a list of PointRecord
	 */
	public List<PointRecord> getGeometriesPointsOfMission(int idMission) {
		LinkedList<PointRecord> points = new LinkedList<PointRecord>();

		Cursor c = mDb
				.rawQuery(
						"SELECT * FROM points JOIN geometries ON geometries.id=points.idGeometry WHERE geometries.type=0 and geometries.idMission = "
								+ idMission, null);
		while (c.moveToNext()) {
			points.add(cursorToPoint(c));
		}

		return points;
	}

	/**
	 * Convert a cursor to a Point object
	 * 
	 * @param c
	 *            the cursor
	 * @return a Point object
	 */
	private PointRecord cursorToPoint(Cursor c) {
		if (c.getCount() == 0) {
			return null;
		}
		PointRecord point = new PointRecord();
		point.setId(c.getInt(POINTS_NUM_COL_ID));
		point.setX(c.getDouble(POINTS_NUM_COL_X));
		point.setY(c.getDouble(POINTS_NUM_COL_Y));
		point.setZ(c.getDouble(POINTS_NUM_COL_Z));
		point.setIdGeometry(c.getInt(POINTS_NUM_COL_ID_GEOMETRY));

		return point;
	}

	/**
	 * Request the last index assigned to a table
	 * 
	 * @param table
	 * @return the id of the last index
	 */
	public int getLastIndex(String table) {
		Cursor c = mDb.rawQuery("SELECT last_insert_rowid() FROM " + table,
				null);
		c.moveToNext();
		int id = c.getInt(0);

		return id;
	}

}
