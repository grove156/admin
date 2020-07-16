package com.admin.study.admin.model.network.response;

import com.admin.study.admin.model.entity.OrderGroup;
import com.admin.study.admin.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserOrderInfoResponse {

    private UserApiResponse userApiResponse;

    private List<OrderGroupApiResponse> orderGroupApiResponsesList;

    private List<ItemApiResponse> itemApiResponseList;


}
