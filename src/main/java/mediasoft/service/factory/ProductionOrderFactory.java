package mediasoft.service.factory;

import mediasoft.entity.ProductionOrder;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
    public class ProductionOrderFactory {

        public ProductionOrder build(Instant dtOrder, Instant dtOrderEnd) {
            return new ProductionOrder(dtOrder, dtOrderEnd);
        }
    }