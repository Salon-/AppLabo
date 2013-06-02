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

		// MEMO : データ量が多い場合はトランザクションを使用する
		db.beginTransaction();
		try {
			// MEMO : レコード登録 SQL文使用、insertメソッドの2パターン
			Organization.insertOrganization(db, "Salon_Group");
			User.insertUserExecSQL(db, "Salon", "password");
			User.insertUser(db, "地獄の番犬", "cerberus");
			User.insertUser(db, "AppLabo", "applabo");

			// MEMO : レコードの取得　SQL文使用、queryメソッドの2パターン
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

			Cursor result3 = User.selectUser(db, "地獄の番犬");
			result3.moveToFirst();

			int cerberusId = result3.getInt(result3.getColumnIndex(User._ID));
			result3.close();

			OrganizationUser
					.insertOrganizationUser(db, salonGroupId, applaboId);
			OrganizationUser.insertOrganizationUser(db, salonGroupId,
					cerberusId);

			// MEMO : レコード更新、削除
			// MEMO : 組織、ユーザー関連レコードはカスケード削除する方法を調査
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
