package com.txtled.ellia_r.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by Mr.Quan on 2018/9/21.
 */

@Entity
public class ColorList implements Serializable {
    static final long serialVersionUID = 42L;

    @Id
    private Long id;
    private int color;

    public ColorList() {
    }

    @Generated(hash = 785707412)
    public ColorList(Long id, int color) {
        this.id = id;
        this.color = color;
    }

    public ColorList(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
