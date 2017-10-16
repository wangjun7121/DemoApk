package com.example.wangjun.demoapk.ContentDemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wangjun.demoapk.ActivityDemo.ActivityDemoActivity;
import com.example.wangjun.demoapk.ActivityDemo.CActivityLaunchModeActivity;
import com.example.wangjun.demoapk.ActivityDemo.CActivityLifeCycleActivity;
import com.example.wangjun.demoapk.ListViewDemo.CSimpleCursorAdapter;
import com.example.wangjun.demoapk.R;

public class ContentDemoActivity extends AppCompatActivity {
    private static String sLogcatTAG = "ContentDemoActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contentdemo);

////////////////////////////////////////////////////////////////////////////////////////////////////////////
// 访问其他程序的内容提供器：
//        Content 操作流程：
//            内容 URI 最标准的格式写法如下：
//                content://com.example.app.provider/table1
//                content://com.example.app.provider/table2
//
//                    content://路径.权限/表名[/id]
//                        id: 通配符
//                                *：表示匹配任意长度的任意字符
//                                #：表示匹配任意长度的数字
//
//        查询数据步骤：
//            1. 解析 URI:
//                Uri uri = Uri.parse("content://com.example.app.provider/table1")
//
//            2.查询数据
//                Cursor cursor = getContentResolver().query(
//                        uri,
//                        projection,
//                        selection,
//                        selectionArgs,
//                        sortOrder);
//
//                query()方法参数                         对应 SQL 部分                       描述
//                uri                                     from table_name                     指定查询某个应用程序下的某一张表
//                projection                              select column1, column2             指定查询的列名
//                selection                               where column = value                指定 where 的约束条件
//                selectionArgs                           -                                   为 where 中的占位符提供具体的值
//                orderBy                                 order by column1, column2           指定查询结果的排序方式
//
//            3.通过 Cursor 对象遍历：
//                if (cursor != null) {
//                    while (cursor.moveToNext()) {
//                        String column1 = cursor.getString(cursor.getColumnIndex("column1"));
//                        int column2 = cursor.getInt(cursor.getColumnIndex("column2"));
//                    }
//                    cursor.close();
//                }
//
//             4. 参数 ContentValues 缓存数据，然后进行增加数据
//                ContentValues values = new ContentValues();
//                values.put("column1", "text");
//                values.put("column2", 1);
//                getContentResolver().insert(uri, values);
//
//            5. 进行修改操作
//                现 在 如果 我 们 想 要更 新 这 条 新 添加 的 数 据 ，把 column1 的 值 清空 ， 可 以 借 助
//                ContentResolver 的 update()方法实现，代码如下所示：
//                    ContentValues values = new ContentValues();
//                    values.put("column1", "");
//                    getContentResolver().update(uri, values, "column1 = ? and column2 = ?", new
//                            String[] {"text", "1"});
//
//            6. 进行删除操作：
//                最后，可以调用 ContentResolver 的 delete()方法将这条数据删除掉，代码如下所示：
//                    getContentResolver().delete(uri, "column2 = ?", new String[] { "1" });

        Button readContactBtn;
        readContactBtn = (Button) findViewById(R.id.readContactBtn);
        readContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(ContentDemoActivity.this, CSimpleCursorAdapter.class);
                startActivity(intent);
            }
        });


////////////////////////////////////////////////////////////////////////////////////////////////////////////
// 创建内容提供器：
//        1. 继承 ContentProvider 类
//        2. 重写抽象方法：
//            onCreate(): 初始化内容提供器调用，通常用作数据库创建与升级
//            query(): 从内容提供器中查询数据
//            insert(): 向内容提供器添加一条数据
//            update(): 更新内容提供器中已有的数据
//            delete(): 从内容提供器中删除数据
//            getType(): 根据传入的内容 URI 来返回相应的 MIME 类型
//                getType(): 用于获取 Uri 对象所对应的 MIME 类型
//                     MIME：多功能 Internet 邮件扩展协议
//                     MIME 格式：
//                        1. 必须以 vnd 开头
//                        2. 内容 URI 结尾分两种：
//                            路径结尾：后接 android.cursor.dir/
//                            id 结尾：接不接 android.cursor.item/
//                        3. 最后接上 vnd.<authority>.<path>
//        3. 通过 UriMatcher 来实现通配符匹配

        // 此程序作用：
//            1. 创建了一个数据库操作类
//            2. 创建了一个 ContentProvider ，里面操作数据库
//            3. 通过 uri 访问数据库本地数据库
        Button testContentProviderBtn;
        testContentProviderBtn = (Button) findViewById(R.id.testContentProviderBtn);
        testContentProviderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(ContentDemoActivity.this, CContentProviderActivity.class);
                startActivity(intent);
            }
        });

        // 访问内容提供器：
//            通过 uri 访问程序 2 创建的 ContentProvider
        Button accessContentProviderBtn;
        accessContentProviderBtn = (Button) findViewById(R.id.accessContentProviderBtn);
        accessContentProviderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(sLogcatTAG, "testArrayAdapterBtn");
                Intent intent = new Intent(ContentDemoActivity.this, CAccessContentProviderActivity.class);
                startActivity(intent);
            }
        });
    }
}
