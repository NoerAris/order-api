package com.order.controller;

import com.order.service.ItemService;
import com.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
    @Autowired
    protected ItemService itemService;

    @Autowired
    protected OrderService orderService;
}
