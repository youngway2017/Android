package yw.cn.chat.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2015-09-14.
 */
public class HMDBOpenHelper extends SQLiteOpenHelper{

    private static HMDBOpenHelper ourInstance = null;

    public static HMDBOpenHelper getInstance(Context context) {
        if (ourInstance == null) {
            synchronized (HMDBOpenHelper.class) {
                if (ourInstance == null) {
                    ourInstance = new HMDBOpenHelper(context);
                }
            }
        }
        return ourInstance;
    }

    private HMDBOpenHelper(Context context) {
        super(context,HMDB.NAME,null,HMDB.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(HMDB.Account.SQL_CREATE_TABLE);
        db.execSQL(HMDB.Friend.SQL_CREATE_TABLE);
        db.execSQL(HMDB.Invitation.SQL_CREATE_TABLE);
        db.execSQL(HMDB.Message.SQL_CREATE_TABLE);
        db.execSQL(HMDB.Conversation.SQL_CREATE_TABLE);
        db.execSQL(HMDB.BackTask.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
