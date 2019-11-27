package com.yu.hu.common.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.yu.hu.common.R;
import com.yu.hu.common.dialog.LoadingDialog;

/**
 * create by hy on 2019/11/27 22:52
 * <p>
 * 方法：
 *
 * @see D {@link #getLayoutId()}资源文件对应的DataBinding类型
 * @see #getLayoutId()  必须重写 资源id
 * @see #getLoadingDialog() 可以重写 返回已自定义loadingDialog样式
 * <p>
 * 属性：
 * @see #mContext  == this
 * @see #mDataBinding mDataBinding
 * @see #mLoadingDialog mLoadingDialog
 */
@SuppressWarnings("unused")
public abstract class BaseActivity<D extends ViewDataBinding> extends AppCompatActivity {

    protected Context mContext;
    protected D mDataBinding;
    protected LoadingDialog mLoadingDialog;

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        mDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
    }

    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * showLoadingDialog
     *
     * @see #mLoadingDialog
     */
    public void showLoadingDialog() {
        showLoadingDialog(null);
    }

    /**
     * showLoadingDialog
     * 指定content，注意指定过后之后显示的content都是设置的content
     *
     * @param content content
     */
    public void showLoadingDialog(String content) {
        if (mLoadingDialog == null) {
            mLoadingDialog = getLoadingDialog();
        }
        if (!TextUtils.isEmpty(content)) {
            mLoadingDialog.setContent(content);
        }
        mLoadingDialog.show(getSupportFragmentManager());
    }

    /**
     * hideLoadingDialog
     */
    public void hideLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isVisible()) {
            mLoadingDialog.dismiss();
        }
    }

    /**
     * 可以自定义dialog样式
     *
     * @return LoadingDialog
     * @see LoadingDialog
     */
    protected LoadingDialog getLoadingDialog() {
        return LoadingDialog.newInstance()
                .setContent(R.string.loading)
                .setContentColorResource(R.color.colorPrimary)
                .setProgressBarColorResource(R.color.colorPrimary);
    }
}
