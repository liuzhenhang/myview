package lzh.myview;

import android.app.Application;
import android.util.Log;


/**
 * Created by Administrator on 2017/7/6 0006.
 */

public class MyApplication extends Application {
    /**
     * 全局Context，方便引用
     */
    public static MyApplication appContext;




    @Override
    public void onCreate() {
        super.onCreate();

        appContext = this;
        String appVersion;
        try {
            appVersion = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (Exception e) {
            appVersion = "1.0";
        }
        // initialize最好放在attachBaseContext最前面
//        SophixManager.getInstance().setContext(this)
//                .setAppVersion(appVersion)
//                .setAesKey(null)
//                .setEnableDebug(true)
//                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
//                    @Override
//                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
//                        // 补丁加载回调通知
//                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
//                            // 表明补丁加载成功
//                            Log.d("ssss============","info"+info+"=== code"+code);
//                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
//                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
//                            Log.d("ccccccc============","info"+info+"=== code"+code);
//                            // 建议: 用户可以监听进入后台事件, 然后应用自杀
//                        } else if (code == PatchStatus.CODE_LOAD_FAIL) {
//                            // 内部引擎异常, 推荐此时清空本地补丁, 防止失败补丁重复加载
//                            // SophixManager.getInstance().cleanPatches();
//                            Log.d("============","info"+info+"=== code"+code);
//                        } else {
//                            // 其它错误信息, 查看PatchStatus类说明
//                            Log.d("============","info"+info+"=== code"+code);
//                        }
//                    }
//                }).initialize();
    }
    /***
     * 得到appContext
     *
     * @return
     */
    public static MyApplication getApplication() {
        return appContext;
    }

}
