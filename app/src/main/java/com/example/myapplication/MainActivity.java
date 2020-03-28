package com.example.myapplication;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;

import org.mariuszgromada.math.mxparser.Expression;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button Btn9, Btn8, Btn7, Btn6, Btn5, Btn4, Btn3, Btn2, Btn1, Btn0, DotBtn, MulBtn, DivBtn, ModBtn, AddBtn, SubBtn, EqualBtn, CloseBracket, OpenBracket, Inverse, Sin, Cos, Tan;
    ImageView CutBtn, MenuBtn;
    Button Button;
    TextView Result, Equation;
    RelativeLayout topPanel, layoutMain;
    LinearLayout Buttons, Additional_Operators;
    int Obrac = 0, Cbrac = 0;
    boolean CanAddOpr = false;
    boolean flag = true;
    boolean DotFlag = true;
    boolean Equal = false;
    boolean InverseFlag = true;
    ArrayList<String> Eqn = new ArrayList<>();
    ArrayList<String> Res = new ArrayList<>();
    GestureDetector gestureDetector;

    PagerAdapter pagerAdapter;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentOne());
        fragments.add(new FragmentTwo());

        pagerAdapter = new PagerViewAdapter(getSupportFragmentManager(), fragments);

//        Button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (InverseFlag) {
//                    Additional_Operators.setVisibility(View.VISIBLE);
//                    InverseFlag = false;
//                } else {
//                    Additional_Operators.setVisibility(View.INVISIBLE);
//                    InverseFlag = true;
//                }
//            }
//        });
        Sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trigonometry("sin");
            }
        });
        Cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trigonometry("cos");
            }
        });
        Tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trigonometry("tan");
            }
        });

        gestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {

            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (e2.getY() - e1.getY() > 50) {
                    Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                    intent.putStringArrayListExtra("Equation", Eqn);
                    intent.putStringArrayListExtra("Result", Res);
                    intent.putExtra("CurrentEquation", getEquation());
                    intent.putExtra("CurrentResult", getResult());

                    Pair<View, String> pair1 = new Pair<>(findViewById(R.id.Equation), "Equation");
                    Pair<View, String> pair2 = new Pair<>(findViewById(R.id.Result), "Result");
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, pair1, pair2);
                    startActivity(intent, options.toBundle());
                    Toast.makeText(getApplicationContext(), "DOWN", Toast.LENGTH_SHORT).show();

                }
                return true;
            }
        });

        topPanel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });
        MenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPopUp(v);
            }
        });
        DotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dot();
            }
        });
        OpenBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Brackets("(");
            }
        });
        CloseBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Brackets(")");
            }
        });
        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddSubOpr("+");
            }
        });
        SubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddSubOpr("-");
            }
        });
        MulBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MulDiv("*");
            }
        });
        DivBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MulDiv("/");
            }
        });
        Btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppendNumber("0");
            }
        });
        Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppendNumber("1");
            }
        });
        Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppendNumber("2");
            }
        });
        Btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppendNumber("3");
            }
        });
        Btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppendNumber("4");
            }
        });
        Btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppendNumber("5");
            }
        });
        Btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppendNumber("6");
            }
        });
        Btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppendNumber("7");
            }
        });
        Btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppendNumber("8");
            }
        });
        Btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppendNumber("9");
            }
        });
        CutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cut();
            }
        });
        CutBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Clear();
                return false;
            }
        });
        EqualBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Evaluation();
            }
        });
    }

    private void ShowPopUp(View v) {
        PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v);
        popupMenu.inflate(R.menu.menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.MenuHistory:
                        HistoryActivity();
                        return true;
                    case R.id.MenuDarkMode:
                        Toast.makeText(MainActivity.this, "Dark Mode Enable", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.MenuAbout:
                        Toast.makeText(MainActivity.this, "About this Application", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }
        });
        popupMenu.show();
    }


    private void Cut() {
        if (getEquation().endsWith("(")) {
            Obrac--;
        }
        if (getEquation().endsWith(".")) {
            DotFlag = true;
        }
        if (getEquation().endsWith(")")) {
            Obrac++;
        }
        if (getEquation().endsWith("sin(") || getEquation().endsWith("cos(") || getEquation().endsWith("tan(")) {
            Result.setText("");
            String a = Equation.getText().toString();
            String b = a.substring(0, a.length() - 4);
            Equation.setText(b);
        } else if (!getEquation().isEmpty()) {
            Result.setText("");
            String a = Equation.getText().toString();
            String b = a.substring(0, a.length() - 1);
            Equation.setText(b);
        } else if (getEquation().length() == 0) {
            CanAddOpr = false;
        }
    }

    private void Clear() {
        DotFlag = true;
        Result.setText("");
        Equation.setText("");
        Cbrac = 0;
        Obrac = 0;
        flag = true;
        Equal = false;
        CanAddOpr = false;
    }

    @SuppressLint("SetTextI18n")
    private void dot() {
        if (DotFlag) {
            if (!getResult().isEmpty()) {
                Result.setText("");
                Equation.setText("0.");
            }
            if (getEquation().endsWith("+") || getEquation().endsWith("-") || getEquation().endsWith("*") || getEquation().endsWith("/")) {
                Equation.setText(getEquation() + "0.");
            }
            if (getEquation().isEmpty()) {
                Equation.setText("0.");
            } else if (!getEquation().endsWith(".")) {
                Equation.setText(getEquation() + ".");
            }
            DotFlag = false;
        }
    }

    @SuppressLint("SetTextI18n")
    private void Brackets(String s) {
        if (getResult().isEmpty()) {
            if (s.equals("(")) {
                Equation.setText(getEquation() + s);
                Obrac++;
            }
            if (s.equals(")")) {
                Equation.setText(getEquation() + s);
                Cbrac++;
            }
        } else {
            if (s.equals("(")) {
                Equation.setText(getResult() + s);
                Obrac++;
            }
            if (s.equals(")")) {
                Equation.setText(getResult() + s);
                Cbrac++;
            }
            Result.setText("");
        }
    }

    @SuppressLint("SetTextI18n")
    private void AddSubOpr(String s) {
        DotFlag = true;
        if (getEquation().endsWith(".")) {
            Equation.setText(getEquation() + "0" + s);
        }
        if (!getResult().isEmpty()) {
            Equation.setText(Result.getText().toString() + s);
            Result.setText("");
        } else if (getEquation().isEmpty()) {
            Equation.setText(s);
        } else if (getEquation().equals("+") || getEquation().equals("-")) {
            Equation.setText(s);
        } else {
            String a = Equation.getText().toString();
            if (a.endsWith("/") || a.endsWith("*") || a.endsWith("-") || a.endsWith("+")) {
                String b = a.substring(0, a.length() - 1);
                Equation.setText(b + s);
            } else {
                Equation.setText(getEquation() + s);
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void MulDiv(String s) {
        DotFlag = true;
        if (getEquation().endsWith(".")) {
            Equation.setText(getEquation() + "0" + s);
        }
        if (getEquation().endsWith("(")) {
            CanAddOpr = false;
        }
        if (!getResult().isEmpty()) {
            Equation.setText(Result.getText().toString() + s);
            Result.setText("");
        } else if (CanAddOpr) {
            String a = Equation.getText().toString();
            if (a.endsWith("/") || a.endsWith("*") || a.endsWith("-") || a.endsWith("+")) {
                String b = a.substring(0, a.length() - 1);
                Equation.setText(b + s);
            } else {
                Equation.setText(getEquation() + s);
            }
        }
    }


    @SuppressLint("SetTextI18n")
    private void AppendNumber(String s) {
        if (getResult().isEmpty()) {
            Equation.setText(getEquation() + s);
        } else {
            Equation.setText("");
            Result.setText("");
            Equation.setText(s);
        }
        CanAddOpr = true;
    }

    private void Evaluation() {
        if (!getEquation().isEmpty()) {
            Equal = true;
            String s = Equation.getText().toString();
            String[] arr = {"+", "-", "*", "/", "%"};
            String a = s.substring(s.length() - 1);
            for (int i = 0; i < 4; i++) {
                if (a.equals(arr[i])) {
                    Equation.setText(s.substring(0, s.length() - 1));
                }
            }
            if (getEquation().length() == 2 && getEquation().startsWith("(") && getEquation().endsWith(")")) {
                Equation.setText("");
            } else if (Obrac == Cbrac) {
                Expression e = new Expression(Equation.getText().toString());
                DecimalFormat df = new DecimalFormat("#.##");
                String ans = df.format(e.calculate());
                Result.setText(ans);
                Eqn.add(Equation.getText().toString());
                Res.add(Result.getText().toString());
            } else {
                Toast.makeText(this, "Missing Bracket", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initialize() {
//        Drawer = findViewById(R.id.Drawer);
//        navigationView = findViewById(R.id.NavigationView);
//        pi = findViewById(R.id.pi);
        Sin = findViewById(R.id.Sin);
        Cos = findViewById(R.id.Cos);
        Tan = findViewById(R.id.tan);
//        Inverse = findViewById(R.id.Inverse);
//        Additional_Operators = findViewById(R.id.Additional_Operators);
        MenuBtn = findViewById(R.id.MenuBtn);
        Buttons = findViewById(R.id.Buttons);
        OpenBracket = findViewById(R.id.OpenBracket);
        CloseBracket = findViewById(R.id.CloseBracket);
        topPanel = findViewById(R.id.topPanel);
        layoutMain = findViewById(R.id.layoutMain);
        Btn0 = findViewById(R.id.Btn0);
        Btn1 = findViewById(R.id.Btn1);
        Btn2 = findViewById(R.id.Btn2);
        Btn3 = findViewById(R.id.Btn3);
        Btn4 = findViewById(R.id.Btn4);
        Btn5 = findViewById(R.id.Btn5);
        Btn6 = findViewById(R.id.Btn6);
        Btn7 = findViewById(R.id.Btn7);
        Btn8 = findViewById(R.id.Btn8);
        Btn9 = findViewById(R.id.Btn9);
        DotBtn = findViewById(R.id.DotBtn);
        MulBtn = findViewById(R.id.MulBtn);
        ModBtn = findViewById(R.id.ModBtn);
        AddBtn = findViewById(R.id.AddBtn);
        SubBtn = findViewById(R.id.SubBtn);
        EqualBtn = findViewById(R.id.EqualBtn);
        CutBtn = findViewById(R.id.CutBtn);
        Equation = findViewById(R.id.Equation);
        Result = findViewById(R.id.Result);
        DivBtn = findViewById(R.id.DivBtn);
    }

    @TargetApi(Build.VERSION_CODES.P)
    private void HistoryActivity() {
        Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
        intent.putStringArrayListExtra("Equation", Eqn);
        intent.putStringArrayListExtra("Result", Res);
        intent.putExtra("CurrentEquation", getEquation());
        intent.putExtra("CurrentResult", getResult());
        androidx.core.util.Pair<View, String> pair1 = new androidx.core.util.Pair<>(findViewById(R.id.Equation), "Equation");
        androidx.core.util.Pair<View, String> pair2 = new androidx.core.util.Pair<>(findViewById(R.id.Result), "Result");
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, pair1, pair2);
        startActivity(intent, options.toBundle());
    }

    private String getEquation() {
        return Equation.getText().toString();
    }

    private String getResult() {
        return Result.getText().toString();
    }

    @SuppressLint("SetTextI18n")
    private void trigonometry(String s) {
        Equation.setText(getEquation() + s);
        Brackets("(");
    }
}