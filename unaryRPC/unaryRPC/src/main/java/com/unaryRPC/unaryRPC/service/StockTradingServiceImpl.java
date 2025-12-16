package com.unaryRPC.unaryRPC.service;


import com.unaryRPC.StockRequest;
import com.unaryRPC.StockResponse;
import com.unaryRPC.StockTradingServiceGrpc;
import com.unaryRPC.unaryRPC.entity.Stock;
import com.unaryRPC.unaryRPC.repository.StockRepository;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class StockTradingServiceImpl extends StockTradingServiceGrpc.StockTradingServiceImplBase {

    private final StockRepository stockRepository;

    public StockTradingServiceImpl(StockRepository stockRepository){
        this.stockRepository=stockRepository;
    }

    @Override
    public void getStockPrice(StockRequest request, StreamObserver<StockResponse> responseObserver) {
        String stockSymbol = request.getStockSymbol();

        Stock stockEntity = stockRepository.findByStockSymbol(stockSymbol);

        StockResponse stockResponse=StockResponse.newBuilder()
                .setStockSymbol(stockEntity.getStockSymbol())
                .setPrice(stockEntity.getPrice())
                .setTimestamp(stockEntity.getLastUpdated().toString()).build();

        responseObserver.onNext(stockResponse);
        responseObserver.onCompleted();


    }
}
