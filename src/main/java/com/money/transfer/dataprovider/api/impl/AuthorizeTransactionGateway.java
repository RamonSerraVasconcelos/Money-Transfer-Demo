package com.money.transfer.dataprovider.api.impl;

import com.money.transfer.core.model.TransferAuthorization;
import com.money.transfer.core.model.enums.AuthorizationStatusEnum;
import com.money.transfer.core.usecase.boundary.AuthorizeTransactionBoundary;
import com.money.transfer.dataprovider.api.dto.TransferAuthRequestDto;
import com.money.transfer.dataprovider.api.dto.TransferAuthResponseDto;
import com.money.transfer.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthorizeTransactionGateway implements AuthorizeTransactionBoundary {

    private final RestTemplate restTemplate;

    @Value("${properties.authorization-service-url}")
    private String authorizationHost;

    public TransferAuthorization authorizeTransaction(String userCpf) {
        TransferAuthRequestDto transferAuthRequestDto = TransferAuthRequestDto.builder()
                .accountOwnerCpf(userCpf)
                .build();

        ResponseEntity<TransferAuthResponseDto> transferAuthResponse = restTemplate
                .postForEntity(authorizationHost + "/transfer/authorize", transferAuthRequestDto, TransferAuthResponseDto.class);

        if(transferAuthResponse.getStatusCode().isError()) {
            log.error("Error authorizing transaction. statusCode: [{}]. responseBody: [{}]", transferAuthResponse.getStatusCodeValue(), transferAuthResponse.getBody());
            throw new UnauthorizedException("There was an error when trying to authorize the transaction");
        }

        return TransferAuthorization.builder()
                .status(AuthorizationStatusEnum.valueOf(transferAuthResponse.getBody().getStatus()))
                .build();
    }
}
