package com.minitao.order.service;

import com.minitao.common.entity.User;
import com.minitao.order.entity.Receive;

import java.util.List;

public interface ReceiveService {

    int addReceive(Receive receive, User user);

    int deleteReceive(Receive receive);

    int updateReceive(Receive receive);

    List<Receive> getMyReceives(User user);
}

