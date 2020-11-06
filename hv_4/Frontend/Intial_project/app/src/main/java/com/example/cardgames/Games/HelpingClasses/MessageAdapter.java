package com.example.cardgames.Games.HelpingClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.cardgames.R;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MessageAdapter extends ArrayAdapter<Message> {

    private static final int TYPE_MESSAGE_SENT = 0, TYPE_MESSAGE_RECEIVED = 1, TYPE_JOINED = 2;
    private List<Message> messages;
    private Context context;
    private int resource;

    public MessageAdapter(Context context, int layout_resource, int txt_resource, List<Message> messages){
        super(context, layout_resource, txt_resource, messages);

        this.messages = messages;
        this.resource = layout_resource;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(resource, null);
        Message m = messages.get(position);
        TextView nameTxt = view.findViewById(R.id.Chat_UserName);
        TextView msgTxt = view.findViewById(R.id.Chat_Txt);

        if(m.getType() == TYPE_MESSAGE_SENT) {
            nameTxt.setText(m.getSender());
            nameTxt.setTextColor(context.getResources().getColor(R.color.colorLightBlue));
            msgTxt.setText(m.getMessage());
        }
        else if(m.getType() == TYPE_MESSAGE_RECEIVED){
            nameTxt.setText(m.getSender());
            nameTxt.setTextColor(context.getResources().getColor(R.color.colorDarkGrey));
            msgTxt.setText(m.getMessage());
        }
        else{
            nameTxt.setText("");
            msgTxt.setText(m.getSender() + " " + m.getMessage());
            msgTxt.setTextSize(16);
        }
        return view;
    }
}
