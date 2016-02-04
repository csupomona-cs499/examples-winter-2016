package cs499.cpp.edu.l06_network.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by yusun on 2/3/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {

    private Coord coord;
    private List<WeatherRecord> weather;
    private String base;
    private MainRecord main;
    private Wind wind;
    private Clouds clouds;
    private long dt;
    private int id;
    private int visibility;
    private String name;
    private int cod;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MainRecord getMain() {
        return main;
    }

    public void setMain(MainRecord main) {
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WeatherRecord> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherRecord> weather) {
        this.weather = weather;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }
}
