package com.minitao.store.service.impl;

import com.minitao.store.entity.Store;
import com.minitao.store.entity.User;
import com.minitao.store.feign.UserFeign;
import com.minitao.store.mapper.StoreMapper;
import com.minitao.store.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author July
 * @since 2020-08-06
 */
@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private UserFeign userFeign;
    @Autowired
    private StoreMapper storeMapper;
    @Override
    public Store registerStore(Store store) {
        User user = userFeign.getCurrentUser();
        store.setId(null);
        store.setUserId(user.getId());
        store.setLikes(0);
        storeMapper.insert(store);
        return store;
    }
}
