package net.artemkv.referencewatches.controller.helpers;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PagingValidationHelper {
    public static void ValidatePageSize(int pageSize, int pageSizeLimit) {
        if (pageSize < 1 || pageSize > pageSizeLimit) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                String.format("Size should be a number 1-%d", pageSizeLimit));
        }
    }
}
