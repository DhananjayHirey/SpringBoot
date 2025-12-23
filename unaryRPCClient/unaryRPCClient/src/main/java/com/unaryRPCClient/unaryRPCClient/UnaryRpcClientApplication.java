package com.unaryRPCClient.unaryRPCClient;

import com.unaryRPCClient.unaryRPCClient.service.StockClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UnaryRpcClientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UnaryRpcClientApplication.class, args);
	}
	private final StockClientService clientService;

	public UnaryRpcClientApplication(StockClientService clientService){
		this.clientService=clientService;
	}

	@Override
	public void run(String... args) throws Exception {
//		clientService.subscribeStockPrice("AAPL");
		clientService.placeBulkOrders();
	}
}
