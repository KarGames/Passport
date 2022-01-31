package me.kar.passport;

public enum Countries {
    UNITED_STATES(new Country("United States of America")),
    UNITED_KINGDOM(new Country("United Kingdom"));

    final Country country;

    Countries(Country country) {
        this.country = country;
    }
}
