package com.example.streamflix.service;

import com.example.streamflix.model.Movie;
import com.example.streamflix.model.Series;
import com.example.streamflix.repository.MovieRepository;
import com.example.streamflix.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final MovieRepository movieRepo;
    private final SeriesRepository seriesRepo;

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

    public String addSeries(Series series) {
        seriesRepo.save(series);
        return "Series saved";
    }
}
