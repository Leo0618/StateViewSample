# StateView #
几种状态视图显示工具，包含如下视图控制:


- 正常数据视图
- 加载中视图
- 无数据空视图
- 无网络错误视图
- 加载错误视图

可以通过状态视图创建器设置统一视图，也可以针对不同页面设置相应的对应状态视图，后者优先级高于使用创建器设置的视图，对于无数据空视图、加载错误视图可进行点击视图进行重加载操作，设置点击事件监听即可，对于无网络错误视图，点击视图会跳转到设置界面便于用户进行网络切换操作。

下载Demo体验：[app-debug.apk](https://raw.githubusercontent.com/Leo0618/StateViewSample/master/app-debug.apk)

效果图如下：

![效果图](https://i.imgur.com/91rtziu.gif)

----------

# How to Use #


1. 添加依赖或者下载库include

		compile 'com.leo618:StateView:0.0.2'

		

2. (可选) 建议在Application的静态代码块中设置视图创建管理器，此处设置的视图将作为全局视图，亦可在代码中调用方法进行设置当前页面所需的视图，不设置使用默认。

		public class MyApplication extends Application {

		    static {
		        StateViewHelper.setStateViewCreator(new StateViewCreator() {
		            @Override
		            public View createStateViewLoading(Context context) {
		                return super.createStateViewLoading(context);
		            }
		
		            @Override
		            public View createStateViewEmpty(Context context) {
		                return super.createStateViewEmpty(context);
		            }
		
		            @Override
		            public View createStateViewNetError(Context context) {
		                return super.createStateViewNetError(context);
		            }
		
		            @Override
		            public View createStateViewError(Context context) {
		                return super.createStateViewError(context);
		            }
		        });
		    }
		}

3. 在需要使用的页面对应创建Helper实例并调用相应方法，demo可查看[StateActivity](https://github.com/Leo0618/StateViewSample/blob/master/app/src/main/java/com/leo618/stateviewsample/StateActivity.java)

		switch (item.getItemId()) {
            case R.id.stateNormal://显示数据视图
                mStateViewHelper.stateNormal();
                break;
            case R.id.stateLoading://显示加载中视图
                mStateViewHelper.stateLoading();
                break;
            case R.id.stateEmpty://显示空数据视图
                mStateViewHelper.stateEmpty();
                break;
            case R.id.stateNetError://显示网络错误视图
                mStateViewHelper.stateNetError();
                break;
            case R.id.stateError://显示加载错误视图
                mStateViewHelper.stateError();
                break;
        }

4. 混淆建议

		-dontwarn  com.leo618.stateview.**
		-keep class com.leo618.stateview.**{*;}
		-keepclasseswithmembers class * {
	    	public <init>(android.content.Context);
		}
