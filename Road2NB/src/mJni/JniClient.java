package mJni;

/**
 * 声明两个本地方法名
 * 命令行中  javah JiClient.class 得到这两个native方法函数命声明.h文件 并手动放入工程目录的jni文件夹
 * c/c++中对应方法名 Java_包名_类名_方法名
 * @author 21427754
 *
 */
public class JniClient {
	
	public static native String addStr(String str1, String str2);
	public static native int addInt(int a, int b);

}
