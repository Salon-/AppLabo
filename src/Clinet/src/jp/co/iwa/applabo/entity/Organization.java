package jp.co.iwa.applabo.entity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * 組織情報エンティティ操作を提供するクラスです。
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
	 * 組織を追加します。(SQLiteDatabase.insert()メソッドによる挿入)
	 * 
	 * @param db
	 *            SQLiteDatabase インスタンス
	 * @param name
	 *            組織名
	 * @return 行が挿入された場合ROW_ID、失敗した場合は-1が返却されます。
	 */
	public static long insertOrganization(SQLiteDatabase db, String name) {
		ContentValues values = new ContentValues();
		values.put(NAME, name);
		return db.insertOrThrow(ENTITY_NAME, null, values);
	}

	/**
	 * 組織を検索します。(SQL文による検索)
	 * 
	 * @param db
	 *            SQLiteDatabase インスタンス
	 * @param name
	 *            組織名
	 * @return 検索結果
	 */
	public static Cursor selectOrganizationExecSQL(SQLiteDatabase db,
			String name) {
		return db.rawQuery(SELECT_SQL, new String[] { name });
	}
}
