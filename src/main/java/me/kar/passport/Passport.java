package me.kar.passport;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Passport {
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    long age;
    LocalDate dateOfIssue;
    LocalDate dateOfExpiry;
    Country countryIn;
    List<Country> countriesAllowed;

    public Passport(String firstName, String lastName, LocalDate dateOfBirth, Country countryIn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.age = LocalDate.now().minus(dateOfBirth.getYear(), ChronoUnit.YEARS).getYear();
        this.dateOfIssue = LocalDate.now();
        this.dateOfExpiry = this.dateOfIssue.plus(10, ChronoUnit.YEARS);
        this.countryIn = countryIn;
        this.countriesAllowed = new ArrayList<>();
    }

    void goToNewCountry(Country newCountry) {
        for (Country country : countriesAllowed) {
            if (newCountry != country && country != countryIn) {
                return;
            }
        }
        countryIn = newCountry;
    }

    void setAvailableCountries() {
        if (this.age >= 18) {
            for (Countries country : Countries.values()) {
                this.countriesAllowed.add(country.country);
            }
        } else {
            throw new IllegalStateException("too young to fly (must be 18 or older)!");
        }
    }

    public static void main(String[] args) {
        Passport passport = new Passport("John", "Doe", LocalDate.of(2003, 2, 24), Countries.UNITED_STATES.country);

        passport.setAvailableCountries();
        System.out.println(passport.countryIn.name);
        passport.goToNewCountry(Countries.UNITED_KINGDOM.country);

        System.out.println(passport.countryIn.name);
    }
}
