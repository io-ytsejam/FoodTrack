package backend.main.java.com.backend;

class Test {

    private final String country;

    private final String city;

    private final double temperature;

    Test(String country, String city, Test weather) {
        this.country = country;
        this.city = city;
        this.temperature = weather.getTemperature();
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public double getTemperature() {
        return temperature;
    }


}