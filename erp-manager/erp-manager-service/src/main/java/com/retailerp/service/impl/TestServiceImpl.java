package com.retailerp.service.impl;

import org.springframework.stereotype.Service;
import com.netflix.config.*;
import com.retailerp.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Override
	public void add() {
		// TODO Auto-generated method stub
		System.out.println(111222);
	}

}
