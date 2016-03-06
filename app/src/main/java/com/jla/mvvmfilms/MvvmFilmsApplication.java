package com.jla.mvvmfilms;

import com.jla.mvvmfilms.service.FilmService;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.config.Configuration;
import com.path.android.jobqueue.log.CustomLogger;

import android.app.Application;
import android.util.Log;

public class MvvmFilmsApplication extends Application {

    private static MvvmFilmsApplication instance;
    private JobManager jobManager;
    private FilmService filmService;

    public MvvmFilmsApplication() {
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        configureJobManager();
        filmService = new FilmService(jobManager);
    }

    private void configureJobManager() {
        Configuration configuration = new Configuration.Builder(this)
                .customLogger(new CustomLogger() {
                    private static final String TAG = "JOBS";

                    @Override
                    public boolean isDebugEnabled() {
                        return true;
                    }

                    @Override
                    public void d(String text, Object... args) {
                        Log.d(TAG, String.format(text, args));
                    }

                    @Override
                    public void e(Throwable t, String text, Object... args) {
                        Log.e(TAG, String.format(text, args), t);
                    }

                    @Override
                    public void e(String text, Object... args) {
                        Log.e(TAG, String.format(text, args));
                    }
                })
                .minConsumerCount(1)
                .maxConsumerCount(3)
                .loadFactor(1)
                .build();
        jobManager = new JobManager(this, configuration);
    }

    public FilmService getFilmService() {
        return filmService;
    }

    public static MvvmFilmsApplication getInstance() {
        return instance;
    }
}
