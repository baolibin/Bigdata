package com.libin.bigdata.controller;

import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (c) 2021/11/27. libin Inc. All Rights Reserved.
 * Authors: libin <libin>
 * <p>
 * Purpose :
 */
@RestController
@RequestMapping(value = "/api/v1")
public class BigDataController {

	@RequestMapping("/hello")
	public String hello() {
		JsonObject json = new JsonObject();
		json.addProperty("key","value");
		return json.toString();
	}
}
