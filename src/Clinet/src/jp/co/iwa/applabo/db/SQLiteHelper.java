package jp.co.iwa.applabo.db;

import jp.co.iwa.applabo.entity.Organization;
import jp.co.iwa.applabo.entity.OrganizationUser;
import jp.co.iwa.applabo.entity.User;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLiteへのデータベース操作を提供するヘルパークラスです。
 * @author Masashi Sakano
 *
 */
public class SQLiteHelper extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "applabo_client.db";

	public static SQLiteHelper SINGLETON;

	/**
	 * コンストラクターです。
	 * 
	 * @param context
	 *            Androidコンテキスト
	 */
	private SQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * SQLiteHelperのインスタンスを取得します。
	 * 
	 * @param context
	 *            Androidコンテキスト
	 * @return　シングルトンインスタンス
	 */
	public static SQLiteHelper GetInstance(Context context) {
		if (SINGLETON == null) {
			SINGLETON = new SQLiteHelper(context);
		}
		return SINGLETON;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		// MEMO : 外部キー制約を有効にする
		if (!db.isReadOnly()) {
	        db.execSQL("PRAGMA foreign_keys = ON;");
	    }
		
		// MEMO : データ量が多い場合はトランザクションを使用する
		db.beginTransaction();
		try {
			// MEMO : テーブル生成
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
		// MEMO : DBの構造が変更された(バージョンが異なる)際は、ここで移行処理を実装する
	}
}
