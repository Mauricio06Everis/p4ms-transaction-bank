package com.example.mstransaction.events;

import java.net.URI;
import java.time.LocalDateTime;

import com.example.mstransaction.models.dto.PurseDTO;
import com.example.mstransaction.models.entities.Transaction;
import com.example.mstransaction.services.ITransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class BootCoinKafkaListener {
    
    private final ITransactionService transactionService;

    
    @Autowired
    public BootCoinKafkaListener(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @KafkaListener(topics = "TOPIC_BOOTCOIN", groupId = "FE_Dev") 
    public void listenerTransaction(String data) {
        Gson gson = new Gson();
        PurseDTO obj = gson.fromJson(data.toString(), PurseDTO.class);
        Mono<Transaction> transaction = Mono.just(new Transaction());
        log.info("DATA TRANSACTION" + obj.getAmount());
        transaction
            .flatMap(p -> {
                p.setTransactionType(obj.getType());
                p.setTransactionAmount(obj.getAmount());
                p.setDescription(obj.getType());
                p.setTransactionDate(LocalDateTime.now());
                return Mono.just(p);
            })
            .flatMap(p -> {
                log.info("Creando", p.toString());
                return transactionService.create(p);
            })
            .flatMap(transactionResponse -> ServerResponse.created(URI.create("/api/transaction/".concat(transactionResponse.getId())))
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(transactionResponse));
    }

}
