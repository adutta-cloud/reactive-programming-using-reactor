package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.List;

public class FluxAndMonoGeneratorServiceTest {
    FluxAndMonoGeneratorService service = new FluxAndMonoGeneratorService();

    @Test
    void namesFlux() {

        var namesFlux = service.namesFlux();

        StepVerifier.create(namesFlux)
                .expectNext("alex")
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void namesMono() {
        var namesMono = service.namesMono();

        StepVerifier.create(namesMono)
                .expectNext("alex")
                .verifyComplete();
    }

    @Test
    void namesFluxMap() {

        var namesFlux = service.namesFluxMap();

        StepVerifier.create(namesFlux).expectNext("ALEX", "BEN", "CHLOE", "ADAM", "JILL", "JACK")
                .verifyComplete();
    }

    @Test
    void namesFluxImmutability() {
        var namesFlux = service.namesFluxImmutability();

        StepVerifier.create(namesFlux).expectNext("ALEX", "BEN", "CHLOE", "ADAM", "JILL", "JACK")
                .verifyComplete();
    }

    @Test
    void testNamesFluxMap() {
        int stringLength = 3;
        var namesFlux = service.namesFlaxMap(stringLength);
        StepVerifier.create(namesFlux).expectNext("4-ALEX", "5-CHLOE", "4-ADAM", "4-JILL", "4-JACK")
                .verifyComplete();
    }

    @Test
    void namesMono_map_filter() {
        int stringLength = 3;
        var namesMono = service.namesMono_map_filter(stringLength);
        StepVerifier.create(namesMono).expectNext("ALEX")
                .verifyComplete();
    }

    @Test
    void namesFlaxFlatMap() {
        int stringLength = 3;
        var namesFlux = service.namesFlaxFlatMap(stringLength);
        StepVerifier.create(namesFlux)
                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E", "A", "D", "A", "M", "J", "I", "L", "L", "J", "A", "C", "K")
                .verifyComplete();
    }

    @Test
    void namesFlaxFlatMapAsync() {
        int stringLength = 3;
        var namesFlux = service.namesFlaxFlatMapAsync(stringLength);
        StepVerifier.create(namesFlux)
                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E", "A", "D", "A", "M", "J", "I", "L", "L", "J", "A", "C", "K")
                .verifyComplete();
    }

    @Test
    void namesFlaxConcatMap() {
        int stringLength = 3;
        var namesFlux = service.namesFlaxConcatMap(stringLength);
        StepVerifier.create(namesFlux)
                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E", "A", "D", "A", "M", "J", "I", "L", "L", "J", "A", "C", "K")
                .verifyComplete();
    }

    @Test
    void namesMono_flatmap() {
        int stringLength = 3;
        var namesMono = service.namesMono_flatmap(stringLength);
        StepVerifier.create(namesMono)
                .expectNext(List.of("A", "L", "E", "X"))
                .verifyComplete();
    }

    @Test
    void namesMono_flatmapMany() {
        int stringLength = 3;
        var namesFlux = service.namesMono_flatmapMany(stringLength);
        StepVerifier.create(namesFlux)
                .expectNext("A", "L", "E", "X")
                .verifyComplete();
    }

    @Test
    void namesFlux_Transform() {
        int stringLength = 3;
        var namesFlux = service.namesFlux_Transform(stringLength);
        StepVerifier.create(namesFlux)
                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E", "A", "D", "A", "M", "J", "I", "L", "L", "J", "A", "C", "K")
                .verifyComplete();
    }

    @Test
    void namesFlux_Transform1() {
        int stringLength = 6;
        var namesFlux = service.namesFlux_Transform(stringLength);
        StepVerifier.create(namesFlux)
                .expectNext("default")
                .verifyComplete();
    }

    @Test
    void namesFlux_Transform_switchIfEmpty() {
        int stringLength = 3;
        var namesFlux = service.namesFlux_Transform_switchIfEmpty(stringLength);
        StepVerifier.create(namesFlux)
                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E", "A", "D", "A", "M", "J", "I", "L", "L", "J", "A", "C", "K")
                .verifyComplete();
    }

    @Test
    void namesMono_map_filter_switchIfEmpty() {
        int stringLength = 4;
        var namesFlux = service.namesMono_map_filter_switchIfEmpty(stringLength);
        StepVerifier.create(namesFlux)
                .expectNext("default")
                .verifyComplete();
    }

    @Test
    void namesMono_map_filter_defaultEmpty() {
        int stringLength = 4;
        var namesFlux = service.namesMono_map_filter_defaultEmpty(stringLength);
        StepVerifier.create(namesFlux)
                .expectNext("default")
                .verifyComplete();
    }
}
