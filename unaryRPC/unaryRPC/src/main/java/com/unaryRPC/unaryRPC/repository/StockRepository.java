package com.unaryRPC.unaryRPC.repository;

import com.unaryRPC.unaryRPC.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock,Long> {
    Stock findByStockSymbol(String stockSymbol);
}
