package jp.co.iwa.applabo;

import jp.co.iwa.applabo.db.SQLiteHelper;
import jp.co.iwa.applabo.entity.Organization;
import jp.co.iwa.applabo.entity.OrganizationUser;
import jp.co.iwa.applabo.entity.User;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;

public class SQLiteOperation extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		SQLiteHelper helper = SQLiteHelper.GetInstance(this);
		SQLiteDatabase db = helper.getWritableDatabase();

		// MEMO : �f�[�^�ʂ������ꍇ�̓g�����U�N�V�������g�p����
		db.beginTransaction();
		try {
			// MEMO : ���R�[�h�o�^ SQL���g�p�Ainsert���\�b�h��2�p�^�[��
			Organization.insertOrganization(db, "Salon_Group");
			User.insertUserExecSQL(db, "Salon", "password");
			User.insertUser(db, "�n���̔Ԍ�", "cerberus");
			User.insertUser(db, "AppLabo", "applabo");

			// MEMO : ���R�[�h�̎擾�@SQL���g�p�Aquery���\�b�h��2�p�^�[��
			Cursor result = User.selectUserExecSQL(db, "AppLabo");
			result.moveToFirst();

			int applaboId = result.getInt(result.getColumnIndex(User._ID));
			result.close();

			Cursor result2 = Organization.selectOrganizationExecSQL(db,
					"Salon_Group");
			result2.moveToFirst();

			int salonGroupId = result2.getInt(result2
					.getColumnIndex(Organization._ID));
			result2.close();

			Cursor result3 = User.selectUser(db, "�n���̔Ԍ�");
			result3.moveToFirst();

			int cerberusId = result3.getInt(result3.getColumnIndex(User._ID));
			result3.close();

			OrganizationUser
					.insertOrganizationUser(db, salonGroupId, applaboId);
			OrganizationUser.insertOrganizationUser(db, salonGroupId,
					cerberusId);

			// MEMO : ���R�[�h�X�V�A�폜
			// MEMO : �g�D�A���[�U�[�֘A���R�[�h�̓J�X�P�[�h�폜������@�𒲍�
			User.updateUser(db, "Salon", "salonpass");
			User.deleteUser(db, "AppLabo");
			OrganizationUser
					.deleteOrganizationUser(db, salonGroupId, applaboId);

			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}

		db.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sqlite_operation, menu);
		return true;
	}

}
