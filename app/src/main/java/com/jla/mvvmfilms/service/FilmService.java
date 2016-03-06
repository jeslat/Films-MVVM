package com.jla.mvvmfilms.service;

import com.path.android.jobqueue.JobManager;

public class FilmService {

    private final JobManager jobManager;

    public FilmService(JobManager jobManager) {
        this.jobManager = jobManager;
    }

    public void getPopularFilms(PopularFilmsCallback callback) {
        jobManager.addJob(new GetPopularFilmsJob(callback));
    }

    public void getPopularFilm(int id) {
    }
}
