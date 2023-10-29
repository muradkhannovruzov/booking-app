package com.example.servicecatalogms.controller;


import com.example.servicecatalogms.domain.ServiceCatalog;
import com.example.servicecatalogms.dto.ServiceCatalogReqDto;
import com.example.servicecatalogms.dto.ServiceCatalogResDto;
import com.example.servicecatalogms.response.BaseResponse;
import com.example.servicecatalogms.service.ServiceCatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/service-catalog")
@RequiredArgsConstructor
public class ServiceCatalogController {

    private final ServiceCatalogService serviceCatalogService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello from service-ms";
    }

    @GetMapping("/{id}")
    public BaseResponse<ServiceCatalogResDto> get(@PathVariable Long id) {
        return BaseResponse.success(serviceCatalogService.get(id));
    }

    @GetMapping("list")
    public BaseResponse<Page<ServiceCatalogResDto>> getAll(Pageable pageable) {
        return BaseResponse.success(serviceCatalogService.getAll(pageable));
    }

    @GetMapping("for-provider")
    public BaseResponse<Page<ServiceCatalogResDto>> getAllForProvider
            (@RequestHeader("Authorization") String token,
             Pageable pageable) {
        return BaseResponse.success(serviceCatalogService.getAllForProvider(pageable, token));
    }

    @PostMapping("list-for-provider")
    public BaseResponse<ServiceCatalogResDto> createForProvider
            (@RequestHeader("Authorization") String token,
             @RequestBody ServiceCatalogReqDto dto) {

        return BaseResponse.success(serviceCatalogService.createForProvider(dto, token));
    }

    @PutMapping("for-provider/{id}")
    public BaseResponse<ServiceCatalogResDto> updateForProvider
            (@RequestHeader("Authorization") String token,
             @PathVariable Long id,
             @RequestBody ServiceCatalogReqDto dto) {

        return BaseResponse.success(serviceCatalogService.updateForProvider(id, dto, token));
    }

    @DeleteMapping("for-provider/{id}")
    public BaseResponse<Void> deleteForProvider
            (@RequestHeader("Authorization") String token,
             @PathVariable Long id) {

        serviceCatalogService.deleteForProvider(id, token);
        return BaseResponse.success();
    }


//    @GetMapping("/{id}")
//    @PreAuthorize("hasPermission(#id, 'com.example.servicecatalogms.domain.ServiceCatalog', 'read')")
//    public ServiceCatalog getServiceCatalog(@PathVariable Long id) {
//        System.out.println("service-catalog controller called. id: " + id);
//        return serviceCatalogService.getServiceCatalogById(id);
//    }
//
//    @GetMapping("/provider/{providerId}")
//    public List<ServiceCatalog> getAllServiceCatalogsByProviderId(@PathVariable Long providerId) {
//        return serviceCatalogService.getAllServiceCatalogsByProviderId(providerId);
//    }
//
//    @PostMapping
//    @PreAuthorize("hasPermission('ServiceCatalog', 'create')")
//    public ServiceCatalog createServiceCatalog(@RequestBody ServiceCatalog serviceCatalog) {
//        return serviceCatalogService.createServiceCatalog(serviceCatalog);
//    }
//
//    @PutMapping("/{id}")
//    @PreAuthorize("hasPermission(#id, 'ServiceCatalog', 'update')")
//    public ServiceCatalog updateServiceCatalog(@PathVariable Long id, @RequestBody ServiceCatalog serviceCatalog) {
//        return serviceCatalogService.updateServiceCatalog(id, serviceCatalog);
//    }
//
//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasPermission(#id, 'ServiceCatalog', 'delete')")
//    public void deleteServiceCatalog(@PathVariable Long id) {
//        serviceCatalogService.deleteServiceCatalog(id);
//    }
}
