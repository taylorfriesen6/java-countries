package taylor.friesen.countries.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import taylor.friesen.countries.models.Country;
import taylor.friesen.countries.repositories.CountryRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping(value = "/names/all", produces = "application/json")
    public ResponseEntity<List<Country>> allNames () {
        List<Country> countries = new ArrayList<>();
        countryRepository.findAll().iterator().forEachRemaining(countries::add);
        countries.sort(Comparator.comparing(Country::getName));
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @GetMapping(value = "/names/start/{letter}", produces = "application/json")
    public ResponseEntity<List<Country>> startWith (@PathVariable char letter) {
        List<Country> countries = new ArrayList<>();
        countryRepository.findAll().iterator().forEachRemaining(countries::add);
        List<Country> answer = countries
                .stream()
                .filter(c -> Character.toLowerCase(c.getName().charAt(0)) == letter)
                .sorted(Comparator.comparing(Country::getName))
                .collect(Collectors.toList());
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @GetMapping(value = "/population/total")
    public ResponseEntity<Void> total () {
        List<Country> countries = new ArrayList<>();
        countryRepository.findAll().iterator().forEachRemaining(countries::add);
        long sum = countries.stream().mapToLong(Country::getPopulation).sum();
        System.out.println("The Total Population Is " + sum);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
