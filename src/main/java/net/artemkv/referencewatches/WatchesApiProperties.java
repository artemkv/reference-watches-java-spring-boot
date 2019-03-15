package net.artemkv.referencewatches;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:watchesapi.properties")
@ConfigurationProperties(prefix="watchesapi")
public class WatchesApiProperties {
    private int pageSizeLimit = 1000;

    public int getPageSizeLimit() {
        return pageSizeLimit;
    }

    public void setPageSizeLimit(int pageSizeLimit) {
        this.pageSizeLimit = pageSizeLimit;
    }
}
