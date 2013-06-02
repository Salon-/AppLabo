package jp.co.iwa.applabo.entity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * �g�D�ƃ��[�U�[�A�J�E���g�̊֘A�G���e�B�e�B�����񋟂���N���X�ł��B
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
	 * �g�D�ƃ��[�U�[�̊֘A��ǉ����܂��B(SQLiteDatabase.insert()���\�b�h�ɂ��}��)
	 * 
	 * @param db
	 *            SQLiteDatabase �C���X�^���X
	 * @param organizationId
	 *            �g�DID
	 * @param userId
	 *            ���[�U�[ID
	 * @return �s���}�����ꂽ�ꍇROW_ID�A���s�����ꍇ��-1���ԋp����܂��B
	 */
	public static long insertOrganizationUser(SQLiteDatabase db,
			int organizationId, int userId) {
		ContentValues values = new ContentValues();
		values.put(ORGANIZATION_ID, organizationId);
		values.put(USER_ID, userId);
		return db.insertOrThrow(ENTITY_NAME, null, values);
	}

	/**
	 * �g�D�ƃ��[�U�[�̊֘A���폜���܂��B
	 * 
	 * @param db
	 *            SQLiteDatabase �C���X�^���X
	 * @param organizationId
	 *            �g�DID
	 * @param userId
	 *            ���[�U�[ID
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
