package com.example.cardgames.Games.HelpingClasses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.cardgames.R;
import androidx.fragment.app.DialogFragment;

public class GameHelp extends DialogFragment {

    private TextView GameName, GameCreator;

    /**
     * The view that is created on the pop up screen
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_game_help, container, false);
        GameName = view.findViewById(R.id.GH_GameName);
        GameCreator = view.findViewById(R.id.GH_CreatorName);
        Bundle data = getArguments();
        create(view, data);
        return view;
    }

    /**
     * Creates the frame for the pop up
     * @param view the view the frame is getting added to
     * @param data the information that is going to be added
     */
    public void create(View view, Bundle data){
        GameName.setText(data.getString("gameName"));
        GameCreator.setText("By: " + data.getString("creator"));
        addParagraph(view, "Overview: ", data.getString("overview"));
        addParagraph(view, "Rules: ", data.getString("rules"));
        addParagraph(view, "Scoring: ", data.getString("scoring"));
        addParagraph(view, "Extra Info: ", data.getString("extraInfo"));

    }
    /**
     * Adds a paragraph to the screen
     * @param title title of the paragraph
     * @param body information of the paragraph
     */
    public void addParagraph(View view, String title, String body){
        addText(view, title, 20, 10);
        addText(view, body, 15, 70);
    }
    /**
     * Adds text to the screen
     * @param txt the string to be added
     * @param txtSize the size of the text
     * @param tabLeft how far to the left the text should be
     */
    public void addText(View view, String txt, int txtSize, int tabLeft){
        LinearLayout lin = view.findViewById(R.id.GH_linear);
        FrameLayout Frame = new FrameLayout(view.getContext());
        TextView text = new TextView(view.getContext());
        text.setText(txt);
        text.setTextColor(getResources().getColor(R.color.colorWhite));
        text.setTextSize(txtSize);
        text.setPadding(tabLeft, 0, 0, 10);
        Frame.addView(text);
        lin.addView(Frame);
    }
}
