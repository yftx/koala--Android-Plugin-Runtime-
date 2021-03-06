package android.app;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.AssetManager;

/**
 * pluginmanager的wrapper，隐藏实现
 * 
 * @author zhaoxuyang
 * 
 */
public final class PluginManager {
	/**
	 * 插件管理的实例
	 */
	private static PluginManager mInstance;
	/**
	 * 插件管理的实现
	 */
	private static PluginManagerImpl mImpl;

	/**
	 * 构造方法
	 */
	private PluginManager() {
		mImpl = PluginManagerImpl.getInstance();
	}

	/**
	 * 获取单例
	 * 
	 * @return 插件管理
	 */
	public static PluginManager getInstance() {
		if (mInstance == null) {
			mInstance = new PluginManager();
		}
		return mInstance;
	}

	/**
	 * 初始化
	 * 
	 * @param context
	 *            上下文
	 * @param dop
	 *            插件dex的存放位置
	 */
	public void init(ContextWrapper context, String dop, String pluginRootDir) {
		mImpl.init(context, dop, pluginRootDir);
	}

	/**
	 * 安装插件
	 * 
	 * @param info
	 *            插件信息
	 * @param listener
	 *            安装回掉
	 */
	public void installPlugin(PluginInfo info, InstallPluginListener listener) {
		mImpl.installPlugin(info, listener);
	}

	/**
	 * 扫描某个目录下的插件
	 * 
	 * @param dir
	 *            目录
	 * @param listener
	 *            扫描回掉
	 */
	public void scanApks(ScanPluginListener listener) {
		mImpl.scanApks(listener);
	}

	/**
	 * 启动插件
	 * 
	 * @param info
	 *            插件信息
	 */
	public void startPlugin(PluginInfo info) {
		mImpl.startPlugin(info);
	}
	
	/**
	 * 启动插件内的activity，外部调用一般用隐式启动的方式，
	 * 因为外部获取不到activity的class
	 * @param intent
	 */
	public void startPluginActivity(Intent intent) {
		mImpl.startPluginActivity(intent);
	}
	
	/**
	 * 启动插件内的service，其他同上
	 * @param intent
	 * @return
	 */
	public ComponentName startPluginService(Intent intent) {
		return mImpl.startPluginService(intent);
	}
	
	/**
	 * 停止插件内的service，其他同上
	 * @param intent
	 * @return
	 */
	public boolean stopPluginService(Intent intent) {
		return mImpl.stopPluginService(intent);
	}

	/**
	 * 卸载插件
	 * 
	 * @param activity
	 *            上下文
	 * @param name
	 *            插件的名称
	 */
	public void uninstallPlugin(Activity activity, String name) {
		mImpl.uninstallPlugin(activity, name);
	}

	/**
	 * 插件内谈toast必须使用该方法
	 * 
	 * @param context
	 *            上下文
	 * @param text
	 *            文案
	 * @param duration
	 *            时间
	 */
	public void showToast(Context context, String text, int duration) {
		mImpl.showToast(context, text, duration);
	}

	/**
	 * 插件内谈toast必须使用该方法
	 * 
	 * @param context
	 *            上下文
	 * @param resid
	 *            文案资源ID
	 * @param duration
	 *            时间
	 */
	public void showToast(Context context, int resid, int duration) {
		mImpl.showToast(context, resid, duration);
	}

	/**
	 * 销毁
	 */
	public void destory() {
		mImpl.destory();
	}
	
	/**
	 * @param apks
	 * @param asset
	 * @param listener
	 */
	public void copyApksFromAsset(final ArrayList<ApkFile> apks, final AssetManager asset, final CopyPluginListener listener){
	    mImpl.copyApksFromAsset(apks, asset, listener);
	}

}