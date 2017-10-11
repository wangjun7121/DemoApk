package com.example.wangjun.demoapk.DataPersistenceDemo;

/**
 * Created by wangjun on 2017/10/11.
 */


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class COperateDatabase_MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_BOOK = "create table Book ("
            + "id integer primary key autoincrement, "
            + "author text, "
            + "price real, "
            + "pages integer, "
            + "name text)";

    public static final String CREATE_CATEGORY = "create table Category ("
            + "id integer primary key autoincrement, "
            + "category_name text, "
            + "category_code integer)";

    private Context mContext;

    public COperateDatabase_MyDatabaseHelper(Context context, String name,
                            CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");
        onCreate(db);

//        数据库更新操作：
//           请注意一个非常重要的细节， switch 中每一个 case 的最后都是没有使用 break 的，
//           为什么要这么做呢？这是为了保证在跨版本升级的时候， 每一次的数据库修改都能被全部执
//           行到。比如用户当前是从第二版程序升级到第三版程序的，那么 case 2 中的逻辑就会执行。
//           而如果用户是直接从第一版程序升级到第三版程序的， 那么 case 1 和 case 2 中的逻辑都会执
//           行。 使用这种方式来维护数据库的升级， 不管版本怎样更新， 都可以保证数据库的表结构是
//           最新的，而且表中的数据也完全不会丢失了
//        switch (oldVersion) {
//            case 1:
//                db.execSQL(CREATE_CATEGORY);
//            case 2:
//                db.execSQL("alter table Book add column category_id integer");
//            default:
//        }


    }

}