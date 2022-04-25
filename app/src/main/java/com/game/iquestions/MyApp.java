package com.game.iquestions;

import android.app.Application;
import android.widget.Toast;


/***
 * Главный код приложения, в котором имеются общедоступные методы и утилиты
 */
public class MyApp extends Application {

    /***
     * Показать всплывающее сообщение Toast долго
     * @param text текст сообщения
     */
    public void showToastLong(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    /***
     * Показать всплывающее сообщение Toast коротко
     * @param text текст сообщения
     */
    public void showToastShort(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

}