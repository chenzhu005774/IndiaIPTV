package com.amt.indiaiptv.utils.annima;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import java.util.ArrayList;
import java.util.List;

/**
 *3D 旋转动画视图
 * 主要用于 3D 旋转
 * https://blog.csdn.net/zhaihaohao1/article/details/78584478
 **/
public class Rotate3dAnimation extends Animation {
 private final float mFromDegrees; 
 private final float mToDegrees; 
 private final float mCenterX; 
 private final float mCenterY; 
 private final float mDepthZ; 
 private final boolean mReverse; 
 private Camera mCamera;
 /** 
 * Creates a new 3D rotation on the Y axis. The rotation is defined by its 
 * start angle and its end angle. Both angles are in degrees. The rotation 
 * is performed around a center point on the 2D space, definied by a pair 
 * of X and Y coordinates, called centerX and centerY. When the animation 
 * starts, a translation on the Z axis (depth) is performed. The length 
 * of the translation can be specified, as well as whether the translation 
 * should be reversed in time. 
 * 
 * @param fromDegrees the start angle of the 3D rotation 
 * @param toDegrees the end angle of the 3D rotation 
 * @param centerX the X center of the 3D rotation 
 * @param centerY the Y center of the 3D rotation 
 * @param reverse true if the translation should be reversed, false otherwise 
 */
 public Rotate3dAnimation(float fromDegrees, float toDegrees, 
 float centerX, float centerY, float depthZ, boolean reverse) { 
 mFromDegrees = fromDegrees; 
 mToDegrees = toDegrees; 
 mCenterX = centerX; 
 mCenterY = centerY; 
 mDepthZ = depthZ; 
 mReverse = reverse; 
 }




 @Override
 public void initialize(int width, int height, int parentWidth, int parentHeight) { 
 super.initialize(width, height, parentWidth, parentHeight); 
 mCamera = new Camera(); 
 } 
 @Override
 protected void applyTransformation(float interpolatedTime, Transformation t) {
 final float fromDegrees = mFromDegrees; 
 float degrees = fromDegrees + ((mToDegrees - fromDegrees) * interpolatedTime); 
 final float centerX = mCenterX; 
 final float centerY = mCenterY; 
 final Camera camera = mCamera; 
 final Matrix matrix = t.getMatrix();
 //保存一次camera初始状态，用于restore() 
 camera.save(); 
 if (mReverse) { 
 camera.translate(0.0f, 0.0f, mDepthZ * interpolatedTime); 
 } else { 
 camera.translate(0.0f, 0.0f, mDepthZ * (1.0f - interpolatedTime)); 
 } 
 //围绕Y轴旋转degrees度 
 camera.rotateY(degrees); 
 //行camera中取出矩阵，赋值给matrix 
 camera.getMatrix(matrix); 
 //camera恢复到初始状态，继续用于下次的计算 
 camera.restore(); 
 matrix.preTranslate(-centerX, -centerY); 
 matrix.postTranslate(centerX, centerY); 
 }
//浮动
 public void floatAnim(View view, int delay){
  List<Animator> animators = new ArrayList<>();
  ObjectAnimator translationXAnim = ObjectAnimator.ofFloat(view, "translationX", -6.0f,6.0f,-6.0f);
  translationXAnim.setDuration(1500);
  translationXAnim.setRepeatCount(ValueAnimator.INFINITE);//无限循环
  translationXAnim.start();
  animators.add(translationXAnim);
  ObjectAnimator translationYAnim = ObjectAnimator.ofFloat(view, "translationY",   -3.0f,3.0f,-3.0f);
  translationYAnim.setDuration(1500);
  translationYAnim.setRepeatCount(ValueAnimator.INFINITE);
  translationYAnim.start();
  animators.add(translationYAnim);

  AnimatorSet btnSexAnimatorSet = new AnimatorSet();
  btnSexAnimatorSet.playTogether(animators);
  btnSexAnimatorSet.setStartDelay(delay);
  btnSexAnimatorSet.start();
 }


 /**
  * 属性动画
  * 缩放
  */
 public void setAnimateScale(View view,int time){
  // 将一个TextView沿垂直方向先从原大小（1f）放大到5倍大小（5f），然后再变回原大小。
  ObjectAnimator anim = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.4f, 1f);
  anim.setRepeatCount(ValueAnimator.INFINITE);//无限循环
  ObjectAnimator anim1 = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.4f, 1f);
  anim1.setRepeatCount(ValueAnimator.INFINITE);//无限循环
  AnimatorSet animatorSet = new AnimatorSet();
  animatorSet.play(anim).with(anim1);
  animatorSet.setDuration(time);

  animatorSet.start();

 }

 /**
  * 属性动画
  * 缩放
  */
 public void setAnimateScale(View view,int time,float num){
  // 将一个TextView沿垂直方向先从原大小（1f）放大到5倍大小（5f），然后再变回原大小。
  ObjectAnimator anim = ObjectAnimator.ofFloat(view, "scaleY", num, num, num);
  anim.setRepeatCount(1);//无限循环
  ObjectAnimator anim1 = ObjectAnimator.ofFloat(view, "scaleX", num, num, num);
  anim1.setRepeatCount(1);//无限循环
  AnimatorSet animatorSet = new AnimatorSet();
  animatorSet.play(anim).with(anim1);
  animatorSet.setDuration(time);

  animatorSet.start();

 }



}