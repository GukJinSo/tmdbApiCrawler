package guckflix.crawlservice.controller;

public abstract class TmdbApiConst {

    public final static String API_KEY = System.getenv("TMDB_API_KEY");
    public final static String URL_MOVIE = "https://api.themoviedb.org/3/movie/";
    public final static String URL_ORIGINAL_IMAGE = "https://image.tmdb.org/t/p/original/";
    public final static String URL_W500_IMAGE = "https://image.tmdb.org/t/p/w500/";
    public final static int LOOP_NUMBER = 50;
    public final static int MAX_CREDIT_PER_MOVIE = 15;
}
