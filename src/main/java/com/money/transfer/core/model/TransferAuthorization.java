package com.money.transfer.core.model;

import com.money.transfer.core.model.enums.AuthorizationStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransferAuthorization {

    private AuthorizationStatusEnum status;
}
