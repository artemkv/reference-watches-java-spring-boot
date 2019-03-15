package net.artemkv.referencewatches.controller;

import net.artemkv.referencewatches.WatchesApiProperties;
import net.artemkv.referencewatches.controller.mapper.BrandMapper;
import net.artemkv.referencewatches.dto.BrandDto;
import net.artemkv.referencewatches.dto.BrandToPostDto;
import net.artemkv.referencewatches.dto.BrandToPutDto;
import net.artemkv.referencewatches.dto.GetListResponse;
import net.artemkv.referencewatches.persistence.model.Brand;
import net.artemkv.referencewatches.service.BrandService;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="api/brands")
public class BrandController {
    private BrandService brandService;
    private WatchesApiProperties watchesApiProperties;

    public BrandController(BrandService brandService,
                           WatchesApiProperties watchesApiProperties) {
        this.brandService = brandService;
        this.watchesApiProperties = watchesApiProperties;
    }

    @GetMapping
    public GetListResponse<BrandDto> getBrands(Pageable pageable) {
        if (pageable.getPageSize() < 1 ||
            pageable.getPageSize() > watchesApiProperties.getPageSizeLimit()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                String.format("Size should be a number 1-%d", watchesApiProperties.getPageSizeLimit()));
        }

        Page<Brand> page = brandService.getBrands(pageable);
        List<BrandDto> brands = page.stream()
            .map(BrandMapper::makeBrandDto)
            .collect(Collectors.toList());

        return new GetListResponse<>(
            pageable.getPageNumber(),
            pageable.getPageSize(),
            page.getTotalElements(),
            brands.size(),
            brands);
    }

    @GetMapping("/{id}")
    public BrandDto getBrand(@PathVariable long id) {
        Brand brand = brandService.getBrand(id);
        if (brand == null) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Brand with id %d cannot be found.", id));
        }
        return BrandMapper.makeBrandDto(brand);
    }

    @PostMapping
    public ResponseEntity<BrandDto> createBrand(@Valid @RequestBody BrandToPostDto brandDto) {
        Brand brand = BrandMapper.makeBrand(brandDto);
        BrandDto createdBrandDto = BrandMapper.makeBrandDto(brandService.createBrand(brand));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(createdBrandDto.getId()).toUri();
        return ResponseEntity.created(location).body(createdBrandDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateBrand(
        @PathVariable long id, @Valid @RequestBody BrandToPutDto brandDto) {
        if (id != brandDto.getId()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                String.format("Brand id %d does not match the id in the route: %d.", brandDto.getId(), id));
        }
        if (brandDto.getId() == 0) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                String.format("Brand id cannot be 0."));
        }
        Brand brand = BrandMapper.makeBrand(brandDto);
        boolean updated = brandService.updateBrand(brand);
        if (!updated) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Brand with id %d cannot be found.", id));
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBrand(@PathVariable long id) {
        boolean removed = brandService.deleteBrand(id);
        if (!removed) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Brand with id %d cannot be found.", id));
        }
        return ResponseEntity.noContent().build();
    }
}
