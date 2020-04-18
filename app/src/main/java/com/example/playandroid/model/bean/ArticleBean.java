package com.example.playandroid.model.bean;

public class ArticleBean {

    /**
     * apkLink :
     * audit : 1
     * author :
     * canEdit : false
     * chapterId : 502
     * chapterName : 自助
     * collect : false
     * courseId : 13
     * desc :
     * descMd :
     * envelopePic :
     * fresh : true
     * id : 12722
     * link : https://juejin.im/post/5e85fe4e6fb9a03c6f66eef9#heading-3
     * niceDate : 11小时前
     * niceShareDate : 11小时前
     * origin :
     * prefix :
     * projectLink :
     * publishTime : 1585840513000
     * selfVisible : 0
     * shareDate : 1585840513000
     * shareUser : xll
     * superChapterId : 494
     * superChapterName : 广场Tab
     * tags : []
     * title : 20分钟，我们一起实现爱奇艺式焦点寻找
     * type : 0
     * userId : 22903
     * visible : 1
     * zan : 0
     */

    private String author;
    private int chapterId;
    private String chapterName;
    private boolean collect;
    private int id;
    private String link;
    private String niceDate;
    private String niceShareDate;
    private long shareDate;
    private String shareUser;
    private int superChapterId;
    private String superChapterName;
    private String title;
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
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

    public String getNiceDate() {
        return niceDate;
    }

    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }

    public String getNiceShareDate() {
        return niceShareDate;
    }

    public void setNiceShareDate(String niceShareDate) {
        this.niceShareDate = niceShareDate;
    }

    public long getShareDate() {
        return shareDate;
    }

    public void setShareDate(long shareDate) {
        this.shareDate = shareDate;
    }

    public String getShareUser() {
        return shareUser;
    }

    public void setShareUser(String shareUser) {
        this.shareUser = shareUser;
    }

    public int getSuperChapterId() {
        return superChapterId;
    }

    public void setSuperChapterId(int superChapterId) {
        this.superChapterId = superChapterId;
    }

    public String getSuperChapterName() {
        return superChapterName;
    }

    public void setSuperChapterName(String superChapterName) {
        this.superChapterName = superChapterName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
