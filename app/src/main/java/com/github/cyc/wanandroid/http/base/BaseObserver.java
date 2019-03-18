package com.github.cyc.wanandroid.http.base;

import android.databinding.ObservableField;
import android.support.annotation.Nullable;

import com.github.cyc.wanandroid.R;
import com.github.cyc.wanandroid.enums.LoadState;
import com.github.cyc.wanandroid.http.HttpCode;
import com.github.cyc.wanandroid.http.model.Response;
import com.github.cyc.wanandroid.utils.ToastUtils;
import com.google.gson.JsonParseException;
import com.orhanobut.logger.Logger;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.observers.ResourceObserver;
import retrofit2.HttpException;

/**
 * Observer的基类
 *
 * @param <T> 具体的网络请求响应model
 */
public abstract class BaseObserver<T> extends ResourceObserver<Response<T>> implements ObserverX<T> {

    private ObservableField<LoadState> mLoadState;

    private boolean mShowError;

    public BaseObserver() {

    }

    public BaseObserver(@Nullable ObservableField<LoadState> loadState) {
        this(loadState, true);
    }

    public BaseObserver(@Nullable ObservableField<LoadState> loadState, boolean showError) {
        mLoadState = loadState;
        mShowError = showError;
    }

    @Override
    public final void onNext(Response<T> response) {
        int errorCode = response.getErrorCode();
        if (errorCode == HttpCode.SUCCESS) {
            // 服务端返回成功
            onNextX(response.getData());
        } else {
            String errorMsg = response.getErrorMsg();
            ToastUtils.show(errorMsg);
            if (mLoadState != null && mShowError) {
                mLoadState.set(LoadState.ERROR);
            }

            // 公共错误逻辑处理
            switch (errorCode) {
                case HttpCode.ERROR_LOGIN_INVALID:
                    // TODO: 2019/3/12  
                    break;

                default:
                    break;
            }

            Logger.i("errorCode: %d, errorMsg: %s", errorCode, errorMsg);

            // 服务端返回错误
            onErrorX(errorCode, errorMsg);
        }
    }

    @Override
    public final void onError(Throwable e) {
        int errorCode = HttpCode.ERROR_UNKNOWN;
        String errorMsg = e.getMessage();

        // 公共错误逻辑处理
        if (e instanceof HttpException) {
            errorCode = HttpCode.ERROR_HTTP;
            ToastUtils.show(R.string.error_http);
            if (mLoadState != null && mShowError) {
                mLoadState.set(LoadState.ERROR);
            }
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
            errorCode = HttpCode.ERROR_NETWORK;
            ToastUtils.show(R.string.error_network);
            if (mLoadState != null && mShowError) {
                mLoadState.set(LoadState.NO_NETWORK);
            }
        } else if (e instanceof JsonParseException || e instanceof JSONException) {
            errorCode = HttpCode.ERROR_PARSE;
            ToastUtils.show(R.string.error_parse);
            if (mLoadState != null && mShowError) {
                mLoadState.set(LoadState.ERROR);
            }
        } else if (e instanceof SSLHandshakeException) {
            errorCode = HttpCode.ERROR_SSL;
            ToastUtils.show(R.string.error_ssl);
            if (mLoadState != null && mShowError) {
                mLoadState.set(LoadState.ERROR);
            }
        } else {
            ToastUtils.show(R.string.error_unknown);
            if (mLoadState != null && mShowError) {
                mLoadState.set(LoadState.ERROR);
            }
        }

        Logger.i("errorCode: %d, errorMsg: %s", errorCode, errorMsg);

        onErrorX(errorCode, errorMsg);
    }

    @Override
    public final void onComplete() {
        onCompleteX();
    }

    @Override
    public void onErrorX(int errorCode, String errorMsg) {

    }

    @Override
    public void onCompleteX() {

    }
}
