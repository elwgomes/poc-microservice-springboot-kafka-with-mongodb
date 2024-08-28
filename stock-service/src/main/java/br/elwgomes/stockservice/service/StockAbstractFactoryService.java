package br.elwgomes.stockservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class StockAbstractFactoryService {

  private static final Logger LOG = LoggerFactory.getLogger(StockAbstractFactoryService.class);

  public void generateStock() {

  }

}
