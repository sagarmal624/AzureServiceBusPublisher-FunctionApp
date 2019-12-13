package com.example.orderPublisher.service;

import com.example.orderPublisher.domains.Order;
import com.example.orderPublisher.util.ResponseDTO;
import com.google.gson.Gson;
import com.microsoft.azure.eventgrid.EventGridClient;
import com.microsoft.azure.eventgrid.TopicCredentials;
import com.microsoft.azure.eventgrid.implementation.EventGridClientImpl;
import com.microsoft.azure.eventgrid.models.EventGridEvent;
import com.microsoft.azure.servicebus.Message;
import com.microsoft.azure.servicebus.TopicClient;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
public class OrderService {
    static final Gson GSON = new Gson();

    public ResponseDTO orderPublishWithServiceBus(Order order) {
        try {
            TopicClient sendClient;
            String connectionString = "<Put Your Connection String>";
            sendClient = new TopicClient(new ConnectionStringBuilder(connectionString));
            sendMessagesAsync(sendClient,order).thenRunAsync(() -> sendClient.closeAsync());

        } catch (Exception ex) {
        }
        return new ResponseDTO();
    }

    static CompletableFuture<Void> sendMessagesAsync(TopicClient sendClient,Order order) {


        List<CompletableFuture> tasks = new ArrayList<>();
        Message message = new Message(GSON.toJson(order).getBytes(UTF_8));
        message.setContentType("application/json");
        message.setLabel("Scientist");
        message.setMessageId("testid");
        message.setTimeToLive(Duration.ofMinutes(2));
        System.out.printf("Message sending: Id = %s\n", message.getMessageId());
        tasks.add(sendClient.sendAsync(message).thenRunAsync(() -> {
            System.out.printf("\tMessage acknowledged: Id = %s\n", message.getMessageId());
        }));
        return CompletableFuture.allOf(tasks.toArray(new CompletableFuture<?>[tasks.size()]));
    }

}
