package com.qinyuan15.crawler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Base class of controller
 * Created by qinyuan on 15-2-22.
 */
@Component
public class BaseController {
    @Autowired
    protected HttpServletRequest request;
}
