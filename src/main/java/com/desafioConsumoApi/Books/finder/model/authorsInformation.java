package com.desafioConsumoApi.Books.finder.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public record authorsInformation(
        @JsonAlias("name") String name,
        @JsonAlias("birth_year") String dateOfBirth
) {
}
