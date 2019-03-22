package com.github.cyc.wanandroid.http.model;

import com.github.cyc.wanandroid.app.NoProguard;

import java.io.Serializable;
import java.util.List;

public class Chapter implements NoProguard, Serializable {

    private Integer courseId;
    private Integer id;
    private String name;
    private Integer order;
    private Integer parentChapterId;
    private Boolean userControlSetTop;
    private Integer visible;
    private List<Chapter> children;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getParentChapterId() {
        return parentChapterId;
    }

    public void setParentChapterId(Integer parentChapterId) {
        this.parentChapterId = parentChapterId;
    }

    public Boolean isUserControlSetTop() {
        return userControlSetTop;
    }

    public void setUserControlSetTop(Boolean userControlSetTop) {
        this.userControlSetTop = userControlSetTop;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public List<Chapter> getChildren() {
        return children;
    }

    public void setChildren(List<Chapter> children) {
        this.children = children;
    }
}
