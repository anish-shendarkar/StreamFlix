package com.example.streamflix.service;

import com.example.streamflix.model.Episode;
import com.example.streamflix.model.Movie;
import com.example.streamflix.model.Season;
import com.example.streamflix.model.Series;
import com.example.streamflix.repository.EpisodeRepository;
import com.example.streamflix.repository.MovieRepository;
import com.example.streamflix.repository.SeasonRepository;
import com.example.streamflix.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final MovieRepository movieRepo;
    private final SeriesRepository seriesRepo;
    private final SeasonRepository seasonRepo;
    private final EpisodeRepository episodeRepo;

    // Movie methods

    public String addMovie(Movie movie, MultipartFile thumbnail, MultipartFile videoFile) {

        try {
            System.out.println(movie);
            String thumbnailName = UUID.randomUUID() + "_" + thumbnail.getOriginalFilename();
            Path thumbnailPath = Paths.get("uploads/thumbnails").resolve(thumbnailName);
            Files.createDirectories(thumbnailPath.getParent());
            Files.copy(thumbnail.getInputStream(), thumbnailPath, StandardCopyOption.REPLACE_EXISTING);

            String videoName = UUID.randomUUID() + "_" + videoFile.getOriginalFilename();
            Path videoPath = Paths.get("uplaods/videos").resolve(videoName);
            Files.createDirectories(videoPath.getParent());
            Files.copy(videoFile.getInputStream(), videoPath, StandardCopyOption.REPLACE_EXISTING);

            movie.setThumbnail(thumbnailName);
            movie.setVideoFile(videoName);

            movieRepo.save(movie);
            return "Movie saved successfully";
        } catch (IOException e) {
            return "failed to save movie";
        }
    }

    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Movie not found"));
    }

    // Series methods

    public String addSeries(Series series, MultipartFile thumbnail) {

        try {
            String thumbnailName = UUID.randomUUID() + "_" + thumbnail.getOriginalFilename();
            Path thumbnailPath = Paths.get("uploads/thumbnails").resolve(thumbnailName);
            Files.createDirectories(thumbnailPath.getParent());
            Files.copy(thumbnail.getInputStream(), thumbnailPath, StandardCopyOption.REPLACE_EXISTING);

            series.setThumbnail(thumbnailName);
            seriesRepo.save(series);
            return "Series added succesfully";
        } catch (IOException e) {
            return "Failed to create series: " + e.getMessage();
        }
    }

    public List<Series> getAllSeries() {
        return seriesRepo.findAll();
    }

    public Series getSeriesById(Long id) {
        return seriesRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Series not found"));
    }

    public String updateSeries(Long id, Series series, MultipartFile thumbnail) {
        try {
            Series existingSeries = getSeriesById(id);

            existingSeries.setTitle(series.getTitle());
            existingSeries.setDescription(series.getDescription());
            existingSeries.setGenre(series.getGenre());
            existingSeries.setReleaseDate(series.getReleaseDate());
            existingSeries.setIsPublished(series.getIsPublished());

            if(thumbnail != null && !thumbnail.isEmpty()) {
                String thumbnailName = UUID.randomUUID() +"_"+thumbnail.getOriginalFilename();
                Path thumbnailPath = Paths.get("uploads/thumbnails").resolve(thumbnailName);
                Files.copy(thumbnail.getInputStream(), thumbnailPath, StandardCopyOption.REPLACE_EXISTING);
                existingSeries.setThumbnail(thumbnailName);
            }
            seriesRepo.save(existingSeries);
            return "Series updated successfully";
        } catch (Exception e) {
            return "Failed to update series: " + e.getMessage();
        }
    }

    public String deleteSeries(Long id) {
        try {
            seriesRepo.deleteById(id);
            return "Series deleted successfully";
        } catch (Exception e) {
            return "Failed to delete series: " + e.getMessage();
        }
    }

    // Season methods

    public String createSeason(Long seriesId, Season season) {
        try {
            Series series = getSeriesById(seriesId);
            season.setSeries(series);
            seasonRepo.save(season);

            return "Season created successfully";
        } catch (Exception e) {
            return "Failed to save season: " + e.getMessage();
        }
    }

    public List<Season> getSeasonsBySeriesId(Long seriesId) {
        return seasonRepo.findBySeriesId(seriesId);
    }

    public Season getSeasonById(Long id) {
        return seasonRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Season not found"));
    }

    public String deleteSeason(Long seasonId) {
        try {
            seasonRepo.deleteById(seasonId);
            return "Season deleted successfully";
        } catch (Exception e) {
            return "Failed to delete season: " + e.getMessage();
        }
    }

    // Episode methods

    public String uploadEpisode(Long seasonId, Episode episode, MultipartFile videoFile) {
        try {
            Season season = getSeasonById(seasonId);

            String videoName = UUID.randomUUID() + "_" + videoFile.getOriginalFilename();
            Path videoPath = Paths.get("uploads/videos").resolve(videoName);
            Files.createDirectories(videoPath.getParent());
            Files.copy(videoFile.getInputStream(), videoPath, StandardCopyOption.REPLACE_EXISTING);

            episode.setFileName(videoName);
            episode.setSeason(season);
            episodeRepo.save(episode);

            return "Episode uploaded successfully";
        } catch (Exception e) {
            return "Failed to upload episode: " + e.getMessage();
        }
    }

    public Episode getEpisodeById(Long id) {
        return episodeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Episode not found"));
    }

    public String updateEpisode(Long id, Episode episode, MultipartFile videoFile) {
        try {
            Episode existingEpisode = getEpisodeById(id);

            existingEpisode.setTitle(episode.getTitle());
            existingEpisode.setDescription(episode.getDescription());
            existingEpisode.setEpisodeNumber(episode.getEpisodeNumber());
            existingEpisode.setDuration(episode.getDuration());

            if (videoFile != null && !videoFile.isEmpty()) {
                String videoName = UUID.randomUUID() + "_" + videoFile.getOriginalFilename();
                Path videoPath = Paths.get("uploads/videos").resolve(videoName);
                Files.copy(videoFile.getInputStream(), videoPath, StandardCopyOption.REPLACE_EXISTING);
                existingEpisode.setFileName(videoName);
            }

            episodeRepo.save(existingEpisode);
            return "Episode updated successfully";
        } catch (Exception e) {
            return "Failed to update episode: " + e.getMessage();
        }
    }

    public String uploadMultipleEpisodes(Long seasonId, List<Episode> episodes, List<MultipartFile> videoFiles) {
        if (episodes.size() != videoFiles.size()) {
            return "Episode count and video file count must match";
        }

        try {
            Season season = getSeasonById(seasonId);

            for (int i = 0; i < episodes.size(); i++) {
                Episode episode = episodes.get(i);
                MultipartFile videoFile = videoFiles.get(i);

                String videoName = UUID.randomUUID() + "_" + videoFile.getOriginalFilename();
                Path videoPath = Paths.get("uploads/videos").resolve(videoName);
                Files.createDirectories(videoPath.getParent());
                Files.copy(videoFile.getInputStream(), videoPath, StandardCopyOption.REPLACE_EXISTING);

                episode.setFileName(videoName);
                episode.setSeason(season);
                episodeRepo.save(episode);
            }

            return "All episodes uploaded successfully";
        } catch (Exception e) {
            return "Failed to upload episodes: " + e.getMessage();
        }
    }

    public List<Episode> getEpisodesBySeasonId(Long seasonId) {
        return episodeRepo.findBySeasonId(seasonId);
    }

    public String deleteEpisode(Long id) {
        try {
            episodeRepo.deleteById(id);
            return "Episode deleted successfully";
        } catch (Exception e) {
            return "Failed to delete episode: " + e.getMessage();
        }
    }
}
