package com.study.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import com.study.springboot.dao.ITransaction1Dao;
import com.study.springboot.dao.ITransaction2Dao;

@Service
public class BuyTicketService implements IBuyTicketService {

    @Autowired
    ITransaction1Dao transaction1;
    @Autowired
    ITransaction2Dao transaction2;

    @Autowired
    PlatformTransactionManager transactionManager;
    @Autowired
    TransactionDefinition definition;

    @Override
    public int buy(String consumerId, int amount, String error) {

        TransactionStatus status = transactionManager.getTransaction(definition);

        try {
            transaction1.pay(consumerId, amount);
            
            // 의도적 에러 발생
            if (error.equals("1")) { int n = 10 / 0;}
            
            transaction2.pay(consumerId, amount);
            
            transactionManager.commit(status);    
            return 1;
        } catch(Exception e) {
            System.out.println("[PlatformTransactionManager] Rollback");
            transactionManager.rollback(status);
            return 0;
        }
    }

}
