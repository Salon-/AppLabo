package jp.co.iwa.applabo.db;

import jp.co.iwa.applabo.entity.Organization;
import jp.co.iwa.applabo.entity.OrganizationUser;
import jp.co.iwa.applabo.entity.User;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLite�ւ̃f�[�^�x�[�X�����񋟂���w���p�[�N���X�ł��B
 * @author Masashi Sakano
 *
 */
public class SQLiteHelper extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "applabo_client.db";

	public static SQLiteHelper SINGLETON;

	/**
	 * �R���X�g���N�^�[�ł��B
	 * 
	 * @param context
	 *            Android�R���e�L�X�g
	 */
	private SQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * SQLiteHelper�̃C���X�^���X���擾���܂��B
	 * 
	 * @param context
	 *            Android�R���e�L�X�g
	 * @return�@�V���O���g���C���X�^���X
	 */
	public static SQLiteHelper GetInstance(Context context) {
		if (SINGLETON == null) {
			SINGLETON = new SQLiteHelper(context);
		}
		return SINGLETON;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		// MEMO : �O���L�[�����L���ɂ���
		if (!db.isReadOnly()) {
	        db.execSQL("PRAGMA foreign_keys = ON;");
	    }
		
		// MEMO : �f�[�^�ʂ������ꍇ�̓g�����U�N�V�������g�p����
		db.beginTransaction();
		try {
			// MEMO : �e�[�u������
			db.execSQL(Organization.CREATE_TABLE_SQL);
			db.execSQL(User.CREATE_TABLE_SQL);
			db.execSQL(OrganizationUser.CREATE_TABLE_SQL);
			
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// MEMO : DB�̍\�����ύX���ꂽ(�o�[�W�������قȂ�)�ۂ́A�����ňڍs��������������
	}
}
