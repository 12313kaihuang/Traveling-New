package com.yu.hu.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.CallSuper;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.yu.hu.common.R;
import com.yu.hu.common.util.LogUtil;


/**
 * Created by Hy on 2019/11/18 19:51
 * <p>
 * dialog基类
 * <p>
 * 有一些属性是预先设置的，如{@link #title}，
 * 没有对外暴露，由具体实现类选择是否需要对外暴露。
 *
 * @param <DF> 具体的BaseDialog实现类
 * @param <DB> Dialog对应的DataBinding
 * @see LoadingDialog
 */
@SuppressWarnings({"WeakerAccess", "unused", "unchecked"})
public abstract class BaseDialog<DF extends BaseDialog, DB extends ViewDataBinding> extends DialogFragment {

    protected Context mContext;
    protected DB mDataBinding;
    protected LayoutInflater mLayoutInflater;

    /* *****************基础属性***************** */

    protected int mWidth = WindowManager.LayoutParams.WRAP_CONTENT;

    protected int mHeight = WindowManager.LayoutParams.WRAP_CONTENT;

    protected int mGravity = Gravity.CENTER;

    @StyleRes
    protected int mTheme = R.style.BaseDialog;

    @StyleRes
    protected int mAnimation = R.style.PopStyle;

    /* *****************自定义属性***************** */
    /**
     * 点击按钮后是否自动dismiss
     */
    protected boolean autoDismiss;

    protected String title;

    @ColorInt
    protected int titleColor;


    protected String content;

    @ColorInt
    protected int contentColor;

    //右边按钮
    protected String positiveBtnText;

    @ColorInt
    protected int positiveBtnColor;

    protected BtnClickListener positiveBtnClickListener;

    //左边按钮
    protected String negativeBtnText;

    @ColorInt
    protected int negativeBtnColor;

    protected BtnClickListener negativeBtnClickListener;

    /**
     * 简化show方法
     *
     * @param fragmentManager Activity传{@link AppCompatActivity#getSupportFragmentManager()} ；
     *                        Fragment传{@link Fragment#getChildFragmentManager()}
     */
    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, null);
    }

    /**
     * 创建DataBinding
     * eg:DialogLoadingBinding.inflate(mLayoutInflater, container, false)
     */
    protected abstract DB getDataBinding(@Nullable ViewGroup container);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataBinding = getDataBinding(container);
        initView(savedInstanceState);
        return mDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * 第一步执行
     * 一些初始化操作
     *
     * @see #onCreate(Bundle)
     */
    @CallSuper
    protected void init() {

        if (getArguments() != null) {
            initArguments(getArguments());
        }

        mContext = getContext();
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    /**
     * 第二步执行
     * arguments的一些初始化操作
     *
     * @see #init()
     */
    protected void initArguments(@NonNull Bundle arguments) {

    }


    /**
     * 第三步执行
     * 初始化View
     *
     * @param savedInstanceState savedInstanceState
     * @see #onCreateView(LayoutInflater, ViewGroup, Bundle)
     */
    @CallSuper
    protected void initView(@Nullable Bundle savedInstanceState) {
        setStyle();
    }

    /**
     * 第四步执行
     * 初始化事件如点击事件等
     *
     * @see #onViewCreated(View, Bundle)
     */
    protected void initEvents() {

    }

    /**
     * 有关style的一些初始化操作
     * 如果设置背景透明等
     */
    private void setStyle() {
        //无title  自定义主题
        setStyle(DialogFragment.STYLE_NO_TITLE, mTheme);

        Dialog dialog = getDialog();
        if (dialog == null) {
            LogUtil.warn(getClass().getSimpleName(), "dialog == null");
            return;
        }

        //dialog无title
        dialog.requestWindowFeature(STYLE_NO_TITLE);

        Window window = dialog.getWindow();
        if (window == null) {
            LogUtil.warn(getClass().getSimpleName(), "dialog.window == null");
            return;
        }

        //透明背景
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent, null)));
        } else {
            window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));
        }

        //设置宽高
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = mWidth;
        attributes.height = mHeight;
        attributes.gravity = mGravity;
        //设置动画
        window.setWindowAnimations(mAnimation);
        window.setAttributes(attributes);

    }

    public DF setCancelAbility(boolean cancelable) {
        setCancelable(cancelable);
        return (DF) this;
    }

    protected DF setAutoDismiss(boolean autoDismiss) {
        this.autoDismiss = autoDismiss;
        return (DF) this;
    }

    public DF setWidth(int mWidth) {
        this.mWidth = mWidth;
        return (DF) this;
    }

    public DF setHeight(int mHeight) {
        this.mHeight = mHeight;
        return (DF) this;
    }

    public DF setGravity(int mGravity) {
        this.mGravity = mGravity;
        return (DF) this;
    }

    public DF setTheme(@StyleRes int mTheme) {
        this.mTheme = mTheme;
        return (DF) this;
    }

    public DF setAnimation(@StyleRes int mAnimation) {
        this.mAnimation = mAnimation;
        return (DF) this;
    }

    /**
     * button点击事件
     */
    public interface BtnClickListener {
        void onBtnClicked(View v);
    }
}
