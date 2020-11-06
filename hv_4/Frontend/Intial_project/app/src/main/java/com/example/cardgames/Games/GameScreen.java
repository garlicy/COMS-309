package com.example.cardgames.Games;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

import com.example.cardgames.Games.HelpingClasses.Game;
import com.example.cardgames.Games.HelpingClasses.GameHelp;
import com.example.cardgames.Games.HelpingClasses.InitiateGame;
import com.example.cardgames.Games.DefaultGames.GameState;
import com.example.cardgames.Games.HelpingClasses.Message;
import com.example.cardgames.Games.HelpingClasses.MessageAdapter;
import com.example.cardgames.Games.HelpingClasses.WebsocketHelper;
import com.example.cardgames.Settings.AppHelp;
import com.example.cardgames.Settings.AppSettings;
import com.example.cardgames.StartScreens.Lobby;
import com.example.cardgames.R;
import com.example.cardgames.Games.HelpingClasses.CardGraphics;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author Colton Hazlett
 * The main class for the game play
 */
public class GameScreen  extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener  {

    //Variable initializations
    private int numPiles, ImageID;
    private String GameName = "Golf", UserName;
    private Boolean firstChat = true;

    //Class initialization
    private ArrayList<Pile> piles;
    private ArrayList<Card> deck;
    private ArrayList<Message> messages;
    private MessageAdapter adapter;
    private Player[] players;
    private Game game;
    private CardGraphics cg;
    private GameState gs;
    private InitiateGame init;
    private Rules rules;
    private WebSocketClient client;

    //View initializations
    private ImageView userIcon, info, chat;
    private TextView username;
    private Spinner spin;
    private Button createGame, addPlayer, deal, play;
    private BottomSheetDialog ChatViewDialog;
    private View ChatSheetView;
    private ListView message_View;


    /**
     * Creates the screen
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen_vertical);
        setFrame(getIntent().getStringExtra("name"), getIntent().getIntExtra("imageID", -1));
        GameName = getIntent().getStringExtra("GameName");
        // isDefault = getIntent().getBooleanExtra("isDefault", true);

    }

    /**
     * Click listener that will control the game
     * @param view
     */
    @Override
    public void onClick(View view) {

        if(view.getId() == info.getId())
            popUpInfo();

        if(view.getId() == chat.getId()) {
            if (firstChat) {
                createChat();
                firstChat = false;
            } else
                openChat();
        }
        if(view.getId() == createGame.getId())
            openWebSocket();


        if(view.getId() == addPlayer.getId()) {
            gs.addPlayer(game.getID());
        }
        if(view.getId() == play.getId()){
            addPlayer.setVisibility(View.INVISIBLE);
            play.setVisibility(View.INVISIBLE);
            deal.setVisibility(View.VISIBLE);
        }
        if(view.getId() == deal.getId()) {
            deal.setVisibility(View.INVISIBLE);
            init.setGame(gs, game, piles, deck);
            rules = new Rules(game, piles);
            for(Pile p : piles)
                p.getView().setOnClickListener(this);

            //cg.shuffleNDeal(game, piles, deck, game.getNumPlayers(), 52);
        }
        for(Pile p : piles){
            if(view.getId() == p.getView().getId()){
                checkPileClicked(p);
            }
        }
    }
    /**
     * Listener that checks if a spinner item has been selected
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int index, long l) {
        if (adapterView.getItemAtPosition(index).equals("Settings")) {
            Intent i = new Intent(this, AppSettings.class);
            startActivity(i);
        } else if (adapterView.getItemAtPosition(index).equals("Help")) {
            Intent i = new Intent(this, AppHelp.class);
            startActivity(i);
        } else if (adapterView.getItemAtPosition(index).equals("Leave Game")) {
            Intent i = new Intent(this, Lobby.class);
            i.putExtra("name", UserName);
            i.putExtra("imageID", ImageID);
            startActivity(i);
        } else {

        }
    }

    /**
     * function to show where all the piles are to move a card
     */
    public void showBackgrounds(){
        for(Pile p : piles)
            p.getView().setVisibility(View.VISIBLE);
    }

    /**
     * Function to hide all the piles
     */
    public void hideBackgrounds(){
        for(Pile p : piles) {
            if(p.isEmpty())
                p.getView().setVisibility(View.INVISIBLE);
        }
    }

    /**
     * The game is over so this function freezes all the piles
     */
    public void unClickable(){
        for(Pile p : piles){
            p.getView().setOnClickListener(null);
        }
    }

    /**
     * Checks to see if the piles should be displayed or not
     * @param p
     */
    public void checkPileClicked(Pile p){
        if(!p.connectionClicked() && !p.isClicked()) {
            p.Clicked();
            showBackgrounds();
        }
        else{
            /*if(gs.allMoved()) {
               / rules.endHand();
                if(rules.checkCardsExhausted()) {
                    unClickable();
                    cg.gameOver(rules.getWinner());
                }
            }*/
            p.Released();
            hideBackgrounds();
        }
    }
    /**
     * Opens the game help information
     */
    public void popUpInfo(){
        Log.d("On Click", "Opening Dialog");
        GameHelp dialog = new GameHelp();
        Bundle strs = new Bundle();
        strs.putString("gameName", GameName);
        strs.putString("creator", getIntent().getStringExtra("creator"));
        strs.putString("overview", getIntent().getStringExtra("overview"));
        strs.putString("rules", getIntent().getStringExtra("rules"));
        strs.putString("scoring", getIntent().getStringExtra("scoring"));
        strs.putString("extraInfo", getIntent().getStringExtra("extraInfo"));
        dialog.setArguments(strs);
        dialog.show(getSupportFragmentManager(), "SignUpImageSelect");
    }

