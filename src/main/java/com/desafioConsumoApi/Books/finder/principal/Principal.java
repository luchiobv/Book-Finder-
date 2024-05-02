package com.desafioConsumoApi.Books.finder.principal;

import ch.qos.logback.core.encoder.JsonEscapeUtil;
import com.desafioConsumoApi.Books.finder.model.BookData;
import com.desafioConsumoApi.Books.finder.model.DataBooks;
import com.desafioConsumoApi.Books.finder.service.ConsumAPI;
import com.desafioConsumoApi.Books.finder.service.ConverData;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumAPI consumAPI = new ConsumAPI();
    private ConverData convertData = new ConverData();
    private Scanner scanner = new Scanner(System.in);
    public void showTheMenu() {
        String json;
        json = consumAPI.gettingData(URL_BASE);
        System.out.println(json);
        var data = convertData.gettingData(json, DataBooks.class);
        System.out.println(data);

        //Top 10 of download Books

        System.out.println("Top 10 of download Books");
        data.results().stream()
                .sorted(Comparator.comparing(BookData::numberOfDownloads).reversed())
                .limit(10)
                .map(l -> l.Title().toUpperCase())
                .forEach(System.out::println);

//Book search by name
        System.out.println("Insert the name of the book that you want to search: ");
        var titleBook = scanner.nextLine();
        json = consumAPI.gettingData(URL_BASE+"?search="+titleBook).replace(" ","+");
        var bookSearch = convertData.gettingData(json, DataBooks.class);
        Optional<BookData> bookLook = bookSearch.results().stream()
                .filter(l -> l.Title().toUpperCase().contains(titleBook.toUpperCase()))
                .findFirst();
        if (bookLook.isPresent()) {
            System.out.println("Book Result:");
            System.out.println(bookLook.get());

        }else {
            System.out.println("Book not found");
        }

        //Work with statistics
        DoubleSummaryStatistics est = data.results().stream()
                .filter(d-> d.numberOfDownloads()>0)
                .collect(Collectors.summarizingDouble(BookData::numberOfDownloads));
        System.out.println("Media downloads quantity: "+est.getAverage());
        System.out.println("Maximum downloads count: "+est.getMax());
        System.out.println("Minimum downloads count: "+est.getMin());
        System.out.println("Records evaluated for calculation: " + est.getCount());


    }

}
