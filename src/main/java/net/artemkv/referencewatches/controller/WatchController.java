package net.artemkv.referencewatches.controller;

import net.artemkv.referencewatches.configuration.WatchesApiConfiguration;
import net.artemkv.referencewatches.controller.mapper.WatchMapper;
import net.artemkv.referencewatches.dto.GetListResponse;
import net.artemkv.referencewatches.dto.WatchDto;
import net.artemkv.referencewatches.persistence.model.Watch;
import net.artemkv.referencewatches.service.WatchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static net.artemkv.referencewatches.controller.helpers.PagingValidationHelper.ValidatePageSize;

@RestController
@RequestMapping(value="api/watches")
public class WatchController {
    private WatchService watchService;
    private WatchesApiConfiguration apiConfiguration;

    public WatchController(WatchService watchService,
                           WatchesApiConfiguration apiConfiguration) {
        this.watchService = watchService;
        this.apiConfiguration = apiConfiguration;
    }

    @GetMapping
    public GetListResponse<WatchDto> getWatches(Pageable pageable) {
        ValidatePageSize(pageable.getPageSize(), apiConfiguration.getPageSizeLimit());

        Page<Watch> page = watchService.getWatches(pageable);
        List<WatchDto> watches = page.stream()
            .map(WatchMapper::makeWatchDto)
            .collect(Collectors.toList());

        return new GetListResponse<>(
            pageable.getPageNumber(),
            pageable.getPageSize(),
            page.getTotalElements(),
            watches.size(),
            watches);
    }

    @GetMapping("/{id}")
    public WatchDto getWatch(@PathVariable long id) {
        Watch watch = watchService.getWatch(id);
        if (watch == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Watch with id %d cannot be found.", id));
        }
        return WatchMapper.makeWatchDto(watch);
    }
}
