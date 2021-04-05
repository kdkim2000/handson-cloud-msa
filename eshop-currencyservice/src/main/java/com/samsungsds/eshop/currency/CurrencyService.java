package com.samsungsds.eshop.currency;

import java.math.RoundingMode;
import java.util.Map;

import com.google.common.collect.Maps;
import com.google.common.math.DoubleMath;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {
  private final CurrencyRepository currencyRepository;

  public CurrencyService(CurrencyRepository currencyRepository) {
    this.currencyRepository = currencyRepository;
  }
  
  public Map<String, Double> fetchCurrency() {
    Map<String, Double> currencyMap = Maps.newHashMap();
    //currencyRepository.findAll().forEach(currency -> currencyMap.put(currency.getCurrencyCode(), currency.getCurrencyValue()));
    currencyMap.put("EUR", 1.0     );
    currencyMap.put("USD", 1.1305  );
    currencyMap.put("JPY", 126.40  );
    currencyMap.put("BGN", 1.9558  );
    currencyMap.put("CZK", 25.592  );
    currencyMap.put("DKK", 7.4609  );
    currencyMap.put("GBP", 0.85970 );
    currencyMap.put("HUF", 315.51  );
    currencyMap.put("PLN", 4.2996  );
    currencyMap.put("RON", 4.7463  );
    currencyMap.put("SEK", 10.5375 );
    currencyMap.put("CHF", 1.1360  );
    currencyMap.put("ISK", 136.80  );
    currencyMap.put("NOK", 9.8040  );
    currencyMap.put("HRK", 7.4210  );
    currencyMap.put("RUB", 74.4208 );
    currencyMap.put("TRY", 6.1247  );
    currencyMap.put("AUD", 1.6072  );
    currencyMap.put("BRL", 4.2682  );
    currencyMap.put("CAD", 1.5128  );
    currencyMap.put("CNY", 7.5857  );
    currencyMap.put("HKD", 8.8743  );
    currencyMap.put("IDR", 15999.40);
    currencyMap.put("ILS", 4.0875  );
    currencyMap.put("INR", 79.4320 );
    currencyMap.put("KRW", 1275.05 );
    currencyMap.put("MXN", 21.7999 );
    currencyMap.put("MYR", 4.6289  );
    currencyMap.put("NZD", 1.6679  );
    currencyMap.put("PHP", 59.083  );
    currencyMap.put("SGD", 1.5349  );
    currencyMap.put("THB", 36.012  );
    currencyMap.put("ZAR", 16.0583 );
    return currencyMap;
  }

  public Money convertMoneyCurrency(final CurrencyConvertRequest request) {
    Map<String, Double> currencies = this.fetchCurrency();
    Double fromCurrency = currencies.get(request.getFromCurrencyCode());
    Double toCurrency = currencies.get(request.getToCurrencyCode());
    // 일단 기준 통화(EUR)로 변환
    Money euros = new Money("EUR", DoubleMath.roundToLong(request.getUnits() / fromCurrency, RoundingMode.FLOOR),
        DoubleMath.roundToLong(request.getNanos() / fromCurrency, RoundingMode.HALF_DOWN));

    Money result = new Money(request.getToCurrencyCode(),
        DoubleMath.roundToLong(euros.getUnits() / toCurrency, RoundingMode.FLOOR),
        DoubleMath.roundToLong(euros.getNanos() / toCurrency, RoundingMode.HALF_DOWN));
    return result;
  }
}