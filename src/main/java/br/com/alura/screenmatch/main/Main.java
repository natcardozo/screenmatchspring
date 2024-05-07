package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.model.SeasonData;
import br.com.alura.screenmatch.model.SeriesData;
import br.com.alura.screenmatch.service.ApiCall;
import br.com.alura.screenmatch.service.DataConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private ApiCall apiCall = new ApiCall();
    private DataConverter converter = new DataConverter();

    private final String URL = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=3dd5799f";

    public void showMenu() {
        System.out.println("Digite o nome da série que deseja buscar:");
        String seriesName = scanner.nextLine();

        String json = apiCall.getData(URL + seriesName.replace(" ", "+") + API_KEY);

        SeriesData data = converter.getData(json, SeriesData.class);
        System.out.println(data);

        List<SeasonData> seasonDataList = new ArrayList<>();

		for (int i = 1; i <= data.totalSeasons(); i++) {
			json = apiCall.getData(URL + seriesName.replace(" ", "+") + "&season=" + i + API_KEY);
			SeasonData seasonData = converter.getData(json, SeasonData.class);
			seasonDataList.add(seasonData);
		}
		seasonDataList.forEach(System.out::println);
    }
}
