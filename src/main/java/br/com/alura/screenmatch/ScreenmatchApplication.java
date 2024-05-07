package br.com.alura.screenmatch;

import br.com.alura.screenmatch.module.SeriesData;
import br.com.alura.screenmatch.service.ApiCall;
import br.com.alura.screenmatch.service.DataConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ApiCall apiCall = new ApiCall();
		String json = apiCall.getData("http://www.omdbapi.com/?t=gilmore+girls&apikey=3dd5799f");
		System.out.println(json);

		DataConverter converter = new DataConverter();
		SeriesData data = converter.getData(json, SeriesData.class);
		System.out.println(data);
	}
}
