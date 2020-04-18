package com.example.playandroid.model.bean;

/**
 * @author 徐国林
 * @data 2020/4/6
 * @decription
 */
public class NetBean {
    /**
     * icon :
     * id : 33
     * link : https://developer.android.com/studio/build/gradle-plugin-3-0-0-migration?hl=zh-cn
     * name : AS 指南
     * order : 100
     * visible : 1
     */

    private String icon;
    private int id;
    private String link;
    private String name;
    private int order;
    private int visible;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }
}
