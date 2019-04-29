package com.github.cyc.wanandroid.module.main.viewmodel.item;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.github.cyc.wanandroid.base.viewmodel.BaseItemViewModel;
import com.github.cyc.wanandroid.http.model.Chapter;
import com.github.cyc.wanandroid.navigator.SystemDetailsNavigator;
import com.github.cyc.wanandroid.utils.Utils;

import java.util.List;

/**
 * 体系的ViewModel
 */
public class SystemViewModel extends BaseItemViewModel<Chapter> {

    public ObservableField<String> parent = new ObservableField<>();

    public ObservableField<String> children = new ObservableField<>();

    private SystemDetailsNavigator mSystemDetailsNavigator;

    public SystemViewModel(@NonNull SystemDetailsNavigator systemDetailsNavigator) {
        mSystemDetailsNavigator = systemDetailsNavigator;
    }

    @Override
    protected void setAllModel(@NonNull Chapter chapter) {
        parent.set(chapter.getName());

        List<Chapter> childList = chapter.getChildren();
        if (!Utils.isListEmpty(childList)) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < childList.size(); i++) {
                if (i > 0) {
                    builder.append("  ");
                }
                builder.append(childList.get(i).getName());
            }

            children.set(builder.toString());
        } else {
            children.set("");
        }
    }

    public void onClickItem() {
        Chapter chapter = getBaseModel();
        if (chapter != null) {
            mSystemDetailsNavigator.startSystemDetailsActivity(chapter);
        }
    }
}
