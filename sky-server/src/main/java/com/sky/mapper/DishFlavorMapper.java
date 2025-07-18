package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    /*
    批量插入口味数据
     */
    public void insertBatch(List<DishFlavor> flavors);

    void deleteByDishId(Long id);
}
