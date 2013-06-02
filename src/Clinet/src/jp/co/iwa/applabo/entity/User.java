package jp.co.iwa.applabo.entity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * ユーザーアカウント情報エンティティ操作を提供するクラスです。
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

	// MEMO : SQLite CRUD 操作
	/**
	 * ユーザーを追加します。(SQL文による挿入)
	 * 
	 * @param db
	 *            SQLiteDatabase インスタンス
	 * @param name
	 *            ユーザー名
	 * @param password
	 *            パスワード
	 */
	public static void insertUserExecSQL(SQLiteDatabase db, String name,
			String password) {
		db.execSQL(INSERT_SQL, new Object[] { name, password });
	}

	/**
	 * ユーザーを追加します。(SQLiteDatabase.insert()メソッドによる挿入)
	 * 
	 * @param db
	 *            SQLiteDatabase インスタンス
	 * @param name
	 *            ユーザー名
	 * @param password
	 *            パスワード
	 * @return 行が挿入された場合ROW_ID、失敗した場合は-1が返却されます。
	 */
	public static long insertUser(SQLiteDatabase db, String name,
			String password) {
		ContentValues values = new ContentValues();
		values.put(NAME, name);
		values.put(PASSWORD, password);
		return db.insertOrThrow(ENTITY_NAME, null, values);
	}

	/**
	 * ユーザーを更新します。
	 * 
	 * @param db
	 *            SQLiteDatabase インスタンス
	 * @param name
	 *            ユーザー名
	 * @param password
	 *            パスワード
	 */
	public static void updateUser(SQLiteDatabase db, String name,
			String password) {
		ContentValues values = new ContentValues();
		values.put(PASSWORD, password);
		db.update(ENTITY_NAME, values, "name = ?", new String[] { name });
	}

	/**
	 * ユーザーを削除します。
	 * 
	 * @param db
	 *            SQLiteDatabase インスタンス
	 * @param name
	 *            ユーザー名
	 */
	public static void deleteUser(SQLiteDatabase db, String name) {
		db.delete(ENTITY_NAME, "name = ?", new String[] { name });
	}

	/**
	 * ユーザーを検索します。(SQL文による検索)
	 * 
	 * @param db
	 *            SQLiteDatabase インスタンス
	 * @param name
	 *            ユーザー名
	 * @return 検索結果
	 */
	public static Cursor selectUserExecSQL(SQLiteDatabase db, String name) {
		return db.rawQuery(SELECT_SQL, new String[] { name });
	}

	/**
	 * ユーザーを検索します。(queryメソッドによる検索)
	 * 
	 * @param db
	 *            SQLiteDatabase インスタンス
	 * @param name
	 *            ユーザー名
	 * @return 検索結果
	 */
	public static Cursor selectUser(SQLiteDatabase db, String name) {
		return db.query(ENTITY_NAME, new String[] { _ID, NAME, PASSWORD },
				"name = ?", new String[] { name }, null, null, null);
	}
}
