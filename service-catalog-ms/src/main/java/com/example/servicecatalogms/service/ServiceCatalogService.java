package com.example.servicecatalogms.service;

import com.example.servicecatalogms.config.JwtService;
import com.example.servicecatalogms.domain.ServiceCatalog;
import com.example.servicecatalogms.dto.ServiceCatalogReqDto;
import com.example.servicecatalogms.dto.ServiceCatalogResDto;
import com.example.servicecatalogms.exception.BaseException;
import com.example.servicecatalogms.repository.ServiceCatalogRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.example.servicecatalogms.response.enums.ErrorResponseMessages.FORBIDDEN;

@Service
@RequiredArgsConstructor
public class ServiceCatalogService {
    private final ServiceCatalogRepository serviceRepository;
    private final JwtService jwtService;
    private final ModelMapper mapper;

    public ServiceCatalogResDto createForProvider(ServiceCatalogReqDto dto, String token) {
        Long providerId = jwtService.extractClaim(token,
                claims -> claims.get("id", Long.class));

        ServiceCatalog serviceCatalog = ServiceCatalog.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .duration(dto.getDuration())
                .price(dto.getPrice())
                .providerId(providerId)
                .build();

        return mapper.map(serviceRepository.save(serviceCatalog), ServiceCatalogResDto.class);
    }

    public Page<ServiceCatalogResDto> getAllForProvider(Pageable pageable, String token) {
        Long providerId = jwtService.extractClaim(token,
                claims -> claims.get("id", Long.class));

        return serviceRepository.findAllByProviderId(providerId, pageable)
                .map(serviceCatalog -> mapper.map(serviceCatalog, ServiceCatalogResDto.class));
    }

    public ServiceCatalogResDto updateForProvider(Long id, ServiceCatalogReqDto dto, String token) {
        Long providerId = jwtService.extractClaim(token,
                claims -> claims.get("id", Long.class));

        ServiceCatalog serviceCatalog = serviceRepository.findById(id)
                .orElseThrow(() -> BaseException.notFound(ServiceCatalog.class.getSimpleName(), "id", id.toString()));

        if (serviceCatalog.getProviderId().equals(providerId)) {
            serviceCatalog.setName(dto.getName());
            serviceCatalog.setDescription(dto.getDescription());
            serviceCatalog.setDuration(dto.getDuration());
            serviceCatalog.setPrice(dto.getPrice());

            return mapper.map(serviceRepository.save(serviceCatalog), ServiceCatalogResDto.class);
        } else {
            throw BaseException.of(FORBIDDEN);
        }
    }

    public void deleteForProvider(Long id, String token) {
        Long providerId = jwtService.extractClaim(token,
                claims -> claims.get("id", Long.class));

        ServiceCatalog serviceCatalog = serviceRepository.findById(id)
                .orElseThrow(() ->
                        BaseException.notFound(ServiceCatalog.class.getSimpleName(), "id", id.toString()));

        if (serviceCatalog.getProviderId().equals(providerId)) {
            serviceRepository.deleteById(id);
        } else {
            throw BaseException.of(FORBIDDEN);
        }
    }
}