    /**
     * Creates the chat that will slide up on the bottom of the screen
     */
    public void createChat(){
        ChatViewDialog = new BottomSheetDialog(this, R.style.BottomSheetDialogTheme);
        ChatSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_chat_screen, (LinearLayout)findViewById(R.id.ChatSheetContainer));
        adapter = new MessageAdapter(this, R.layout.item_message, R.id.Chat_UserName, messages);
        message_View = ChatSheetView.findViewById(R.id.messages_view);
        message_View.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        message_View.setAdapter(adapter);
        adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                message_View.setSelection(adapter.getCount() - 1);
            }
        });
        ChatSheetView.findViewById((R.id.SendChat_BTN)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et = ChatSheetView.findViewById(R.id.textbox);
                sendMessage(UserName + ": ", et.getText().toString(), 0);
                et.setText("");
            }
        });
        ChatViewDialog.setContentView(ChatSheetView);
        ChatViewDialog.show();
        Log.d("On Click", "Creating Dialog");
    }
    /**
     * Opens the game chat room on the screen
     */
    public void openChat(){
        ChatViewDialog.show();
        Log.d("On Click", "Opening Chat");
    }

    /**
     * Creates the web socket connection and sets the web socket listener
     */
    public void openWebSocket(){
        addPlayer.setVisibility(View.VISIBLE);
        play.setVisibility(View.VISIBLE);
        createGame.setVisibility(View.INVISIBLE);
        gs.createGame();
        game.setID(gs.getGameID());
        showBackgrounds();

        Draft[] drafts = {new Draft_6455()};

        /**
         * If running this on an android device, make sure it is on the same network as your
         * computer, and change the ip address to that of your computer.
         * If running on the emulator, you can use localhost.
         */
        String w = "wss://echo.websocket.org";

        try {
            Log.d("Socket", "Trying socket");
            client = new WebSocketClient(new URI(w),(Draft) drafts[0]) {
                @Override
                public void onMessage(String message) {
                    Log.d("message", message);
                    Scanner scan = new Scanner(message);
                    String name = scan.next();
                    if(!name.contains(UserName))
                        sendMessage(name, message, 1);
                }

                @Override
                public void onOpen(ServerHandshake handshake) {
                    Log.d("Connection", "Successfully connected to websocket");
                    messages.add(new Message(UserName, " has entered the chat", 2));
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.d("CLOSE", "onClose() returned: " + reason);
                    sendMessage(UserName, " has left the chat", 2);
                }

                @Override
                public void onError(Exception e)
                {
                    Log.d("Exception:", e.toString());
                }
            };
        }
        catch (URISyntaxException e) {
            Log.d("Exception:", e.getMessage().toString());
            e.printStackTrace();
        }
        client.connect();
    }

    /**
     * Function used with game chat uses websocket to send new chat to all devices
     * @param message
     */
    public void sendMessage(String name, String message, int TYPE){
        try {
            if(TYPE == 2)
                client.send(name + " " + message);
            else
                client.send(name + ": " + message);
            messages.add(new Message(name, message, TYPE));
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            Log.d("ExceptionSendMessage:", e.getMessage().toString());
        }
    }
    /**
     * Sets the frame for the screen
     * @param name the name of the user name
     * @param IconID the id of the users icon
     */
    public void setFrame(String name, int IconID){
        info = findViewById(R.id.INFO_GS_Vert);
        username = findViewById(R.id.GS_Vert_UsersName);
        userIcon = findViewById(R.id.GS_Vert_UsersImg);
        spin = findViewById(R.id.SPIN_GS_Vert);

        chat = findViewById(R.id.ChatIcon);
        chat.setOnClickListener(this);
        createGame = findViewById(R.id.createGame_BTN);
        createGame.setOnClickListener(this);
        deal = findViewById(R.id.deal_BTN);
        deal.setOnClickListener(this);
        addPlayer = findViewById(R.id.addPlayer_BTN_BTN);
        addPlayer.setOnClickListener(this);
        play = findViewById(R.id.play_BTN);
        play.setOnClickListener(this);

        UserName = name;
        ImageID = IconID;
        cg = new CardGraphics(this, (FrameLayout)findViewById(R.id.Frame_GS_Vert), getResources());
        init = new InitiateGame(cg);
        game = init.getGame(GameName);
        gs = new GameState(game);
        cg.setGameState(gs);
        piles = new ArrayList<>();
        deck = new ArrayList<>();
        messages = new ArrayList<>();


        username.setText(name);
        userIcon.setImageDrawable(getDrawable(IconID));
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.SET_InGame, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);
        info.setOnClickListener(this);
    }
    
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}