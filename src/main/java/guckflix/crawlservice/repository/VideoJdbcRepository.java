package guckflix.crawlservice.repository;

import guckflix.crawlservice.dto.VideoRequestResults.VideoDto;
import guckflix.crawlservice.entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class VideoJdbcRepository {

    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    @Autowired
    public VideoJdbcRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    public void bulkSave(List<Video> list) {
        batchInsert(1000, list);
    }

    private void batchInsert(int count, List<Video> list) {

        jdbcTemplate.batchUpdate(
                "INSERT INTO video " +
                        "(video_id, movie_id, iso_3166, iso_639, video_key, video_name, official, published_at, site, video_type)" +
                        " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                list,
                count, (ps, argument)-> {
                    ps.setString(1, argument.getId());
                    ps.setLong(2, argument.getMovieId());
                    ps.setString(3, argument.getIso3166().toString());
                    ps.setString(4, argument.getIso639().toString());
                    ps.setString(5, argument.getKey());
                    ps.setString(6, argument.getName());
                    ps.setInt(7, argument.getOfficial() == true ? 1 : 0);
                    ps.setTimestamp(8, Timestamp.valueOf(argument.getPublishedAt()));
                    ps.setString(9, argument.getSite().toString());
                    ps.setString(10, argument.getType().toString());
                }
        );
    }
}
