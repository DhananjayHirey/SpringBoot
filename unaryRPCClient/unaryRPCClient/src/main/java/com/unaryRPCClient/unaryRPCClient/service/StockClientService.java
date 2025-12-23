package com.unaryRPCClient.unaryRPCClient.service;

import com.unaryRPCClient.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import static java.lang.Thread.sleep;

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

    public void placeBulkOrders(){

        StreamObserver<OrderSummary> responseObserver = new StreamObserver<OrderSummary>() {
            @Override
            public void onNext(OrderSummary orderSummary) {
                System.out.println("Order Summary Received from Server:");
                System.out.println("Total Orders: "+orderSummary.getTotalOrders());
                System.out.println("Successful Orders: "+orderSummary.getSuccessCount());
                System.out.println("Total Amount: $"+orderSummary.getTotalAmount());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Order Summary error: "+throwable.getMessage());

            }

            @Override
            public void onCompleted() {
                System.out.println("Order summary received from server!");
            }
        };

        StreamObserver<StockOrder> requestObserver=stockTradingServiceStub.bulkStockOrder(responseObserver);

        //send multiple stream of stock order message/request
        try{
            requestObserver.onNext(StockOrder.newBuilder().setOrderId("1").setStockSymbol("AAPL").setOrderType("BUY").setPrice(150.5).setQuantity(10).build());
            requestObserver.onNext(StockOrder.newBuilder().setOrderId("2").setStockSymbol("GOOGL").setOrderType("SELL").setPrice(2700.0).setQuantity(5).build());
            requestObserver.onNext(StockOrder.newBuilder().setOrderId("3").setStockSymbol("TSLA").setOrderType("BUY").setPrice(700.0).setQuantity(8).build());
            requestObserver.onCompleted();
        }catch(Exception e){
            requestObserver.onError(e);
        }
    }

    public void startLiveTrading() throws InterruptedException {
        StreamObserver<StockOrder> requestObserver = stockTradingServiceStub.liveTrading(new StreamObserver<>() {
            @Override
            public void onNext(TradeStatus tradeStatus) {
                System.out.println("Server response : " + tradeStatus);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Server error: " + throwable.getMessage());

            }

            @Override
            public void onCompleted() {
                System.out.println("stream completed!");

            }
        });
        for(int i=1;i<=10;i++){
            StockOrder stockOrder =StockOrder.newBuilder().setOrderId("order-"+i).setStockSymbol("APPL").setQuantity(i*10).setOrderType("BUY").setPrice(150.0+i).build();
            requestObserver.onNext(stockOrder);
            Thread.sleep(500);
        }
        requestObserver.onCompleted();
    }
}
