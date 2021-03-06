package imonoko.androiddevfinalproject;

import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import static android.util.TypedValue.COMPLEX_UNIT_PX;

public class Rules extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int width = size.x;
        int height = size.y;
        TextView rules = new TextView(this);
        TextView rulesHTP = new TextView(this);
        TextView rulesWin = new TextView(this);
        TextView rulesLoss = new TextView(this);
        TextView rulesWinD = new TextView(this);
        TextView rulesLossD = new TextView(this);
        ScrollView SV = new ScrollView(this);
        LinearLayout LR = new LinearLayout(this);
        LR.setOrientation(LinearLayout.VERTICAL);
        LR.setBackgroundColor(Color.BLACK);

        rulesHTP.setText("How to Play");
        rulesHTP.setGravity(Gravity.CENTER_HORIZONTAL);
        rulesHTP.setTextSize(COMPLEX_UNIT_PX,70f);
        rulesHTP.setTextColor(Color.GREEN);

        rules.setText("\n1. On startup all user will get chance/turn to roll one die. Highest die roll goes first." +
                " If it is a draw this must continue until it is established who goes first." +
                "\n\n2. A winner will be determined by the first person who wins 2 rounds. " +
                "\n\n3. The first player who goes will roll first until he/she reaches point." +
                "\nA point is when two of the three dice are the same, and the odd die is point. Ex{2|2|5} 5 is your point." +
                "\n\n4. Once bank has point, the other player now has to roll higher to beat the banker's point. " +
                "If the player who goes second rolls the same point as the first player, then the first player wins the round.\n\n");
        rules.setGravity(Gravity.CENTER_HORIZONTAL);
        rules.setTextSize(COMPLEX_UNIT_PX,50f);
        rules.setTextColor(Color.WHITE);

        rulesWin.setText("Automatic Wins");
        rulesWin.setGravity(Gravity.CENTER_HORIZONTAL);
        rulesWin.setTextSize(COMPLEX_UNIT_PX,70f);
        rulesWin.setTextColor(Color.GREEN);

        rulesWinD.setText("Trips: All three die land on the same number.\nCeelo: Rolling a 4, 5, and 6.\nRolling a Point of 6.\n\n");
        rulesWinD.setGravity(Gravity.CENTER_HORIZONTAL);
        rulesWinD.setTextSize(COMPLEX_UNIT_PX,50f);
        rulesWinD.setTextColor(Color.WHITE);

        rulesLoss.setText("Automatic Loss");
        rulesLoss.setGravity(Gravity.CENTER_HORIZONTAL);
        rulesLoss.setTextSize(COMPLEX_UNIT_PX,70f);
        rulesLoss.setTextColor(Color.GREEN);

        rulesLossD.setText("Rolling 1,2,3 is also an automatic loss.\n\n\n");
        rulesLossD.setGravity(Gravity.CENTER_HORIZONTAL);
        rulesLossD.setTextSize(COMPLEX_UNIT_PX,50f);
        rulesLossD.setTextColor(Color.WHITE);

        ButtonHandler bh = new ButtonHandler();
        Button back = new Button(this);
        back.setOnClickListener(bh);
        back.setText("BACK");
        back.setTextSize(20f);

        LR.addView(back, (int)(width*.35),(int) (height*.1) );
        LR.addView(rulesHTP);
        LR.addView(rules);
        LR.addView(rulesWin);
        LR.addView(rulesWinD);
        LR.addView(rulesLoss);
        LR.addView(rulesLossD);

        SV.addView(LR);
        setContentView(SV);
    }
    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {finish();}
    }
}
