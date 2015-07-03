package com.gmail.at.irotech.ehcache.beans.vos;

import java.util.Date;
import java.util.List;

public class CacheVO {

    private long id;
    private Date date;
    private String string;
    private List<String> list;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CacheVO cacheVO = (CacheVO) o;

        if (id != cacheVO.id) return false;
        if (date != null ? !date.equals(cacheVO.date) : cacheVO.date != null) return false;
        if (list != null ? !list.equals(cacheVO.list) : cacheVO.list != null) return false;
        if (string != null ? !string.equals(cacheVO.string) : cacheVO.string != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (string != null ? string.hashCode() : 0);
        result = 31 * result + (list != null ? list.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CacheVO{" +
                "id=" + id +
                ", date=" + date +
                ", string='" + string + '\'' +
                ", list=" + list +
                '}';
    }

}
