package com.txtled.ellia_r.model.db;



import com.txtled.ellia_r.bean.ColorList;

import java.util.List;

/**
 * Created by Mr.Quan on 2018/4/17.
 */

public interface DBHelper {
    void insertColor(ColorList color);

    void deleteColor(ColorList color);

    List<ColorList> getColorList();
//
//    void insertFlame(Flame flame);
//
//    void updateFlame(String type, int value);
//
//    Flame getFlame();
}
