package jp.co.iwa.applabo.entity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * 組織とユーザーアカウントの関連エンティティ操作を提供するクラスです。
 * @author Masashi Sakano
 *
 */
public class OrganizationUser implements BaseColumns {

	public static final String ENTITY_NAME = "organization_user";
	public static final String ORGANIZATION_ID = "organization_id";
	public static final String USER_ID = "user_account_id";

	public static final String CREATE_TABLE_SQL = "create table organization_user("
			+ "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ "organization_id integer not null, "
			+ "user_account_id integer not null, "
			+ "FOREIGN KEY(organization_id) REFERENCES organization(_id) ON DELETE CASCADE, "
			+ "FOREIGN KEY(user_account_id) REFERENCES user_account(_id) ON DELETE CASCADE"
			+ ");";

	/**
	 * 組織とユーザーの関連を追加します。(SQLiteDatabase.insert()メソッドによる挿入)
	 * 
	 * @param db
	 *            SQLiteDatabase インスタンス
	 * @param organizationId
	 *            組織ID
	 * @param userId
	 *            ユーザーID
	 * @return 行が挿入された場合ROW_ID、失敗した場合は-1が返却されます。
	 */
	public static long insertOrganizationUser(SQLiteDatabase db,
			int organizationId, int userId) {
		ContentValues values = new ContentValues();
		values.put(ORGANIZATION_ID, organizationId);
		values.put(USER_ID, userId);
		return db.insertOrThrow(ENTITY_NAME, null, values);
	}

	/**
	 * 組織とユーザーの関連を削除します。
	 * 
	 * @param db
	 *            SQLiteDatabase インスタンス
	 * @param organizationId
	 *            組織ID
	 * @param userId
	 *            ユーザーID
	 */
	public static void deleteOrganizationUser(SQLiteDatabase db,
			int organizationId, int userId) {
		db.delete(
				ENTITY_NAME,
				"organization_id = ? AND user_account_id = ?",
				new String[] { Integer.toString(organizationId),
						Integer.toString(userId) });
	}
}
