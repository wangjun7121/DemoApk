package com.example.wangjun.demoapk.ServiceDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wangjun.demoapk.R;

public class CAsyncTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servicedemo_casynctask);


//        AsyncTask 的基本用法， 由于 AsyncTask 是一个抽象类， 所以如果我们想
//        使用它， 就必须要创建一个子类去继承它。 在继承时我们可以为 AsyncTask 类指定三个泛型
//        参数，这三个参数的用途如下：
//            1. Params
//                在执行 AsyncTask 时需要传入的参数，可用于在后台任务中使用。
//            2. Progress
//                后台任务执行时， 如果需要在界面上显示当前的进度， 则使用这里指定的泛型作为
//                进度单位。
//            3. Result
//                当任务执行完毕后， 如果需要对结果进行返回， 则使用这里指定的泛型作为返回值
//                类型。
//        因此，一个最简单的自定义 AsyncTask 就可以写成如下方式：
//            class DownloadTask extends AsyncTask<Void, Integer, Boolean> {
//                ……
//            }
//
//
//        经常需要去重写的方法有以下四个。
//            1. onPreExecute()
//                这个方法会在后台任务开始执行之前调用， 用于进行一些界面上的初始化操作， 比
//                如显示一个进度条对话框等。
//            2. doInBackground(Params...)
//                这个方法中的所有代码都会在子线程中运行， 我们应该在这里去处理所有的耗时任
//                务。 任务一旦完成就可以通过 return 语句来将任务的执行结果返回， 如果 AsyncTask 的
//                第三个泛型参数指定的是 Void， 就可以不返回任务执行结果。 注意， 在这个方法中是不
//                可以进行 UI 操作的， 如果需要更新 UI 元素， 比如说反馈当前任务的执行进度， 可以调
//                用 publishProgress(Progress...)方法来完成。
//            3. onProgressUpdate(Progress...)
//                当在后台任务中调用了 publishProgress(Progress...)方法后，这个方法就会很快被调
//                用，方法中携带的参数就是在后台任务中传递过来的。在这个方法中可以对 UI 进行操
//                作，利用参数中的数值就可以对界面元素进行相应地更新。
//            4. onPostExecute(Result)
//                当后台任务执行完毕并通过 return 语句进行返回时， 这个方法就很快会被调用。 返
//                回的数据会作为参数传递到此方法中，可以利用返回的数据来进行一些 UI 操作，比如
//                说提醒任务执行的结果，以及关闭掉进度条对话框等。
//
//
//        因此，一个比较完整的自定义 AsyncTask 就可以写成如下方式：
//            class DownloadTask extends AsyncTask<Void, Integer, Boolean> {
//                @Override
//                protected void onPreExecute() {
//                    progressDialog.show(); // 显示进度对话框
//                }
//                @Override
//                protected Boolean doInBackground(Void... params) {
//                    try {
//                        while (true) {
//                            int downloadPercent = doDownload(); // 这是一个虚构的方法
//                            publishProgress(downloadPercent); // 进行进度更新
//                            if (downloadPercent >= 100) {
//                                break;
//                            }
//                        }
//                    } catch (Exception e) {
//                        return false;
//                    }
//                    return true;
//                }
//                @Override
//                protected void onProgressUpdate(Integer... values) {
//                // 在这里更新下载进度
//                    progressDialog.setMessage("Downloaded " + values[0] + "%");
//                }
//                @Override
//                protected void onPostExecute(Boolean result) {
//                    progressDialog.dismiss(); // 关闭进度对话框
//                // 在这里提示下载结果
//                    if (result) {
//                        Toast.makeText(context, "Download succeeded",
//                                Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(context, " Download failed",
//                                Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        简单来说，使用 AsyncTask 的诀窍就是，在 doInBackground()方法中去执行具体的耗时
//        任务，在 onProgressUpdate()方法中进行 UI 操作，在 onPostExecute()方法中执行一些任务的收尾工作
//
//        如果想要启动这个任务，只需编写以下代码即可：
//            new DownloadTask().execute();
    }
}
