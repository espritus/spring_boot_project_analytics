package com.example.spring_boot_project_analytics.Forecast.service.impl;

import com.example.spring_boot_project_analytics.Forecast.entity.ForecastResult;
import com.example.spring_boot_project_analytics.Forecast.repository.ForecastResultRepository;
import com.example.spring_boot_project_analytics.Forecast.service.ForecastService;
import com.example.spring_boot_project_analytics.MainTable.Helper.JsonUtil;
import com.example.spring_boot_project_analytics.MainTable.dto.RecordDto;
import com.example.spring_boot_project_analytics.MainTable.entity.Record;
import com.example.spring_boot_project_analytics.MainTable.mapper.CSVRecordMapper;
import com.example.spring_boot_project_analytics.TableDetails.entity.TableDetails;
import com.example.spring_boot_project_analytics.TableDetails.repository.TableDetailsRepository;
import com.example.spring_boot_project_analytics.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
public class ForecastServiceImpl implements ForecastService {

    private final TableDetailsRepository metaDataRepository;
    private final ForecastResultRepository forecastResultRepository;
    private final WebClient webClient;

    @Autowired
    public ForecastServiceImpl(WebClient.Builder webClientBuilder, TableDetailsRepository metaDataRepository, ForecastResultRepository forecastResultRepository) {
        this.webClient = webClientBuilder.baseUrl("http://desolate-plateau-96383-08argk18aa65.herokuapp.com").build();
        this.metaDataRepository = metaDataRepository;
        this.forecastResultRepository = forecastResultRepository;
    }

    @Override
    public Mono<Void> sendFileToFlaskAndGetForecastAsync(Long metaId) {
        Mono<TableDetails> tableDetailsMono = metaDataRepository.findById(metaId)
                .map(Mono::just)
                .orElseGet(() -> Mono.error(new ResourceNotFoundException("That file does not exist with the given id: " + metaId)));

        return tableDetailsMono.flatMap(table -> {
            List<Record> records = table.getRecords();
            List<RecordDto> recordDTOs = records.stream().map(CSVRecordMapper::mapToDataDto).toList();
            String jsonPayload = JsonUtil.toJson(recordDTOs);

            return webClient.post()
                    .uri("/upload")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(jsonPayload))
                    .retrieve()
                    .bodyToFlux(ForecastResult.class)
                    .collectList()
                    .flatMap(results -> saveForecastResults(results, table));
        });
    }

    private Mono<Void> saveForecastResults(List<ForecastResult> results, TableDetails metadata) {
        return Mono.fromCallable(() -> {
                    results.forEach(result -> result.setMetadata(metadata));
                    forecastResultRepository.saveAll(results);
                    return null;
                })
                .subscribeOn(Schedulers.boundedElastic())
                .then();
    }
}
