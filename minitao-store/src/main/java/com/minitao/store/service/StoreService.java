package com.minitao.store.service;

import com.item.common.vo.SpuVo;
import com.minitao.store.entity.Store;

public interface StoreService {
    Store registerStore(Store store);

    void addSpu(SpuVo spuVo);

    int deleteSpu(Long spuId);
}
