package com.libin.bigdata.mapper;

import com.libin.bigdata.entity.BigDataEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Copyright (c) 2021/11/27. libin Inc. All Rights Reserved.
 * Authors: libin <libin>
 * <p>
 * Purpose :
 */
@Mapper
public interface BigDataMapper {
	List<BigDataEntity> queryBigDataList();
}
