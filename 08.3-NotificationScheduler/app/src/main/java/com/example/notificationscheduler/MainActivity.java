package com.example.notificationscheduler;

import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/*
    https://codelabs.developers.google.com/codelabs/android-training-job-scheduler

 */
public class MainActivity extends AppCompatActivity implements
        View.OnClickListener, SeekBar.OnSeekBarChangeListener{
    private static final int JOB_ID = 0;
    private JobScheduler mScheduler;
    //Switches for setting job options
    private Switch mDeviceIdleSwitch;
    private Switch mDeviceChargingSwitch;
    TextView seekBarProgress;
    //
    //Override deadline seekbar
    private SeekBar mSeekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        Button button=findViewById(R.id.button_sch_job);
        button.setOnClickListener(this);
        button=findViewById(R.id.button_stop_job);
        button.setOnClickListener(this);
        //
        mDeviceIdleSwitch = findViewById(R.id.idleSwitch);
        mDeviceChargingSwitch = findViewById(R.id.chargingSwitch);
        //
        mSeekBar = findViewById(R.id.seekBar);
        mSeekBar.setOnSeekBarChangeListener(this);
        seekBarProgress = findViewById(R.id.seekBarProgress);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.button_sch_job) {
            scheduleJob();
        } else if (v.getId()==R.id.button_stop_job) {
            cancelJobs();
        }
    }

    private void cancelJobs() {
        if (mScheduler!=null){
            mScheduler.cancelAll();
            mScheduler = null;
            Toast.makeText(this, "Jobs cancelled", Toast.LENGTH_SHORT).show();
        }

    }

    private void scheduleJob() {
        int seekBarInteger = mSeekBar.getProgress();
        boolean seekBarSet = seekBarInteger > 0;

        RadioGroup networkOptions = findViewById(R.id.networkOptions);
        int selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE;
        int selectedNetworkID = networkOptions.getCheckedRadioButtonId();
        switch(selectedNetworkID){
            case R.id.noNetwork:
                selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE;
                break;
            case R.id.anyNetwork:
                selectedNetworkOption = JobInfo.NETWORK_TYPE_ANY;
                break;
            case R.id.wifiNetwork:
                selectedNetworkOption = JobInfo.NETWORK_TYPE_UNMETERED;
                break;
        }
        mScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        //
        ComponentName serviceName = new ComponentName(getPackageName(),
                NotificationJobService.class.getName());
        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, serviceName);
        builder.setRequiredNetworkType(selectedNetworkOption)
                .setRequiresDeviceIdle(mDeviceIdleSwitch.isChecked())
                .setRequiresCharging(mDeviceChargingSwitch.isChecked());
        if (seekBarSet) {
            builder.setOverrideDeadline(seekBarInteger * 1000);
        }
        //
        boolean constraintSet = selectedNetworkOption != JobInfo.NETWORK_TYPE_NONE
                || mDeviceChargingSwitch.isChecked() || mDeviceIdleSwitch.isChecked()
                || seekBarSet;
        if(constraintSet) {
            JobInfo myJobInfo = builder.build();
            // schedule the job which sends notification to the user
            mScheduler.schedule(myJobInfo);
            Toast.makeText(this, "Job Scheduled, job will run when " +
                    "the constraints are met.", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Please set at least one constraint",
                    Toast.LENGTH_SHORT).show();
        }
    }

    // seekBar event listeners
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (progress > 0){
            seekBarProgress.setText(progress + " s");
        }else {
            seekBarProgress.setText("Not Set");
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
