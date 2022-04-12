package com.game.iquestions;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

/***
 * Угадай флаг страны
 */
public class FOC_Act extends AppCompatActivity {

    /***
     * Для обращения к общим методам приложения
     */
    private MyApp app;

    /***
     * Кнопки вариантов ответа
     */
    private Button first, second, third, fourth;

    /***
     * Текст вопроса
     */
    private TextView t_question;

    /***
     * Список вопросов
     */
    private ArrayList<HashMap<String, Object>> questions = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foc);

        app = (MyApp) getApplicationContext();

        first = findViewById(R.id.Franch);
        second = findViewById(R.id.Germany);
        third = findViewById(R.id.Russian);
        fourth = findViewById(R.id.Japan);
        t_question = findViewById(R.id.t_question);

        // Не обязательно создавать переменную для хранения ссылки на кнопку,
        // если с ней производится только одно действие.
        // Тем более при выходе из onCreate, переменная исчезнет
//        Button back = findViewById(R.id.back);
        findViewById(R.id.back).setOnClickListener(view -> {
            Intent back1 = new Intent(FOC_Act.this, LevelsActivity.class);
            startActivity(back1);
        });

        initQuestions();

        setNewQuestion();
    }

    //Создать список вопросов

    private void initQuestions() {
        // очистим список создав новый экземпляр списка
        questions = new ArrayList<>();
        addNextQuestion(questions, "Это флаг какой страны? россия", R.drawable.flag_rf_enl, "Франция", "Германия", "Россия", "Япония", "Россия");
        addNextQuestion(questions, "Это флаг какой страны? франция", R.drawable.flag_frantsija_new, "Франция", "Германия", "Россия", "Япония", "Франция");
        addNextQuestion(questions, "Это флаг какой страны? германия", R.drawable.flag_germanija_enl, "Франция", "Германия", "Россия", "Япония", "Германия");
        addNextQuestion(questions, "Это флаг какой страны? украина", R.drawable.flag_ukraina_new, "Франция", "Германия", "Россия", "Украина", "Украина");
    }

    //Добавить вопрос в список

    private void addNextQuestion(ArrayList<HashMap<String, Object>> list, String quest, int img, String a1, String a2, String a3, String a4, String right) {
        // варианты
        ArrayList<String> variants = new ArrayList<>();
        variants.add(a1);
        variants.add(a2);
        variants.add(a3);
        variants.add(a4);
        // все данные по вопросу
        HashMap<String, Object> map = new HashMap<>();
        map.put("question", quest);
        map.put("image", img);
        map.put("right", right);
        map.put("variants", variants);
        // добавить
        list.add(map);
    }

    //Показать новый вопрос

    private void setNewQuestion() {
        int size = questions.size();

        if (size > 0) {
            // вопросы ещё не кончились
            int q = size > 1 ? new Random().nextInt(size - 1) : 0;
            HashMap<String, Object> map = questions.get(q);

            // удалить вопрос из списка
            questions.remove(map);

            // массив кнопок для вариантов ответов
            Button[] v = new Button[] {first, second, third, fourth};

            ArrayList<String> var = (ArrayList<String>) map.get("variants");
            Collections.shuffle(var);

            // прячем все кнопки
            for (Button b : v) b.setVisibility(View.GONE);

            // пишем текст на кнопках

            int i = 0;
            for (String text : var) {
                v[i].setText(text);
                // показываем только кнопки с имеющимися вариантами
                v[i].setVisibility(View.VISIBLE);

                // назначаем реакцию на нажатие
                if (text.equals(map.get("right").toString())) {
                    v[i].setOnClickListener(v1 -> {
                        app.showToastShort("Правильный ответ");
                        setNewQuestion();
                    });
                } else {
                    v[i].setOnClickListener(v12 -> app.showToastShort("Неправильный ответ"));
                }

                i++;
            }

            t_question.setText(map.get("question").toString());

            //TODO добавить ImageView на макет страницы для картинки флага


        } else {
            // конец опроса
            endQuestions();
        }
    }


    private void endQuestions() {
        app.showToastShort("Вопросы кончились");
    }

    public void onBackPressed(){app.showToastShort("Нельзя вернуться назда!");}
}