package com.admin.study.admin.service;

import com.admin.study.admin.ifs.CrudInterface;
import com.admin.study.admin.model.entity.Item;
import com.admin.study.admin.model.network.Header;
import com.admin.study.admin.model.network.request.ItemApiRequest;
import com.admin.study.admin.model.network.response.ItemApiResponse;
import com.admin.study.admin.repository.ItemRepository;
import com.admin.study.admin.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ItemApiLogicService implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {
        ItemApiRequest body = request.getData();

        Item item = Item.builder()
                .status(body.getStatus())
                .name(body.getName())
                .title(body.getTitle())
                .content(body.getContent())
                .price(body.getPrice())
                .brandName(body.getBrandName())
                .registeredAt(body.getRegisteredAt())
                .partner(partnerRepository.getOne(body.getPartnerId()))
                .build();

        Item newItem = itemRepository.save(item);
        return response(newItem);
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {
       return itemRepository.findById(id)
                .map(item ->response(item))
                .orElseGet(()->{
                    return Header.Error("NO DATA");
                });
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        ItemApiRequest body = request.getData();

        return itemRepository.findById(body.getId())
                .map(entityItem ->{
                    entityItem.setStatus(body.getStatus())
                            .setName(body.getName())
                            .setTitle(body.getTitle())
                            .setContent(body.getContent())
                            .setPrice(body.getPrice())
                            .setBrandName(body.getBrandName())
                            .setRegisteredAt(body.getRegisteredAt())
                            .setUnregisteredAt(body.getUnregisteredAt());
                    return entityItem;
                })
                .map(newEntityItem -> {
                    itemRepository.save(newEntityItem);
                    return newEntityItem;
                })
                .map(item->response(item))
                .orElseGet(()->Header.Error("DO ITEM"));
    }

    @Override
    public Header delete(Long id) {
        return itemRepository.findById(id)
                .map(item ->{
                    itemRepository.delete(item);
                    return Header.OK();
                })
                .orElseGet(()->Header.Error("NO DATA"));
    }

    public Header<ItemApiResponse> response(Item item){
        ItemApiResponse body = ItemApiResponse.builder()
                .id(item.getId())
                .status(item.getStatus())
                .name(item.getName())
                .title(item.getTitle())
                .content(item.getContent())
                .price(item.getPrice())
                .brandName(item.getBrandName())
                .registeredAt(item.getRegisteredAt())
                .unregisteredAt(item.getUnregisteredAt())
                .partnerId(item.getPartner().getId())
                .build();

        return Header.OK(body);
    }
}
