package com.spring.shelf.Services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.*;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Service
public class BookConversionService {
    
    String apiKey = "636d55c2b336ba8808c1bb5001d6d0afd3197b4e";
    String baseURL = "https://api.zamzar.com//v1/jobs/";
    String targetFormat = "pdf";
    Path path = Path.of("/home/marktailorson/IdeaProjects/shelf/sample3.pdf");
    WebClient webClient = WebClient.builder()
            .baseUrl(baseURL)
            .defaultHeaders(HttpHeaders -> HttpHeaders.setBasicAuth(apiKey,""))
            .build();


    public void sendToConversion(MultipartFile file) throws IOException {

        MultipartBodyBuilder requestBodyBuilder = new MultipartBodyBuilder();
        requestBodyBuilder.part("source_file",file.getResource());
        requestBodyBuilder.part("target_format",targetFormat);

        Mono<String> sendRequest = webClient.post().accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders -> httpHeaders.setBasicAuth(apiKey,""))
                .body(BodyInserters.fromMultipartData(requestBodyBuilder.build()))
                .retrieve().bodyToMono(String.class).log();
        String sendResponse = sendRequest.block();
        JSONObject jsonSendResponse = new JSONObject(sendResponse);
        System.out.println(jsonSendResponse);
        System.out.println("вошли");
        String status = jsonSendResponse.getString("status");
        JSONArray targetFiles = new JSONArray();
        do {
            try {
                TimeUnit.SECONDS.sleep(5);
                Mono<String> checkRequest = webClient.get()
                        .uri(baseURL+jsonSendResponse.get("id"))
                        .retrieve().bodyToMono(String.class).log();
                String checkResponse = checkRequest.block();
                JSONObject jsonCheckResponse = new JSONObject(checkResponse);
                status = jsonCheckResponse.getString("status");
                targetFiles = jsonCheckResponse.getJSONArray("target_files");
                System.out.println(jsonCheckResponse);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        } while (!status.equals("successful"));
        JSONArray finalTargetFiles = targetFiles;
        String id = (IntStream.range(0, targetFiles.length()).mapToObj(index -> ((JSONObject) finalTargetFiles.get(index)).optString("id")).toList()).get(0);
        System.out.println(id);

        Flux<DataBuffer> downloadRequest = webClient.get()
                .uri("https://api.zamzar.com//v1/files/"+id+"/content")
                .retrieve().bodyToFlux(DataBuffer.class);

        DataBufferUtils.write(downloadRequest,path,StandardOpenOption.CREATE)
                .share()
                .block();

    }



}