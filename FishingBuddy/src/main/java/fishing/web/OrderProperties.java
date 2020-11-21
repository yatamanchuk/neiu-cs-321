package fishing.web;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "fish.orders")
@Data
public class OrderProperties {

    private int pageSize;
}
