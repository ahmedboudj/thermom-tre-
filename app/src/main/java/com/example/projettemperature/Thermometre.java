package com.example.projettemperature;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import androidx.annotation.NonNull;

public class Thermometre extends View {

    private float temp;
    private String unit;

    public Thermometre(Context context) {
        super(context);
        this.temp = 0.0f * 10;
        this.unit = "C";
    }

    public Thermometre(Context context, float temp) {
        super(context);
        this.temp = temp * 10;
        this.unit = "C";
    }

    public Thermometre(Context context, float temp, String unit) {
        super(context);
        this.temp = temp;
        this.unit = unit;
    }

    public void setTemp(float temp) {
        this.temp = temp * 22;
        invalidate();
    }

    public void setUnit(String unit) {
        this.unit = unit;
        invalidate();
    }

    private float CelsiusToFahrenheit(float celsius)
    {
        return (celsius * 9 / 5) + 32;
    }

    private float CelsiusToKelvin(float celsius) {
        return celsius + 273.15f;
    }

    private void drawEchelleCelcius(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        p.setTextSize(80);
        p.setStrokeWidth(6);
        float centerX = this.getWidth() / 2.0f;
        float centerY = this.getHeight() - 60.0f;
        float radius = 60.0f;
        float scaleLength = 100.0f;
        float scaleValueInterval = 10.0f;
        float scaleValueCelsius = 0.0f;
        float scaleInterval = (this.getHeight() - 2 * (centerY - radius)) / 10.0f;
        float scaleStartY = centerY - radius;

        for (int i = 0; i <= 10; i++) {
            canvas.drawLine(centerX - scaleLength / 2, scaleStartY, centerX + scaleLength / 2, scaleStartY, p);
            canvas.drawText(String.valueOf((int) scaleValueCelsius), centerX + scaleLength / 2 + 20, scaleStartY + 10, p);


            if (i < 10) {
                float decimalInterval = scaleInterval / 10.0f;
                for (int j = 1; j < 10; j++) {
                    float y = scaleStartY + j * decimalInterval;
                    canvas.drawLine(centerX - scaleLength / 4, y, centerX + scaleLength / 4, y, p);
                }
            }

            scaleStartY += scaleInterval;
            scaleValueCelsius += scaleValueInterval;
        }
    }


    private void drawEchelleFahrenheit(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        p.setTextSize(80);
        p.setStrokeWidth(6);
        float centerX = this.getWidth() / 2.0f;
        float centerY = this.getHeight() - 60.0f;
        float radius = 60.0f;
        float scaleLength = 100.0f;
        float scaleValueInterval = 10.0f;
        float scaleValueFahrenheit = 32.0f;
        float scaleInterval = (this.getHeight() - 2 * (centerY - radius)) / 10.0f;
        float scaleStartY = centerY - radius;

        for (int i = 0; i <= 10; i++) {
            canvas.drawLine(centerX - scaleLength / 2, scaleStartY, centerX + scaleLength / 2, scaleStartY, p);
            canvas.drawText(String.valueOf((int) scaleValueFahrenheit), centerX + scaleLength / 2 + 20, scaleStartY + 10, p);


            if (i < 10) {
                float decimalInterval = scaleInterval / 10.0f;
                for (int j = 1; j < 10; j++) {
                    float y = scaleStartY + j * decimalInterval;
                    canvas.drawLine(centerX - scaleLength / 4, y, centerX + scaleLength / 4, y, p);
                }
            }

            scaleStartY += scaleInterval;
            scaleValueFahrenheit += scaleValueInterval;
        }
    }


    private void drawEchelleKelvin(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        p.setTextSize(80);
        p.setStrokeWidth(6);
        float centerX = this.getWidth() / 2.0f;
        float centerY = this.getHeight() - 60.0f;
        float radius = 60.0f;
        float scaleLength = 100.0f;
        float scaleValueInterval = 10.0f;
        float scaleValueKelvin = 273.15f;
        float scaleInterval = (this.getHeight() - 2 * (centerY - radius)) / 10.0f;
        float scaleStartY = centerY - radius;

        for (int i = 0; i <= 10; i++) {
            canvas.drawLine(centerX - scaleLength / 2, scaleStartY, centerX + scaleLength / 2, scaleStartY, p);
            canvas.drawText(String.valueOf((int) scaleValueKelvin), centerX + scaleLength / 2 + 20, scaleStartY + 10, p);


            if (i < 10) {
                float decimalInterval = scaleInterval / 10.0f;
                for (int j = 1; j < 10; j++) {
                    float y = scaleStartY + j * decimalInterval;
                    canvas.drawLine(centerX - scaleLength / 4, y, centerX + scaleLength / 4, y, p);
                }
            }

            scaleStartY += scaleInterval;
            scaleValueKelvin += scaleValueInterval;
        }
    }


    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

    // la ligne + boule du thermomètre
        Paint p = new Paint();
        p.setColor(Color.RED);
        float centerX = this.getWidth() / 2.0f;
        float centerY = this.getHeight() - 60.0f;
        float radius = 60.0f;
        float scaleLength = 40.0f;
        p.setStrokeWidth(20);
        canvas.drawLine(centerX, centerY - temp, centerX, centerY, p);
        canvas.drawCircle(centerX, centerY, radius, p);

        // l'échelle
        p.setColor(Color.BLACK);
        p.setTextSize(80);
        p.setStrokeWidth(8);
        if (unit.equals("C")) {
            drawEchelleCelcius(canvas);
        } else if (unit.equals("F")) {
            drawEchelleFahrenheit(canvas);
        } else if (unit.equals("K")) {
            drawEchelleKelvin(canvas);
        }

        // Unité
        p.setTextSize(150);
        canvas.drawText(unit, centerX + scaleLength / 2 + 200, centerY - radius - 40, p);

        // Le rectangle
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(8);
        canvas.drawRect(200, 10, getWidth() - 200, getHeight() - 1, p);
    }
}
