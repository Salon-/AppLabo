package jp.co.iwa.applabo.entity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * �g�D���G���e�B�e�B�����񋟂���N���X�ł��B
 * @author Masashi Sakano
 *
 */
public class Organization implements BaseColumns {

	public static final String ENTITY_NAME = "organization";
	public static final String NAME = "name";

	public static final String CREATE_TABLE_SQL = "create table organization("
			+ "_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "name text not null"
			+ ");";

	public static final String SELECT_SQL = "select _id, name from organization where name = ?;";

	/**
	 * �g�D��ǉ����܂��B(SQLiteDatabase.insert()���\�b�h�ɂ��}��)
	 * 
	 * @param db
	 *            SQLiteDatabase �C���X�^���X
	 * @param name
	 *            �g�D��
	 * @return �s���}�����ꂽ�ꍇROW_ID�A���s�����ꍇ��-1���ԋp����܂��B
	 */
	public static long insertOrganization(SQLiteDatabase db, String name) {
		ContentValues values = new ContentValues();
		values.put(NAME, name);
		return db.insertOrThrow(ENTITY_NAME, null, values);
	}

	/**
	 * �g�D���������܂��B(SQL���ɂ�錟��)
	 * 
	 * @param db
	 *            SQLiteDatabase �C���X�^���X
	 * @param name
	 *            �g�D��
	 * @return ��������
	 */
	public static Cursor selectOrganizationExecSQL(SQLiteDatabase db,
			String name) {
		return db.rawQuery(SELECT_SQL, new String[] { name });
	}
}
