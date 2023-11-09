package com.money.transfer.dataprovider.api.impl;

import com.money.transfer.core.usecase.boundary.NotifyTransferReceiverBoundary;
import com.money.transfer.dataprovider.api.dto.NotifyTransferReceiverDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotifyTransferReceiverGateway implements NotifyTransferReceiverBoundary {

    private final RestTemplate restTemplate;

    @Value("${properties.authorization-service-url}")
    private String authorizationHost;

    public void notifyUser(String userEmail, BigDecimal amountReceived) {
        NotifyTransferReceiverDto notifyTransferReceiverDto = NotifyTransferReceiverDto.builder()
                .email(userEmail)
                .amount(amountReceived)
                .build();

        ResponseEntity<Void> response = restTemplate.postForEntity(authorizationHost + "/transfer/notify", notifyTransferReceiverDto, Void.class);

        if(response.getStatusCode().isError()) {
            log.error("Error notifying user. statusCode: [{}]", response.getStatusCodeValue());
        }
    }
}
