package com.libin.bigdata.business;

import com.libin.bigdata.mapper.BigDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (c) 2021/11/27. libin Inc. All Rights Reserved.
 * Authors: libin <libin>
 * <p>
 * Purpose :
 */
@Service
public class BigDataBusiness {
	@Autowired
	private BigDataMapper bigDataMapper;

}
