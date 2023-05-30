package com.example.cal;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv,solutionTv;
    MaterialButton buttonC,buttonBracketOpen,buttonBracketClose;
    MaterialButton buttonDivide,buttonPlus,buttonMultiply,buttonMinus,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAc,buttonDot;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv=findViewById(R.id.result_text);
        solutionTv=findViewById((R.id.sol_text));


        setId(buttonBracketClose,R.id.button_closeBracket);
        setId(buttonBracketOpen,R.id.button_openBracket);
        setId(buttonDivide,R.id.button_divide);
        setId(buttonPlus,R.id.button_Plus);
        setId(buttonMultiply,R.id.button_Multiply);
        setId(buttonMinus,R.id.button_Minus);
        setId(buttonEquals,R.id.button_Equal);
        setId(button0,R.id.button_Zero);
        setId(button1,R.id.button_1);
        setId(button2,R.id.button_2);
        setId(button3,R.id.button_3);
        setId(button4,R.id.button_4);
        setId(button5,R.id.button_5);
        setId(button6,R.id.button_6);
        setId(button7,R.id.button_7);
        setId(button8,R.id.button_8);
        setId(button9,R.id.button_9);
        setId(buttonAc,R.id.AC);
        setId(buttonDot,R.id.button_Dot);
        setId(buttonC,R.id.button_c);
    }
    void setId(MaterialButton btn, int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        MaterialButton button= (MaterialButton) view;
        String buttonText=button.getText().toString();
        String dataToCalculate=solutionTv.getText().toString();

        solutionTv.setText(dataToCalculate+buttonText);

        if(buttonText.equals("C")){
            dataToCalculate=dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate=dataToCalculate+buttonText;
        }
        String finalResult=getResult(dataToCalculate);



        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if(!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }
    }

    String getResult(String Data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initSafeStandardObjects();
            String finalResult=context.evaluateString(scriptable,Data,"Javascript",1,null).toString();
            return  finalResult;
        }catch (Exception e){
            return "Err";
        }
    }


}