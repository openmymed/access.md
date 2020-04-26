package me.kisoft.covid19.services;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class BackgroundTask extends Worker {
    private static final String TAG="BackgroundCheck";

    public BackgroundTask(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        //Work to be done here
        Log.e(TAG,"Worker Started work");
        return Result.success();
    }
}
