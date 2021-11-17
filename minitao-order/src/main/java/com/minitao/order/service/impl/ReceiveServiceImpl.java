package com.minitao.order.service.impl;

import com.minitao.common.entity.User;
import com.minitao.order.entity.Receive;
import com.minitao.order.mapper.ReceiveMapper;
import com.minitao.order.service.ReceiveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: July
 * @Date: 2021-11-14 11:08
 **/
@Service
public class ReceiveServiceImpl implements ReceiveService {
    @Resource
    private ReceiveMapper receiveMapper;

    @Override
    public int addReceive(Receive receive, User user) {
        receive.setId(null);
        receive.setUserId(user.getId());
        return receiveMapper.insert(receive);
    }

    @Override
    public int deleteReceive(Receive receive) {
        return receiveMapper.deleteById(receive.getId());
    }

    @Override
    public int updateReceive(Receive receive) {
        return receiveMapper.updateById(receive);
    }

    @Override
    public List<Receive> getMyReceives(User user) {
        return receiveMapper.selectByUserId(user.getId());
    }
}
