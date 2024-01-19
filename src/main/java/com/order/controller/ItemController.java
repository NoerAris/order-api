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

@RequestMapping("/api/item")
@RestController
public class ItemController extends BaseController {
    private final Logger LOG = LoggerFactory.getLogger(ItemController.class);

    @GetMapping("")
    public Item findOne(@RequestParam(name = "id") Long id) {
        return itemService.findOne(id);
    }

    @GetMapping("/all")
    public List<Item> findAll(Pageable pageable) {
        return itemService.findAll(pageable);
    }

    @PostMapping("/save")
    public ResponseState save (@Valid @RequestBody Item item) {
        ResponseState responseState = new ResponseState();
        try {
            itemService.save(item);

            responseState.setState(CommonConstant.RESPONSE_STATUS.OK.getValue());
            responseState.setMessage(CommonConstant.RESPONSE_MSG.OK.getValue());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            responseState.setState(CommonConstant.RESPONSE_STATUS.FAILED.getValue());
            responseState.setMessage(CommonConstant.RESPONSE_MSG.FAILED.getValue());
        }

        return responseState;
    }

    @PostMapping("/update")
    public ResponseState update (@Valid @RequestBody Item item) {
        ResponseState responseState = new ResponseState();
        try {
            Item rs = itemService.findOne(item.getId());
            if (rs != null) {
                rs.setName(item.getName());
                rs.setPrice(item.getPrice());
                itemService.save(rs);
            } else {
                itemService.save(item);
            }


            responseState.setState(CommonConstant.RESPONSE_STATUS.OK.getValue());
            responseState.setMessage(CommonConstant.RESPONSE_MSG.OK.getValue());
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
            Item item = itemService.findOne(id);
            if (item != null) {
                itemService.delete(item);
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
