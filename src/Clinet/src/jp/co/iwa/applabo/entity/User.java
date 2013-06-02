package jp.co.iwa.applabo.entity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * ���[�U�[�A�J�E���g���G���e�B�e�B�����񋟂���N���X�ł��B
 * @author Masashi Sakano
 *
 */
public class User implements BaseColumns {

	public static final String ENTITY_NAME = "user_account";
	public static final String NAME = "name";
	public static final String PASSWORD = "password";

	public static final String CREATE_TABLE_SQL = "create table user_account("
			+ "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ "name text not null, " + "password text not null" + ");";

	public static final String INSERT_SQL = "insert into user_account(name, password) values (?, ?);";

	public static final String SELECT_SQL = "select _id, name, password from user_account where name = ?;";

	// MEMO : SQLite CRUD ����
	/**
	 * ���[�U�[��ǉ����܂��B(SQL���ɂ��}��)
	 * 
	 * @param db
	 *            SQLiteDatabase �C���X�^���X
	 * @param name
	 *            ���[�U�[��
	 * @param password
	 *            �p�X���[�h
	 */
	public static void insertUserExecSQL(SQLiteDatabase db, String name,
			String password) {
		db.execSQL(INSERT_SQL, new Object[] { name, password });
	}

	/**
	 * ���[�U�[��ǉ����܂��B(SQLiteDatabase.insert()���\�b�h�ɂ��}��)
	 * 
	 * @param db
	 *            SQLiteDatabase �C���X�^���X
	 * @param name
	 *            ���[�U�[��
	 * @param password
	 *            �p�X���[�h
	 * @return �s���}�����ꂽ�ꍇROW_ID�A���s�����ꍇ��-1���ԋp����܂��B
	 */
	public static long insertUser(SQLiteDatabase db, String name,
			String password) {
		ContentValues values = new ContentValues();
		values.put(NAME, name);
		values.put(PASSWORD, password);
		return db.insertOrThrow(ENTITY_NAME, null, values);
	}

	/**
	 * ���[�U�[���X�V���܂��B
	 * 
	 * @param db
	 *            SQLiteDatabase �C���X�^���X
	 * @param name
	 *            ���[�U�[��
	 * @param password
	 *            �p�X���[�h
	 */
	public static void updateUser(SQLiteDatabase db, String name,
			String password) {
		ContentValues values = new ContentValues();
		values.put(PASSWORD, password);
		db.update(ENTITY_NAME, values, "name = ?", new String[] { name });
	}

	/**
	 * ���[�U�[���폜���܂��B
	 * 
	 * @param db
	 *            SQLiteDatabase �C���X�^���X
	 * @param name
	 *            ���[�U�[��
	 */
	public static void deleteUser(SQLiteDatabase db, String name) {
		db.delete(ENTITY_NAME, "name = ?", new String[] { name });
	}

	/**
	 * ���[�U�[���������܂��B(SQL���ɂ�錟��)
	 * 
	 * @param db
	 *            SQLiteDatabase �C���X�^���X
	 * @param name
	 *            ���[�U�[��
	 * @return ��������
	 */
	public static Cursor selectUserExecSQL(SQLiteDatabase db, String name) {
		return db.rawQuery(SELECT_SQL, new String[] { name });
	}

	/**
	 * ���[�U�[���������܂��B(query���\�b�h�ɂ�錟��)
	 * 
	 * @param db
	 *            SQLiteDatabase �C���X�^���X
	 * @param name
	 *            ���[�U�[��
	 * @return ��������
	 */
	public static Cursor selectUser(SQLiteDatabase db, String name) {
		return db.query(ENTITY_NAME, new String[] { _ID, NAME, PASSWORD },
				"name = ?", new String[] { name }, null, null, null);
	}
}
