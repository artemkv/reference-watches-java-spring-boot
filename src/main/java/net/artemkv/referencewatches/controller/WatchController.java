package net.artemkv.referencewatches.controller;

import net.artemkv.referencewatches.configuration.WatchesApiConfiguration;
import net.artemkv.referencewatches.controller.mapper.WatchMapper;
import net.artemkv.referencewatches.dto.BrandDto;
import net.artemkv.referencewatches.dto.GetListResponse;
import net.artemkv.referencewatches.dto.WatchDto;
import net.artemkv.referencewatches.dto.WatchToPostDto;
import net.artemkv.referencewatches.dto.WatchToPutDto;
import net.artemkv.referencewatches.persistence.model.Gender;
import net.artemkv.referencewatches.persistence.model.Watch;
import net.artemkv.referencewatches.service.WatchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static net.artemkv.referencewatches.controller.helpers.PagingValidationHelper.ValidatePageSize;

@RestController
@RequestMapping(value="api/watches")
public class WatchController {
    private final WatchService watchService;
    private final WatchesApiConfiguration apiConfiguration;

    public WatchController(WatchService watchService,
                           WatchesApiConfiguration apiConfiguration) {
        this.watchService = watchService;
        this.apiConfiguration = apiConfiguration;
    }

    @GetMapping
    public GetListResponse<WatchDto> getWatches(
        @RequestParam(defaultValue = "") String title,
        @RequestParam(defaultValue = "MENS") Gender gender,
        @RequestParam(defaultValue = "") String brandTitle,
        Pageable pageable) {
        ValidatePageSize(pageable.getPageSize(), apiConfiguration.getPageSizeLimit());

        Page<Watch> page = watchService.getWatches(title, gender, brandTitle, pageable);
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

    @PostMapping
    public ResponseEntity<WatchDto> createWatch(@Valid @RequestBody WatchToPostDto watchDto) {
        Watch watch = WatchMapper.makeWatch(watchDto);
        WatchDto createdWatchDto = WatchMapper.makeWatchDto(watchService.createWatch(watch));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(createdWatchDto.getId()).toUri();
        return ResponseEntity.created(location).body(createdWatchDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateWatch(
        @PathVariable long id, @Valid @RequestBody WatchToPutDto watchDto) {
        if (id != watchDto.getId()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                String.format("Watch id %d does not match the id in the route: %d.", watchDto.getId(), id));
        }
        if (watchDto.getId() == 0) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                String.format("Watch id cannot be 0."));
        }
        Watch watch = WatchMapper.makeWatch(watchDto);
        boolean updated = watchService.updateWatch(watch);
        if (!updated) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Watch with id %d cannot be found.", id));
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteWatch(@PathVariable long id) {
        boolean removed = watchService.deleteWatch(id);
        if (!removed) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Watch with id %d cannot be found.", id));
        }
        return ResponseEntity.noContent().build();
    }
}
