package com.example.wangjun.demoapk.DataPersistenceDemo;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.wangjun.demoapk.R;

public class COperateDatabaseActivity extends Activity {
    private COperateDatabase_MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datapersistence_coperatedatabase);

//        如何直接使用 SQL 来完成前面几小节中学过的 CRUD 操作。
//            添加数据的方法如下：
//                db.execSQL("insert into Book (name, author, pages, price) values(?, ?, ?, ?)",
//                        new String[] { "The Da Vinci Code", "Dan Brown", "454", "16.96" });
//                db.execSQL("insert into Book (name, author, pages, price) values(?, ?, ?, ?)",
//                        new String[] { "The Lost Symbol", "Dan Brown", "510", "19.95" });
//            更新数据的方法如下：
//                db.execSQL("update Book set price = ? where name = ?", new String[] { "10.99",
//                        "The Da Vinci Code" });
//            删除数据的方法如下：
//                db.execSQL("delete from Book where pages > ?", new String[] { "500" });
//            查询数据的方法如下：
//                db.rawQuery("select * from Book", null);
//            可以看到， 除了查询数据的时候调用的是 SQLiteDatabase 的 rawQuery()方法， 其他的操
//            作都是调用的 execSQL()方法








        /////////////////////////////////////////////////////////////////////////////////////////////
        // 创建/更新数据库
//        参数将数据库名指定为 BookStore.db，版本号指定为 1,会导致 onCreate() 函数被调用
        // 存储路径：/data/data/<packagename>/databases/
        dbHelper = new COperateDatabase_MyDatabaseHelper(this, "BookStore.db", null, 1);
        // 指定版本号为 2 时，会导致 onUpgrade() 函数被调用，更新数据库
        // dbHelper = new COperateDatabase_MyDatabaseHelper(this, "BookStore.db", null, 2);
        Button createDatabase = (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

//                getReadableDatabase() 和 getWritableDatabase()：
//                    这两个方法都可以创建或打开一个现有的数据库（如果数据库已存在则直接打开，否则创建一个新的数据库），
//                    并返回一个可对数据库进行读写操作的对象。
//                不同的是，当数据库不可写入的时候（如磁盘空间已满）
//                    getReadableDatabase()方法返回的对象将以只读的方式去打开数据库，而
//                    getWritableDatabase()方法则将出现异常
                dbHelper.getWritableDatabase();
            }
        });

        /////////////////////////////////////////////////////////////////////////////////////////////
        // 添加数据
        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                // 开始组装第一条数据
                values.put("name", "The Da Vinci Code");
                values.put("author", "Dan Brown");
                values.put("pages", 454);
                values.put("price", 16.96);
                db.insert("Book", null, values); // 插入第一条数据
                values.clear();
                // 开始组装第二条数据
                values.put("name", "The Lost Symbol");
                values.put("author", "Dan Brown");
                values.put("pages", 510);
                values.put("price", 19.95);
                db.insert("Book", null, values); // 插入第二条数据
            }
        });

        /////////////////////////////////////////////////////////////////////////////////////////////
        // 更新数据
        Button updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price", 10.99);
                db.update("Book", values, "name = ?",
                        new String[] { "The Da Vinci Code" });
            }
        });

        /////////////////////////////////////////////////////////////////////////////////////////////
        // 删除数据
        Button deleteButton = (Button) findViewById(R.id.delete_data);
        deleteButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("Book", "pages > ?", new String[] { "500" });
            }
        });

        /////////////////////////////////////////////////////////////////////////////////////////////
        // 查询数据
        Button queryButton = (Button) findViewById(R.id.query_data);
        queryButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Book", null, null, null, null, null,
                        null);
                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor
                                .getColumnIndex("name"));
                        String author = cursor.getString(cursor
                                .getColumnIndex("author"));
                        int pages = cursor.getInt(cursor
                                .getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor
                                .getColumnIndex("price"));
                        Log.d("MainActivity", "book name is " + name);
                        Log.d("MainActivity", "book author is " + author);
                        Log.d("MainActivity", "book pages is " + pages);
                        Log.d("MainActivity", "book price is " + price);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        });

        /////////////////////////////////////////////////////////////////////////////////////////////
        // 事务示例
        Button replaceData = (Button) findViewById(R.id.replace_data);
        replaceData.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();

//                Android中事务的标准用法：
//                    首先调用 SQLiteDatabase的 beginTransaction()方法来开启一个事务，
//                    然后在一个异常捕获的代码块中去执行具体的数据库操作，
//                    当所有的操作都完成之后，调用 setTransactionSuccessful()表示事务已经执行成功了，
//                    最后在 finally 代码块中调用 endTransaction()来结束事务



                db.beginTransaction();  // 开启事务
                try {
                    db.delete("Book", null, null);
//					if (true) {
//						throw new NullPointerException();
//					}
                    ContentValues values = new ContentValues();
                    values.put("name", "Game of Thrones");
                    values.put("author", "George Martin");
                    values.put("pages", 720);
                    values.put("price", 20.85);
                    db.insert("Book", null, values);
                    db.setTransactionSuccessful();  // 事务已经执行成功
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    db.endTransaction();    // 结束事务
                }
            }
        });

    }
}
