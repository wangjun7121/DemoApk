package com.example.wangjun.demoapk.ContentDemo;

/**
 * Created by wangjun on 2017/10/11.
 */

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class CContentProvider_DatabaseProvider extends ContentProvider {

    // 定义四个个常量：
//        访问 Book 表中的所有数据、
//        访问 Book 表中的单条数据、
//        访问 Category 表中的所有数据和访问
//        Category 表中的单条数据
    public static final int BOOK_DIR = 0;
    public static final int BOOK_ITEM = 1;
    public static final int CATEGORY_DIR = 2;
    public static final int CATEGORY_ITEM = 3;



    public static final String AUTHORITY = "com.example.databasetest.provider";

    private static UriMatcher uriMatcher;

    private CContentProvider_MyDatabaseHelper dbHelper;

//    在静态代码块里对 UriMatcher 进行了初始化操作， 将期望匹
//    配的几种 URI 格式添加了进去
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        //////////////////////////////////////////////////
        // 查询 book 表中的所有数据
        uriMatcher.addURI(AUTHORITY, "book", BOOK_DIR);
        // 查询 book 表中的单条数据
        uriMatcher.addURI(AUTHORITY, "book/#", BOOK_ITEM);           // #：表示匹配任意长度的数字

        //////////////////////////////////////////////////
        // 查询 category 表中的所有数据
        uriMatcher.addURI(AUTHORITY, "category", CATEGORY_DIR);
        // 查询 category 表中的所有数据
        uriMatcher.addURI(AUTHORITY, "category/#", CATEGORY_ITEM); // #：表示匹配任意长度的数字
    }

//    1. onCreate()
//        初始化内容提供器的时候调用。通常会在这里完成对数据库的创建和升级等操作，
//        返回 true 表示内容提供器初始化成功，返回 false 则表示失败。注意，只有当存在
//        ContentResolver 尝试访问我们程序中的数据时，内容提供器才会被初始化。
    @Override
    public boolean onCreate() {
        // 创建数据库
        dbHelper = new CContentProvider_MyDatabaseHelper(getContext(), "BookStore.db", null, 2);
        return true;
    }

//    2. query()
//        从内容提供器中查询数据。 使用 uri 参数来确定查询哪张表， projection 参数用于确
//        定查询哪些列， selection 和 selectionArgs 参数用于约束查询哪些行， sortOrder 参数用于
//        对结果进行排序，查询的结果存放在 Cursor 对象中返回。
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        // 根据传入的 Uri 参数判断出用户想要访问哪张表
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                cursor = db.query("Book", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case BOOK_ITEM:
//                getPathSegments()方法， 它会将内容 URI 权限之后的部分以“/” 符号进行分割， 并把分割后
//                    的结果放入到一个字符串列表中， 那这个列表的第 0 个位置存放的就是路径， 第 1 个位置存
//                    放的就是 id 了
                String bookId = uri.getPathSegments().get(1);
                cursor = db.query("Book", projection, "id = ?", new String[] { bookId }, null, null,
                        sortOrder);
                break;
            case CATEGORY_DIR:
                cursor = db.query("Category", projection, selection, selectionArgs, null, null,
                        sortOrder);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                cursor = db.query("Category", projection, "id = ?", new String[] { categoryId }, null,
                        null, sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

//    3. insert()
//        向内容提供器中添加一条数据。使用 uri 参数来确定要添加到的表，待添加的数据
//        保存在 values 参数中。添加完成后，返回一个用于表示这条新记录的 URI。
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookId = db.insert("Book", null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/book/" + newBookId);
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                long newCategoryId = db.insert("Category", null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/category/" + newCategoryId);
                break;
            default:
                break;
        }
        return uriReturn;
    }

//    4. update()
//        更新内容提供器中已有的数据。使用 uri 参数来确定更新哪一张表中的数据，新数
//        据保存在 values 参数中， selection 和 selectionArgs 参数用于约束更新哪些行， 受影响的
//        行数将作为返回值返回。
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updatedRows = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                updatedRows = db.update("Book", values, selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                updatedRows = db.update("Book", values, "id = ?", new String[] { bookId });
                break;
            case CATEGORY_DIR:
                updatedRows = db.update("Category", values, selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                updatedRows = db.update("Category", values, "id = ?", new String[] { categoryId });
                break;
            default:
                break;
        }
        return updatedRows;
    }

//    5. delete()
//        从内容提供器中删除数据。使用 uri 参数来确定删除哪一张表中的数据， selection
//        和 selectionArgs 参数用于约束删除哪些行，被删除的行数将作为返回值返回。
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deletedRows = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                deletedRows = db.delete("Book", selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                deletedRows = db.delete("Book", "id = ?", new String[] { bookId });
                break;
            case CATEGORY_DIR:
                deletedRows = db.delete("Category", selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                deletedRows = db.delete("Category", "id = ?", new String[] { categoryId });
                break;
            default:
                break;
        }
        return deletedRows;
    }

//    6. getType()
//        根据传入的内容 URI 来返回相应的 MIME 类型。
    @Override
    public String getType(Uri uri) {

//        一个内容 URI 所对应的 MIME 字符串主要由三部分组分， Android 对这三个部分做了如下格式规定
//            1. 必须以 vnd 开头。
//            2. 如果内容 URI 以路径结尾，则后接 android.cursor.dir/，
//                如果内容 URI 以 id 结尾，则后接 android.cursor.item/。
//            3. 最后接上 vnd.<authority>.<path>。

//       【注】：com.example.databasetest.provider 是在 AndroidManifest.xml 中指定的"

        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.databasetest.provider.book";
            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.databasetest.provider.book";
            case CATEGORY_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.databasetest.provider.category";
            case CATEGORY_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.databasetest.provider.category";
        }
        return null;
    }

}
