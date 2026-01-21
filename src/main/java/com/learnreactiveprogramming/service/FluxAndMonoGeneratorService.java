package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FluxAndMonoGeneratorService {

    public Flux<String> namesFlux() {
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .log();
    }

    public Mono<String> namesMono() {
        return Mono.just("alex")
                .log();
    }

    public Flux<String> namesFluxMap() {
        return Flux.fromIterable(List.of("alex", "ben", "chloe", "adam", "jill", "jack"))
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> namesFluxImmutability() {

        var namesFlux = Flux.fromIterable(List.of("alex", "ben", "chloe", "adam", "jill", "jack"));
        namesFlux.map(String::toUpperCase);

        return namesFlux.log();
    }

    public Flux<String> namesFlaxMap(int stringLength){
        return Flux.fromIterable(List.of("alex", "ben", "chloe", "adam", "jill", "jack"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .map(s -> s.length() + "-" + s)
                .log();
    }

    public Mono<String> namesMono_map_filter(int stringLength){
        return Mono.just("alex")
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .log();
    }

    public Mono<List<String>> namesMono_flatmap(int stringLength){
        return Mono.just("alex")
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMap(this::splitStringMono)
                .log();
    }

    private Mono<List<String>> splitStringMono(String s) {
        var charArray = s.split("");
        var charList = List.of(charArray);
        return Mono.just(charList);
    }

    public Flux<String> namesMono_flatmapMany(int stringLength){
        return Mono.just("alex")
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMapMany(this::splitString)
                .log();
    }

    public Flux<String> namesFlaxFlatMap(int stringLength){
        return Flux.fromIterable(List.of("alex", "ben", "chloe", "adam", "jill", "jack"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMap(this::splitString)
                .log();
    }

    private Flux<String> splitString(String name) {
        var charArray = name.split("");
        return Flux.fromArray(charArray);
    }

    public Flux<String> namesFlaxFlatMapAsync(int stringLength){
        return Flux.fromIterable(List.of("alex", "ben", "chloe", "adam", "jill", "jack"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMap(this::splitStringWithDelay)
                .log();
    }

    private Flux<String> splitStringWithDelay(String name) {
        var charArray = name.split("");
        var delay = new Random().nextInt(1000);
        return Flux.fromArray(charArray)
                .delayElements(Duration.ofMillis(delay));
    }

    public Flux<String> namesFlaxConcatMap(int stringLength){
        return Flux.fromIterable(List.of("alex", "ben", "chloe", "adam", "jill", "jack"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .concatMap(this::splitStringWithDelay)
                .log();
    }

    public Flux<String> namesFlux_Transform(int stringLength){

        Function<Flux<String>, Flux<String>> fluxFunction = name -> name.map(String::toUpperCase)
                .filter(s -> s.length() > stringLength);

        return Flux.fromIterable(List.of("alex", "ben", "chloe", "adam", "jill", "jack"))
                .transform(fluxFunction)
                .flatMap(this::splitString)
                .defaultIfEmpty("default")
                .log();
    }

    public Flux<String> namesFlux_Transform_switchIfEmpty(int stringLength){

        Function<Flux<String>, Flux<String>> fluxFunction = name -> name.map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMap(this::splitString);

        var defaultFlux = Flux.just("default")
                .transform(fluxFunction);

        return Flux.fromIterable(List.of("alex", "ben", "chloe", "adam", "jill", "jack"))
                .transform(fluxFunction)
                .defaultIfEmpty(String.valueOf(defaultFlux))
                .log();
    }

    public Flux<String> namesMono_map_filter_defaultEmpty(int stringLength){
        return Mono.just("alex")
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMapMany(this::splitString)
                .defaultIfEmpty("default")
                .log();
    }

    public Flux<String> namesMono_map_filter_switchIfEmpty(int stringLength){
        return Mono.just("alex")
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMapMany(this::splitString)
                .switchIfEmpty(Flux.just("default"))
                .log();
    }

    public static void main(String[] args) {
        FluxAndMonoGeneratorService service = new FluxAndMonoGeneratorService();
        service.namesFlux()
                .subscribe(name -> System.out.println("Name is : " + name));

        service.namesMono()
                .subscribe(name -> System.out.println("Mono Name is : " + name));


    }
}
