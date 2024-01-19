package com.order.controller;

import com.order.constant.CommonConstant;
import com.order.entity.Item;
import com.order.entity.TrsOrder;
import com.order.model.ResponseState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/order")
@RestController
public class OrderController extends BaseController {
    private final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    @GetMapping("")
    public TrsOrder findOne(@RequestParam(name = "id") Long id) {
        return orderService.findOne(id);
    }

    @GetMapping("/all")
    public List<TrsOrder> findAll(Pageable pageable) {
        return orderService.findAll(pageable);
    }

    @PostMapping("/save")
    public ResponseState save (@Valid @RequestBody TrsOrder trsOrder) {
        ResponseState responseState = new ResponseState();
        try {
            Item item = itemService.findOne(trsOrder.getIdItem());
            if (item != null) {
                trsOrder.setItem(item);
                orderService.save(trsOrder);

                responseState.setState(CommonConstant.RESPONSE_STATUS.OK.getValue());
                responseState.setMessage(CommonConstant.RESPONSE_MSG.OK.getValue());
            } else {
                responseState.setMessage("Data item not found");
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            responseState.setState(CommonConstant.RESPONSE_STATUS.FAILED.getValue());
            responseState.setMessage(CommonConstant.RESPONSE_MSG.FAILED.getValue());
        }

        return responseState;
    }

    @PostMapping("/update")
    public ResponseState update(@Valid @RequestBody TrsOrder trsOrder) {
        ResponseState responseState = new ResponseState();
        try {
            TrsOrder order = orderService.findOne(trsOrder.getId());
            if (order != null) {
                Item item = itemService.findOne(trsOrder.getIdItem());
                if (item != null) {
                    trsOrder.setItem(item);
                    orderService.save(trsOrder);

                    responseState.setState(CommonConstant.RESPONSE_STATUS.OK.getValue());
                    responseState.setMessage(CommonConstant.RESPONSE_MSG.OK.getValue());
                }

                responseState.setMessage("Data item not found");
            } else {
                orderService.save(trsOrder);

                responseState.setState(CommonConstant.RESPONSE_STATUS.OK.getValue());
                responseState.setMessage(CommonConstant.RESPONSE_MSG.OK.getValue());
            }

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            responseState.setState(CommonConstant.RESPONSE_STATUS.FAILED.getValue());
            responseState.setMessage(CommonConstant.RESPONSE_MSG.FAILED.getValue());
        }

        return responseState;
    }

    @DeleteMapping("/delete")
    public ResponseState delete(@RequestParam(name = "id") Long id) {
        ResponseState responseState = new ResponseState();
        try {
            TrsOrder order = orderService.findOne(id);
            if (order != null) {
                orderService.delete(order);

                responseState.setMessage(CommonConstant.RESPONSE_MSG.OK.getValue());
            } else {
                responseState.setMessage("Data not found");
            }

            responseState.setState(CommonConstant.RESPONSE_STATUS.OK.getValue());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            responseState.setState(CommonConstant.RESPONSE_STATUS.FAILED.getValue());
            responseState.setMessage(CommonConstant.RESPONSE_MSG.FAILED.getValue());
        }

        return responseState;
    }
}
