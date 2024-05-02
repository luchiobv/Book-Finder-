package com.desafioConsumoApi.Books.finder.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData(
        @JsonAlias("title") String Title,
        @JsonAlias("authors")List<authorsInformation> authors,
        @JsonAlias("languages")List <String> languages,
        @JsonAlias("download_count")Double numberOfDownloads

) {
}
