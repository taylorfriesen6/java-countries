package taylor.friesen.countries.repositories;

import org.springframework.data.repository.CrudRepository;
import taylor.friesen.countries.models.Country;

public interface CountryRepository extends CrudRepository<Country, Long> {
}
