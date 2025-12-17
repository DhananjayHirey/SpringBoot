package com.unaryRPCClient.unaryRPCClient.service;

import com.unaryRPCClient.StockRequest;
import com.unaryRPCClient.StockResponse;
import com.unaryRPCClient.StockTradingServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class StockClientService {
    @GrpcClient("stockService")
//    private StockTradingServiceGrpc.StockTradingServiceBlockingStub serviceBlockingStub;
    private StockTradingServiceGrpc.StockTradingServiceStub stockTradingServiceStub;

    //StockResponse getStockPrice(StockRequest)
//    public StockResponse getStockPrice(String stockSymbol){
//        StockRequest request = StockRequest.newBuilder().setStockSymbol(stockSymbol).build();
//        return serviceBlockingStub.getStockPrice(request);
//    }

    public void subscribeStockPrice(String symbol){
        StockRequest request = StockRequest.newBuilder().setStockSymbol(symbol).build();
        stockTradingServiceStub.subscribeStockPrice(request, new StreamObserver<StockResponse>() {
            @Override
            public void onNext(StockResponse stockResponse) {
                System.out.println("Stock Price update: "+stockResponse.getStockSymbol()+", Price: "+stockResponse.getPrice()+", TimeStamp: "+stockResponse.getTimestamp());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error: "+throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Stock Price Stream completed");
            }
        });
    }
}
