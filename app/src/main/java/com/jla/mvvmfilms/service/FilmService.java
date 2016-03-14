package com.jla.mvvmfilms.service;

import com.jla.mvvmfilms.service.callback.FilmCallback;
import com.jla.mvvmfilms.service.callback.PopularFilmsCallback;
import com.jla.mvvmfilms.service.job.GetFilmJob;
import com.jla.mvvmfilms.service.job.GetPopularFilmsJob;
import com.path.android.jobqueue.JobManager;

public class FilmService {

    private final JobManager jobManager;

    public FilmService(JobManager jobManager) {
        this.jobManager = jobManager;
    }

    public void getPopularFilms(PopularFilmsCallback callback) {
        jobManager.addJob(new GetPopularFilmsJob(callback));
    }

    public void getFilm(int id, FilmCallback callback) {
        jobManager.addJob(new GetFilmJob(id, callback));
    }
}
