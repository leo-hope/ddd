package com.runssnail.ddd.demo.infra.acl.ump;

import com.runssnail.ddd.demo.domain.acl.ump.CalcPriceRequest;
import com.runssnail.ddd.demo.domain.acl.ump.CalcPriceResponse;
import com.runssnail.ddd.demo.domain.acl.ump.MarketingService;
import org.springframework.stereotype.Service;


@Service
public class MarketingServiceImpl implements MarketingService {
    @Override
    public CalcPriceResponse calcPrice(CalcPriceRequest req) {
        return null;
    }
}
