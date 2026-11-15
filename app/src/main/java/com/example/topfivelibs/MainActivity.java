package com.example.topfivelibs;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;

import java.util.ArrayList;
import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {

    private ExoPlayer exoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        LineChart lineChart = findViewById(R.id.lineChart);
        Button btnCheckStatus = findViewById(R.id.btnCheckStatus);
        LottieAnimationView lottieView = findViewById(R.id.lottieView);
        PlayerView playerView = findViewById(R.id.playerView);
        PhotoView photoView = findViewById(R.id.photoView);

        setupChart(lineChart);
        btnCheckStatus.setOnClickListener(v -> {
            Toasty.success(this, "Сервер працює стабільно! ТПС: 20.0", Toasty.LENGTH_SHORT, true).show();
        });

        lottieView.setAnimationFromUrl("https://assets8.lottiefiles.com/packages/lf20_touohxv0.json");

        exoPlayer = new ExoPlayer.Builder(this).build();
        playerView.setPlayer(exoPlayer);
        MediaItem mediaItem = MediaItem.fromUri("https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4");
        exoPlayer.setMediaItem(mediaItem);
        exoPlayer.prepare();
        photoView.setImageResource(R.mipmap.ic_launcher);
    }

    private void setupChart(LineChart chart) {
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 20f));
        entries.add(new Entry(1, 45f));
        entries.add(new Entry(2, 30f));
        entries.add(new Entry(3, 85f));
        entries.add(new Entry(4, 120f));
        entries.add(new Entry(5, 90f));

        LineDataSet dataSet = new LineDataSet(entries, "Онлайн гравців");
        dataSet.setColor(Color.parseColor("#00E676"));
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTextSize(12f);
        dataSet.setLineWidth(3f);
        dataSet.setCircleColor(Color.parseColor("#19558C"));
        dataSet.setCircleRadius(5f);
        dataSet.setDrawFilled(true);
        dataSet.setFillColor(Color.parseColor("#004D40"));

        chart.setData(new LineData(dataSet));
        chart.setBackgroundColor(Color.parseColor("#121212"));
        chart.getAxisLeft().setTextColor(Color.WHITE);
        chart.getAxisRight().setEnabled(false);
        chart.getLegend().setTextColor(Color.WHITE);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"00:00", "04:00", "08:00", "12:00", "16:00", "20:00"}));
        xAxis.setGranularity(1f);

        Description desc = new Description();
        desc.setText("Статистика за добу");
        desc.setTextColor(Color.LTGRAY);
        chart.setDescription(desc);
        chart.animateX(1500);
        chart.invalidate();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (exoPlayer != null) {
            exoPlayer.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (exoPlayer != null) {
            exoPlayer.release();
        }
    }
}
