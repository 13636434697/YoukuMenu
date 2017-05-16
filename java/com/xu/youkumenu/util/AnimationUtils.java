package com.xu.youkumenu.util;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

/*
* 因为用到很多次的动画，所以抽取出来一个类
* 这里用到了补间动画Animation
* 属性动画是Animat
* */
public class AnimationUtils {


	// 正在运行的动画个数
	public static int runningAnimationCount = 0;
	
	// 旋转出去的动画
	public static void rotateOutAnim(RelativeLayout layout, long delay) {
		//获取所有的子控件
		int childCount = layout.getChildCount();
		// 如果隐藏. 则找到所有的子View, 禁用
		for (int i = 0; i < childCount; i++) {
			layout.getChildAt(i).setEnabled(false);
		}
		
		RotateAnimation ra = new RotateAnimation(
				0f, -180f, // 开始, 结束的角度, 逆时针
				Animation.RELATIVE_TO_SELF, 0.5f,  // 相对的x坐标点(指定旋转中心x值)
				Animation.RELATIVE_TO_SELF, 1.0f); // 相对的y坐标点(指定旋转中心y值)
		
		ra.setDuration(500);
		ra.setFillAfter(true); // 设置动画停留在结束位置
		ra.setStartOffset(delay); // 设置动画开始延时
		ra.setAnimationListener(new MyAnimationListener()); // 添加监听
		
		layout.startAnimation(ra);
	}

	// 旋转进来的动画
	public static void rotateInAnim(RelativeLayout layout, long delay) {

		int childCount = layout.getChildCount();
		// 如果隐藏. 则找到所有的子View, 启用
		for (int i = 0; i < childCount; i++) {
			layout.getChildAt(i).setEnabled(true);
		}
		
		RotateAnimation ra = new RotateAnimation(
				-180f, 0f, // 开始, 结束的角度, 顺时针
				Animation.RELATIVE_TO_SELF, 0.5f,  // 相对的x坐标点(指定旋转中心x值)
				Animation.RELATIVE_TO_SELF, 1.0f); // 相对的y坐标点(指定旋转中心y值)
		
		ra.setDuration(500);
		ra.setFillAfter(true);
		ra.setStartOffset(delay); // 设置动画开始延时
		ra.setAnimationListener(new MyAnimationListener()); // 添加监听
		
		layout.startAnimation(ra);
	}

	//监听动画
	static class MyAnimationListener implements AnimationListener {

		//开始
		@Override
		public void onAnimationStart(Animation animation) {
			runningAnimationCount ++;
		}
		//结束
		@Override
		public void onAnimationEnd(Animation animation) {
			runningAnimationCount -- ;
		}
		@Override
		public void onAnimationRepeat(Animation animation) {
		}

	}

}
