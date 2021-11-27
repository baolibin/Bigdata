package com.libin.bigdata.mapper;

import com.libin.bigdata.entity.BigDataEntity;

import java.util.List;

/**
 * Copyright (c) 2021/11/27. libin Inc. All Rights Reserved.
 * Authors: libin <libin>
 * <p>
 * Purpose :
 */
public interface BigDataMapper {
	List<BigDataEntity> queryBigDataList();
}
