package com.example.orderPublisher.azurehandler;

import com.example.orderPublisher.domains.Order;
import com.example.orderPublisher.util.ResponseDTO;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

import java.util.Optional;

public class OrderHandler extends AzureSpringBootRequestHandler<Object, ResponseDTO> {

    @FunctionName("order")
    public ResponseDTO save(
            @HttpTrigger(name = "orderRequest", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<Order>> request,
            ExecutionContext context) {
        return handleRequest(request.getBody().get(), context);
    }

}